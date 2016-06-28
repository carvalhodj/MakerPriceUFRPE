package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.gui.ComponenteActivity;
import makerprice.com.makerpriceufrpe.componente.negocio.ComponenteService;
import makerprice.com.makerpriceufrpe.infra.ComponenteListAdapter;
import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.infra.ParametroBuscaEnum;
import makerprice.com.makerpriceufrpe.infra.ProjetoListAdapter;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.gui.ProjetoMainActivity;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;

public class PesquisaActivity extends AppCompatActivity {
    private ProjetoService projetoService = new ProjetoService(this);
    private ComponenteService componenteService = new ComponenteService(this);
    private Sessao sessao = Sessao.getInstancia();
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
    }

    public void onButtonClickPesquisar(View v){

        if (v.getId() == R.id.botao_pesquisar) {
            EditText busca = (EditText) findViewById(R.id.parametro_busca);
            ListView listView = (ListView) findViewById(R.id.lista_projetos_busca);
            Spinner parametroSpinner = (Spinner) findViewById(R.id.pesquisa_spinner);
            TextView resultadoVazio = (TextView) findViewById(R.id.pesquisa_vazia);

            String buscaString = busca.getText().toString();
            String parametro = parametroSpinner.getSelectedItem().toString();

            if (parametro.equals(ParametroBuscaEnum.Parametro.PROJETO.getNome())) {
                ArrayList<Projeto> listaProjetos = (ArrayList<Projeto>) projetoService.buscaProjetos(buscaString);

                if (listaProjetos.isEmpty()) {

                    resultadoVazio.setText("Não há resultados");

                } else {
                    resultadoVazio.setText("");
                }

                ProjetoListAdapter projetoAdapter = new ProjetoListAdapter(this, 0, listaProjetos);
                listView.setAdapter(projetoAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Projeto projeto = (Projeto) parent.getAdapter().getItem(position);
                        sessao.setProjeto(projeto);

                        Intent intent = new Intent(PesquisaActivity.this, ProjetoMainActivity.class);
                        startActivity(intent);

                    }
                });
            }


            else if (parametro.equals(ParametroBuscaEnum.Parametro.COMPONENTE.getNome())) {
                ArrayList<Componente> listaComponentes = (ArrayList<Componente>) componenteService.buscaComponentes(buscaString);

                if (listaComponentes.isEmpty()){
                    resultadoVazio.setText("Não há resultados");

                } else {
                    resultadoVazio.setText("");
                }

                ComponenteListAdapter componenteAdapter = new ComponenteListAdapter(this, 0, listaComponentes);
                listView.setAdapter(componenteAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Componente componente = (Componente) parent.getAdapter().getItem(position);
                        long idComponente = componente.getId();

                        Intent intent = new Intent(PesquisaActivity.this, ComponenteActivity.class);
                        intent.putExtra("selected-item", String.valueOf(idComponente));
                        startActivity(intent);
                    }
                });

            }
        }
    }
}
