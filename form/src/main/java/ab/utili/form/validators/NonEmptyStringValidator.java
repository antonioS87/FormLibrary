package ab.utili.form.validators;

import ab.utili.form.forms.LeafFormItem;

public class NonEmptyStringValidator implements LeafFormItem.ItemValidator<String> {

    @Override
    public boolean isValid(String s) {
        if(s != null && !s.isEmpty()) {
            return true;
        }
        return false;
    }
}
