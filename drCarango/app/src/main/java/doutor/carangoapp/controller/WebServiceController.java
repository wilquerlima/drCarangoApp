package doutor.carangoapp.controller;

import android.app.Activity;
import android.util.Log;

import java.io.IOException;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.okHttpController.OkHttpController;

/**
 * Created by wilqu on 25/05/2018.
 */

public class WebServiceController {

    public static String recuperarListaOficinas(String categoria,String filtro) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/estabelecimentos/filter/categoria="+categoria+"&ranking="+filtro;
        Log.d("WebServiceController",url);

        return OkHttpController.getHttp(url,null);
    }
    public static String recuperarInformacoesOficina(int id) throws IOException{

        String url="http://doutorcarango.kinghost.net:21015/estabelecimentos/procurar/id="+id+"&nome=*&email=*";

        return OkHttpController.getHttp(url, null);
    }

    public static String recuperarListaOficinasCategoria(String categoria, String ranking) throws IOException {
        String url = "http://doutorcarango.kinghost.net:21015/estabelecimentos/filter/categoria=" + categoria + "&ranking=" + ranking;

        return OkHttpController.getHttp(url, null);
    }
}
