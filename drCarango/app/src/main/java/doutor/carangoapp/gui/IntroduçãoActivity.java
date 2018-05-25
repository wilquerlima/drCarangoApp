package doutor.carangoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import doutor.carangoapp.R;

public class IntroduçãoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_entrar, btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducao);

        btn_entrar = findViewById(R.id.btn_entrar);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);

        btn_entrar.setOnClickListener(this);
        btn_cadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_entrar:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_cadastrar:
                startActivity(new Intent(this,CadastroActivity.class));
                break;
        }
    }
}
