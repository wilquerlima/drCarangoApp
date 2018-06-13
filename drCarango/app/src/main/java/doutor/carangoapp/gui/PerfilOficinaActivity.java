package doutor.carangoapp.gui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        ArrayList<BaseComentario> coments=new ArrayList<>();
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

        mRecyclerViewComenarios.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    private void setEnderecoOficinaOnTextView() {
        tv_EnderecoOficina.append(mOficina.getRua());
        tv_EnderecoOficina.append(", ");
        tv_EnderecoOficina.append(mOficina.getNumero());
        tv_EnderecoOficina.append(", ");
        tv_EnderecoOficina.append(mOficina.getComplemento());
        tv_EnderecoOficina.append(", ");
        tv_EnderecoOficina.append(mOficina.getBairro());
        tv_EnderecoOficina.append(", ");
        tv_EnderecoOficina.append(mOficina.getCidade());
        tv_EnderecoOficina.append(", ");
        tv_EnderecoOficina.append(mOficina.getEstado());

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
