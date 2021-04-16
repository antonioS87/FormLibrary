package ab.utili.form.validators;

import com.tihi.formlibrary.forms.LeafFormItem;

public class NonEmptyStringValidator implements LeafFormItem.ItemValidator<String> {

    @Override
    public boolean isValid(String s) {
        if(s != null && !s.isEmpty()) {
            return true;
        }
        return false;
    }
}
