package makerprice.com.makerpriceufrpe.projeto.negocio;


import android.content.Context;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.projeto.dao.ProjetoDAO;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ProjetoService {
    private Sessao sessao = Sessao.getInstancia();
    private ProjetoDAO projetoDAO;

    public ProjetoService(Context context){
        projetoDAO = new ProjetoDAO(context);
    }

    public void cadastrar(String nome, String descricao, String plataforma, String aplicacao, String comp1, String comp2, String comp3) {

        Projeto projeto = new Projeto();
        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setPlataforma(plataforma);
        projeto.setAplicacao(aplicacao);
        projeto.setComponente_1(comp1);
        projeto.setComponente_2(comp2);
        projeto.setComponente_3(comp3);

        projetoDAO.inserir(projeto);

    }

    public ArrayList<Projeto> getTodosProjetos(){
        return projetoDAO.getTodosProjetos();
    }

    public Projeto getProjeto(String nome){
        return projetoDAO.getProjeto(nome);
    }


}
