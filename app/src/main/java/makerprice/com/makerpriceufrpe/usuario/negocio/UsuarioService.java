package makerprice.com.makerpriceufrpe.usuario.negocio;

import android.content.Context;

import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 * Created by Vinicius on 19/05/2016.
 */
public class UsuarioService  {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(Context context){
        usuarioDAO= new UsuarioDAO(context);
    }

    public void login(String email, String senha) throws Exception{

        Usuario usuario= usuarioDAO.getUsuario(email, senha);

        if(usuario==null) {
            throw new Exception("Usuario ou senha invalidos.");
        }

    }

}
