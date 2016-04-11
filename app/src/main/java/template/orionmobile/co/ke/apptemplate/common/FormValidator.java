package template.orionmobile.co.ke.apptemplate.common;

import android.widget.EditText;


/**
 * Created by bryanitur on 4/11/2016.
 */
public class FormValidator {
    private String getText(EditText editText)
    {
        return editText.getText().toString();
    }
    public boolean isPhone(EditText editText)
    {
        /* You might want to change the regex here to suit your version of validation */
        return this.getText(editText).matches("\\+\\d(-\\d{3}){2}-\\d{4}");
    }
    public boolean isString(EditText editText)
    {
        return this.getText(editText).trim().length() > 0;
    }
    public boolean isEmail(EditText editText)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this.getText(editText)).matches();
    }
    public boolean areSimilar(EditText editText, EditText editText1)
    {
        return this.getText(editText).equals(this.getText(editText1));
    }
}
