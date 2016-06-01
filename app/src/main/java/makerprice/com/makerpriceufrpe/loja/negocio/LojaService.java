package makerprice.com.makerpriceufrpe.loja.negocio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.infra.Criptografia;
import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.loja.dao.LojaDAO;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;
import makerprice.com.makerpriceufrpe.usuario.negocio.UsuarioService;

/**
 * Created by Vinicius on 26/05/2016.
 */
public class LojaService {
    private Sessao sessao = Sessao.getInstancia();
    private LojaDAO lojaDAO;
    private Criptografia criptografia;

    public LojaService(Context context){
        lojaDAO= new LojaDAO(context);
    }

    public void login(String email, String senha)throws Exception{
        sessao.reset();

        String senha_mascarada=criptografia.mascararSenha(senha);

        Loja loja= lojaDAO.getLoja(email, senha_mascarada);

        if(loja == null){
            throw new Exception("Usuario ou senha Invalidos");
        }
        sessao.setUsuario(loja.getUsuario());

    }

    public void cadastrar(String nome, String email, String senha, String cnpj, String bancoJson, String linkImagem) throws Exception {
        Loja loja= lojaDAO.getLoja(email, senha);

        if (loja != null){
            throw new Exception("Email já cadastrado");
        }
        String senha_mascarada=criptografia.mascararSenha(senha);

        Usuario usuario= new Usuario();
        usuario.setName(nome);
        usuario.setEmail(email);
        usuario.setPass(senha_mascarada);

        Loja pessoaJuridica= new Loja();
        pessoaJuridica.setBancoJson(bancoJson);
        pessoaJuridica.setCnpj(cnpj);
        pessoaJuridica.setImagem(linkImagem);
        pessoaJuridica.setUsuario(usuario);

        lojaDAO.inserir(pessoaJuridica);

    }
}
