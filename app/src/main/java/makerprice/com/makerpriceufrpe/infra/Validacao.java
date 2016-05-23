package makerprice.com.makerpriceufrpe.infra;

import android.text.TextUtils;
import android.widget.EditText;

import makerprice.com.makerpriceufrpe.R;

public class Validacao {
    private static Validacao validacaoUtil = new Validacao();

    private Validacao(){
    }

    public static Validacao getValidacaoUtil(){
        return validacaoUtil;
    }

    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /*public boolean isEmailEmpty(EditText campo){
        if (TextUtils.isEmpty(campo.getText().toString())){
            campo.requestFocus();
            campo.setError(getString(R.string.email_invalido));
        }
    }

    public boolean isPasswordEmpty(EditText campo){
        if (TextUtils.isEmpty(campo.getText().toString())){
            campo.requestFocus();
            campo.setError(.getString(R.string.validacao_senha);
        }
    }

    public boolean validarLogin(EditText campoEmail, EditText campoSenha){
        String campoEmailString = campoEmail.getText().toString();
        String campoSenhaString = campoSenha.getText().toString();
        return this.isEmailEmpty(campoEmailString) && this.isPasswordEmpty(campoSenhaString) &&this.isEmailValid(campoEmailString);
    }*/

}
