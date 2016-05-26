package makerprice.com.makerpriceufrpe.usuario.negocio;

import android.content.Context;

import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class UsuarioService  {
    private Sessao sessao = Sessao.getInstancia();
    private UsuarioDAO usuarioDAO;

    public UsuarioService(Context context){
        usuarioDAO= new UsuarioDAO(context);
    }

    public void login(String email, String senha) throws Exception{
        sessao.reset();
        Usuario usuario= usuarioDAO.getUsuario(email, senha);

        if(usuario==null) {
            throw new Exception("Usuário ou senha inválidos");
        }

        sessao.setUsuario(usuario);

    }

    public void cadastrar(String nome, String email, String senha) throws Exception{

        Usuario usuario = usuarioDAO.getUsuario(email);

        if (usuario!=null){
            throw new Exception("Email já cadastrado");
        }
        usuario = new Usuario();
        usuario.setName(nome);
        usuario.setEmail(email);
        usuario.setPass(senha);

        usuarioDAO.inserir(usuario);

        sessao.setUsuario(usuario);


    }


}
