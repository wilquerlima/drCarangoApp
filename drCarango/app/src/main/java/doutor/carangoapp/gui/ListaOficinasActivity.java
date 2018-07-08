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
import android.util.Log;
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

public class ListaOficinasActivity extends AppCompatActivity implements View.OnClickListener,AdapterOficinas.OnItemClickListener,LoaderManager.LoaderCallbacks<String> {

    private RecyclerView mRecyclerView;
    private AdapterOficinas mAdapterOficinas;
    private ToggleButton mButtonViewPreco;
    private ToggleButton mButtonViewAgilidade;
    private ToggleButton mButtonViewDistancia;
    private String JSonListOficinas;
    private static String mCategoria;

    private String jsonString;
    private ArrayList<BaseEstabelecimento> arrayOficinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1-troca de oleo 2-reparos 3-bateria 4-revisao

        setContentView(R.layout.activity_lista_oficina);

        mButtonViewAgilidade= findViewById(R.id.btn_agilidade);
        mButtonViewPreco=findViewById(R.id.btn_preco);
        mButtonViewDistancia=findViewById(R.id.btn_distancia);
        mButtonViewDistancia.setOnClickListener(this);
        mButtonViewPreco.setOnClickListener(this);
        mButtonViewAgilidade.setOnClickListener(this);


        Intent intent=getIntent();
        String title=intent.getStringExtra("categoria");
        String categoria=intent.getStringExtra("categoria");

        getCategoriaClicada(categoria);

        this.setTitle(title);
        Bundle args=new Bundle();
        args.putString("categoria",mCategoria);
        mAdapterOficinas=new AdapterOficinas(this);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());

        mRecyclerView=findViewById(R.id.recycler_view_lista_oficinas);
        mRecyclerView.setLayoutManager(manager);

        AsynListarOficinas async = new AsynListarOficinas(ListaOficinasActivity.this);
        async.execute("Troca%20de%20oleo","1");


        //getSupportLoaderManager().initLoader(0, args, this).forceLoad();




        //mAdapterOficinas.setmOficinas(SetupAdapterTest());
        //mAdapterOficinas.setmOficinas(SetupAdapterTest());
        //mRecyclerView.setAdapter(mAdapterOficinas);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        super.onCreate(savedInstanceState);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new LoaderOficinasList(this,args.getString("categoria"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        this.mAdapterOficinas.setmOficinas(getOficinasFromJson(data));

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


    private ArrayList<BaseEstabelecimento> getOficinasFromJson(String json){
        ArrayList<BaseEstabelecimento> oficinas=new ArrayList<>();
        try{

            JSONArray jsonOficinas=new JSONArray(json);
            JSONObject oficina;

            for(int i=0;i<jsonOficinas.length();i++){
                oficina=new JSONObject(jsonOficinas.getString(i));
                BaseEstabelecimento estabelecimento=new BaseEstabelecimento();
                estabelecimento.setId(oficina.getInt("id"));
                estabelecimento.setNome(oficina.getString("nome"));
                estabelecimento.setRua(oficina.getString("rua"));
                estabelecimento.setNumero(oficina.getString("numero"));
                estabelecimento.setBairro(oficina.getString("bairro"));
                estabelecimento.setCidade(oficina.getString("cidade"));
                estabelecimento.setCep(oficina.getString("cep"));
                estabelecimento.setEstado(oficina.getString("estado"));
                estabelecimento.setPais(oficina.getString("pais"));
                estabelecimento.setComplemento(oficina.getString("complemento"));
                estabelecimento.setCredenciada(Integer.parseInt(oficina.getString("credencia")));
                estabelecimento.setNumeroAvaliacoes(Integer.parseInt(oficina.getString("numeroAvaliacoes")));
                estabelecimento.setNumeroComentarios(Integer.parseInt(oficina.getString("numeroComentarios")));
                estabelecimento.setNumeroPromocoes(Integer.parseInt(oficina.getString("numeroPromocoes")));
                oficinas.add(estabelecimento);
            }

            return oficinas;


        }catch (Exception e ){
            Log.d("ListaOficinas",e.getMessage());
            return null;
        }

    }



    private void getCategoriaClicada(String categoria) {
        if(categoria.equals("Troca de Óleo")){
            mCategoria="Troca de oleo";
        }
        if(categoria.equals("Reparo")){
            mCategoria="Reparo";
        }
        if(categoria.equals("Revisão")){
            mCategoria="Revisão";
        }
        if(categoria.equals("bateria")){
            mCategoria="Bateria";
        }
    }




    @Override
    public void onItemClick(int position) {
        Intent perfilOficinaIntent=new Intent(this,PerfilOficinaActivity.class);
        perfilOficinaIntent.putExtra("idOficina",Integer.toString(position));
        startActivity(perfilOficinaIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_preco:{
                //adicionar codigo para ordenar lista por preco
                mButtonViewDistancia.setChecked(false);
                mButtonViewAgilidade.setChecked(false);
                break;
            }
            case R.id.btn_agilidade:{
                //adicionar codigo para ordenar lista por agilidade
                mButtonViewPreco.setChecked(false);
                mButtonViewDistancia.setChecked(false);
                break;            }
            case R.id.btn_distancia:{
                //adicionar codigo para ordenar lista por distancia
                mButtonViewPreco.setChecked(false);
                mButtonViewAgilidade.setChecked(false);
                break;
            }
        }
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
            //codigo para abrir notificacoes
            return true;
        }
        if(id==R.id.foto_perfil_menu){
            Intent intent=new Intent(this,PrefilUsuarioActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    private class AsynListarOficinas extends AsyncGenerico<String,Integer,Long>{

        Activity myActivity;

        public AsynListarOficinas(Activity activity) {
            super(activity);
            this.myActivity = activity;
        }

        @Override
        protected Long doInBackground(String... params) {

            try{
                jsonString = WebServiceController.recuperarListaOficinas(params[0],params[1]);

                arrayOficinas = getOficinasFromJson(jsonString);
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("AsynListarOficinas","em run on UI thread");
                        mAdapterOficinas.setmOficinas(arrayOficinas);
                        mRecyclerView.setAdapter(mAdapterOficinas);
                        Log.d("AsynListarOficinas","setados adapter e recylcer view");
                    }
                });

            }catch (Exception e){
                alertError(e.getMessage());
            }
            return null;
        }
    }
}

