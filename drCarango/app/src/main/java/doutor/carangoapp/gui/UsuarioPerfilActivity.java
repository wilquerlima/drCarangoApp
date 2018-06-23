package doutor.carangoapp.gui;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.AdapterHistoricoServicosUsuario;

public class UsuarioPerfilActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterHistoricoServicosUsuario mAdapterServicos;
    private String mNomeUsario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            mNomeUsario=savedInstanceState.getString("NOME_USUARIO");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_perfil);

        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        mRecyclerView=findViewById(R.id.rv_servicos_realizados_perfil_usuario);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(this.mAdapterServicos);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.app_bar,menu);
        return true;
    }
}
