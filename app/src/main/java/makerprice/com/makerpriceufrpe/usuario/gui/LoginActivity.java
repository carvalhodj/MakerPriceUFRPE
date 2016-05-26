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
import makerprice.com.makerpriceufrpe.usuario.negocio.UsuarioService;

public class LoginActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();
    UsuarioService usuarioService = new UsuarioService();
    GuiUtil guiUtil = GuiUtil.getGuiUtil();
    Validacao validacaoUtil = Validacao.getValidacaoUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessao.setContext(this);
    }

    public void onButtonClick(View v){

        if (v.getId() == R.id.botaoSignIn){
            EditText usuarioEmail = (EditText) findViewById(R.id.sign_in_usuario);
            EditText usuarioSenha = (EditText) findViewById(R.id.sign_in_senha);
            String usuarioEmailString = usuarioEmail.getText().toString();
            String usuarioSenhaString = usuarioSenha.getText().toString();

            if (validacaoUtil.isFieldEmpty(usuarioEmail)){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.error_login_email_vazio));
                return;
            }

            if (validacaoUtil.isFieldEmpty(usuarioSenha)){
                usuarioSenha.requestFocus();
                usuarioSenha.setError(getString(R.string.error_login_senha_vazia));
                return;
            }

            if(!validacaoUtil.isEmailValid(usuarioEmailString)){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.email_invalido));
                return;
            }

            if(!validacaoUtil.hasSpacePassword(usuarioSenha)){
                usuarioSenha.requestFocus();
                usuarioSenha.setError("Espaços!!!");
                return;
            }

            try {
                    usuarioService.login(usuarioEmailString, usuarioSenhaString);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
            } catch (Exception exception) {
                    guiUtil.toastLong(getApplicationContext(), exception.getMessage());
                }

        }

        else if (v.getId() == R.id.linkCadastrar) {
            Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
            startActivity(intent);
        }
    }
}
