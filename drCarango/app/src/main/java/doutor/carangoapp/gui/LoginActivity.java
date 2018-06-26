package doutor.carangoapp.gui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import doutor.carangoapp.R;

/**
 * Created by wilqu on 25/05/2018.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login;
    private TextView txt_esqueceu_senha, txt_nao_tem_conta;

    private String tipoLogin = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        txt_esqueceu_senha = findViewById(R.id.txt_esqueceu_senha);
        txt_nao_tem_conta = findViewById(R.id.txt_nao_tem_conta);

        Intent it = new Intent();
        tipoLogin = it.getStringExtra("tipoLogin");

        txt_esqueceu_senha.setPaintFlags(txt_esqueceu_senha.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                //verificar
                break;
        }
    }
}
