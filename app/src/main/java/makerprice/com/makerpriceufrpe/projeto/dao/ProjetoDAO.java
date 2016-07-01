package makerprice.com.makerpriceufrpe.projeto.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteQuantidade;
import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;
import makerprice.com.makerpriceufrpe.usuario.dao.PessoaFisicaDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;

/**
 * Essa classe contém os métodos de cadastro e consultas de projetos
 * no banco de dados do app.
 */
public class ProjetoDAO {
    private DatabaseHelper helper;
    private PessoaFisicaDAO pessoaFisicaDAO;
    private ComponenteDAO componenteDAO;

    public ProjetoDAO(Context context) {
        helper = new DatabaseHelper(context);
        pessoaFisicaDAO = new PessoaFisicaDAO(context);
        componenteDAO = new ComponenteDAO(context);
    }

    /**
     * Método para cadastrar um projeto no banco de dados
     *
     * @param projeto Objeto do tipo Projeto
     * @return Retorna o id do projeto cadastrado
     */
    public long inserir(Projeto projeto){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        ContentValues valuesImagens = new ContentValues();

        String nameColumn = DatabaseHelper.COLUMN_NAME;
        String nome = projeto.getNome();

        String descricaoColumn = DatabaseHelper.COLUMN_DESCRICAO;
        String descricao = projeto.getDescricao();

        String plataformaColumn = DatabaseHelper.COLUMN_PLATAFORMA;
        String plataforma = projeto.getPlataforma();

        String aplicacaoColumn = DatabaseHelper.COLUMN_APLICACAO;
        String aplicacao = projeto.getAplicacao();

        PessoaFisica criador = projeto.getCriador();
        long idCriador = criador.getID();

        String criadorColumn = DatabaseHelper.COLUMN_PESSOAFISICA_ID;
        String idCriadorString = Long.toString(idCriador);

        values.put(nameColumn, nome);
        values.put(descricaoColumn, descricao);
        values.put(plataformaColumn, plataforma);
        values.put(aplicacaoColumn, aplicacao);
        values.put(criadorColumn, idCriadorString);

        String tabela = DatabaseHelper.TABLE_PROJETO;
        String tabelaImagem = DatabaseHelper.TABLE_IMAGEM;

        long id = db.insert(tabela, null, values);

        projeto.setId(id);

        ArrayList<String> projetoImagens = projeto.getImagens();

        String caminhoColumn = DatabaseHelper.COLUMN_CAMINHO;
        String projetoId = DatabaseHelper.COLUMN_PROJETO_ID;

        for (String imagem : projetoImagens){
            valuesImagens.put(caminhoColumn, imagem);
            valuesImagens.put(projetoId, id);
            db.insert(tabelaImagem, null, valuesImagens);
        }

        componenteDAO.vincularProjetoComponentes(projeto);

        db.close();
        return id;
    }

    /**
     * Método para buscar no banco os projetos do usuário ativo na sessão
     *
     * @param idCriador id do usuário ativo na sessão
     * @return Retorna uma lista de projetos do usuário
     */
    public ArrayList<Projeto> getTodosProjetosUnicoCriador(long idCriador){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_PROJETO +
                " WHERE " + DatabaseHelper.COLUMN_PESSOAFISICA_ID + " LIKE ?";

        String idCriadorString = Long.toString(idCriador);

        String[] argumentos = {idCriadorString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<Projeto> listaProjetos = new ArrayList<>();

        while (cursor.moveToNext()) {

            Projeto projeto = criaProjeto(cursor);;

            listaProjetos.add(projeto);

        }
        cursor.close();
        db.close();

        return listaProjetos;
    }

    /**
     * Método para buscar no banco de dados as imagens de um determinado projeto
     *
     * @param id id do projeto que se deseja buscar as imagens
     * @return Retorna uma lista com as imagens em formato String
     */
    public ArrayList<String> getImagensUnicoProjeto (long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_IMAGEM +
                " WHERE " + DatabaseHelper.COLUMN_PROJETO_ID + " LIKE ?";

        String idString = Long.toString(id);

        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<String> listaImagensProjeto = new ArrayList<>();

        String caminhoColumn = DatabaseHelper.COLUMN_CAMINHO;
        int indexColumnCaminho = cursor.getColumnIndex(caminhoColumn);

        while (cursor.moveToNext()) {

            String caminho = cursor.getString(indexColumnCaminho);

            listaImagensProjeto.add(caminho);

        }
        cursor.close();
        db.close();

        return listaImagensProjeto;


    }

    /**
     * Método que recebe um termo informado pelo usuário e retorna os projetos encontrados
     * cujos nomes estão de acordo com o termo, seja total ou parcialmente
     *
     * @param busca termo a ser pesquisado no banco de dados
     * @return Retorna uma lista com os projetos encontrados
     */
    public List<Projeto> buscaProjetos(String busca) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_PROJETO +
                " WHERE " + DatabaseHelper.COLUMN_NAME + " LIKE ? " +
                "OR " + DatabaseHelper.COLUMN_DESCRICAO + " LIKE ?";

        String argumento = "%" + busca + "%";

        String[] argumentos = {argumento, argumento};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<Projeto> listaProjetos = new ArrayList<>();

        while (cursor.moveToNext()) {

            Projeto projeto = criaProjeto(cursor);

            listaProjetos.add(projeto);
        }
        cursor.close();
        db.close();

        return listaProjetos;
    }

    /**
     * Método para criar um objeto do tipo Projeto
     *
     * @param cursor cursor que percorre as colunas da tabela no banco de dados
     * @return Retorna um objeto do tipo Projeto
     */
    public Projeto criaProjeto(Cursor cursor){

        String idColumn = DatabaseHelper.COLUMN_ID;
        int indexColumnId = cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnId);

        String nomeColumn= DatabaseHelper.COLUMN_NAME;
        int indexColumnNome= cursor.getColumnIndex(nomeColumn);
        String nome = cursor.getString(indexColumnNome);

        String descricaoColumn= DatabaseHelper.COLUMN_DESCRICAO;
        int indexColumnDescricao= cursor.getColumnIndex(descricaoColumn);
        String descricao = cursor.getString(indexColumnDescricao);

        String plataformaColumn= DatabaseHelper.COLUMN_PLATAFORMA;
        int indexColumnPlataforma= cursor.getColumnIndex(plataformaColumn);
        String plataforma = cursor.getString(indexColumnPlataforma);

        String aplicacaoColumn= DatabaseHelper.COLUMN_APLICACAO;
        int indexColumnAplicacao= cursor.getColumnIndex(aplicacaoColumn);
        String aplicacao = cursor.getString(indexColumnAplicacao);

        String idCriadorColumn = DatabaseHelper.COLUMN_PESSOAFISICA_ID;
        int indexColumnCriadorId = cursor.getColumnIndex(idCriadorColumn);
        long idCriador = cursor.getLong(indexColumnCriadorId);

        ArrayList<String> listaImagensProjeto = getImagensUnicoProjeto(id);

        PessoaFisica criador = pessoaFisicaDAO.getPessoaFisica(idCriador);

        ArrayList<ComponenteQuantidade> listaComponentes = (ArrayList<ComponenteQuantidade>) componenteDAO.getComponentesUnicoProjeto(id);

        Projeto projeto = new Projeto();
        projeto.setId(id);
        projeto.setNome(nome);
        projeto.setDescricao(descricao);
        projeto.setPlataforma(plataforma);
        projeto.setAplicacao(aplicacao);
        projeto.setCriador(criador);
        projeto.setImagens(listaImagensProjeto);
        projeto.setComponentes(listaComponentes);

        return projeto;
    }


}
