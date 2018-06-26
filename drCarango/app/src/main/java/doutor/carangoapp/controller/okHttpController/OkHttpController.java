package doutor.carangoapp.controller.okHttpController;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wilqu on 25/05/2018.
 */

public class OkHttpController {

    public static Object postHttp(String url, ContentValues params){
        OkHttpClient client = new OkHttpClient();
        String container = parseToString(params);
        RequestBody body = new FormBody.Builder()
                .add("container",container)
                .build();

        Request request = new Request.Builder()
                .url(url)//url do serviço
                .post(body)
                .build();

        Response response;
        Object jsonObject = null;

        try {
            response = client.newCall(request).execute();
            JSONObject json = new JSONObject(response.body().string());
            jsonObject = json.get("");//nome do objeto que vai retornar

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }

    public static String parseToString(ContentValues c){
        StringBuilder s = new StringBuilder();
        for (String name: c.keySet()) {
            String value = c.get(name).toString();
            s.append(name+"="+value+"&");
        }
        if(s.length() > 0){
            s.deleteCharAt(s.length()-1);
        }
        return s.toString();
    }

    public static String parseToStringForGet(ContentValues c){

        StringBuilder s = new StringBuilder();

        for (String name: c.keySet()) {
            String value = c.get(name).toString();
            s.append("/"+value);
        }
        return s.toString();
    }
    public static String getHttp(String urlBase, ContentValues params) throws IOException{
        OkHttpClient client = new OkHttpClient();

        String parametros=parseToStringForGet(params);
        String url=urlBase+parametros;

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        String jsonResposta=response.body().toString();

        return jsonResposta;

    }

}
