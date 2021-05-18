package ab.utili.form.inputControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import ab.utili.form.R;
import ab.utili.form.forms.BasicFormItem;
import ab.utili.form.forms.LeafFormItem;

public class FormCheckBox extends androidx.appcompat.widget.AppCompatCheckBox implements LeafFormItem<Boolean> {
    private BasicFormItem<Boolean> formCheckBox;

    public FormCheckBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs){
        boolean isRequired = false;
        int validatorClassNameRef;

        LeafFormItem.ItemValidator<Boolean> itemValidator = null;
        String validatorClassName = "";
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FormCheckBox,0,0);

        try {
            isRequired = array.getBoolean(R.styleable.FormCheckBox_isRequired, false);
            validatorClassNameRef = array.getResourceId(R.styleable.FormCheckBox_validator, -1);


            if(validatorClassNameRef != -1){
                validatorClassName = context.getResources().getString(validatorClassNameRef);
                Class<LeafFormItem.ItemValidator<Boolean>> validatorClass = (Class<LeafFormItem.ItemValidator<Boolean>>) Class.forName(validatorClassName);
                Constructor<?> constructor = validatorClass.getConstructor();
                itemValidator = (LeafFormItem.ItemValidator<Boolean>) constructor.newInstance();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            array.recycle();
        }

        formCheckBox = new BasicFormItem<>(isRequired, itemValidator);
        formCheckBox.setValue(false);

        this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                formCheckBox.setValue(b);
            }
        });
    }

    @Override
    public void setValue(Boolean isChecked) {
        formCheckBox.setValue(isChecked);
    }

    @Override
    public Boolean getValue() {
        return formCheckBox.getValue();
    }

    @Override
    public boolean isValueValid() {
        return formCheckBox.isValueValid();
    }

    @Override
    public LeafFormItem<Boolean> setFormItemValidator(ItemValidator<Boolean> itemValidator) {
        return formCheckBox;
    }

    @Override
    public Boolean isRequired() {
        return formCheckBox.isRequired();
    }

    @Override
    public LiveData<ItemStatus> getItemStatus() {
        return formCheckBox.getItemStatus();
    }
}
