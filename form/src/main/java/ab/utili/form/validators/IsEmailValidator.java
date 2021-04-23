package ab.utili.form.validators;

import android.text.TextUtils;

import ab.utili.form.forms.LeafFormItem;

public class IsEmailValidator implements LeafFormItem.ItemValidator<String> {
    @Override
    public boolean isValid(String s) {
        if(!TextUtils.isEmpty(s)){
            return android.util.Patterns.EMAIL_ADDRESS.matcher(s).matches();
        }
        return false;
    }
}
