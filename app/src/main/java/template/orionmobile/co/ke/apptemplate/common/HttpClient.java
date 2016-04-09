package template.orionmobile.co.ke.apptemplate.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by bryanitur on 12/21/15.
 */
class HttpClient {

    private final JLogger jLogger = new JLogger();

    private final int TIMEOUT = 120000;

    private boolean isHttps(String link)
    {

        return link.trim().substring(0, Math.min(link.length(), 5)).equalsIgnoreCase(Constants.HTTPS);

    }

    public String URLPost(String link, String params, String encoding)

    {

        if(isHttps(link))

            return HttpsPost(link, params, encoding);

        else


            return HttpPost(link, params, encoding);

    }

    public String URLGet(String link)

    {

        if(isHttps(link))

            return HttpsGet(link);

        else

            return HttpGet(link);

    }

    private String HttpPost(String link, String params, String encoding)

    {

        jLogger.i("Posted Params: " + params);

        StringBuilder sb = new StringBuilder();
        HttpURLConnection httpConn;
        InputStreamReader in = null;
        URL url;
        try {
            url = new URL(link);
        } catch (MalformedURLException mue) {
            jLogger.e("MalformedURLException was just detected" + link);
            return null;
        }
        try {
            httpConn = (HttpURLConnection) url.openConnection();
        } catch(IOException ioe) {
            jLogger.e("IOException was just detected opening the connection to " + link);
            return null;
        }
        if (httpConn != null)
        {
            try {
                httpConn.setRequestMethod(Constants.HTTP_POST);
                httpConn.setRequestProperty(Constants.CONTENT_TYPE, encoding);
                httpConn.setDoOutput(true);
                httpConn.setConnectTimeout(this.TIMEOUT);
                httpConn.setReadTimeout(this.TIMEOUT);
            } catch (ProtocolException pe) {
                jLogger.e("ProtocolException was just detected connecting to " + link);
                return null;
            }
            OutputStream os;
            try {
                os = httpConn.getOutputStream();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                jLogger.e("IOException was just detected getting the output stream over connection to " + link);
                return null;
            }
            try {
                os.write(params.getBytes());
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected getting the parameter bytes over connection to " + link);
                return null;
            }
            try {
                os.flush();
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected flushing the output stream over connection to " + link);
                return null;
            }
            try {
                os.close();
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected closing the output stream over connection to " + link);
                return null;
            }
        }

        else

        {

            jLogger.e("httpConnection == null was just detected connecting to " + link);
            return null;

        }

        try {
            if (httpConn.getInputStream() != null) {

                try {
                    in = new InputStreamReader(httpConn.getInputStream(),
                            Charset.defaultCharset());
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to get input stream");
                    return null;
                }
                BufferedReader bufferedReader = new BufferedReader(in);

                int cp;
                try {
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to extract data from buffered reader");
                    return null;
                }
                try {
                    bufferedReader.close();
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to close the buffered reader");
                    return null;
                }

            }
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to get inputstream from connection");
            ioe.printStackTrace();
            return null;
        }
        try {
            in.close();
        } catch (NullPointerException npe) {
            jLogger.e("NullPointerException was detected trying to close the inputstream");
            return null;
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to close the inputstream");
            return null;
        }
        jLogger.i("Http Response: " + sb.toString());
        return sb.toString();

    }

    private String HttpsPost(String link, String params, String encoding)

    {
        jLogger.i("Posting to https: " + link);

        StringBuilder sb = new StringBuilder();
        HttpsURLConnection httpsConn;
        InputStreamReader in = null;
        URL url;
        try {
            url = new URL(link);
        } catch (MalformedURLException mue) {
            jLogger.e("MalformedURLException was just detected" + link);
            return null;
        }
        try {
            httpsConn = (HttpsURLConnection) url.openConnection();
        } catch(IOException ioe) {
            jLogger.e("IOException was just detected opening the connection to " + link);
            return null;
        }
        if (httpsConn != null)
        {
            try {
                httpsConn.setRequestMethod(Constants.HTTP_POST);
                httpsConn.setRequestProperty(Constants.CONTENT_TYPE, encoding);
                httpsConn.setDoOutput(true);
                httpsConn.setConnectTimeout(this.TIMEOUT);
                httpsConn.setReadTimeout(this.TIMEOUT);
            } catch (ProtocolException pe) {
                jLogger.e("ProtocolException was just detected connecting to " + link);
                return null;
            }
            OutputStream os;
            try {
                os = httpsConn.getOutputStream();
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected getting the output stream over connection to " + link + ioe.getMessage());
                return null;
            }
            try {
                os.write(params.getBytes());
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected getting the parameter bytes over connection to " + link);
                return null;
            }
            try {
                os.flush();
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected flushing the output stream over connection to " + link);
                return null;
            }
            try {
                os.close();
            } catch (IOException ioe) {
                jLogger.e("IOException was just detected closing the output stream over connection to " + link);
                return null;
            }
        }

        else

        {

            jLogger.e("httpConnection == null was just detected connecting to " + link);
            return null;

        }

        try {
            if (httpsConn.getInputStream() != null) {

                try {
                    in = new InputStreamReader(httpsConn.getInputStream(),
                            Charset.defaultCharset());
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to get input stream");
                    return null;
                }
                BufferedReader bufferedReader = new BufferedReader(in);

                int cp;
                try {
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to extract data from buffered reader");
                    return null;
                }
                try {
                    bufferedReader.close();
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to close the buffered reader");
                    return null;
                }

            }
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to get inputstream from connection");
            return null;
        }
        try {
            in.close();
        } catch (NullPointerException npe) {
            jLogger.e("NullPointerException was detected trying to close the inputstream");
            return null;
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to close the inputstream");
            return null;
        }
        jLogger.i("Http Response: " + sb.toString());
        return sb.toString();

    }

