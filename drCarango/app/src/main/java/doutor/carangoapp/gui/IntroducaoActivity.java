package doutor.carangoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import doutor.carangoapp.R;

public class IntroducaoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_motorista, btn_oficina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducao);

        //View face_login=findViewById(R.id.iv_face_icon);
        btn_motorista = findViewById(R.id.btn_motorista);
        btn_oficina = findViewById(R.id.btn_oficina);

        btn_motorista.setOnClickListener(this);
        btn_oficina.setOnClickListener(this);
        //face_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent it;
        switch (v.getId()){
            case R.id.btn_motorista:
                it = new Intent(this,LoginActivity.class);
                it.putExtra("tipoLogin","motorista");
                startActivity(it);
                break;
            case R.id.btn_oficina:
                it = new Intent(this,LoginActivity.class);
                it.putExtra("tipoLogin","oficina");
                startActivity(it);
                break;
            /*case R.id.iv_face_icon:
                startActivity(new Intent(this,SugestoesActivity.class));
                break;  */

        }
    }
}
