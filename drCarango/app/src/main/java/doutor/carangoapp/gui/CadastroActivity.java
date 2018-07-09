package doutor.carangoapp.gui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.base.BaseUsuario;
import doutor.carangoapp.controller.AsyncGenerico;
import doutor.carangoapp.controller.Session;
import doutor.carangoapp.controller.WebServiceController;
import doutor.carangoapp.controller.okHttpController.OkHttpController;

/**
 * Created by wilqu on 25/05/2018.
 */

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private String tipoLogin = "";
    private EditText edit_nome, edit_email, edit_senha, edit_telefone;
    private Button btn_cadastrar;
    private TextView txt_ja_tem_conta;
    private String respostaCadastro;
    private String respostaLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_telefone = findViewById(R.id.edit_telefone);
        edit_senha = findViewById(R.id.edit_senha);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        txt_ja_tem_conta = findViewById(R.id.txt_ja_tem_conta);

        btn_cadastrar.setOnClickListener(this);
        txt_ja_tem_conta.setOnClickListener(this);

        Intent it = getIntent();
        tipoLogin = it.getStringExtra("tipoLogin");

        if (tipoLogin.equalsIgnoreCase("oficina")) {
            edit_nome.setHint("Nome do estabelecimento");
        }
    }


    public boolean checkCampos() {
        if (edit_nome.getText().toString().equals("")) {
            return false;
        }
        if (edit_email.getText().toString().equals("")) {
            return false;
        }

        if (edit_telefone.getText().toString().equals("")) {
            return false;
        }
        if (edit_senha.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cadastrar:
                if (checkCampos()) {
                    //cadastro sucesso, com login
                    AsyncCadastrar async1 = new AsyncCadastrar(CadastroActivity.this);
                    async1.execute();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(false);
                    builder.setTitle("Ops");
                    builder.setMessage("Verifique se algum campo não está preenchido!");
                    builder.setNeutralButton("OK", null);
                    builder.show();
                }
                break;
            case R.id.txt_ja_tem_conta:
                Intent it = new Intent(this, LoginActivity.class);
                it.putExtra("tipoLogin", tipoLogin);
                startActivity(it);
                finish();
                break;
        }
    }

    private class AsyncCadastrar extends AsyncGenerico<Object, Integer, Long> {
        Activity myActivity;

        public AsyncCadastrar(Activity activity) {
            super(activity);
            this.myActivity = activity;

        }

        @Override
        protected Long doInBackground(Object... objects) {

            String nome = edit_nome.getText().toString();
            String email = edit_email.getText().toString();
            String senha = edit_senha.getText().toString();
            String telefone = edit_telefone.getText().toString();

            try {
                respostaCadastro = WebServiceController.cadastrar(nome, email, senha, telefone);
            } catch (Exception e) {
                alertError(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            String msg = "";
            try {
                msg = getMsgFromJson();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(myActivity).setCancelable(false);
            builder.setMessage(msg);
            final String finalMsg = msg;
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (finalMsg.equalsIgnoreCase("cadastrado")) {
                        AsyncLogin async1 = new AsyncLogin(CadastroActivity.this);
                        async1.execute();
                    }
                }
            });

            builder.show();

        }
    }

    public String getMsgFromJson() throws JSONException {
        JSONObject json = null;
        try {
            json = new JSONObject(respostaCadastro);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.getString("men");
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
                respostaLogin = WebServiceController.login(email, senha);
            } catch (Exception e) {
                alertError(e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if (!respostaLogin.equals("")) {
                if (tipoLogin.equalsIgnoreCase("motorista")) {
                    Session.loggedUsuario = getLoggedUsuario(respostaLogin);
                } else {
                    Session.loggedEstabelecimento = getLoggedOficina(respostaLogin);
                }
                startActivity(new Intent(myActivity, SugestoesActivity.class));
                finish();
            } else {
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

            JSONObject oficina = new JSONObject(json);

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
