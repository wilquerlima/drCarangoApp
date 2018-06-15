package doutor.carangoapp.gui;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import doutor.carangoapp.R;

public class SugestoesActivity extends AppCompatActivity implements View.OnClickListener{



    private View  vCategory_Oil, imCategory_Reparo,imCategory_Revisao,imCategory_Bateria;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestoes);

        imCategory_Bateria=findViewById(R.id.v_category_bateria);
        imCategory_Reparo=findViewById(R.id.v_category_reparo);
        imCategory_Revisao=findViewById(R.id.v_category_revisao);
        vCategory_Oil=findViewById(R.id.v_category_oil);

        vCategory_Oil.setOnClickListener(this);
        imCategory_Revisao.setOnClickListener(this);
        imCategory_Bateria.setOnClickListener(this);
        imCategory_Reparo.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.menu_sugestoes_oficinas,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.notification_sugestao_icon){
            //exibe as notificacoes
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        TextView mSugestao = findViewById(R.id.tv_sugestoes);
        String TAG=this.getClass().getName();
        switch (view.getId()){

            case R.id.v_category_oil:
                startActivity(new Intent(this,ListaOficinasActivity.class));
                break;

            case R.id.v_category_reparo:
                startActivity(new Intent(this,ListaOficinasActivity.class));
                break;

            case R.id.v_category_revisao:
                startActivity(new Intent(this,ListaOficinasActivity.class));
                break;

            case R.id.v_category_bateria:
                startActivity(new Intent(this,ListaOficinasActivity.class));
                break;

        }
    }
}
