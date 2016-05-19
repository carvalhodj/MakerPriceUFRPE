package makerprice.com.makerpriceufrpe.infra;

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

}
