package makerprice.com.makerpriceufrpe.infra;

import java.util.Date;

import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;

public class Sessao {
    private static Sessao instancia = new Sessao();
    private PessoaFisica pessoaFisica;
    private Date horaLogin;
    private Loja loja;
    private Projeto projeto;

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

    public Loja getLoja() { return loja; }

    public void setLoja(Loja loja) { this.loja = loja; }

    public void reset(){
        this.pessoaFisica = null;
        this.horaLogin = null;
        this.loja = null;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

}
