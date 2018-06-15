package doutor.carangoapp.gui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import doutor.carangoapp.R;
import doutor.carangoapp.base.BaseComentario;
import doutor.carangoapp.base.BaseEstabelecimento;
import doutor.carangoapp.base.testeServidor;
import doutor.carangoapp.controller.AdapterComentsOficina;

public class PerfilOficinaActivity extends AppCompatActivity {

    private TextView tv_NomeNoficina;
    private TextView tv_NotaOficina;
    private TextView tv_EnderecoOficina;
    private TextView tv_TelefoneNoficina;
    private TextView tv_AmigosVisitaramOficina;
    private RecyclerView mRecyclerViewComenarios;
    private AdapterComentsOficina mComentariosAdapter;
    private BaseEstabelecimento mOficina;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testeServidor servidor=new testeServidor();
        servidor.SetupEstabelecimentoTest();
        servidor.setUpComentariosTeste();
        ArrayList<BaseComentario> coments=servidor.getComentarios();
        mComentariosAdapter=new AdapterComentsOficina(coments);

        int posisao= Integer.parseInt(getIntent().getCharSequenceExtra("idOficina").toString());
        mOficina=servidor.getEstabelecimentos().get(posisao);
        this.setTitle(mOficina.getNome());



        setContentView(R.layout.activity_pefiloficina);

        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());

        tv_NomeNoficina=findViewById(R.id.tv_nome_oficina_perfil);
        tv_NotaOficina=findViewById(R.id.tv_nota_oficina_perfil);
        tv_EnderecoOficina=findViewById(R.id.tv_endereco_oficina_perfil);
        tv_TelefoneNoficina=findViewById(R.id.tv_telefone_oficina_perfil);
        tv_AmigosVisitaramOficina=findViewById(R.id.tv_amigos_visitaram);

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
        inflater.inflate(R.menu.perfil_oficina_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.item_menu_notifications){

        }

        return super.onOptionsItemSelected(item);
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


}
