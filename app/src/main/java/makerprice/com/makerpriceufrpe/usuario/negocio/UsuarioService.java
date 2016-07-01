package makerprice.com.makerpriceufrpe.usuario.negocio;

import android.content.Context;

import makerprice.com.makerpriceufrpe.infra.Criptografia;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.loja.negocio.LojaService;
import makerprice.com.makerpriceufrpe.usuario.dao.PessoaFisicaDAO;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 * Classe de Serviço para comunicação com a classe UsuarioDAO e validação.
 */

public class UsuarioService  {
    private Sessao sessao = Sessao.getInstancia();
    private UsuarioDAO usuarioDAO;
    private PessoaFisicaDAO pessoaFisicaDAO;
    private Criptografia criptografia = new Criptografia();
    private LojaService lojaService;

    /**
     * Construtor
     *
     * @param context
     */

    public UsuarioService(Context context){
        usuarioDAO = new UsuarioDAO(context);
        pessoaFisicaDAO = new PessoaFisicaDAO(context);
        lojaService = new LojaService(context);
    }

    /**
     * Metodo que verifica a existencia de um usuario previamente cadastrado.
     *
     * @param email E-mail de um Usuario já cadastrado.
     * @param senha Senha de um Usuario já cadastrado.
     * @throws Exception Caso a validação não encontre um E-mail e senha associados.
     * @see Criptografia
     * @see UsuarioDAO
     * @see PessoaFisicaDAO
     * @see Sessao
     * @see LojaService
     */

   public void login(String email, String senha) throws Exception {
        sessao.reset();

        String senhaMascarada = criptografia.mascararSenha(senha);

        Usuario usuario= usuarioDAO.getUsuario(email, senhaMascarada);

        if(usuario==null) {
            throw new Exception("Usuário ou senha inválidos");
        }

        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoaFisica(usuario);

       if (pessoaFisica != null){
           sessao.setPessoaFisica(pessoaFisica);
       }

       else {
           lojaService.login(usuario);
       }

   }

    /**
     * Metodo que valida um usuario a ser cadastrado e requisita a inserção a classe UsuarioDAO.
     *
     * @param nome Nome da pessoa fisica.
     * @param email E-mail da pessoa fisica.
     * @param senha Senha definida pelo usuario.
     * @throws Exception Caso ja exista um e-mail já cadastrado.
     * @see Criptografia
     * @see UsuarioDAO
     * @see PessoaFisicaDAO
     * @see PessoaFisica
     */

    public void cadastrar(String nome, String email, String senha) throws Exception {
        sessao.reset();

        Usuario usuario = usuarioDAO.getUsuario(email);

        if (usuario!=null){
            throw new Exception("Email já cadastrado");
        }

        String senhaMascarada = criptografia.mascararSenha(senha);

        usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPass(senhaMascarada);

        long idUsuario = usuarioDAO.inserir(usuario);

        usuario.setID(idUsuario);

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome(nome);
        pessoaFisica.setUsuario(usuario);

        long idPessoaFisica = pessoaFisicaDAO.inserir(pessoaFisica);
        pessoaFisica.setID(idPessoaFisica);

        sessao.setPessoaFisica(pessoaFisica);

    }


}
