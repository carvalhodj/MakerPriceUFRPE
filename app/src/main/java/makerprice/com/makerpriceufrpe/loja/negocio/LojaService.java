package makerprice.com.makerpriceufrpe.loja.negocio;

import android.content.Context;

import makerprice.com.makerpriceufrpe.infra.Criptografia;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.loja.dao.LojaDAO;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;


public class LojaService {
    private Sessao sessao = Sessao.getInstancia();
    private LojaDAO lojaDAO;
    private UsuarioDAO usuarioDAO;
    private Criptografia criptografia=new Criptografia();

    public LojaService(Context context){
        lojaDAO= new LojaDAO(context);
        usuarioDAO=new UsuarioDAO(context);
    }

    public void login(Usuario usuario){
        sessao.reset();

        Loja loja = lojaDAO.getLoja(usuario);

        sessao.setLoja(loja);

    }

    public void cadastrar(Loja loja) throws Exception {

        Usuario loja_usuario =loja.getUsuario();
        String email=loja_usuario.getEmail();
        String senha=loja_usuario.getPass();

        Usuario usuario= usuarioDAO.getUsuario(email);

        if (usuario != null){
            throw new Exception("Email já cadastrado");
        }

        String senhaMascarada=criptografia.mascararSenha(senha);

        loja_usuario.setPass(senhaMascarada);

        long idUsuario = usuarioDAO.inserir(loja_usuario);

        loja_usuario.setID(idUsuario);

        long idLoja = lojaDAO.inserir(loja);
        loja.setId(idLoja);

        sessao.setLoja(loja);

    }
}
