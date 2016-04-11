package template.orionmobile.co.ke.apptemplate.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhaarman.supertooltips.ToolTip;

import template.orionmobile.co.ke.apptemplate.R;
import template.orionmobile.co.ke.apptemplate.common.FormValidator;
import template.orionmobile.co.ke.apptemplate.common.Helper;
import template.orionmobile.co.ke.apptemplate.common.Message;

public class SignUpActivity extends AppCompatActivity {
    private EditText et_name, et_phone, et_email, et_password, et_cpassword;
    Helper helper = new Helper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.text_empty);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_cpassword = (EditText) findViewById(R.id.et_cpassword);
        Button ok = (Button) findViewById(R.id.btn_signup);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                    helper.toast(getApplicationContext(), "Done");
            }
        });
    }

    private void resetValidation()
    {

        et_name.setError(null);
        et_phone.setError(null);
        et_email.setError(null);
        et_password.setError(null);
        et_cpassword.setError(null);
    }

    private boolean validate()
    {
        resetValidation();
        FormValidator formValidator = new FormValidator();
        if(!formValidator.isString(et_name))
        {
            et_name.setError(Message.invalid_name);
            return false;
        }
        else if(!formValidator.isPhone(et_phone))
        {
            et_phone.setError(Message.invalid_phone);
            return false;
        }
        else if(!formValidator.isEmail(et_email))
        {
            et_email.setError(Message.invalid_email);
            return false;
        }
        else if(!formValidator.isString(et_password))
        {
            et_password.setError(Message.invalid_password);
            return false;
        }
        else if(!formValidator.isString(et_cpassword))
        {
            et_password.setError(Message.invalid_password);
            return false;
        }
        else if(!formValidator.areSimilar(et_password, et_cpassword))
        {
            et_cpassword.setError(Message.passwords_no_match);
            return false;
        }
        else
            return true;
    }
}
