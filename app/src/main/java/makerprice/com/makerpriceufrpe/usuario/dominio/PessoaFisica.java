package makerprice.com.makerpriceufrpe.usuario.dominio;

public class PessoaFisica {

    private String nome ;
    private Usuario usuario;
    long id;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return this.id;
    }

}
