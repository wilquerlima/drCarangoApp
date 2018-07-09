package doutor.carangoapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.TextView;

import org.json.JSONArray;

import doutor.carangoapp.Helper.JsonHandlingHelper;
import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.controller.AsyncGenerico;
import doutor.carangoapp.controller.WebServiceController;

public class PerfilUsuarioOficinaActivity extends AppCompatActivity implements View.OnClickListener{

    private View mFotoPerfil;
    private TextView mNomeOficina;
    private TextView mEnderecoOficina;
    private TextView mPromocoesInformacoes;
    private TextView mEditarPromocoes;
    private TextView mTelefoneOficina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario_oficina);
        mFotoPerfil=findViewById(R.id.v_foto_perfil_oficina_usuario_oficina);
        mEditarPromocoes=findViewById(R.id.tv_editar_promocoes);
        mEnderecoOficina=findViewById(R.id.tv_endereco_oficina_perfil);
        mNomeOficina=findViewById(R.id.tv_nome_oficina_perfil_oficina);
        mPromocoesInformacoes=findViewById(R.id.tv_promocoes_informacoes_perfil_oficina);
        mTelefoneOficina=findViewById(R.id.tv_fone_oficina_perfil);

        mEditarPromocoes.setOnClickListener(this);

        AsyncPerfilOficinaUsuarioOficina asyncPerfilOficinaUsuarioOficina=new AsyncPerfilOficinaUsuarioOficina(this);
        asyncPerfilOficinaUsuarioOficina.execute();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case(R.id.tv_editar_promocoes):
                Intent intent=new Intent(this,EditarPromocoes.class);
                startActivity(intent);
        }
    }
    private class AsyncPerfilOficinaUsuarioOficina extends AsyncGenerico<Object,Integer,Long>{

        Activity myActivity;

        public AsyncPerfilOficinaUsuarioOficina(Activity activity){
            super(activity);
            myActivity=activity;
        }
        @Override
        protected Long doInBackground(Object... objects) {

            try{
                String jsonString= WebServiceController.recuperarInformacoesOficina(1);//passar depois o id da oficina de verdade
                JSONArray resposta=new JSONArray(jsonString);
                String oficinaJson=resposta.getString(0);
                final BaseEstabelecimento oficina = JsonHandlingHelper.getOficinaFromJson(oficinaJson);

                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       mEnderecoOficina.setText(getEnderecoFromBaseEstabelecimento(oficina));
                       mNomeOficina.setText(oficina.getNome());
                       mTelefoneOficina.setText(oficina.getTelefone());
                    }
                });



            }catch (Exception e){
                alertError(e.getMessage());
            }

            return null;
        }

        private String getEnderecoFromBaseEstabelecimento(BaseEstabelecimento oficina) {
            StringBuilder builder=new StringBuilder();
            builder.append(oficina.getRua());
            builder.append(", ");
            builder.append(oficina.getNumero());
            builder.append(", ");
            builder.append(oficina.getBairro());
            builder.append(", ");
            builder.append(oficina.getCidade());
            builder.append("- ");
            builder.append(oficina.getEstado());
            return builder.toString();
        }
    }
}
