package doutor.carangoapp.controller;

import android.app.Activity;

import java.io.IOException;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.okHttpController.OkHttpController;

/**
 * Created by wilqu on 25/05/2018.
 */

public class WebServiceController{

    public static String recuperarListaOficinas() throws IOException {
        String url = "http://doutorcarango.kinghost.net:21193/estabelecimentos/procurar/id=*&nome=*&cnpj=*&login=*";

        return OkHttpController.getHttp(url,null);
    }
}
