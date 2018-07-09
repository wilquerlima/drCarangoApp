package doutor.carangoapp.controller;

import android.app.Activity;
import android.content.ContentValues;

import java.io.IOException;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.okHttpController.OkHttpController;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by wilqu on 25/05/2018.
 */

public class WebServiceController {

    public static String recuperarListaOficinas() throws IOException {
        String url = "http://doutorcarango.kinghost.net:21193/estabelecimentos/procurar/id=*&nome=*&cnpj=*&login=*";

        return OkHttpController.getHttp(url, null);
    }

    public static String recuperarListaOficinasCategoria(String categoria, String ranking) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/estabelecimentos/filter/categoria=" + categoria + "&ranking=" + ranking;

        return OkHttpController.getHttp(url, null);
    }

    public static String cadastrar(String nome, String email, String senha, String telefone) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/usuarios/cadastrar";

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("senha", senha);
        contentValues.put("telefone1", telefone);

        return OkHttpController.postHttp(url, contentValues);
    }

    public static String login(String email, String senha) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/login";

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("senha", senha);

        return OkHttpController.postHttp(url, contentValues);
    }
}
