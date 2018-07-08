package doutor.carangoapp.gui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import doutor.carangoapp.R;

/**
 * Created by wilqu on 25/05/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private TextView txt_esqueceu_senha, txt_nao_tem_conta;
    private EditText edit_email, edit_senha;

    private String tipoLogin = "";

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

        txt_esqueceu_senha.setPaintFlags(txt_esqueceu_senha.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_nao_tem_conta.setPaintFlags(txt_nao_tem_conta.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btn_login.setOnClickListener(this);
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
                    startActivity(new Intent(this, SugestoesActivity.class));
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(false);
                    builder.setTitle("Ops");
                    builder.setMessage("Verifique se algum campo não está preenchido!");
                    builder.setNeutralButton("OK", null);
                    builder.show();
                }
                break;
            case R.id.txt_esqueceu_senha:

                break;
            case R.id.txt_nao_tem_conta:
                Intent it = new Intent(this, CadastroActivity.class);
                it.putExtra("tipoLogin", tipoLogin);
                startActivity(it);
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
}
