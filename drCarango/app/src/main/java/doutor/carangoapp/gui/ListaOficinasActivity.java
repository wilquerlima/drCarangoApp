package doutor.carangoapp.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DividerItemDecoration;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Comparator;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.controller.AdapterOficinas;

public class ListaOficinasActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,AdapterOficinas.OnItemClickListener{

    private RecyclerView mRecyclerView;
    private AdapterOficinas mAdapterOficinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_lista_oficina);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());

        mRecyclerView=findViewById(R.id.recycler_view_lista_oficinas);

        mRecyclerView.setLayoutManager(manager);

        mAdapterOficinas=new AdapterOficinas(this);
        mAdapterOficinas.setmOficinas(SetupAdapterTest());
        mRecyclerView.setAdapter(mAdapterOficinas);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        super.onCreate(savedInstanceState);

    }

    private ArrayList<BaseEstabelecimento> SetupAdapterTest() {

        ArrayList<BaseEstabelecimento> listaOficinas=new ArrayList<BaseEstabelecimento>();
        BaseEstabelecimento oficina1=new BaseEstabelecimento();
        oficina1.setNome("Oficina S. João");
        oficina1.setRankingServico(4.98);
        BaseEstabelecimento oficina2=new BaseEstabelecimento();
        oficina2.setNome("Central Auto Center");
        oficina2.setRankingServico(4.84);
        BaseEstabelecimento oficina3=new BaseEstabelecimento();
        oficina3.setNome("Perfet Tecnologia");
        oficina3.setRankingServico(4.75);
        BaseEstabelecimento oficina4=new BaseEstabelecimento();
        oficina4.setNome("Oficina Calango");
        oficina4.setRankingServico(4.24);
        BaseEstabelecimento oficina5=new BaseEstabelecimento();
        oficina5.setNome("Oficina do Amigo");
        oficina5.setRankingServico(3.86);
        BaseEstabelecimento oficina6=new BaseEstabelecimento();
        oficina6.setNome("Oficina do Manuca");
        oficina6.setRankingServico(3.80);
        BaseEstabelecimento oficina7=new BaseEstabelecimento();
        oficina7.setNome("Lojão do Petróleo");
        oficina7.setRankingServico(3.23);
        BaseEstabelecimento oficina8=new BaseEstabelecimento();
        oficina8.setNome("Biauto");
        oficina8.setRankingServico(2.45);
        BaseEstabelecimento oficina9=new BaseEstabelecimento();
        oficina9.setNome("Oficina Auto Center Casa Forte");
        oficina9.setRankingServico(2.33);
        listaOficinas.add(oficina1);
        listaOficinas.add(oficina2);
        listaOficinas.add(oficina3);
        listaOficinas.add(oficina4);
        listaOficinas.add(oficina5);
        listaOficinas.add(oficina6);
        listaOficinas.add(oficina7);
        listaOficinas.add(oficina8);
        listaOficinas.add(oficina9);


        return listaOficinas;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ToggleButton tooglebutton = (ToggleButton) buttonView;

        switch (buttonView.getId()){
            case R.id.btn_preco:{

            }

            case R.id.btn_agilidade:{

            }
            case R.id.btn_distancia:{

            }


        }
    }

    @Override
    public void onItemClick(int position) {
        Intent perfilOficinaIntent=new Intent(this,PerfilOficinaActivity.class);
        perfilOficinaIntent.putExtra("idOficina",Integer.toString(position));
        startActivity(perfilOficinaIntent);
    }
}
