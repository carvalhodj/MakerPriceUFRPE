package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.infra.Validacao;
import makerprice.com.makerpriceufrpe.usuario.dao.DatabaseHelper;
import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
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
            EditText pass = (EditText) findViewById(R.id.campoSenha);
            EditText repPass = (EditText) findViewById(R.id.campoRepeteSenha);

            String nomeString = nome.getText().toString();
            String emailString = email.getText().toString();
            String passString = pass.getText().toString();
            String repPassString = repPass.getText().toString();

            if(!validacaoUtil.isEmailValid(emailString)){
                email.requestFocus();
                email.setError("Email incorreto!");
                return;
            }

            if (!passString.equals(repPassString)){
                guiUtil.toastShort(getApplicationContext(), "Senhas não coincidem!");
            }

            else {
                Usuario c = new Usuario();
                c.setName(nomeString);
                c.setEmail(emailString);
                c.setPass(passString);
                helper.inserirUsuario(c);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }
    }
}