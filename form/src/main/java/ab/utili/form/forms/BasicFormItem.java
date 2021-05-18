package ab.utili.form.forms;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.security.InvalidParameterException;

public class BasicFormItem<VALUE> implements LeafFormItem<VALUE>{
    private VALUE value;

    private Boolean required;

    private final MutableLiveData<ItemStatus> itemStatus = new MutableLiveData<>();

    private ItemValidator<VALUE> itemValidator;

    public BasicFormItem(boolean required, @Nullable ItemValidator<VALUE> itemValidator){
        this.required = required;
        if(required){
            if(itemValidator == null){
                throw new InvalidParameterException("If form item is required, item validator cannot be NULL!");
            } else {
                this.itemValidator = itemValidator;
            }
        }

        if(isValueValid()){
            itemStatus.setValue(ItemStatus.Valid);
        } else {
            itemStatus.setValue(ItemStatus.Invalid);
        }
    }

    public void setValue(VALUE value){
        this.value = value;
        if(isValueValid()){
            itemStatus.setValue(ItemStatus.Valid);
        } else {
            itemStatus.setValue(ItemStatus.Invalid);
        }
    }

    public LeafFormItem<VALUE> setFormItemValidator(ItemValidator<VALUE> itemValidator){
        this.itemValidator = itemValidator;
        return this;
    }

    public boolean isValueValid(){
        if(required){
            return itemValidator.isValid(value);
        } else {
            return true;
        }
    }

    public VALUE getValue(){
        return value;
    }

    public Boolean isRequired(){
        return required;
    }

    public LiveData<ItemStatus> getItemStatus(){
        return this.itemStatus;
    }

    public void setRequired(boolean isRequired, @Nullable ItemValidator<VALUE> itemValidator) {
        this.required = isRequired;
        if(required){
            if(itemValidator == null){
                throw new InvalidParameterException("If form item is required, item validator cannot be NULL!");
            } else {
                this.itemValidator = itemValidator;
            }
        }

        if(isValueValid()){
            itemStatus.setValue(ItemStatus.Valid);
        } else {
            itemStatus.setValue(ItemStatus.Invalid);
        }
    }
}
