package ab.utili.form.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;
import java.util.Date;

public  class DatePickerDialogFragment extends DialogFragment {
    private DatePickerDialog datePickerDialog;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private Date minDate, maxDate, currentDate;

    public DatePickerDialogFragment(DatePickerDialog.OnDateSetListener onDateSetListener){
        this.onDateSetListener = onDateSetListener;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();

        if(currentDate != null){
            calendar.setTime(currentDate);
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);

        if(minDate != null){
            datePickerDialog.getDatePicker().setMinDate(minDate.getTime());
        }

        if(maxDate != null){
            datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());
        }
        return datePickerDialog;
    }

    public void setMinDate(Date minDate){
        this.minDate = minDate;
    }

    public void setMaxDate(Date maxDate){
       this.maxDate = maxDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
