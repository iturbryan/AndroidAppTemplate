package template.orionmobile.co.ke.apptemplate.beans;

import org.json.JSONObject;

import template.orionmobile.co.ke.apptemplate.model.User;

/**
 * Created by bryanitur on 4/7/2016.
 */
public interface UserEJB {
    JSONObject toJson(User user);
    String create(User user);
    String login(User user);
    String logout(User user);
    String changePwd(User user, String password);
}
