package makerprice.com.makerpriceufrpe.componente.negocio;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteQuantidade;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ComponenteService {
    private Sessao sessao = Sessao.getInstancia();
    private ComponenteDAO componenteDAO;

    public ComponenteService (Context context) {componenteDAO = new ComponenteDAO(context);}

    /**
     * Método que requisita ao banco de dados todos os componentes cadastrados no banco de dados,
     *
     * @return Retorna uma lista de componentes
     * @see ComponenteDAO
     */
    public ArrayList<Componente> getTodosComponentesSpinner() {
        return (ArrayList<Componente>) componenteDAO.getTodosComponentes();
    }

    /**
     * Método que requisista ao banco de dados o cadastro de um componente
     *
     * @param componente Objeto do tipo Componente
     */
    public void inserirComponente(Componente componente) {
        long id = componenteDAO.inserir(componente);
        componente.setId(id);
    }

    /**
     * Método para consultar um determinado componente
     *
     * @param id id do componente que se quer consultar
     * @return Retorna o componente
     */
    public Componente getComponente(long id) {
        return componenteDAO.getComponente(id);
    }

    /**
     * Método de pesquisa que requisita ao banco de dados um componente
     * de acordo com o termo informado pelo usuário
     *
     * @param busca Termo que se deseja pesquisar
     * @return Retorna componentes cujos nomes atendem ao termo informado, parcial ou totalmente
     */
    public List<Componente> buscaComponentes(String busca) {
        return componenteDAO.buscaComponentes(busca);
    }

}
