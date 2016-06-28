package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.ProjetoListAdapter;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.gui.ProjetoMainActivity;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;

public class PesquisaActivity extends AppCompatActivity {
    private ProjetoService projetoService = new ProjetoService(this);
    private Sessao sessao = Sessao.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
    }

    public void onButtonClickPesquisar(View v){

        if (v.getId() == R.id.botao_pesquisar) {
            EditText busca = (EditText) findViewById(R.id.parametro_busca);
            ListView listView = (ListView) findViewById(R.id.lista_projetos_busca);

            String buscaString = busca.getText().toString();

            ArrayList<Projeto> listaProjetos = (ArrayList<Projeto>) projetoService.buscaProjetos(buscaString);

            ProjetoListAdapter projetoAdapter = new ProjetoListAdapter(this, 0, listaProjetos);
            listView.setAdapter(projetoAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Projeto projeto = (Projeto) parent.getAdapter().getItem(position);
                    sessao.setProjeto(projeto);

                    Intent intent = new Intent(PesquisaActivity.this, ProjetoMainActivity.class);
                    //intent.putExtra("nome", projeto.getNome());
                    startActivity(intent);
                }
            });

        }
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

            TextView listText = (TextView) view.findViewById(R.id.nome_projeto_listagem);
            String text = listText.getText().toString();

            Intent intent = new Intent(PesquisaActivity.this, ProjetoMainActivity.class);

            intent.putExtra("selected-item", text);
            startActivity(intent);
        }
    }
}
