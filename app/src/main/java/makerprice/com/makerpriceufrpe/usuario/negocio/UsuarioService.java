package makerprice.com.makerpriceufrpe.usuario.negocio;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import makerprice.com.makerpriceufrpe.infra.Criptografia;
import makerprice.com.makerpriceufrpe.infra.MakerPriceException;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.usuario.dao.PessoaFisicaDAO;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class UsuarioService  {
    private Sessao sessao = Sessao.getInstancia();
    private UsuarioDAO usuarioDAO;
    private PessoaFisicaDAO pessoaFisicaDAO;
    private Criptografia criptografia=new Criptografia();

    public UsuarioService(Context context){
        usuarioDAO = new UsuarioDAO(context);
        pessoaFisicaDAO = new PessoaFisicaDAO(context);
    }



   public Usuario login(String email, String senha) throws MakerPriceException, UnsupportedEncodingException, NoSuchAlgorithmException {
        sessao.reset();

        String senhaMascarada = criptografia.mascararSenha(senha);

        Usuario usuario= usuarioDAO.getUsuario(email, senhaMascarada);

        if(usuario==null) {
            throw new MakerPriceException("Usuário ou senha inválidos");
        }

        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoaFisica(usuario);

        sessao.setPessoaFisica(pessoaFisica);

       return usuario;
   }
    public void cadastrar(String nome, String email, String senha) throws MakerPriceException, UnsupportedEncodingException, NoSuchAlgorithmException {

        Usuario usuario = usuarioDAO.getUsuario(email);

        if (usuario!=null){
            throw new MakerPriceException("Email já cadastrado");
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
