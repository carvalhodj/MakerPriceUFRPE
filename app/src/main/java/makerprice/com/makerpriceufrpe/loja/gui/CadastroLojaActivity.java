package makerprice.com.makerpriceufrpe.loja.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.infra.Validacao;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.loja.negocio.LojaService;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;
import makerprice.com.makerpriceufrpe.usuario.gui.LoginActivity;
import makerprice.com.makerpriceufrpe.usuario.gui.MainActivity;
import makerprice.com.makerpriceufrpe.usuario.negocio.UsuarioService;

public class CadastroLojaActivity extends AppCompatActivity {

    LojaService lojaService = new LojaService(this);
    Validacao validacaoUtil = Validacao.getValidacaoUtil();
    GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_loja);
    }

    public void onButtonClickLoja(View v){

        if (v.getId() == R.id.botaoRealizarCadastroLoja){
            EditText nome = (EditText) findViewById(R.id.campoNomeLoja);
            EditText email = (EditText) findViewById(R.id.campoEmail);
            EditText cnpj = (EditText) findViewById(R.id.campoCNPJ);
            EditText senha = (EditText) findViewById(R.id.campoSenha);
            EditText repSenha = (EditText) findViewById(R.id.campoRepeteSenha);

            String nomeString = nome.getText().toString();
            String emailString = email.getText().toString();
            String cnpjString = cnpj.getText().toString();
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

            if (validacaoUtil.isFieldEmpty(cnpj)){
                cnpj.requestFocus();
                cnpj.setError(getString(R.string.error_cnpj_vazio));
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

                    Usuario usuario = new Usuario();
                    usuario.setEmail(emailString);
                    usuario.setPass(senhaString);

                    Loja novaLoja = new Loja();
                    novaLoja.setNome(nomeString);
                    novaLoja.setCnpj(cnpjString);
                    novaLoja.setUsuario(usuario);

                    lojaService.cadastrar(novaLoja);
                    guiUtil.toastLong(getApplicationContext(), "Cadastro realizado com sucesso");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }catch(Exception exception){

                    guiUtil.toastLong(getApplicationContext(), exception.getMessage());
                }
            }
        }
    }
}
