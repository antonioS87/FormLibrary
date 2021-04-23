package ab.utili.form.validators;

import ab.utili.form.forms.LeafFormItem;

public class IsEmailValidator implements LeafFormItem.ItemValidator<String> {
    @Override
    public boolean isValid(String s) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches();
    }
}
