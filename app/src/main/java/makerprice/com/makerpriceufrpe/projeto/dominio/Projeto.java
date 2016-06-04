package makerprice.com.makerpriceufrpe.projeto.dominio;

import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;

public class Projeto {
    private String nome, descricao, plataforma, aplicacao, componente_1, componente_2, componente_3;
    private PessoaFisica criador;
    private long id;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getPlataforma() { return plataforma; }

    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getAplicacao() { return aplicacao; }

    public void setAplicacao(String aplicacao) { this.aplicacao = aplicacao; }

    public String getComponente_1() { return componente_1; }

    public void setComponente_1(String componente_1) { this.componente_1 = componente_1; }

    public String getComponente_2() { return componente_2; }

    public void setComponente_2(String componente_2) { this.componente_2 = componente_2; }

    public String getComponente_3() { return componente_3; }

    public void setComponente_3(String componente_3) { this.componente_3 = componente_3; }

    public PessoaFisica getCriador() {
        return criador;
    }

    public void setCriador(PessoaFisica criador) {
        this.criador = criador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
