package doutor.carangoapp.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseComentario;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.base.testeServidor;
import doutor.carangoapp.controller.AdapterComentsOficina;

public class PerfilOficinaActivity extends AppCompatActivity implements View.OnClickListener{

    private View btn_EntrarContato;
    private View btn_AbrirMaps;
    private View btn_AvaliarOficina;
    private TextView tv_NomeNoficina;
    private TextView tv_NotaOficina;
    private TextView tv_EnderecoOficina;
    private TextView tv_TelefoneNoficina;
    private TextView tv_promocoes;
    private RecyclerView mRecyclerViewComenarios;
    private AdapterComentsOficina mComentariosAdapter;
    private BaseEstabelecimento mOficina;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_pefiloficina);
        super.onCreate(savedInstanceState);

        testeServidor servidor=new testeServidor();
        servidor.SetupEstabelecimentoTest();
        servidor.setUpComentariosTeste();
        ArrayList<BaseComentario> coments=servidor.getComentarios();
        mComentariosAdapter=new AdapterComentsOficina(coments);

        int posisao= Integer.parseInt(getIntent().getCharSequenceExtra("idOficina").toString());
        mOficina=servidor.getEstabelecimentos().get(posisao);
        this.setTitle(mOficina.getNome());

        btn_AvaliarOficina=findViewById(R.id.btn_avalie_oficina);
        btn_AbrirMaps=findViewById(R.id.btn_abrir_maps_perfil_oficina);
        btn_EntrarContato=findViewById(R.id.btn_entrar_contato_perfil_oficina);
        btn_EntrarContato.setOnClickListener(this);
        btn_AbrirMaps.setOnClickListener(this);
        btn_AvaliarOficina.setOnClickListener(this);



        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());

        tv_NomeNoficina=findViewById(R.id.tv_nome_oficina_perfil);
        tv_NotaOficina=findViewById(R.id.tv_nota_oficina_perfil);
        tv_EnderecoOficina=findViewById(R.id.tv_endereco_oficina_perfil);
        tv_TelefoneNoficina=findViewById(R.id.tv_telefone_oficina_perfil);
        tv_promocoes=findViewById(R.id.tv_promo_perfil_oficina);

        tv_promocoes.setText("10 % de desconto na troca de óleo");
        tv_NomeNoficina.setText(mOficina.getNome());
        tv_NotaOficina.setText(Double.toString(mOficina.getRankingServico()));
        tv_TelefoneNoficina.setText(mOficina.getTelefone());
        setEnderecoOficinaOnTextView();


        mRecyclerViewComenarios=findViewById(R.id.rv_comentarios_perfil_ofcina);
        mRecyclerViewComenarios.setLayoutManager(manager);
        mRecyclerViewComenarios.setAdapter(mComentariosAdapter);


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
        int id=v.getId();

        switch (id){
            case(R.id.btn_avalie_oficina):
                Intent intent=new Intent(this,AvaliacaoCustoActivity.class);
                startActivity(intent);
                break;
            case(R.id.btn_abrir_maps_perfil_oficina):
                break;
            case(R.id.btn_entrar_contato_perfil_oficina):
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("id_oficina",mOficina.getId());
        super.onSaveInstanceState(outState);
    }
}
