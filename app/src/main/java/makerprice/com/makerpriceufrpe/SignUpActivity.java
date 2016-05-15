package makerprice.com.makerpriceufrpe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUpClick(View v){
        if (v.getId() == R.id.botaoSignUp){
            EditText nome = (EditText)findViewById(R.id.campoNome);
            EditText email = (EditText)findViewById(R.id.campoEmail);
            EditText pass = (EditText)findViewById(R.id.campoSenha);
            EditText repPass = (EditText)findViewById(R.id.campoRepSenha);

            String nomeString = nome.getText().toString();
            String emailString = email.getText().toString();
            String passString = pass.getText().toString();
            String repPassString = repPass.getText().toString();

            if (!passString.equals(repPassString)){
                Toast ErrorPass = Toast.makeText(SignUpActivity.this, "Senhas não coincidem!", Toast.LENGTH_SHORT);
                ErrorPass.show();
            }

            else {
                Contact c = new Contact();
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
