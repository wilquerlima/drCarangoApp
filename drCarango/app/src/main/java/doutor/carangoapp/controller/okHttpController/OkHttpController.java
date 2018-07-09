package doutor.carangoapp.controller.okHttpController;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import doutor.carangoapp.Helper.JsonHandlingHelper;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by wilqu on 25/05/2018.
 */

public class OkHttpController {
    public static final MediaType JSON
            = MediaType.parse("application/json");

    public static String postHttp(String url, ContentValues params) throws IOException {

        Object jsonObject = null;
        Response response = null;
        try {
            OkHttpClient client = new OkHttpClient();
            String container = parseToString(params);
            RequestBody body = RequestBody.create(JSON,container);

            Request request = new Request.Builder()
                    .url(url)//url do serviço
                    .post(body)
                    .build();



            response = client.newCall(request).execute();
            //jsonObject = new JSONObject(response.body().string());
            //nome do objeto que vai retornar

        } catch (Exception e) {
            Log.d("OKhttpController",e.getMessage());
        }

        return response.body().string();

    }

    public static String parseToString(ContentValues c) throws JSONException{

        JSONObject object=new JSONObject();

        for (String name : c.keySet()) {
            String value = c.get(name).toString();
            object.accumulate(name,value);
        }
        return object.toString();
    }

    public static String parseToStringForGet(ContentValues c) {

        StringBuilder s = new StringBuilder();

        for (String name : c.keySet()) {
            String value = c.get(name).toString();
            s.append("/" + value);
        }
        return s.toString();
    }

    public static String getHttp(String urlBase, ContentValues params) throws IOException {

        try {
            OkHttpClient client = new OkHttpClient();

            if (params != null) {
                String parametros = parseToStringForGet(params);
                urlBase = urlBase + parametros;
            }
            Request request = new Request.Builder().url(urlBase).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            Log.d(TAG, "getHttp: " + e.getMessage() + "///" + e.getStackTrace());
            return null;
        }
    }

}

