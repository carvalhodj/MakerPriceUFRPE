package makerprice.com.makerpriceufrpe.componente.dominio;


import java.util.Iterator;
import java.util.Map;

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

    @Override
    public String toString() {
        Map prop = getComponenteEspc().getPropriedades();
        Iterator<Map.Entry<String,String>> iterator = prop.entrySet().iterator();
        String dado = "";
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
            dado += entry.getValue() + " ";
        }
        return dado;
    }
}