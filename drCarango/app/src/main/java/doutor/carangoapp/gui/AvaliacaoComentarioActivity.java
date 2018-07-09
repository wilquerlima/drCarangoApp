package doutor.carangoapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.controller.AsyncGenerico;
import doutor.carangoapp.controller.WebServiceController;

public class AvaliacaoComentarioActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn_concluir;
    private int mAvaliacao_qualidade;
    private int mAvaliacao_custo;
    private int mAvaliacao_agilidade;
    private int mIdOficina;
    private EditText mComentario;
    private String mComentarioEscrito;
    private BaseEstabelecimento mOficina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_comentario);
        Intent IntentIniciouAcitvity=getIntent();
        mAvaliacao_agilidade=IntentIniciouAcitvity.getIntExtra("avaliacao_agilidade",0);
        mAvaliacao_custo=IntentIniciouAcitvity.getIntExtra("avaliacao_custo",0);
        mAvaliacao_qualidade=IntentIniciouAcitvity.getIntExtra("avaliacao_qualidade",0);
        mComentario=findViewById(R.id.input_comentario_avaliacao);
        mIdOficina=getIntent().getIntExtra("idOficina",0);
        mBtn_concluir=findViewById(R.id.btn_proximo_comentario);
        mBtn_concluir.setOnClickListener(this);
        mOficina= (BaseEstabelecimento) getIntent().getSerializableExtra("oficina");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.btn_proximo_comentario):

                mComentarioEscrito=mComentario.getText().toString();
                Log.d("Activity_Comentario","numero ate então : "+"agilidade "+mAvaliacao_agilidade+ "/ "+"qualidade "+mAvaliacao_qualidade+"/"+"custo "+mAvaliacao_custo);
                Log.d("Activity_Comentario","Comentario : "+mComentario.getText());
                AsyncTaskAvaliacao asyncTaskAvaliacao=new AsyncTaskAvaliacao(this);
                asyncTaskAvaliacao.execute();
        }
    }

    private class AsyncTaskAvaliacao extends AsyncGenerico<Object,Integer,Long>{

        private Activity myActivity;
        public AsyncTaskAvaliacao(Activity activity){
            super(activity);
            this.myActivity=activity;
        }
        @Override
        protected Long doInBackground(Object... objects) {
            try {
                String respostaCusto = WebServiceController.enviarAvaliacaoCusto(mIdOficina, mAvaliacao_custo, 1);
                Log.d("Avaliacoes",respostaCusto);

                String respostaServico=WebServiceController.enviarAvaliacaoServico(mIdOficina,mAvaliacao_qualidade,1);
                Log.d("Avaliacoes",respostaServico);

                String respostaAgilidade=WebServiceController.enviarAvaliacaoAgilidade(mIdOficina,mAvaliacao_agilidade,1);
                Log.d("Avaliacoes",respostaAgilidade);

                String respostaComentario=WebServiceController.enviarComentario(mIdOficina,mComentarioEscrito,1);
                Log.d("Avaliacoes",respostaComentario);

                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(myActivity,PerfilOficinaActivity.class);
                        intent.putExtra("oficina",mOficina);
                        startActivity(intent);
                    }
                });

            }catch (Exception e){

                alertError(e.getMessage());
            }


            return null;
        }

    }
}
