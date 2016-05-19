package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import makerprice.com.makerpriceufrpe.usuario.dao.DatabaseHelper;
import makerprice.com.makerpriceufrpe.R;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

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

            if (usuarioEmailString.length() == 0){
                usuarioEmail.requestFocus();
                usuarioEmail.setError(getString(R.string.error_login_email_vazio));
            }

            if (usuarioSenhaString.length() == 0){
                usuarioSenha.requestFocus();
                usuarioSenha.setError(getString(R.string.error_login_senha_vazia));
            }

            else {

                String senha = helper.procurarSenha(usuarioEmailString);
                String usuarioNome = helper.nomeUsuario(usuarioEmailString);

                if (usuarioSenhaString.equals(senha)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Usuário", usuarioNome);
                    startActivity(intent);
                } else {
                    Toast temp = Toast.makeText(LoginActivity.this, "Usuário e/ou senha não coincidem!", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }

        }
        else if (v.getId() == R.id.linkCadastrar) {
            Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
            startActivity(intent);
        }
    }
}
