package doutor.carangoapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DividerItemDecoration;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.controller.AdapterOficinas;
import doutor.carangoapp.controller.AsyncGenerico;
import doutor.carangoapp.controller.LoaderOficinasList;
import doutor.carangoapp.controller.WebServiceController;

public class ListaOficinasActivity extends AppCompatActivity implements View.OnClickListener, AdapterOficinas.OnItemClickListener, LoaderManager.LoaderCallbacks<String> {

    private RecyclerView mRecyclerView;
    private AdapterOficinas mAdapterOficinas;
    private ToggleButton mButtonViewPreco;
    private ToggleButton mButtonViewAgilidade;
    private ToggleButton mButtonViewDistancia;
    private String JSonListOficinas;
    private static String mCategoria;
    private static String mRanking = "1";

    private String jsonString;
    private ArrayList<BaseEstabelecimento> arrayOficinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1-troca de oleo 2-reparos 3-bateria 4-revisao

        setContentView(R.layout.activity_lista_oficina);

        mButtonViewAgilidade = findViewById(R.id.btn_agilidade);
        mButtonViewPreco = findViewById(R.id.btn_preco);
        mButtonViewDistancia = findViewById(R.id.btn_distancia);
        mButtonViewDistancia.setOnClickListener(this);
        mButtonViewPreco.setOnClickListener(this);
        mButtonViewAgilidade.setOnClickListener(this);

        mButtonViewPreco.setChecked(true);

        Intent intent = getIntent();
        String title = intent.getStringExtra("categoria");
        String categoria = intent.getStringExtra("categoria");

        getCategoriaClicada(categoria);

        this.setTitle(title);
        Bundle args = new Bundle();
        args.putString("categoria", mCategoria);

        AsynListarOficinas async = new AsynListarOficinas(ListaOficinasActivity.this);
        async.execute();

