package makerprice.com.makerpriceufrpe.usuario.dominio;

public class Usuario {
    String email, pass;
    long id;

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getPass(){
        return this.pass;
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return this.id;
    }

}
