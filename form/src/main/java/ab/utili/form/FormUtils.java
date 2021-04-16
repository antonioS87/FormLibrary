package ab.utili.form;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import ab.utili.form.forms.LeafFormItem;

public class FormUtils {

    public static ArrayList<LeafFormItem> getFormItemsFromLayout(ViewGroup rootFormContainer){

        ArrayList<ViewGroup> viewGroups = new ArrayList<>();
        ArrayList<LeafFormItem> formItems = new ArrayList<>();
        viewGroups.add(rootFormContainer);
        int i = 0;
        while (i < viewGroups.size()){
            for(int j = 0; j < viewGroups.get(i).getChildCount(); j++){
                View view = viewGroups.get(i).getChildAt(j);
                if(view instanceof ViewGroup){
                    viewGroups.add((ViewGroup) view);
                }

                if(view instanceof LeafFormItem){
                    formItems.add((LeafFormItem) view);
                }
            }
            i++;
        }

        return formItems;

    }
}
