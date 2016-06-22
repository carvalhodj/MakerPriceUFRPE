package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.infra.Validacao;
import makerprice.com.makerpriceufrpe.loja.gui.CadastroLojaActivity;
import makerprice.com.makerpriceufrpe.loja.gui.LojaMainActivity;
import makerprice.com.makerpriceufrpe.loja.negocio.LojaService;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;
import makerprice.com.makerpriceufrpe.usuario.negocio.UsuarioService;

public class LoginActivity extends AppCompatActivity {
    UsuarioService usuarioService = new UsuarioService(this);
    LojaService lojaService = new LojaService(this);
    GuiUtil guiUtil = GuiUtil.getGuiUtil();
    Validacao validacaoUtil = Validacao.getValidacaoUtil();
    private Sessao sessao = Sessao.getInstancia();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v){

        if (v.getId() == R.id.botaoSignIn){
            EditText usuarioEmail = (EditText) findViewById(R.id.sign_in_usuario);
            EditText usuarioSenha = (EditText) findViewById(R.id.sign_in_senha);
            String usuarioEmailString = usuarioEmail.getText().toString();
            String usuarioSenhaString = usuarioSenha.getText().toString();

            if (validacaoUtil.isFieldEmpty(usuarioEmail)){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.error_email_vazio));
                return;
            }

            if (validacaoUtil.isFieldEmpty(usuarioSenha)){
                usuarioSenha.requestFocus();
                usuarioSenha.setError(getString(R.string.error_senha_vazia));
                return;
            }

            if(!validacaoUtil.isEmailValid(usuarioEmailString)){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.email_invalido));
                return;
            }

            try {
                    Usuario usuario = usuarioService.login(usuarioEmailString, usuarioSenhaString);
                    if (sessao.getPessoaFisica()!=null){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        lojaService.login(usuario);
                        Intent intent = new Intent(getApplicationContext(), LojaMainActivity.class);
                        startActivity(intent);
                    }

            } catch (Exception exception) {
                    guiUtil.toastLong(getApplicationContext(), exception.getMessage());
                }

        }

        else if (v.getId() == R.id.linkCadastrar) {
            Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
            startActivity(intent);
        }

        else if(v.getId() == R.id.linkCadastrarLoja){
            Intent intent= new Intent(getApplicationContext(), CadastroLojaActivity.class);
            startActivity(intent);
        }
    }
}
