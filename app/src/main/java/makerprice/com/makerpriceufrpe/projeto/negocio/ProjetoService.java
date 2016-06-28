package makerprice.com.makerpriceufrpe.projeto.negocio;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dao.ProjetoDAO;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ProjetoService {
    private Sessao sessao = Sessao.getInstancia();
    private ProjetoDAO projetoDAO;

    public ProjetoService(Context context){
        projetoDAO = new ProjetoDAO(context);
    }

    public void cadastrar(Projeto projeto) {

        projetoDAO.inserir(projeto);

    }

    public ArrayList<Projeto> getTodosProjetos(){
        return projetoDAO.getTodosProjetos();
    }

    public Projeto getProjeto(String nomeProjeto, long idUsuario){
        return projetoDAO.getProjeto(nomeProjeto, idUsuario);
    }

    public ArrayList<Projeto> getTodosProjetosUnicoCriador(long idCriador){
        return projetoDAO.getTodosProjetosUnicoCriador(idCriador);
    }

    public List<Projeto> buscaProjetos(String busca) {
        return projetoDAO.buscaProjetos(busca);
    }


}
