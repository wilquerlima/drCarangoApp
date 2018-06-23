package doutor.carangoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import doutor.carangoapp.R;

public class IntroducaoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_entrar, btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducao);
        View face_login=findViewById(R.id.iv_face_icon);
        face_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_face_icon:
                startActivity(new Intent(this,SugestoesActivity.class));
                break;

        }
    }
}
