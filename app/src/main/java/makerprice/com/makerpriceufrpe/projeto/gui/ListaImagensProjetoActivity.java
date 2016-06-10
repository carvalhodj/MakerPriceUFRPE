package makerprice.com.makerpriceufrpe.projeto.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.ProjetoImagemListAdapter;
import makerprice.com.makerpriceufrpe.infra.ProjetoListAdapter;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.usuario.gui.MainActivity;

public class ListaImagensProjetoActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagens_projeto);

        ProjetoImagemListAdapter projetoImagemAdapter;
        ListView listView = (ListView) findViewById(R.id.listaImagensProjeto);
        projetoImagemAdapter = new ProjetoImagemListAdapter(this, 0, sessao.getProjeto().getImagens());
        listView.setAdapter(projetoImagemAdapter);
    }
}