        //getSupportLoaderManager().initLoader(0, args, this).forceLoad();

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());

        mRecyclerView = findViewById(R.id.recycler_view_lista_oficinas);
        mRecyclerView.setLayoutManager(manager);

        mAdapterOficinas = new AdapterOficinas(this);
        //mAdapterOficinas.setmOficinas(SetupAdapterTest());
        //mAdapterOficinas.setmOficinas(SetupAdapterTest());
        //mRecyclerView.setAdapter(mAdapterOficinas);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        super.onCreate(savedInstanceState);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new LoaderOficinasList(this, args.getString("categoria"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        this.mAdapterOficinas.setmOficinas(getOficinasFromJson(data));

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    private ArrayList<BaseEstabelecimento> getOficinasFromJson(String json) {
        ArrayList<BaseEstabelecimento> oficinas = new ArrayList<>();
        try {

            JSONArray jsonOficinas = new JSONArray(json);
            JSONObject oficina;

            for (int i = 0; i < jsonOficinas.length(); i++) {
                oficina = new JSONObject(jsonOficinas.getString(i));
                BaseEstabelecimento estabelecimento = new BaseEstabelecimento();
                estabelecimento.setId(oficina.getInt("id"));
                estabelecimento.setNome(oficina.getString("nome"));
                //estabelecimento.setCpf(oficina.getString("cnpj"));
                //estabelecimento.setEmail(oficina.getString("email"));
                estabelecimento.setRua(oficina.getString("rua"));
                estabelecimento.setNumero(oficina.getString("numero"));
                estabelecimento.setBairro(oficina.getString("bairro"));
                estabelecimento.setCidade(oficina.getString("cidade"));
                estabelecimento.setCep(oficina.getString("cep"));
                estabelecimento.setEstado(oficina.getString("estado"));
                estabelecimento.setPais(oficina.getString("pais"));
                estabelecimento.setComplemento(oficina.getString("complemento"));
                //estabelecimento.setRankingAgilidade(Double.parseDouble(oficina.getString("rankingAgilidade")));
                //estabelecimento.setRankingCustoBeneficio(Double.parseDouble(oficina.getString("rankingCustoBeneficio")));
                //estabelecimento.setRankingServico(Double.parseDouble(oficina.getString("rankingServico")));
                estabelecimento.setNumeroAvaliacoes(Double.parseDouble(oficina.getString("numeroAvaliacoes")));
                estabelecimento.setNumeroComentarios(Double.parseDouble(oficina.getString("numeroComentarios")));
                estabelecimento.setNumeroPromocoes(Double.parseDouble(oficina.getString("numeroPromocoes")));
                oficinas.add(estabelecimento);
            }

            return oficinas;


        } catch (Exception e) {
            return null;
        }

    }

    private ArrayList<BaseEstabelecimento> SetupAdapterTest() {

        ArrayList<BaseEstabelecimento> listaOficinas = new ArrayList<BaseEstabelecimento>();
        BaseEstabelecimento oficina1 = new BaseEstabelecimento();
        oficina1.setNome("Oficina S. João");
        oficina1.setRankingServico(4.98);
        BaseEstabelecimento oficina2 = new BaseEstabelecimento();
        oficina2.setNome("Central Auto Center");
        oficina2.setRankingServico(4.84);
        BaseEstabelecimento oficina3 = new BaseEstabelecimento();
        oficina3.setNome("Perfet Tecnologia");
        oficina3.setRankingServico(4.75);
        BaseEstabelecimento oficina4 = new BaseEstabelecimento();
        oficina4.setNome("Oficina Calango");
        oficina4.setRankingServico(4.24);
        BaseEstabelecimento oficina5 = new BaseEstabelecimento();
        oficina5.setNome("Oficina do Amigo");
        oficina5.setRankingServico(3.86);
        BaseEstabelecimento oficina6 = new BaseEstabelecimento();
        oficina6.setNome("Oficina do Manuca");
        oficina6.setRankingServico(3.80);
        BaseEstabelecimento oficina7 = new BaseEstabelecimento();
        oficina7.setNome("Lojão do Petróleo");
        oficina7.setRankingServico(3.23);
        BaseEstabelecimento oficina8 = new BaseEstabelecimento();
        oficina8.setNome("Biauto");
        oficina8.setRankingServico(2.45);
        BaseEstabelecimento oficina9 = new BaseEstabelecimento();
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

    private void getCategoriaClicada(String categoria) {
        if (categoria.equals("Troca de oleo")) {
            mCategoria = "Troca de oleo";
        }
        if (categoria.equals("Reparo")) {
            mCategoria = "Reparo";
        }
        if (categoria.equals("Revisao")) {
            mCategoria = "Revisao";
        }
        if (categoria.equals("Bateria")) {
            mCategoria = "Bateria";
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent perfilOficinaIntent = new Intent(this, PerfilOficinaActivity.class);
        //perfilOficinaIntent.putExtra("idOficina",Integer.toString(position));
        perfilOficinaIntent.putExtra("oficina", arrayOficinas.get(position));
        startActivity(perfilOficinaIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_preco:
                //codigo para ordenar lista por preco
                mButtonViewDistancia.setChecked(false);
                mButtonViewAgilidade.setChecked(false);
                mRanking = "2";
                break;
            case R.id.btn_agilidade:
                //codigopara ordenar lista por agilidade
                mButtonViewPreco.setChecked(false);
                mButtonViewDistancia.setChecked(false);
                mRanking = "1";
                break;
            case R.id.btn_distancia:
                //codigo para ordenar lista por distancia
                mButtonViewPreco.setChecked(false);
                mButtonViewAgilidade.setChecked(false);
                mRanking = "3";
                break;
        }
        AsynListarOficinas async = new AsynListarOficinas(ListaOficinasActivity.this);
        async.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_notifications) {
            //codigo para abrir notificacoes
            return true;
        }
        if (id == R.id.foto_perfil_menu) {
            Intent intent = new Intent(this, PrefilUsuarioActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    private class AsynListarOficinas extends AsyncGenerico<Object, Integer, Long> {

        Activity myActivity;

        public AsynListarOficinas(Activity activity) {
            super(activity);
            this.myActivity = activity;
        }

        @Override
        protected Long doInBackground(Object... objects) {

            try {
                //jsonString = WebServiceController.recuperarListaOficinas();
                jsonString = WebServiceController.recuperarListaOficinasCategoria(mCategoria, mRanking);

                arrayOficinas = getOficinasFromJson(jsonString);
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapterOficinas.setmOficinas(arrayOficinas);
                        mRecyclerView.setAdapter(mAdapterOficinas);
                    }
                });

            } catch (Exception e) {
                alertError(e.getMessage());
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

