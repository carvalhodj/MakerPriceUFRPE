package makerprice.com.makerpriceufrpe.infra;

import java.util.Date;

import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;

public class Sessao {
    private static Sessao instancia = new Sessao();
    private PessoaFisica pessoaFisica;
    private Date horaLogin;

    private Sessao(){
    }

    public static Sessao getInstancia(){
        return instancia;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica){
        this.pessoaFisica = pessoaFisica;
    }

    public PessoaFisica getPessoaFisica(){
        return this.pessoaFisica;
    }

    public Date getHoraLogin() {
        return horaLogin;
    }

    public void setHoraLogin(Date horaLogin) {
        this.horaLogin = horaLogin;
    }

    public void reset(){
        this.pessoaFisica = null;
        this.horaLogin = null;
    }
}
