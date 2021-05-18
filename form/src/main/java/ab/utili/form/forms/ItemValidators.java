package ab.utili.form.forms;

import java.util.Date;

public class ItemValidators {

    public static LeafFormItem.ItemValidator<String> getNonEmptyStringValidator(){
        return new BasicFormItem.ItemValidator<String>() {
            @Override
            public boolean isValid(String s) {
                return !s.isEmpty();
            }
        };
    }

    public static LeafFormItem.ItemValidator<String> getResidencyValidator() {
        return new BasicFormItem.ItemValidator<String>() {
            @Override
            public boolean isValid(String residency) {
                return residency.equals("US") || residency.equals("EU") || residency.equals("NEITHER");
            }
        };
    }

    public static LeafFormItem.ItemValidator<Integer> getVisaSerialNumberValidator() {
        return new BasicFormItem.ItemValidator<Integer>() {
            @Override
            public boolean isValid(Integer serialNumber) {
                return serialNumber != null;
            }
        };
    }

    public static LeafFormItem.ItemValidator<Date> getVisaDatesValidator() {
        return new BasicFormItem.ItemValidator<Date>() {
            @Override
            public boolean isValid(Date date) {
                return date != null;
            }
        };
    }


    public static <VALUE> LeafFormItem.ItemValidator<VALUE> getNonNullValidator() {
        return new BasicFormItem.ItemValidator<VALUE>() {
            @Override
            public boolean isValid(VALUE value) {
                return value != null;
            }
        };
    }

    public static LeafFormItem.ItemValidator<Boolean>  booleanValidator() {
        return new LeafFormItem.ItemValidator<Boolean>() {
            @Override
            public boolean isValid(Boolean b) {
                return b;
            }
        };
    }
}
