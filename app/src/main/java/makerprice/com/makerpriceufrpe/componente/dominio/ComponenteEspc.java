package makerprice.com.makerpriceufrpe.componente.dominio;

import java.util.Map;
import java.util.HashMap;

/**
 * Essa classe contém um atributo do tipo Map, denominado 'propriedades',
 * onde conterá as informações necessárias de cada componente presente no
 * sistema.
 */
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
