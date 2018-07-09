package doutor.carangoapp.base;

import doutor.carangoapp.R;

public class BaseFotos {
    public static final int FotoNorteCenterAutomotivo= R.drawable.norte_center_automotivo;
    public static final int FotoAutoGas=R.drawable.auto_gas;
    public static final int FotoGimano=R.drawable.gimano;
    public static final int fotoDefault=R.drawable.oficina_teste;

    public static int getFoto(int idOficina){

        switch (idOficina){
            case 2:
                return FotoNorteCenterAutomotivo;
            case 3:
                return FotoGimano;
            case 4:
                return FotoAutoGas;
            default:
                return fotoDefault;
        }
    }

}
