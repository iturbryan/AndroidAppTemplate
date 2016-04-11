package template.orionmobile.co.ke.apptemplate.common;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by bryanitur on 12/21/15.
 */
public class Helper {

    private final JLogger jLogger = new JLogger();

    private final HttpClient httpClient = new HttpClient();

    public boolean isEmail(CharSequence target) {

        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();

    }



    public boolean similarStrings(String s1, String s2)

    {

        return s1.equals(s2);

    }

    public String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getString(EditText editText)

    {

        return editText.getText().toString();

    }

    public boolean isNumeric(String str)
    {
        try
        {

            Double.parseDouble(str);

            return true;

        }
        catch(NumberFormatException nfe)
        {

            return false;

        }
    }

    public boolean isNullEditText(EditText et)

    {

        return this.getString(et).trim().equals("");

    }

    public String response(boolean success, String message, JSONObject data) throws JSONException

    {

        if(data == null)

            data = new JSONObject();

        String res = this.toString(new JSONObject().put("success", success).put("message", message).put("data", data));

        jLogger.i("Response: " + res);

        return res;

    }

    public String toString(Object o)

    {

        try {

            return String.valueOf(o);

        } catch (NullPointerException npe) {

            return null;

        }

    }

    public JSONObject executeCurl(String link, String params)

    {

        String json;

        json = httpClient.URLPost(link, params, Constants.APPLICATION_X_WWW_FORM_URL_ENCODED);

        return this.toJson(json);

    }



    public void toast(Context context, String message)
    {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

    public void hideEditText(EditText editText)
    {

        editText.setVisibility(View.GONE);

    }

    public void showEditText(EditText editText)
    {

        editText.setVisibility(View.VISIBLE);

    }

    public void showTextView(TextView textView)
    {
        textView.setVisibility(View.VISIBLE);
    }

    public void hideTextView(TextView textView)
    {
        textView.setVisibility(View.GONE);
    }

    public void hideButton(Button button)
    {

        button.setVisibility(View.GONE);

    }

    public void showButton(Button button)
    {

        button.setVisibility(View.VISIBLE);

    }

    private JSONObject toJson(String json)

    {

        try {

            return new JSONObject(json);

        } catch (JSONException je) {

            return null;

        } catch (NullPointerException je) {

            return null;

        }

    }

    public String uniqueID()

    {

        return uppercase(toString(UUID.randomUUID()));

    }

    private String uppercase(String string)

    {

        return string.toUpperCase();

    }

    public long toLong(String value)

    {

        try {

            return Long.valueOf(value);

        } catch (NullPointerException npe) {

            return 0;

        } catch (NumberFormatException nfe) {

            return 0;

        }

    }

    public double toDouble(String value)

    {

        try {

            return Double.valueOf(value);

        } catch (NullPointerException npe) {

            return 0;

        } catch (NumberFormatException nfe) {

            return 0;

        }

    }

    public BigDecimal toBigDecimal(String value)

    {

        try {

            return BigDecimal.valueOf(toDouble(value));

        } catch (NullPointerException npe) {

            return BigDecimal.ZERO;

        } catch (NumberFormatException nfe) {

            return BigDecimal.ZERO;

        }

    }

}
