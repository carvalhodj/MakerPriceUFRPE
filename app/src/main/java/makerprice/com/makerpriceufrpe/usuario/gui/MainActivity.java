package makerprice.com.makerpriceufrpe.usuario.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.Sessao;

public class MainActivity extends AppCompatActivity {
    Sessao usuarioSessao = Sessao.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*String nomeUsuario = getIntent().getStringExtra("Usuário");
        TextView tv = (TextView) findViewById(R.id.TVusuario);
        tv.setText(nomeUsuario);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}
