package makerprice.com.makerpriceufrpe.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import makerprice.com.makerpriceufrpe.dao.DatabaseHelper;
import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.dominio.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
    }

    public void cadastrar(View v){
        if (v.getId() == R.id.botaoRealizarCadastroUsuario){
            EditText nome = (EditText)findViewById(R.id.campoNome);
            EditText email = (EditText)findViewById(R.id.campoEmail);
            EditText pass = (EditText)findViewById(R.id.campoSenha);
            EditText repPass = (EditText)findViewById(R.id.campoRepeteSenha);

            String nomeString = nome.getText().toString();
            String emailString = email.getText().toString();
            String passString = pass.getText().toString();
            String repPassString = repPass.getText().toString();

            if (!passString.equals(repPassString)){
                Toast ErrorPass = Toast.makeText(CadastroUsuarioActivity.this, "Senhas não coincidem!", Toast.LENGTH_SHORT);
                ErrorPass.show();
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
