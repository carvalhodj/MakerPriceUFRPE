package makerprice.com.makerpriceufrpe.componente.negocio;


import android.content.Context;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.infra.Sessao;

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


}
