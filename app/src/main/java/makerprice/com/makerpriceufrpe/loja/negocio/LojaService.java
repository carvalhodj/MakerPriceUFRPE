package makerprice.com.makerpriceufrpe.loja.negocio;

import android.content.Context;

import makerprice.com.makerpriceufrpe.infra.Criptografia;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.loja.dao.LojaDAO;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 * Classe de Serviço para comunicação com a classe LojaDAO e validação.
 */

public class LojaService {
    private Sessao sessao = Sessao.getInstancia();
    private LojaDAO lojaDAO;
    private UsuarioDAO usuarioDAO;
    private Criptografia criptografia=new Criptografia();

    /**
     * Construtor
     * @param context
     */

    public LojaService(Context context){
        lojaDAO= new LojaDAO(context);
        usuarioDAO=new UsuarioDAO(context);
    }

    /**
     * Metodo que valida a existencia de uma Loja previamente cadastrada na tabela TABLE_LOJA, ao passar um objeto da classe Usuario, e o seta na sessão.
     * @param usuario Objeto Usuario a ser verificado.
     * @see LojaDAO
     */

    public void login(Usuario usuario){
        sessao.reset();

        Loja loja = lojaDAO.getLoja(usuario);

        sessao.setLoja(loja);

    }

    /**
     *  Metodo que valida uma Loja a ser cadastrado e requisita a inserção a classe LojaDAO.
     * @param loja Objeto da classe Loja a ser cadastrado.
     * @throws Exception Retorna uma exceção caso já exista um Usuario com mesmo e-mail ja cadastrado.
     * @see UsuarioDAO
     * @see Criptografia
     * @see LojaDAO
     */

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
