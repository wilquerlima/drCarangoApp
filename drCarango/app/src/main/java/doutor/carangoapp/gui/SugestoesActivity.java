package doutor.carangoapp.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
        inflater.inflate(R.menu.app_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.item_menu_notifications){

            return true;
        }
        if(id==R.id.foto_perfil_menu){
            Intent intent=new Intent(this,PrefilUsuarioActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    @Override
    public void onClick(View view) {

        TextView mSugestao = findViewById(R.id.tv_sugestoes);
        String TAG=this.getClass().getName();
        switch (view.getId()){

            case R.id.v_category_oil:
                Intent intent=new Intent(this,ListaOficinasActivity.class);
                intent.putExtra("categoria","Troca de oleo");
                startActivity(intent);
                break;

            case R.id.v_category_reparo:

                Intent intent2=new Intent(this,ListaOficinasActivity.class);
                intent2.putExtra("categoria","Reparo");
                startActivity(intent2);
                break;

            case R.id.v_category_revisao:
                Intent intent3=new Intent(this,ListaOficinasActivity.class);
                intent3.putExtra("categoria","Revisao");
                startActivity(intent3);
                break;

            case R.id.v_category_bateria:
                Intent intent4=new Intent(this,ListaOficinasActivity.class);
                intent4.putExtra("categoria","Bateria");
                startActivity(intent4);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
