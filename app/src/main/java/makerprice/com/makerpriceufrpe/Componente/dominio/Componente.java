package makerprice.com.makerpriceufrpe.Componente.dominio;


public class Componente {

    private String serialNumber;
    private ComponenteEspc componenteEspc;


    public Componente(String serialNumber, ComponenteEspc espc){
        this.serialNumber= serialNumber;
        this.componenteEspc= espc;
    }

    public ComponenteEspc getComponenteEspc() {
        return componenteEspc;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

}