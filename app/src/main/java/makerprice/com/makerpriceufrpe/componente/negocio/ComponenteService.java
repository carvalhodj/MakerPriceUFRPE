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

    public ArrayList<Componente> getTodosComponentesSpinner() {
        return (ArrayList<Componente>) componenteDAO.getTodosComponentes();
    }

    public void inserirComponente(Componente componente) {
        long id = componenteDAO.inserir(componente);
        componente.setId(id);
    }

    public List<ComponenteQuantidade> getComponentesUnicoProjeto(long idProjeto) {
        return componenteDAO.getComponentesUnicoProjeto(idProjeto);
    }

    public Componente getComponente(long id) {
        return componenteDAO.getComponente(id);
    }

    public List<Componente> buscaComponentes(String busca) {
        return componenteDAO.buscaComponentes(busca);
    }

}
