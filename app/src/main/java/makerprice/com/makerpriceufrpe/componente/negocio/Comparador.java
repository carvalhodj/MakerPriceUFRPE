package makerprice.com.makerpriceufrpe.componente.negocio;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteQuantidade;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

/**
 * Classe que buscar e retornar os obejtos ComponenteLoja com menor preço de um projeto e uma lista
 * de ComponenteLoja de um determinado componente.
 */

public class Comparador {
    private ComponenteDAO componenteDAO;

    /**
     * Construtor
     * @param context
     */

    public Comparador(Context context) { componenteDAO = new ComponenteDAO(context); }

    /**
     * Metodo responsavel por uscar e retornar um lista de obejtos ComponenteLoja com menor preço da
     * lista de objetos da classe componente que compõem um projeto.
     * @param projeto Objeto Projeto que tera seus componentes com menor preço encontrado.
     * @return Retorna uma lista de objeto ComponenteLoja com menor preço.
     * @see ComponenteQuantidade
     * @see ComponenteLoja
     * @see Componente
     */

    public List<ComponenteLoja> getPrecoProjeto(Projeto projeto) {
        ArrayList<ComponenteQuantidade> listaComponentesProjeto = projeto.getComponentes();
        ArrayList<ComponenteLoja> listaComponenteLoja = new ArrayList<>();
        ComponenteLoja componenteLoja;

        for (ComponenteQuantidade comp : listaComponentesProjeto) {
            Componente componente = comp.getComponente();
            componenteLoja = componenteDAO.getMinimo(componente);
            listaComponenteLoja.add(componenteLoja);
        }

        return listaComponenteLoja;
    }

    /**
     * Metodo que retorna uma lista de objetos da classe ComponenteLoja de um determinado Componente
     * @param componente Componente a ser buscado.
     * @return Retorna uma lista de objetos da classe ComponenteLoja.
     * @see ComponenteDAO
     */

    public List<ComponenteLoja> getPrecosComponente(Componente componente) {
        return componenteDAO.getComponenteLojas(componente);
    }
}
