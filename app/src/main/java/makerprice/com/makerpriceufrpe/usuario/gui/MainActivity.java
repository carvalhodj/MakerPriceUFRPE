package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.ProjetoListAdapter;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.gui.CadastroProjetoActivity;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class MainActivity extends AppCompatActivity {
    Sessao sessao = Sessao.getInstancia();
    ProjetoService projetoService = new ProjetoService(this);
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.nome_usuario);
        Usuario usuario = sessao.getUsuario();
        String nomeUsuario = usuario.getName();
        tv.setText(nomeUsuario);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<Projeto> listaProjetos = projetoService.getTodosProjetos();
        ArrayList<String> listaProjetosNomes = null;
        for (Projeto projeto: listaProjetos){
            listaProjetosNomes.add(projeto.getNome());

        }

        listView = (ListView) findViewById(R.id.listaProjetos);

        ArrayAdapter<String> arrayAdapter = new ProjetoListAdapter(this,listaProjetosNomes);
        listView.setAdapter(arrayAdapter);







        //
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CadastroProjetoActivity.class));
            }
        });
    }



}
