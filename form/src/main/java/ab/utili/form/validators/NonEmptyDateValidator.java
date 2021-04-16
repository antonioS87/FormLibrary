package ab.utili.form.validators;

import java.util.Date;
import ab.utili.form.forms.LeafFormItem;

public class NonEmptyDateValidator implements LeafFormItem.ItemValidator<Date> {

    @Override
    public boolean isValid(Date date) {
        if(date != null){
            return true;
        }
        return false;
    }
}
