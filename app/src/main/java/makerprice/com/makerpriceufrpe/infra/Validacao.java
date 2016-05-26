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

    public boolean isEmailValid(CharSequence email) { return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); }

    public boolean isFieldEmpty(EditText campo){ return TextUtils.isEmpty(campo.getText().toString()); }

}
