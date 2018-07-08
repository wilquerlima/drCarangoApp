package doutor.carangoapp.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import doutor.carangoapp.R;

/**
 * Created by wilqu on 07/07/2018.
 */

public class RecuperarSenhaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_email;
    private Button btn_enviar_email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperar_senha_activity);

        edit_email = findViewById(R.id.edit_email);
        btn_enviar_email = findViewById(R.id.btn_enviar_email);

        btn_enviar_email.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_enviar_email:
                edit_email.getText().toString();
                break;
        }
    }
}
