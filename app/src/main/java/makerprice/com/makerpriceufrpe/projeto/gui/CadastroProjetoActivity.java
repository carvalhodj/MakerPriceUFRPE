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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import makerprice.com.makerpriceufrpe.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_projeto);
    }

    public void cadastrar(View v){
        EditText nome = (EditText) findViewById(R.id.cadastroProjetoNome);
        EditText descricao = (EditText) findViewById(R.id.cadastroProjetoDescricao);
        Spinner plataforma = (Spinner) findViewById(R.id.cadastroProjetoSpinnerPlataforma);
        Spinner aplicacao = (Spinner) findViewById(R.id.cadastroProjetoSpinnerAplicacao);
        EditText componente1 = (EditText) findViewById(R.id.componente1);
        EditText componente2 = (EditText) findViewById(R.id.componente2);
        EditText componente3 = (EditText) findViewById(R.id.componente3);

        String nomeString = nome.getText().toString();
        String descricaoString = descricao.getText().toString();
        String plataformaString = plataforma.getSelectedItem().toString();
        String aplicacaoString = aplicacao.getSelectedItem().toString();
        String componente1String = componente1.getText().toString();
        String componente2String = componente2.getText().toString();
        String componente3String = componente3.getText().toString();

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

        if (validacaoUtil.isFieldEmpty(componente1)){
            componente1.requestFocus();
            componente1.setError(getString(R.string.error_componente_vazio));
            return;
        }

        if (validacaoUtil.isFieldEmpty(componente2)){
            componente2.requestFocus();
            componente2.setError(getString(R.string.error_componente_vazio));
            return;
        }

        if (validacaoUtil.isFieldEmpty(componente3)){
            componente3.requestFocus();
            componente3.setError(getString(R.string.error_componente_vazio));
            return;
        }

        PessoaFisica criador = sessao.getPessoaFisica();

        projeto.setNome(nomeString);
        projeto.setDescricao(descricaoString);
        projeto.setPlataforma(plataformaString);
        projeto.setAplicacao(aplicacaoString);
        projeto.setComponente_1(componente1String);
        projeto.setComponente_2(componente2String);
        projeto.setComponente_3(componente3String);
        projeto.setCriador(criador);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1234 && resultCode == RESULT_OK){
            Uri uriImagemGaleria = data.getData();
            Bitmap imagemGaleriaBitmap = null;
            try {
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uriImagemGaleria, "r");
                imagemGaleriaBitmap = converter.getBitmapFromUri(parcelFileDescriptor);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String imagemBitmapString = converter.BitMapToString(imagemGaleriaBitmap);
            projeto.getImagens().add(imagemBitmapString);

        }
    }



}
