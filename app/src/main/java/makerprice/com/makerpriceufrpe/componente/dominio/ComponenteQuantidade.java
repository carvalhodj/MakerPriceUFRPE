package makerprice.com.makerpriceufrpe.componente.dominio;

/**
 * Essa classe tem como função relacionar os componentes de um projeto
 * com as respectivas quantidades utilizadas no mesmo.
 */
public class ComponenteQuantidade {
    private long id;
    private Componente componente;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
