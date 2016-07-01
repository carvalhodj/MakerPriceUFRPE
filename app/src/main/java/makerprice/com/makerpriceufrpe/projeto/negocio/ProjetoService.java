package makerprice.com.makerpriceufrpe.projeto.negocio;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dao.ProjetoDAO;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

/**
 * Essa classe contém os métodos que requisitam à classe ProjetoDAO
 * o cadastro e consultas de projetos.
 *
 * @see ProjetoDAO
 */

public class ProjetoService {
    private Sessao sessao = Sessao.getInstancia();
    private ProjetoDAO projetoDAO;

    public ProjetoService(Context context){
        projetoDAO = new ProjetoDAO(context);
    }

    /**
     * Método que requisita ao projetoDAO o cadastro do projeto
     *
     * @param projeto
     * @see ProjetoDAO
     */
    public void cadastrar(Projeto projeto) {
        projetoDAO.inserir(projeto);

    }

    /**
     * Método para requisitar ao projetoDAO todos os projetos do respectivo usuário
     *
     * @param idCriador id do usuário criador do projeto
     * @return Retorna uma lista com os projetos
     * @see ProjetoDAO
     */
    public ArrayList<Projeto> getTodosProjetosUnicoCriador(long idCriador){
        return projetoDAO.getTodosProjetosUnicoCriador(idCriador);
    }

    /**
     *  Método utilizado pela busca do app para retornar os resultados encontrados ao se pesquisar por um projeto
     *
     * @param busca Termo ser pesquisado
     * @return Retorna todos os projetos que contém o termo, total ou parcialmente
     * @see ProjetoDAO
     */
    public List<Projeto> buscaProjetos(String busca) {
        return projetoDAO.buscaProjetos(busca);
    }


}
