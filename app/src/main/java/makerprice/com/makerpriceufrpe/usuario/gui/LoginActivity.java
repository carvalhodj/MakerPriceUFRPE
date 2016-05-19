package makerprice.com.makerpriceufrpe.usuario.gui;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import makerprice.com.makerpriceufrpe.infra.GuiUtil;
import makerprice.com.makerpriceufrpe.usuario.dao.DatabaseHelper;
import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.usuario.negocio.UsuarioService;

/*

 */

public class LoginActivity extends AppCompatActivity {
    UsuarioService usuarioService = new UsuarioService(this);
    GuiUtil guiUtil = GuiUtil.getGuiUtil();

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
                return;
            }

            if (usuarioSenhaString.length() == 0){
                usuarioSenha.requestFocus();
                usuarioSenha.setError(getString(R.string.error_login_senha_vazia));
                return;
            }

            else {
                try {
                    usuarioService.login(usuarioEmailString, usuarioSenhaString);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }catch (Exception exception) {
                    guiUtil.toastLong(getApplicationContext(), exception.getMessage());
                }

            }

        }

        else if (v.getId() == R.id.linkCadastrar) {
            Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
            startActivity(intent);
        }
    }
}
