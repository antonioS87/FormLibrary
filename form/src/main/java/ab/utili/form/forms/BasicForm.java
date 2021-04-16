package ab.utili.form.forms;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.HashMap;

import ab.utili.form.FormUtils;

public class BasicForm implements FormItem {
    private final MutableLiveData<ItemStatus> formStatus;

    private final HashMap<Integer, LeafFormItem> formItems;

    private final boolean isRequired;

    private int numberOfRequiredItems = 0;

    public BasicForm(Boolean isRequired){
        this.isRequired = isRequired;
        formStatus = new MutableLiveData<>();
        formStatus.setValue(ItemStatus.Invalid);
        formItems = new HashMap<>();
    }

    public void loadFormItemsFromLayout(ViewGroup viewGroup){
        for(LeafFormItem formItem : FormUtils.getFormItemsFromLayout(viewGroup)){
            addFormItem(((View)formItem).getId(), formItem);
        }
    }

    protected void addFormItem(Integer key, @NonNull LeafFormItem formItem){
        formItems.put(key, formItem);
        if(formItem.isRequired()){
            numberOfRequiredItems++;
            formItem.getItemStatus().observeForever(new Observer<ItemStatus>(){
                @Override
                public void onChanged(ItemStatus itemStatus) {
                    checkIfOtherFormItemsAreValid();
                }
            });
        }
    }

    protected void removeForItem(String key){
        FormItem removedItem = formItems.remove(key);
        if(removedItem != null && removedItem.isRequired()){
            numberOfRequiredItems--;
            checkIfOtherFormItemsAreValid();
        }
    }

    public <VALUE> LeafFormItem<VALUE> getFormItem(int key){
        return formItems.get(key);
    }

    private void checkIfOtherFormItemsAreValid() {
        int valid = 0;
        for(FormItem formItem : formItems.values()){
            if(formItem.getItemStatus().getValue() == ItemStatus.Valid && formItem.isRequired()){
                valid++;
            }
        }

        if(valid == numberOfRequiredItems && formStatus.getValue() != ItemStatus.Valid){
            formStatus.setValue(ItemStatus.Valid);
        } else if (valid != numberOfRequiredItems && formStatus.getValue() != ItemStatus.Invalid){
            formStatus.setValue(ItemStatus.Invalid);
        }
    }

    public LiveData<ItemStatus> getFormStatus(){
        return formStatus;
    }

    @Override
    public Boolean isRequired() {
        return isRequired;
    }

    @Override
    public LiveData<ItemStatus> getItemStatus() {
        return formStatus;
    }


}
