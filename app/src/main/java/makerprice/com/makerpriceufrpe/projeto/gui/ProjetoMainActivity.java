package makerprice.com.makerpriceufrpe.projeto.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
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
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;
import makerprice.com.makerpriceufrpe.componente.gui.ComponenteActivity;
import makerprice.com.makerpriceufrpe.componente.negocio.Comparador;
import makerprice.com.makerpriceufrpe.componente.negocio.ComponenteService;
import makerprice.com.makerpriceufrpe.infra.ComponenteListAdapter;
import makerprice.com.makerpriceufrpe.infra.ComponenteLojaListAdapter;
import makerprice.com.makerpriceufrpe.infra.Converter;
import makerprice.com.makerpriceufrpe.infra.GuiUtil;
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
    private Comparador comparador = new Comparador(this);
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();

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
        TextView textViewPrecoTotal = (TextView) findViewById(R.id.preco_total_projeto);
        ListView listViewComponentes = (ListView) findViewById(R.id.lista_componentes_projeto);

        textViewNome.setText(projeto.getNome());
        textViewDescricao.setText(projeto.getDescricao());
        textViewPlataforma.setText(projeto.getPlataforma());
        textViewAplicacao.setText(projeto.getAplicacao());

        ArrayList<ComponenteLoja> listaComponenteLoja = (ArrayList<ComponenteLoja>) comparador.getPrecoProjeto(projeto);
        ComponenteLojaListAdapter componenteLojaAdapter = new ComponenteLojaListAdapter(this, 0, listaComponenteLoja);
        listViewComponentes.setAdapter(componenteLojaAdapter);
        listViewComponentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComponenteLoja componenteLoja = (ComponenteLoja) parent.getAdapter().getItem(position);

                Intent intent = new Intent(ProjetoMainActivity.this, ComponenteActivity.class);
                intent.putExtra("selected-item", String.valueOf(componenteLoja.getComponente().getId()));
                startActivity(intent);
            }
        });

        double precoTotal = 0;
        for (ComponenteLoja componenteLoja : listaComponenteLoja) {
            precoTotal += componenteLoja.getPreco();
        }
        textViewPrecoTotal.setText("R$ " + String.valueOf(precoTotal));

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

}
