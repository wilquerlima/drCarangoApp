package doutor.carangoapp.gui;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import doutor.carangoapp.R;
import doutor.carangoapp.controller.AdapterHistoricoServicosUsuario;
import doutor.carangoapp.controller.Session;

public class PrefilUsuarioActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterHistoricoServicosUsuario mAdapterServicos;
    private String mNomeUsario;
    private TextView tv_Usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_perfil);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        tv_Usuario=findViewById(R.id.tv_nome_usuario_perfil);
        mNomeUsario= Session.loggedUsuario.getNome();
        tv_Usuario.setText(mNomeUsario);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.app_bar,menu);
        return true;
    }
}
