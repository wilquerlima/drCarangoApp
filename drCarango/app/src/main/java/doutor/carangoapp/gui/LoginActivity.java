package doutor.carangoapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.base.BaseUsuario;
import doutor.carangoapp.controller.AsyncGenerico;
import doutor.carangoapp.controller.Session;
import doutor.carangoapp.controller.WebServiceController;

/**
 * Created by wilqu on 25/05/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private TextView txt_esqueceu_senha, txt_nao_tem_conta;
    private EditText edit_email, edit_senha;

    private String tipoLogin = "";
    private String respostaLogin = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        txt_esqueceu_senha = findViewById(R.id.txt_esqueceu_senha);
        txt_nao_tem_conta = findViewById(R.id.txt_nao_tem_conta);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);

        Intent it = getIntent();
        tipoLogin = it.getStringExtra("tipoLogin");

        //txt_esqueceu_senha.setPaintFlags(txt_esqueceu_senha.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //txt_nao_tem_conta.setPaintFlags(txt_nao_tem_conta.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btn_login.setOnClickListener(this);
        txt_esqueceu_senha.setOnClickListener(this);
        txt_nao_tem_conta.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //verificar campos
                if (checkCampos()) {
                    //login sucesso
                    //salvar o usuario logado no Session.logged
                    AsyncLogin async1 = new AsyncLogin(LoginActivity.this);
                    async1.execute();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(false);
                    builder.setTitle("Ops");
                    builder.setMessage("Verifique se algum campo não está preenchido!");
                    builder.setNeutralButton("OK", null);
                    builder.show();
                }
                break;
            case R.id.txt_esqueceu_senha:
                Intent it = new Intent(this, RecuperarSenhaActivity.class);
                it.putExtra("tipoLogin", tipoLogin);
                startActivity(it);
                finish();
                break;
            case R.id.txt_nao_tem_conta:
                Intent it2 = new Intent(this, CadastroActivity.class);
                it2.putExtra("tipoLogin", tipoLogin);
                startActivity(it2);
                finish();
                break;
        }
    }

    public boolean checkCampos() {
        if (edit_email.getText().toString().equals("")) {
            return false;
        }
        if (edit_senha.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    private class AsyncLogin extends AsyncGenerico<Object, Integer, Long> {
        Activity myActivity;

        public AsyncLogin(Activity activity) {
            super(activity);
            this.myActivity = activity;

        }

        @Override
        protected Long doInBackground(Object... objects) {

            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();

            try {
                respostaLogin = WebServiceController.login(email, senha,tipoLogin);
            } catch (Exception e) {
                alertError(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (!respostaLogin.equals("[]")){
                if (tipoLogin.equalsIgnoreCase("motorista")) {
                    Session.loggedUsuario = getLoggedUsuario(respostaLogin);
                    startActivity(new Intent(myActivity, SugestoesActivity.class));
                } else {
                    Session.loggedEstabelecimento = getLoggedOficina(respostaLogin);
                    startActivity(new Intent(myActivity,PerfilUsuarioOficinaActivity.class));
                }

                finish();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(myActivity).setCancelable(false);
                builder.setTitle("Ops");
                builder.setMessage("Verifique se os dados informados estão corretos.");
                builder.setNeutralButton("OK", null);
                builder.show();
            }
        }
    }

    private BaseEstabelecimento getLoggedOficina(String json) {
        try {

            JSONArray array=new JSONArray(json);
            JSONObject oficina = new JSONObject(array.get(0).toString());

            BaseEstabelecimento estabelecimento = new BaseEstabelecimento();
            estabelecimento.setId(oficina.getInt("id"));
            estabelecimento.setNome(oficina.getString("nome"));
            //estabelecimento.setCpf(oficina.getString("cnpj"));
            //estabelecimento.setEmail(oficina.getString("email"));
            estabelecimento.setRua(oficina.getString("rua"));
            estabelecimento.setNumero(oficina.getString("numero"));
            estabelecimento.setBairro(oficina.getString("bairro"));
            estabelecimento.setCidade(oficina.getString("cidade"));
            estabelecimento.setCep(oficina.getString("cep"));
            estabelecimento.setEstado(oficina.getString("estado"));
            estabelecimento.setPais(oficina.getString("pais"));
            estabelecimento.setComplemento(oficina.getString("complemento"));
            //estabelecimento.setRankingAgilidade(Double.parseDouble(oficina.getString("rankingAgilidade")));
            //estabelecimento.setRankingCustoBeneficio(Double.parseDouble(oficina.getString("rankingCustoBeneficio")));
            //estabelecimento.setRankingServico(Double.parseDouble(oficina.getString("rankingServico")));
            estabelecimento.setNumeroAvaliacoes(Double.parseDouble(oficina.getString("numeroAvaliacoes")));
            estabelecimento.setNumeroComentarios(Double.parseDouble(oficina.getString("numeroComentarios")));
            estabelecimento.setNumeroPromocoes(Double.parseDouble(oficina.getString("numeroPromocoes")));


            return estabelecimento;

        } catch (Exception e) {
            Log.d("ErrologingOficina",e.getMessage());
            return null;
        }

    }

    private BaseUsuario getLoggedUsuario(String json) {
        try {
            JSONArray array = new JSONArray(json);
            JSONObject usuarioJson = new JSONObject(array.getString(0));
            BaseUsuario usuario = new BaseUsuario();

            usuario.setId(usuarioJson.getInt("id"));
            usuario.setNome(usuarioJson.getString("nome"));
            usuario.setEmail(usuarioJson.getString("email"));
            usuario.setRua(usuarioJson.getString("rua"));
            usuario.setNumero(usuarioJson.getString("numero"));
            usuario.setBairro(usuarioJson.getString("bairro"));
            usuario.setCidade(usuarioJson.getString("cidade"));
            usuario.setCep(usuarioJson.getString("cep"));
            usuario.setEstado(usuarioJson.getString("estado"));
            usuario.setPais(usuarioJson.getString("pais"));
            usuario.setComplemento(usuarioJson.getString("complemento"));
            usuario.setTelefone1(usuarioJson.getString("telefone1"));
            usuario.setTelefone2(usuarioJson.getString("telefone2"));

            return usuario;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
}
