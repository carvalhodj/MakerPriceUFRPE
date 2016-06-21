package makerprice.com.makerpriceufrpe.projeto.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.negocio.ComponenteService;
import makerprice.com.makerpriceufrpe.infra.ComponenteListAdapter;
import makerprice.com.makerpriceufrpe.infra.Converter;
import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.infra.Validacao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.projeto.negocio.ProjetoService;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;
import makerprice.com.makerpriceufrpe.usuario.gui.MainActivity;

public class CadastroProjetoActivity extends AppCompatActivity {

    private Validacao validacaoUtil = Validacao.getValidacaoUtil();
    private ProjetoService projetoService = new ProjetoService(this);
    private Sessao sessao = Sessao.getInstancia();
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();
    private Projeto projeto = new Projeto();
    private Converter converter = Converter.getInstancia();
    private ComponenteService componenteService = new ComponenteService(this);
    private Spinner componente1;
    private Spinner componente2;
    private Spinner componente3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_projeto);

        componente1 = (Spinner) findViewById(R.id.componente1);
        componente2 = (Spinner) findViewById(R.id.componente2);
        componente3 = (Spinner) findViewById(R.id.componente3);

        loadSpinnerData(componente1);
        loadSpinnerData(componente2);
        loadSpinnerData(componente3);
    }

    public void cadastrar(View v){
        EditText nome = (EditText) findViewById(R.id.cadastroProjetoNome);
        EditText descricao = (EditText) findViewById(R.id.cadastroProjetoDescricao);
        Spinner plataforma = (Spinner) findViewById(R.id.cadastroProjetoSpinnerPlataforma);
        Spinner aplicacao = (Spinner) findViewById(R.id.cadastroProjetoSpinnerAplicacao);

        String nomeString = nome.getText().toString();
        String descricaoString = descricao.getText().toString();
        String plataformaString = plataforma.getSelectedItem().toString();
        String aplicacaoString = aplicacao.getSelectedItem().toString();
        Componente comp1 = (Componente) componente1.getSelectedItem();
        Componente comp2 = (Componente) componente2.getSelectedItem();
        Componente comp3 = (Componente) componente3.getSelectedItem();

        if (validacaoUtil.isFieldEmpty(nome)){
            nome.requestFocus();
            nome.setError(getString(R.string.error_nome_vazio));
            return;
        }

        if (validacaoUtil.isFieldEmpty(descricao)){
            descricao.requestFocus();
            descricao.setError(getString(R.string.error_descricao_vazia));
            return;
        }

        PessoaFisica criador = sessao.getPessoaFisica();

        ArrayList<Componente> listaComponentes = new ArrayList<>();
        listaComponentes.add(comp1);
        listaComponentes.add(comp2);
        listaComponentes.add(comp3);

        projeto.setNome(nomeString);
        projeto.setDescricao(descricaoString);
        projeto.setPlataforma(plataformaString);
        projeto.setAplicacao(aplicacaoString);
        projeto.setCriador(criador);
        projeto.setComponentes(listaComponentes);

        projetoService.cadastrar(projeto);
        guiUtil.toastLong(getApplicationContext(), "Projeto cadastrado com sucesso");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void adicionarFoto (View v){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1234);
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public void loadSpinnerData(Spinner spinner) {
        ArrayList<Componente> componentes = componenteService.getTodosComponentesSpinner();
        ComponenteListAdapter dataAdapter = new ComponenteListAdapter(this, 0, componentes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1234 && resultCode == RESULT_OK){
            Uri uriImagemGaleria = data.getData();
            Bitmap imagemGaleriaBitmap = null;
            try {
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uriImagemGaleria, "r");
                imagemGaleriaBitmap = converter.getBitmapFromUri(parcelFileDescriptor);
                String imagemBitmapString = converter.BitMapToString(imagemGaleriaBitmap);
                projeto.getImagens().add(imagemBitmapString);
                guiUtil.toastShort(getApplicationContext(), "Imagem adicionada");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }



}
