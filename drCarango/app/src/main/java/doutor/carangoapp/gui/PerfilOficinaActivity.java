package doutor.carangoapp.gui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseComentario;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.base.BaseFotos;
import doutor.carangoapp.base.BaseUsuario;
import doutor.carangoapp.base.testeServidor;
import doutor.carangoapp.controller.AdapterComentsOficina;
import doutor.carangoapp.controller.AsyncGenerico;
import doutor.carangoapp.controller.WebServiceController;

public class PerfilOficinaActivity extends AppCompatActivity implements View.OnClickListener {

    private View mFotoOficina;
    private View iv_promocao_icone;
    private View iv_icone_parceira;
    private TextView tv_NomeNoficina;
    private TextView tv_NotaOficina;
    private TextView tv_EnderecoOficina;
    private TextView tv_TelefoneNoficina;
    private TextView tv_promocoes;
    private TextView tv_oficina_parceira;
    private RecyclerView mRecyclerViewComenarios;
    private AdapterComentsOficina mComentariosAdapter;
    private BaseEstabelecimento mOficina;
    private Button btn_entrar_contato_perfil_oficina, btn_abrir_maps_perfil_oficina;
    private Button btn_avaliar_oficina;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mComentariosAdapter = new AdapterComentsOficina();

        mOficina = (BaseEstabelecimento) getIntent().getSerializableExtra("oficina");

        this.setTitle(mOficina.getNome());

