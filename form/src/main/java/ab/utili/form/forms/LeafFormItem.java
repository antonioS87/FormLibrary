package ab.utili.form.forms;

public interface LeafFormItem<VALUE> extends FormItem {
    void setValue(VALUE value);
    VALUE getValue();
    boolean isValueValid();
    LeafFormItem<VALUE> setFormItemValidator(ItemValidator<VALUE> itemValidator);

    interface ItemValidator<VALUE>{
        boolean isValid(VALUE value);
    }
}
