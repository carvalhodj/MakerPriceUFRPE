package makerprice.com.makerpriceufrpe.componente.dominio;

import java.util.Map;
import java.util.HashMap;

public class ComponenteEspc {

    private Map propriedades;

    public ComponenteEspc(Map propriedades){
        if (propriedades==null){
            this.propriedades= new HashMap();
        } else {
            this.propriedades= new HashMap(propriedades);
        }

    }

    public Object getPropriedade(String caracteristica){
        return propriedades.get(caracteristica);
    }

    public Map getPropriedades(){
        return this.propriedades;
    }


}
