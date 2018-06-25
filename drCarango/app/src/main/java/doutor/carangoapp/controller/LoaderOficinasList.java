package doutor.carangoapp.controller;


import android.content.ContentValues;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.okHttpController.OkHttpController;

public class LoaderOficinasList extends AsyncTaskLoader<String> {

    private String categoriaServico;

    public LoaderOficinasList(Context context, String categoria){
        super(context);
        categoriaServico=categoria;
    }


    @Override
    public String loadInBackground() {
        //faz consulta por oficinas usando okhttp
        ContentValues values=new ContentValues();
        values.put("0","estabelecimentos");
        values.put("1","categoria");
        values.put("2",categoriaServico);

        String url=getContext().getString(R.string.urlDrCarango);
        try {
            String result=OkHttpController.getHttp(url,values);

        } catch (IOException e) {
            Log.d("error",e.getMessage());
        }
        return null;
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
    }
}
