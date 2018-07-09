package doutor.carangoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;

public class AvaliacaoCustoActivity extends AppCompatActivity implements View.OnClickListener{

    private ToggleButton mStar1;
    private ToggleButton mStar2;
    private ToggleButton mStar3;
    private ToggleButton mStar4;
    private ToggleButton mStar5;
    private Button mBtnProximo;
    private ToggleButton[] mEstrelas;
    private int mAvaliacaoCusto;
    private int mIdOficina;
    private BaseEstabelecimento mOficina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao_custo);
        mIdOficina=getIntent().getIntExtra("idOficina",0);
        mOficina= (BaseEstabelecimento) getIntent().getSerializableExtra("oficina");
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
        mBtnProximo=findViewById(R.id.btn_proximo_custo);
        mEstrelas=new ToggleButton[5];
        mStar1=findViewById(R.id.iv_star_1_custo);
        mStar2=findViewById(R.id.iv_star_2_custo);
        mStar3=findViewById(R.id.iv_star_3_custo);
        mStar4=findViewById(R.id.iv_star_4_custo);
        mStar5=findViewById(R.id.iv_star_5_custo);
    }

    @Override
    public void onClick(View buttonView) {

        switch (buttonView.getId()){

            case(R.id.iv_star_1_custo):
                mStar1.setChecked(true);
                mStar2.setChecked(false);
                mStar3.setChecked(false);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_2_custo):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(false);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_3_custo):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_4_custo):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(true);
                mStar5.setChecked(false);
                break;

            case(R.id.iv_star_5_custo):
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(true);
                mStar5.setChecked(true);
                break;
            case(R.id.btn_proximo_custo):

                int avaliacao=getAvaliacao();
                mAvaliacaoCusto=avaliacao;
                Intent intent=new Intent(this,AvaliacaoQualidadeActivity.class);
                intent.putExtra("avaliacao_custo",mAvaliacaoCusto);
                intent.putExtra("idOficina",mIdOficina);
                intent.putExtra("oficina",mOficina);
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
