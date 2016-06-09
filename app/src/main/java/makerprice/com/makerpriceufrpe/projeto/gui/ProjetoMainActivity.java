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
import android.widget.ImageView;
import android.widget.TextView;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;

public class ProjetoMainActivity extends AppCompatActivity {
    private ProjetoService projetoService = new ProjetoService(this);
    private Sessao sessao = Sessao.getInstancia();
    private Projeto projeto;

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
        TextView textViewComp1 = (TextView) findViewById(R.id.comp1_projeto_main);
        TextView textViewComp2 = (TextView) findViewById(R.id.comp2_projeto_main);
        TextView textViewComp3 = (TextView) findViewById(R.id.comp3_projeto_main);

        textViewNome.setText(projeto.getNome());
        textViewDescricao.setText(projeto.getDescricao());
        textViewPlataforma.setText(projeto.getPlataforma());
        textViewAplicacao.setText(projeto.getAplicacao());
        textViewComp1.setText(projeto.getComponente_1());
        textViewComp2.setText(projeto.getComponente_2());
        textViewComp3.setText(projeto.getComponente_3());

        String imagemPrincipal = projeto.getImagens().get(0);
        Bitmap imagem = StringToBitMap(imagemPrincipal);
        imageView.setImageBitmap(imagem);

    }

    public void onButtonClic(View v){

        if (v.getId() == R.id.projeto_imagem_principal){
            Intent intent= new Intent(getApplicationContext(), ImageSliderActivity.class);
            intent.putExtra("lista-imagens", projeto.getImagens());
            startActivity(intent);
        }
    }

    private Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
