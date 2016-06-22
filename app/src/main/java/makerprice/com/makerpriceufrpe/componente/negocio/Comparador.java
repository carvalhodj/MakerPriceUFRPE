package makerprice.com.makerpriceufrpe.componente.negocio;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class Comparador {
    private ComponenteDAO componenteDAO;

    public Comparador(Context context) { componenteDAO = new ComponenteDAO(context); }

    public List<ComponenteLoja> getPrecoProjeto(Projeto projeto) {
        ArrayList<Componente> listaComponentesProjeto = projeto.getComponentes();
        ArrayList<ComponenteLoja> listaComponenteLoja = new ArrayList<>();
        ComponenteLoja componenteLoja;

        for (Componente comp : listaComponentesProjeto) {
            componenteLoja = componenteDAO.getMinimo(comp);
            listaComponenteLoja.add(componenteLoja);
        }

        return listaComponenteLoja;
    }

    public List<ComponenteLoja> getPrecosComponente(Componente componente) {
        return componenteDAO.getComponenteLojas(componente);
    }
}
