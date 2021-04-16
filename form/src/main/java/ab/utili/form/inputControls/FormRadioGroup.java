package ab.utili.form.inputControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.tihi.formlibrary.R;
import com.tihi.formlibrary.forms.BasicFormItem;
import com.tihi.formlibrary.forms.LeafFormItem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FormRadioGroup extends RadioGroup implements LeafFormItem<Integer> {
    private BasicFormItem<Integer> formRadioGroup;

    public FormRadioGroup(@NonNull Context context) {
        super(context);
    }

    public FormRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs){

        boolean isRequired = false;
        int validatorClassNameRef;
        ItemValidator<Integer> itemValidator = null;
        String validatorClassName = "";
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FormRadioGroup,0,0);

        try {
            isRequired = array.getBoolean(R.styleable.FormEditText_isRequired, false);
            validatorClassNameRef = array.getResourceId(R.styleable.FormEditText_validator, -1);
            if(validatorClassNameRef != -1){
                validatorClassName = context.getResources().getString(validatorClassNameRef);
                Class<ItemValidator<String>> validatorClass = (Class<ItemValidator<String>>) Class.forName(validatorClassName);
                Constructor<?> constructor = validatorClass.getConstructor();
                itemValidator = (ItemValidator<Integer>) constructor.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            array.recycle();
        }


        formRadioGroup = new BasicFormItem<Integer>(isRequired, itemValidator);

        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int selectedRadioButton) {
                setValue(selectedRadioButton);
            }
        });
    }


    @Override
    public void setValue(Integer selectedRadioButton) {
        formRadioGroup.setValue(selectedRadioButton);
    }

    @Override
    public Integer getValue() {
        return formRadioGroup.getValue();
    }

    @Override
    public boolean isValueValid() {
        return formRadioGroup.isValueValid();
    }

    @Override
    public LeafFormItem<Integer> setFormItemValidator(ItemValidator<Integer> itemValidator) {
        return this;
    }

    @Override
    public Boolean isRequired() {
        return formRadioGroup.isRequired();
    }

    @Override
    public LiveData<ItemStatus> getItemStatus() {
        return formRadioGroup.getItemStatus();
    }
}
