package doutor.carangoapp.gui;

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

import org.w3c.dom.Text;

import doutor.carangoapp.R;

/**
 * Created by wilqu on 25/05/2018.
 */

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private String tipoLogin = "";
    private EditText edit_nome, edit_email, edit_senha, edit_telefone;
    private Button btn_cadastrar;
    private TextView txt_ja_tem_conta;

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

        Intent it = new Intent();
        tipoLogin = it.getStringExtra("tipoLogin");

        if(tipoLogin.equalsIgnoreCase("oficina")){
            edit_nome.setHint("Nome do estabelecimento");
        }
    }


    public boolean checkCampos(){
        if(edit_nome.getText().toString().equals("")){
            return false;
        }
        if(edit_email.getText().toString().equals("")){
            return false;
        }

        if(edit_telefone.getText().toString().equals("")){
            return false;
        }
        if(edit_senha.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cadastrar:
                if(checkCampos()){
                    //cadastro sucesso, com login
                    startActivity(new Intent(this,SugestoesActivity.class));
                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(false);
                    builder.setTitle("Ops");
                    builder.setMessage("Verifique se algum campo não está preenchido!");
                    builder.setNeutralButton("OK", null);
                    builder.show();
                }
                break;
            case R.id.txt_ja_tem_conta:
                Intent it = new Intent(this,LoginActivity.class);
                it.putExtra("tipoLogin",tipoLogin);
                startActivity(it);
                finish();
                break;
        }
    }
}
