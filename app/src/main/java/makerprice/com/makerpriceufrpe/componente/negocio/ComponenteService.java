package makerprice.com.makerpriceufrpe.componente.negocio;


import android.content.Context;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.infra.Sessao;

public class ComponenteService {
    private Sessao sessao = Sessao.getInstancia();
    private ComponenteDAO componenteDAO;

    public ComponenteService (Context context) {componenteDAO = new ComponenteDAO(context);}

    public ArrayList<String> getTodosComponentesSpinner() {
        return componenteDAO.getTodosComponentesString();
    }

}
