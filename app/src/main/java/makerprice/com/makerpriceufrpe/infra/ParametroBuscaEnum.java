package makerprice.com.makerpriceufrpe.infra;

/**
 * Created by d3jota on 28/06/16.
 */
public class ParametroBuscaEnum {

    public enum Parametro{
        PROJETO("Projeto"), COMPONENTE("Componente");
        private String nome;

        Parametro(String nome){
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

    }
}
