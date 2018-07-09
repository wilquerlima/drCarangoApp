package doutor.carangoapp.controller;

import android.app.Application;

import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.base.BaseUsuario;

/**
 * Created by wilqu on 08/07/2018.
 */

public class Session extends Application {

    //salvar a sessão do login
    public static BaseUsuario loggedUsuario = null;
    public static BaseEstabelecimento loggedEstabelecimento = null;

}
