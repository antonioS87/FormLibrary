package ab.utili.form.inputControls;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.LiveData;

import com.tihi.formlibrary.R;
import com.tihi.formlibrary.forms.BasicFormItem;
import com.tihi.formlibrary.forms.LeafFormItem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FormEditText extends AppCompatEditText implements LeafFormItem<String> {
    private BasicFormItem<String> formEditText;

    public FormEditText(@NonNull Context context) {
        super(context);
    }


    public FormEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public FormEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs){

        boolean isRequired = false;
        int validatorClassNameRef;
        ItemValidator<String> itemValidator = null;
        String validatorClassName = "";
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FormEditText,0,0);

        try {
            isRequired = array.getBoolean(R.styleable.FormEditText_isRequired, false);
            validatorClassNameRef = array.getResourceId(R.styleable.FormEditText_validator, -1);
            if(validatorClassNameRef != -1){
                validatorClassName = context.getResources().getString(validatorClassNameRef);
                Class<ItemValidator<String>> validatorClass = (Class<ItemValidator<String>>) Class.forName(validatorClassName);
                Constructor<?> constructor = validatorClass.getConstructor();
                itemValidator = (ItemValidator<String>) constructor.newInstance();
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


        formEditText = new BasicFormItem<>(isRequired, itemValidator);

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setValue(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void setRequired(boolean isRequired, @Nullable ItemValidator<String> itemValidator){
        formEditText.setRequired(isRequired, itemValidator);
    }

    @Override
    public void setValue(String s) {
        formEditText.setValue(s);
    }

    @Override
    public String getValue() {
        return formEditText.getValue();
    }

    @Override
    public boolean isValueValid() {
        return formEditText.isValueValid();
    }

    @Override
    public LeafFormItem<String> setFormItemValidator(ItemValidator<String> itemValidator) {
        formEditText.setFormItemValidator(itemValidator);
        return this;
    }



    @Override
    public Boolean isRequired() {
        return formEditText.isRequired();
    }

    @Override
    public LiveData<ItemStatus> getItemStatus() {
        return formEditText.getItemStatus();
    }


}
