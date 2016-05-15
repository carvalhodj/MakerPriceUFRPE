package makerprice.com.makerpriceufrpe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v){

        //v.getId() verifica o id do item pressionado
        if (v.getId() == R.id.botaoSignIn){
            EditText usuarioEmail = (EditText) findViewById(R.id.sign_in_usuario);
            EditText usuarioSenha = (EditText) findViewById(R.id.sign_in_senha);
            String usuarioEmailString = usuarioEmail.getText().toString();
            String usuarioSenhaString = usuarioSenha.getText().toString();

            String senha = helper.searchPass(usuarioEmailString);

            if (usuarioSenhaString.equals(senha)){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Usuário", usuarioEmailString);
                startActivity(intent);
            } else {
                Toast temp = Toast.makeText(LoginActivity.this, "Usuário e/ou senha não coincidem!", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        else if (v.getId() == R.id.linkSignUp) {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        }
    }
}
