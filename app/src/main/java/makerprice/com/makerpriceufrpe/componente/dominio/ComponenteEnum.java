package makerprice.com.makerpriceufrpe.componente.dominio;


public class ComponenteEnum {

    public enum ComponenteTipo{
        RESISTOR, LED, CAPACITOR;

        public String toString(){
            switch(this){
                case RESISTOR: return "resistor";
                case LED: return "led";
                case CAPACITOR: return "capacitor";
                default: return "não especificado";
            }
        }

    }

    public enum Cor{
        VERMELHO,VERDE;

        public String toString(){
            switch(this){
                case VERMELHO: return "vermelho";
                case VERDE: return "verde";
                default: return "não especificado";
            }

        }
    }

    public enum Resistencia{
        R220,R330;

        public String toString(){
            switch(this){
                case R220: return "220R";
                case R330: return "330R";
                default: return "não especificado";
            }
        }

    }

    public enum Capacitancia{
        UF1, UF100;

        public String toString(){
            switch (this){
                case UF1: return "1uF";
                case UF100: return "100uF";
                default: return "não especificado";
            }
        }
    }

}
