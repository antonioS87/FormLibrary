package ab.utili.form.inputControls;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import ab.utili.form.R;
import ab.utili.form.forms.BasicFormItem;
import ab.utili.form.forms.LeafFormItem;
import ab.utili.form.fragments.DatePickerDialogFragment;

public class FormDatePickerTextView extends androidx.appcompat.widget.AppCompatTextView implements DatePickerDialog.OnDateSetListener, LeafFormItem<Date> {
    private BasicFormItem<Date> formDatePickerTv;

    private DatePickerDialogFragment datePickerFragment;

    private String dateFormat = null;

    private FormDatePickerTextView dateBefore, dateAfter;

    private int dateBeforeId, dateAfterId;

    public FormDatePickerTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void init(Context context, @Nullable AttributeSet attrs){
        boolean isRequired = false;
        int validatorClassNameRef;

        LeafFormItem.ItemValidator<Date> itemValidator = null;
        String validatorClassName = "";
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FormDatePickerTextView,0,0);

        try {
            isRequired = array.getBoolean(R.styleable.FormDatePickerTextView_isRequired, false);
            validatorClassNameRef = array.getResourceId(R.styleable.FormDatePickerTextView_validator, -1);
            dateFormat = array.getString(R.styleable.FormDatePickerTextView_datePattern);

            dateBeforeId = array.getResourceId(R.styleable.FormDatePickerTextView_dateBefore, -1);
            dateAfterId = array.getResourceId(R.styleable.FormDatePickerTextView_dateAfter, -1);

            if(validatorClassNameRef != -1){
                validatorClassName = context.getResources().getString(validatorClassNameRef);
                Class<LeafFormItem.ItemValidator<Date>> validatorClass = (Class<LeafFormItem.ItemValidator<Date>>) Class.forName(validatorClassName);
                Constructor<?> constructor = validatorClass.getConstructor();
                itemValidator = (LeafFormItem.ItemValidator<Date>) constructor.newInstance();
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

        formDatePickerTv = new BasicFormItem<>(isRequired, itemValidator);
        datePickerFragment = new DatePickerDialogFragment(FormDatePickerTextView.this);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dateAfterId != -1){
                    if(dateAfter == null && getContext() instanceof AppCompatActivity){
                        dateAfter = ((AppCompatActivity)getContext()).findViewById(dateAfterId);
                    }

                    if(dateAfter.getValue() != null){
                        datePickerFragment.setMinDate(dateAfter.getValue());
                    }
                }

                if(dateBeforeId != -1){
                    if(dateBefore == null && getContext() instanceof AppCompatActivity){
                        dateBefore = ((AppCompatActivity)getContext()).findViewById(dateBeforeId);
                    }

                    if(dateBefore.getValue() != null){
                        datePickerFragment.setMaxDate(dateBefore.getValue());
                    }
                }

                if(formDatePickerTv.getValue() != null){
                    datePickerFragment.setCurrentDate(formDatePickerTv.getValue());
                }

                if(view.getContext() instanceof AppCompatActivity){
                    FragmentManager fragmentManager = ((AppCompatActivity)(AppCompatActivity) view.getContext()).getSupportFragmentManager();
                    datePickerFragment.show(fragmentManager, "datePickerFragment");
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        if(dateFormat != null){
            this.setText(formatDate(calendar.getTime()));
            formDatePickerTv.setValue(calendar.getTime());
        }
    }

    public String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format( date );
    }

    @Override
    public void setValue(Date date) {
        formDatePickerTv.setValue(date);
    }

    @Override
    public Date getValue() {
        return formDatePickerTv.getValue();
    }

    @Override
    public boolean isValueValid() {
        return formDatePickerTv.isValueValid();
    }

    @Override
    public LeafFormItem<Date> setFormItemValidator(ItemValidator<Date> itemValidator) {
        formDatePickerTv.setFormItemValidator(itemValidator);
        return null;
    }

    @Override
    public Boolean isRequired() {
        return formDatePickerTv.isRequired();
    }

    @Override
    public LiveData<ItemStatus> getItemStatus() {
        return formDatePickerTv.getItemStatus();
    }
}
