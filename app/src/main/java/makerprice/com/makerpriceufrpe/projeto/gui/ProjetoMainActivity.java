package makerprice.com.makerpriceufrpe.projeto.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.negocio.ComponenteService;
import makerprice.com.makerpriceufrpe.infra.ComponenteListAdapter;
import makerprice.com.makerpriceufrpe.infra.Converter;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;
import makerprice.com.makerpriceufrpe.usuario.gui.MainActivity;

public class ProjetoMainActivity extends AppCompatActivity {
    private ProjetoService projetoService = new ProjetoService(this);
    private ComponenteService componenteService = new ComponenteService(this);
    private Sessao sessao = Sessao.getInstancia();
    private Projeto projeto;
    private Converter converter = Converter.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_main);

        Intent intent = getIntent();
        String item = intent.getStringExtra("selected-item");

        PessoaFisica criador = sessao.getPessoaFisica();
        long idCriador = criador.getID();

        projeto = projetoService.getProjeto(item, idCriador);

        ImageView imageView = (ImageView) findViewById(R.id.projeto_imagem_principal);
        TextView textViewNome = (TextView) findViewById(R.id.nome_projeto_main);
        TextView textViewDescricao = (TextView) findViewById(R.id.descricao_projeto_main);
        TextView textViewPlataforma = (TextView) findViewById(R.id.plataforma_projeto_main);
        TextView textViewAplicacao = (TextView) findViewById(R.id.aplicacao_projeto_main);
        ListView listViewComponentes = (ListView) findViewById(R.id.lista_componentes_projeto);

        textViewNome.setText(projeto.getNome());
        textViewDescricao.setText(projeto.getDescricao());
        textViewPlataforma.setText(projeto.getPlataforma());
        textViewAplicacao.setText(projeto.getAplicacao());

        ArrayList<Componente> listaComponentes = (ArrayList<Componente>) componenteService.getComponentesUnicoProjeto(projeto.getId());
        ComponenteListAdapter componenteAdapter = new ComponenteListAdapter(this, 0, listaComponentes);
        listViewComponentes.setAdapter(componenteAdapter);
        listViewComponentes.setOnItemClickListener(new ListClickHandler());

        String imagemPrincipal = projeto.getImagens().get(0);
        Bitmap imagem = converter.StringToBitMap(imagemPrincipal);
        imageView.setImageBitmap(imagem);

    }

    public void onButtonClickProjeto(View v){

        if (v.getId() == R.id.projeto_imagem_principal){
            Intent intent= new Intent(this, ListaImagensProjetoActivity.class);
            sessao.setProjeto(projeto);
            startActivity(intent);
        }
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

            //TextView listText = (TextView) view.findViewById(R.id.nome_componente_listagem);
            //String text = listText.getText().toString();

            Intent intent = new Intent(ProjetoMainActivity.this, MainActivity.class);

            //intent.putExtra("selected-item", text);
            startActivity(intent);
        }
    }
}
