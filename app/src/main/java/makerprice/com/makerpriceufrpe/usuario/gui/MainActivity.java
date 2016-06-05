package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.ProjetoListAdapter;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.gui.CadastroProjetoActivity;
import makerprice.com.makerpriceufrpe.projeto.gui.ProjetoMainActivity;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;


public class MainActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();
    private ProjetoService projetoService = new ProjetoService(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.nome_usuario);
        PessoaFisica pessoaFisica = sessao.getPessoaFisica();
        String nome = pessoaFisica.getNome();
        tv.setText(nome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProjetoListAdapter projetoAdapter;
        ListView listView = (ListView) findViewById(R.id.listaProjetos);

        long idPessoa = pessoaFisica.getID();
        ArrayList<Projeto> listaProjetosTela = projetoService.getTodosProjetosUnicoCriador(idPessoa);
        projetoAdapter = new ProjetoListAdapter(this, 0, listaProjetosTela);
        listView.setAdapter(projetoAdapter);
        listView.setOnItemClickListener(new ListClickHandler());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CadastroProjetoActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.botao_logout) {
            sessao.reset();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

            TextView listText = (TextView) view.findViewById(R.id.nome_projeto_listagem);
            String text = listText.getText().toString();

            Intent intent = new Intent(MainActivity.this, ProjetoMainActivity.class);

            intent.putExtra("selected-item", text);
            startActivity(intent);
        }
    }





}