        setContentView(R.layout.activity_pefiloficina);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());

        AsyncComentarios async = new AsyncComentarios(this);
        async.execute();
        mFotoOficina=findViewById(R.id.v_foto_oficina);
        mFotoOficina.setBackgroundResource(BaseFotos.getFoto(mOficina.getId()));

        tv_oficina_parceira=findViewById(R.id.tv_oficina_parceira);
        iv_icone_parceira=findViewById(R.id.iv_oficina_parceira_perfil);
        iv_promocao_icone=findViewById(R.id.iv_ic_promo_perfil_oficina);
        tv_NomeNoficina = findViewById(R.id.tv_nome_oficina_perfil);
        tv_NotaOficina = findViewById(R.id.tv_nota_oficina_perfil);
        tv_EnderecoOficina = findViewById(R.id.tv_endereco_oficina_perfil);
        tv_TelefoneNoficina = findViewById(R.id.tv_telefone_oficina_perfil);
        tv_promocoes = findViewById(R.id.tv_promo_perfil_oficina);
        btn_abrir_maps_perfil_oficina = findViewById(R.id.btn_abrir_maps_perfil_oficina);
        btn_entrar_contato_perfil_oficina = findViewById(R.id.btn_entrar_contato_perfil_oficina);
        btn_avaliar_oficina = findViewById(R.id.btn_avaliar_oficina);

        btn_abrir_maps_perfil_oficina.setOnClickListener(this);
        btn_entrar_contato_perfil_oficina.setOnClickListener(this);
        btn_avaliar_oficina.setOnClickListener(this);
        if(mOficina.getNumeroPromocoes()==0){
            tv_promocoes.setVisibility(View.GONE);
            iv_promocao_icone.setVisibility(View.GONE);
        }else{
            tv_promocoes.setText("10 % de desconto na troca de óleo");
        }
        if(!mOficina.isParceira()){
            iv_icone_parceira.setVisibility(View.GONE);
            tv_oficina_parceira.setVisibility(View.GONE);
        }
        tv_NomeNoficina.setText(mOficina.getNome());
        tv_NotaOficina.setText(Double.toString(mOficina.getRankingServico()));
        //tv_TelefoneNoficina.setText(mOficina.getTelefone());
        setEnderecoOficinaOnTextView();


        mRecyclerViewComenarios = findViewById(R.id.rv_comentarios_perfil_ofcina);
        mRecyclerViewComenarios.setLayoutManager(manager);
        // mRecyclerViewComenarios.setAdapter(mComentariosAdapter);

        super.onCreate(savedInstanceState);

    }

    private void setEnderecoOficinaOnTextView() {
        String rua=mOficina.getRua();
        if(rua!=null){
            tv_EnderecoOficina.append(rua);
            tv_EnderecoOficina.append(", ");
        }
        String numero=mOficina.getNumero();
        if(rua!=null){
            tv_EnderecoOficina.append(numero);
            tv_EnderecoOficina.append(", ");
        }
        String complemento=mOficina.getComplemento();
        if(rua!=null){
            tv_EnderecoOficina.append(complemento);
            tv_EnderecoOficina.append(", ");
        }
        String bairro=mOficina.getBairro();
        if(rua!=null){
            tv_EnderecoOficina.append(bairro);
            tv_EnderecoOficina.append(", ");
        }
        String cidade=mOficina.getCidade();
        if(rua!=null){
            tv_EnderecoOficina.append(cidade);
            tv_EnderecoOficina.append(", ");
        }
        String estado=mOficina.getEstado();
        if(rua!=null){
            tv_EnderecoOficina.append(estado);
            tv_EnderecoOficina.append(", ");
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

            return true;
        }
        if(id==R.id.foto_perfil_menu){
            Intent intent=new Intent(this,PrefilUsuarioActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    public AdapterComentsOficina getmComentariosAdapter() {
        return mComentariosAdapter;
    }

    public void setmComentariosAdapter(AdapterComentsOficina mComentariosAdapter) {
        this.mComentariosAdapter = mComentariosAdapter;
    }

    public BaseEstabelecimento getmOficina() {
        return mOficina;
    }

    public void setmOficina(BaseEstabelecimento mOficina) {
        this.mOficina = mOficina;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_abrir_maps_perfil_oficina:
                Uri gmmIntentUri = Uri.parse("geo:-8.05428,-34.8813");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            case R.id.btn_entrar_contato_perfil_oficina:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tv_TelefoneNoficina.getText().toString().replace("-","")));
                startActivity(intent);
                break;
            case R.id.btn_avaliar_oficina:
                Intent intentAvaliar=new Intent(this,AvaliacaoCustoActivity.class);
                intentAvaliar.putExtra("idOficina",mOficina.getId());
                intentAvaliar.putExtra("oficina",mOficina);
                startActivity(intentAvaliar);
                break;
        }
    }


    private class AsyncComentarios<Object,Integer,Long> extends AsyncGenerico<Object,Integer,Long>{

        private Activity myActivity;
        public AsyncComentarios(Activity activity){
            super(activity);
            this.myActivity=activity;
        }

        @Override
        protected java.lang.Long doInBackground(java.lang.Object... objects) {

            try {
                String jsonResponse = WebServiceController.recuperarComentariosParaOficina(mOficina.getId());
                final ArrayList<BaseComentario> comentarios=getComentariosFromJson(jsonResponse);
                myActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mComentariosAdapter.setComentarios(comentarios);
                        mRecyclerViewComenarios.setAdapter(mComentariosAdapter);
                    }
                });

            }catch (Exception e){
                alertError(e.getMessage());
            }

            return null;
        }
    }

    private ArrayList<BaseComentario> getComentariosFromJson(String jsonResponse) throws IOException,JSONException {
        ArrayList<BaseComentario> comentarios=new ArrayList<>();

        JSONArray jsonComentarios=new JSONArray(jsonResponse);
        JSONObject comentario;

        for(int i=0;i<jsonComentarios.length();i++){

            comentario=new JSONObject(jsonComentarios.getString(i));
            int idUsuario=comentario.getInt("id_usuarios");
            int idOficina=comentario.getInt("id_estabelecimentos");
            String texto=comentario.getString("comentario");
            String nomeUsuario=comentario.getString("nomeUsuario");


            BaseComentario baseComentario=new BaseComentario(idUsuario,idOficina,nomeUsuario,texto);
            comentarios.add(baseComentario);


        }
        return comentarios;

    }
}
