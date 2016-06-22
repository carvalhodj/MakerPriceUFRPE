package makerprice.com.makerpriceufrpe.componente.dominio;


import makerprice.com.makerpriceufrpe.loja.dominio.Loja;

public class ComponenteLoja {
    private Loja loja;
    private Componente componente;
    private int preco;
    private long id;

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
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
