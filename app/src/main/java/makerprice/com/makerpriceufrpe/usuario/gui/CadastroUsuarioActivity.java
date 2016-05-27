package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.infra.Validacao;
import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.usuario.negocio.UsuarioService;



public class CadastroUsuarioActivity extends AppCompatActivity {
    UsuarioService usuarioService = new UsuarioService(this);
    Validacao validacaoUtil = Validacao.getValidacaoUtil();
    GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
    }

    public void cadastrar(View v){
        if (v.getId() == R.id.botaoRealizarCadastroUsuario){
            EditText nome = (EditText) findViewById(R.id.campoNome);
            EditText email = (EditText) findViewById(R.id.campoEmail);
            EditText senha = (EditText) findViewById(R.id.campoSenha);
            EditText repSenha = (EditText) findViewById(R.id.campoRepeteSenha);

            String nomeString = nome.getText().toString();
            String emailString = email.getText().toString();
            String senhaString = senha.getText().toString();
            String repSenhaString = repSenha.getText().toString();

            if (validacaoUtil.isFieldEmpty(nome)){
                nome.requestFocus();
                nome.setError(getString(R.string.error_nome_vazio));
                return;
            }

            if (validacaoUtil.isFieldEmpty(email)){
                email.requestFocus();
                email.setError(getString(R.string.error_email_vazio));
                return;
            }

            if (validacaoUtil.isFieldEmpty(senha) || validacaoUtil.isFieldEmpty(repSenha)){
                if (validacaoUtil.isFieldEmpty(senha)){
                    senha.requestFocus();
                    senha.setError(getString(R.string.error_senha_vazia));
                }

                if (validacaoUtil.isFieldEmpty(repSenha)){
                    repSenha.requestFocus();
                    repSenha.setError(getString(R.string.error_senha_vazia));
                }

                return;
            }

            if (!validacaoUtil.hasSpacePassword(senha)){
                senha.requestFocus();
                senha.setError(getString(R.string.error_espaco_branco));
                return;
            }

            if(!validacaoUtil.isEmailValid(emailString)){
                email.requestFocus();
                email.setError(getString(R.string.email_invalido));
                return;
            }

            if (!senhaString.equals(repSenhaString)){
                senha.requestFocus();
                senha.setError(getString(R.string.error_senha_diferente));
                repSenha.requestFocus();
                repSenha.setError(getString(R.string.error_senha_diferente));
            }

            else {
                try {
                    usuarioService.cadastrar(nomeString, emailString, senhaString);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }catch(Exception exception){
                    guiUtil.toastLong(getApplicationContext(), exception.getMessage());
                }
            }
        }
    }
}
