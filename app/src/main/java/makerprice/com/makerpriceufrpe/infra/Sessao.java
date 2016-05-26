package makerprice.com.makerpriceufrpe.infra;

import android.content.Context;

import java.util.Date;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class Sessao {
    private static Sessao instancia = new Sessao();
    private Usuario usuario;
    private Date horaLogin;

    private Sessao(){
    }

    public static Sessao getInstancia(){
        return instancia;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public Date getHoraLogin() {
        return horaLogin;
    }

    public void setHoraLogin(Date horaLogin) {
        this.horaLogin = horaLogin;
    }

    public void reset(){
        this.usuario = null;
        this.horaLogin = null;
    }
}
