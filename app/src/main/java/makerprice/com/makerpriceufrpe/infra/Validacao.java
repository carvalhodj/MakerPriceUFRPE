package makerprice.com.makerpriceufrpe.infra;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean hasSpacePassword(EditText campo){
        String senha = campo.getText().toString();
        Pattern p= Pattern.compile("((?!\\s)\\A)(\\s|(?<!\\s)\\S){4,20}((?!\\s)\\Z)");
        Matcher m = p.matcher(senha);
        return m.matches();
    }

}
