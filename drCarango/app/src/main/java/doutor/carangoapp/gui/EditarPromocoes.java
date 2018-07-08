package doutor.carangoapp.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import doutor.carangoapp.R;

public class EditarPromocoes extends AppCompatActivity {

    private EditText mPromocaoTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_promocoes);
        mPromocaoTexto=findViewById(R.id.et_editar_promocao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar_promocoes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()){
           case(R.id.menu_item_salvar_editar_promocoes):
               //codigo para fazer post promocao
               String promocao=mPromocaoTexto.getText().toString();

       }
        return super.onOptionsItemSelected(item);
    }
}
