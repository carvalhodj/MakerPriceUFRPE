package makerprice.com.makerpriceufrpe.projeto.dominio;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteQuantidade;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;

/***
 * Teste
 *
 */

public class Projeto {
    private String nome, descricao, plataforma, aplicacao;
    private ArrayList<String> imagens = new ArrayList<>();
    private ArrayList<ComponenteQuantidade> componentes = new ArrayList<>();
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

    public ArrayList<String> getImagens() {
        return imagens;
    }

    public void setImagens(ArrayList<String> imagens) {
        this.imagens = imagens;
    }

    public ArrayList<ComponenteQuantidade> getComponentes() { return componentes; }

    public void setComponentes(ArrayList<ComponenteQuantidade> componentes) { this.componentes = componentes; }
}
