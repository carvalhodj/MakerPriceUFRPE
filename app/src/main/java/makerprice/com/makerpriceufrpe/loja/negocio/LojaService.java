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

public class LojaService {
    private Sessao sessao = Sessao.getInstancia();
    private LojaDAO lojaDAO;
    private Criptografia criptografia=new Criptografia();

    public LojaService(Context context){
        lojaDAO= new LojaDAO(context);
    }

    public void login(String email, String senha)throws Exception{
        sessao.reset();

        String senhaMascarada=criptografia.mascararSenha(senha);

        Loja loja= lojaDAO.getLoja(email, senhaMascarada);

        if(loja == null){
            throw new Exception("Usuário ou senha inválidos");
        }
        //sessao.setUsuario(loja.getUsuario());

    }

    public void cadastrar(String nome, String email, String senha, String cnpj) throws Exception {

        Loja loja= lojaDAO.getLoja(email);

        if (loja != null){
            throw new Exception("Email já cadastrado");
        }
        String senhaMascarada=criptografia.mascararSenha(senha);

        Usuario usuario= new Usuario();
        usuario.setEmail(email);
        usuario.setPass(senhaMascarada);

        Loja pessoaJuridica= new Loja();
        pessoaJuridica.setNome(nome);
        pessoaJuridica.setCnpj(cnpj);
        pessoaJuridica.setUsuario(usuario);

        lojaDAO.inserir(pessoaJuridica);
        sessao.setLoja(pessoaJuridica);

    }
}
