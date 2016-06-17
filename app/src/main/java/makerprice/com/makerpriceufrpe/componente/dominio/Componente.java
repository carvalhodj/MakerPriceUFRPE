package makerprice.com.makerpriceufrpe.componente.dominio;


public class Componente {

    private ComponenteEspc componenteEspc;
    private long id;

    public ComponenteEspc getComponenteEspc() {
        return componenteEspc;
    }

    public void setComponenteEspc(ComponenteEspc componenteEspc) {
        this.componenteEspc = componenteEspc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}