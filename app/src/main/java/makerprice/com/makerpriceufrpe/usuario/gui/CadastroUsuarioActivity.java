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
import makerprice.com.makerpriceufrpe.infra.Sessao;



public class CadastroUsuarioActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();
    UsuarioService usuarioService = new UsuarioService();
    Validacao validacaoUtil = Validacao.getValidacaoUtil();
    GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        sessao.setContext(this);
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

            if (nomeString.length() == 0){
                nome.requestFocus();
                nome.setError("Digite o nome");
                return;
            }

            if (emailString.length() == 0){
                email.requestFocus();
                email.setError("Digite o email");
                return;
            }

            if (senhaString.length() == 0){
                senha.requestFocus();
                senha.setError("Digite a senha!");
                return;
            }

            if(!validacaoUtil.isEmailValid(emailString)){
                email.requestFocus();
                email.setError("Email inválido!");
                return;
            }

            if (!senhaString.equals(repSenhaString)){
                guiUtil.toastShort(getApplicationContext(), "Senhas não coincidem!");
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
