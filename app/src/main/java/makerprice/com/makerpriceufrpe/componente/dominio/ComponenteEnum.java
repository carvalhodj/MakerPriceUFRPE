package makerprice.com.makerpriceufrpe.componente.dominio;


public class ComponenteEnum {

    public enum ComponenteTipo{
        RESISTOR("resistor"), LED("led"), CAPACITOR("capacitor");
        private String nome;

        ComponenteTipo(String nome){
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

    }

    public enum Cor{
        VERMELHO("vermelho"),VERDE("verde");
        private String nome;

        Cor(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

    }

    public enum Resistencia{
        R220("220R"),R330("330R");

        private String nome;

        Resistencia(String nome){
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

    }



    public enum Capacitancia {
        UF1("1uF"), UF100("100uF");

        private String nome;

        Capacitancia(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

    }

}
