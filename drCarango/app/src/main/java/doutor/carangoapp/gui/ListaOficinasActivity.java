package doutor.carangoapp.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DividerItemDecoration;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;

public class ListaOficinasActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterOficinas mAdapterOficinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_lista_oficina);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());

        mRecyclerView=findViewById(R.id.recycler_view_lista_oficinas);

        mRecyclerView.setLayoutManager(manager);

        mAdapterOficinas=SetupAdapterDummy();

        mRecyclerView.setAdapter(mAdapterOficinas);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        super.onCreate(savedInstanceState);

    }

    private AdapterOficinas SetupAdapterDummy() {

        ArrayList<BaseEstabelecimento> listaOficinas=new ArrayList<BaseEstabelecimento>();
        BaseEstabelecimento oficina1=new BaseEstabelecimento();
        oficina1.setNome("Oficina S. João");
        oficina1.setRankingServico(4.84);
        BaseEstabelecimento oficina2=new BaseEstabelecimento();
        oficina2.setNome("Central Auto Center");
        oficina2.setRankingServico(2.84);
        BaseEstabelecimento oficina3=new BaseEstabelecimento();
        oficina3.setNome("Perfet Tecnologia");
        oficina3.setRankingServico(3.84);
        BaseEstabelecimento oficina4=new BaseEstabelecimento();
        oficina4.setNome("Oficina Calango");
        oficina4.setRankingServico(4.74);
        listaOficinas.add(oficina1);
        listaOficinas.add(oficina2);
        listaOficinas.add(oficina3);
        listaOficinas.add(oficina4);
        return new AdapterOficinas(listaOficinas);
    }
}
