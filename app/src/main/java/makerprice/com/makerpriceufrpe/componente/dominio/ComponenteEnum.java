package makerprice.com.makerpriceufrpe.componente.dominio;


public class ComponenteEnum {

    public enum ComponenteTipo{
        RESISTOR, LED;

        public String toString(){
            switch(this){
                case RESISTOR: return "resistor";
                case LED: return "led";
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

}
