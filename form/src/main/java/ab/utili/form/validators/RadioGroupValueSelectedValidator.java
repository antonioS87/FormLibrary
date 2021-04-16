package ab.utili.form.validators;

import ab.utili.form.forms.LeafFormItem;

public class RadioGroupValueSelectedValidator implements LeafFormItem.ItemValidator<Integer> {

    @Override
    public boolean isValid(Integer selectedRadioButton) {
        if(selectedRadioButton != null){
            return true;
        }
        return false;
    }

}
