package doutor.carangoapp.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

/**
 * Created by wilqu on 02/07/2018.
 */

public abstract class AsyncGenerico<Object, Integer, Long> extends AsyncTask<Object, Integer, Long> {

    private Activity myContext;
    public ProgressDialog progressDialog = null;

    public void alertError( String texto) {
        final String msg = texto;
        myContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(myContext).setCancelable(false);
                if (msg.equalsIgnoreCase("")) {
                    builder.setMessage("Erro não identificado, tente novamente.");
                } else {
                    builder.setMessage(msg);
                }
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                        myContext.finish();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(myContext);
        progressDialog.setTitle("Aguarde");
        progressDialog.setMessage("Carregando informações...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public AsyncGenerico(Activity activity) {
        this.myContext = activity;
    }
}
