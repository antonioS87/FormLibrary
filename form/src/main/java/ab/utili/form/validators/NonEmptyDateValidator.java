package ab.utili.form.validators;

import com.tihi.formlibrary.forms.LeafFormItem;

import java.util.Date;

public class NonEmptyDateValidator implements LeafFormItem.ItemValidator<Date> {

    @Override
    public boolean isValid(Date date) {
        if(date != null){
            return true;
        }
        return false;
    }
}
