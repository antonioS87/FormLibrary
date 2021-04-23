package ab.utili.form.validators;

import ab.utili.form.forms.LeafFormItem;

public class FormRadioGroupValidator implements LeafFormItem.ItemValidator<Integer> {
    @Override
    public boolean isValid(Integer integer) {
        if(integer != null){
            return true;
        }
        return false;
    }
}
