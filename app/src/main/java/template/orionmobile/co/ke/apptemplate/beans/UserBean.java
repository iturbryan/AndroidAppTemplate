package template.orionmobile.co.ke.apptemplate.beans;

import org.json.JSONException;
import org.json.JSONObject;

import template.orionmobile.co.ke.apptemplate.common.Constants;
import template.orionmobile.co.ke.apptemplate.common.Fields;
import template.orionmobile.co.ke.apptemplate.common.Helper;
import template.orionmobile.co.ke.apptemplate.common.JLogger;
import template.orionmobile.co.ke.apptemplate.common.Objects;
import template.orionmobile.co.ke.apptemplate.model.User;

/**
 * Created by bryanitur on 4/7/2016.
 */
public class UserBean implements UserEJB {
    JLogger jlogger = new JLogger();
    Helper helper = new Helper();
    @Override
    public String changePwd(User user, String password) {
        String params;
        try {
            params = new JSONObject()
                    .put(Objects.user, this.toJson(user))
                    .put(Fields.password, password).toString();
        } catch (JSONException je) {
            params = null;
        }
        return this.execute(Constants.URL_LOGIN, params).toString();
    }

    @Override
    public JSONObject toJson(User user) {
        try {
            return user == null ? null : new JSONObject()
                    .put(Fields.id, user.getId())
                    .put(Fields.name, user.getName())
                    .put(Fields.email, user.getEmail())
                    .put(Fields.phone, user.getPhone())
                    .put(Fields.username, user.getUsername())
                    .put(Fields.password, user.getPassword());
        } catch (JSONException je) {
            jlogger.e("JSONException was detected " + je.getMessage());
            return null;
        }
    }

    @Override
    public String create(User user) {
        return null;
    }

    @Override
    public String login(User user) {
        return null;
    }

    @Override
    public String logout(User user) {
        return null;
    }

    private JSONObject execute(String link, String params)
    {
        return helper.executeCurl(link, params);
    }
}
