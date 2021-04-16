package ab.utili.form.forms;

import androidx.lifecycle.LiveData;

public interface FormItem {
    Boolean isRequired();
    LiveData<ItemStatus> getItemStatus();

    enum ItemStatus{
        Valid,
        Invalid
    }
}
