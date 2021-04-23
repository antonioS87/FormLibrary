package ab.utili.form.validators;

import ab.utili.form.forms.LeafFormItem;

public class CheckBoxValidator implements LeafFormItem.ItemValidator<Boolean> {

    @Override
    public boolean isValid(Boolean bool) {
        if(bool != null) {
            return bool;
        }
        return false;
    }
}