    private String HttpsGet(String link)

    {

        StringBuilder sb = new StringBuilder();
        HttpsURLConnection httpsConn;
        InputStreamReader in = null;
        URL url;
        try {
            url = new URL(link);
        } catch (MalformedURLException mue) {
            jLogger.e("MalformedURLException was just detected" + link);
            return null;
        }
        try {
            httpsConn = (HttpsURLConnection) url.openConnection();
        } catch(IOException ioe) {
            jLogger.e("IOException was just detected opening the connection to " + link);
            return null;
        }
        if (httpsConn != null)
        {
            try {
                httpsConn.setRequestMethod(Constants.HTTP_GET);
                httpsConn.setDoOutput(true);
                httpsConn.setConnectTimeout(this.TIMEOUT);
                httpsConn.setReadTimeout(this.TIMEOUT);
            } catch (ProtocolException pe) {
                jLogger.e("ProtocolException was just detected connecting to " + link);
                return null;
            }
        }

        else

        {

            jLogger.e("httpConnection == null was just detected connecting to " + link);
            return null;

        }

        try {
            if (httpsConn.getInputStream() != null) {

                try {
                    in = new InputStreamReader(httpsConn.getInputStream(),
                            Charset.defaultCharset());
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to get input stream");
                    return null;
                }
                BufferedReader bufferedReader = new BufferedReader(in);

                int cp;
                try {
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to extract data from buffered reader");
                    return null;
                }
                try {
                    bufferedReader.close();
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to close the buffered reader");
                    return null;
                }

            }
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to get inputstream from connection");
            return null;
        }
        try {
            in.close();
        } catch (NullPointerException npe) {
            jLogger.e("NullPointerException was detected trying to close the inputstream");
            return null;
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to close the inputstream");
            return null;
        }
        return sb.toString();
    }

    private String HttpGet(String link)

    {

        StringBuilder sb = new StringBuilder();
        HttpURLConnection httpConn;
        InputStreamReader in = null;
        URL url;
        try {
            url = new URL(link);
        } catch (MalformedURLException mue) {
            jLogger.e("MalformedURLException was just detected" + link);
            return null;
        }
        try {
            httpConn = (HttpURLConnection) url.openConnection();
        } catch(IOException ioe) {
            jLogger.e("IOException was just detected opening the connection to " + link);
            return null;
        }
        if (httpConn != null)
        {
            try {
                httpConn.setRequestMethod(Constants.HTTP_GET);
                httpConn.setDoOutput(true);
                httpConn.setConnectTimeout(this.TIMEOUT);
                httpConn.setReadTimeout(this.TIMEOUT);
            } catch (ProtocolException pe) {
                jLogger.e("ProtocolException was just detected connecting to " + link);
                return null;
            }
        }

        else

        {

            jLogger.e("httpConnection == null was just detected connecting to " + link);
            return null;

        }

        try {
            if (httpConn.getInputStream() != null) {

                try {
                    in = new InputStreamReader(httpConn.getInputStream(),
                            Charset.defaultCharset());
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to get input stream");
                    return null;
                }
                BufferedReader bufferedReader = new BufferedReader(in);

                int cp;
                try {
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to extract data from buffered reader");
                    return null;
                }
                try {
                    bufferedReader.close();
                } catch (IOException ioe) {
                    jLogger.e("IOException was detected trying to close the buffered reader");
                    return null;
                }

            }
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to get inputstream from connection");
            return null;
        }
        try {
            in.close();
        } catch (NullPointerException npe) {
            jLogger.e("NullPointerException was detected trying to close the inputstream");
            return null;
        } catch (IOException ioe) {
            jLogger.e("IOException was detected trying to close the inputstream");
            return null;
        }
        return sb.toString();
    }

}
