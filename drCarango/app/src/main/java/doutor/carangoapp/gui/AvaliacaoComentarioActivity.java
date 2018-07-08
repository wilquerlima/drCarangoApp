package doutor.carangoapp.gui;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import doutor.carangoapp.R;

public class AvaliacaoComentarioActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtn_concluir;
    private int mAvaliacao_qualidade;
    private int mAvaliacao_custo;
    private int mAvaliacao_agilidade;
    private EditText mComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_comentario);
        Intent IntentIniciouAcitvity=getIntent();
        mAvaliacao_agilidade=IntentIniciouAcitvity.getIntExtra("avaliacao_agilidade",0);
        mAvaliacao_custo=IntentIniciouAcitvity.getIntExtra("avaliacao_custo",0);
        mAvaliacao_qualidade=IntentIniciouAcitvity.getIntExtra("avaliacao_qualidade",0);
        mComentario=findViewById(R.id.input_comentario_avaliacao);
        mBtn_concluir=findViewById(R.id.btn_proximo_comentario);

        mBtn_concluir.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.btn_proximo_comentario):
                Log.d("Activity_Comentario","numero ate então : "+"agilidade "+mAvaliacao_agilidade+ "/ "+"qualidade "+mAvaliacao_qualidade+"/"+"custo "+mAvaliacao_custo);
                Log.d("Activity_Comentario","Comentario : "+mComentario.getText());
        }
    }
}
