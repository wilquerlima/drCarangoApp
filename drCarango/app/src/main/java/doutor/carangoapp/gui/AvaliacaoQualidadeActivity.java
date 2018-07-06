package doutor.carangoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import doutor.carangoapp.R;

public class AvaliacaoQualidadeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnProximo;
    private ToggleButton mStar1;
    private ToggleButton mStar2;
    private ToggleButton mStar3;
    private ToggleButton mStar4;
    private ToggleButton mStar5;
    private ToggleButton[] mEstrelas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_qualidade);

        setVariables();

        setOnClickListenerOnVariables();

        putStarVariablesOnArray();
    }

    private void putStarVariablesOnArray() {
        mEstrelas[0]=mStar1;
        mEstrelas[1]=mStar2;
        mEstrelas[2]=mStar3;
        mEstrelas[3]=mStar4;
        mEstrelas[4]=mStar5;
    }

    private void setOnClickListenerOnVariables() {
        mStar1.setOnClickListener(this);
        mStar2.setOnClickListener(this);
        mStar3.setOnClickListener(this);
        mStar4.setOnClickListener(this);
        mStar5.setOnClickListener(this);
        mBtnProximo.setOnClickListener(this);
    }

    private void setVariables() {
        mEstrelas=new ToggleButton[5];
        mStar1=findViewById(R.id.iv_star_1_qualidade);
        mStar2=findViewById(R.id.iv_star_2_qualidade);
        mStar3=findViewById(R.id.iv_star_3_qualidade);
        mStar4=findViewById(R.id.iv_star_4_qualidade);
        mStar5=findViewById(R.id.iv_star_5_qualidade);
        mBtnProximo=findViewById(R.id.btn_proximo_qualidade);
    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()){

            case(R.id.iv_star_1_qualidade):
                mStar1.setChecked(true);
                mStar2.setChecked(false);
                mStar3.setChecked(false);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_2_qualidade):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(false);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_3_qualidade):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_4_qualidade):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(true);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_5_qualidade):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(true);
                mStar5.setChecked(true);
                break;
            case(R.id.btn_proximo_qualidade):

                int avaliacao=getAvaliacao();
                //envia ou guarda informacao de avaliacao
                Intent intent=new Intent(this,AvalicaoAgilidadeActivity.class);
                startActivity(intent);
        }


    }

    private int getAvaliacao() {
        for(int i=0;i<mEstrelas.length;i++){
            if(!mEstrelas[i].isChecked()){
                return i;
            }
        }
        return 5;
    }
}
