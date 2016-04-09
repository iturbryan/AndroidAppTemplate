package template.orionmobile.co.ke.apptemplate.common;

import android.util.Log;

/**
 * Created by bryanitur on 12/21/15.
 */
public class JLogger {

    public void d(String message)
    {

        Log.d(Constants.TAG_DEBUG, message);

    }

    public void e(String message)
    {

        Log.e(Constants.TAG_ERROR, message);

    }

    public void i(String message)
    {

        Log.i(Constants.TAG_INFO, message);

    }

    public void w(String message)
    {

        Log.w(Constants.TAG_WARN, message);

    }

}
