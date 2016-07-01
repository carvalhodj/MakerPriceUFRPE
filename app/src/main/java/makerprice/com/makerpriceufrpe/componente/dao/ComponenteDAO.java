package makerprice.com.makerpriceufrpe.componente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEnum;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEspc;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteQuantidade;
import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.loja.dao.LojaDAO;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

/**
 * Classe de persistencia para classe Componente.
 */

public class ComponenteDAO {
    private DatabaseHelper helper;
    private LojaDAO lojaDAO;

    /**
     * Construtor
     * @param context
     */

    public ComponenteDAO(Context context) {
        helper = new DatabaseHelper(context);
        lojaDAO = new LojaDAO(context);
    }

    /**
     * Metodo que insere um Objeto do tipo Componente na tabela TABLE_COMPONENTE.
     * @param componente Objeto da classe componente a ser inserido no banco de dados.
     * @return Retorna o id do componente inserido.
     */

    public long inserir(Componente componente){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        ComponenteEspc spec = componente.getComponenteEspc();

        for (String col : DatabaseHelper.COLUMNS_COMPONENTE_SPEC) {
            Object prop = spec.getPropriedade(col);
            if (prop != null) {
                values.put(col,prop.toString());
            }
        }

        String tabela = DatabaseHelper.TABLE_COMPONENTE;

        long id=db.insert(tabela,null,values);
        db.close();
        return id;

    }

    /**
     * Metodo que relaciona um obejto da classe Projeto cadastrado aos objetos da classe Componente que
     * o compõem relacionando-os pelo id.
     * @param projeto Objeto da classe projeto com uma lista de componente que o compõem.
     *
     */

    public void vincularProjetoComponentes(Projeto projeto){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String idProjetoColumn = DatabaseHelper.COLUMN_PROJETO_ID;
        String idComponenteColumn = DatabaseHelper.COLUMN_COMPONENTE_ID;
        String quantidadeColumn = DatabaseHelper.COLUMN_QUANTIDADE;
        String tabela = DatabaseHelper.TABLE_COMPONENTE_PROJETO;

        ArrayList<ComponenteQuantidade> componentes = projeto.getComponentes();
        long idProjeto = projeto.getId();

        for (ComponenteQuantidade comp : componentes){

            long idComponente = comp.getComponente().getId();
            int quantidade = comp.getQuantidade();

            values.put(idProjetoColumn, idProjeto);
            values.put(idComponenteColumn, idComponente);
            values.put(quantidadeColumn, quantidade);

            long id = db.insert(tabela, null, values);

        }

        db.close();

    }

    /**
     * Metodo que retorna uma lista de todos os componentes cadastrado no banco de dados.
     *
     * @return Retorna uma lista de obejtos Componente.
     */

    public List<Componente> getTodosComponentes() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE;

        Cursor cursor = db.rawQuery(comando, null);

        ArrayList<Componente> listaComponentes = new ArrayList<>();

        while (cursor.moveToNext()) {

            String idColumn= DatabaseHelper.COLUMN_ID;
            int indexColumnId = cursor.getColumnIndex(idColumn);
            long id = cursor.getLong(indexColumnId);

            Componente componente = getComponente(id);

            listaComponentes.add(componente);
        }
        cursor.close();
        db.close();

        return listaComponentes;
    }

    /**
     * Metodo que busca e retorna um obejto da classe Componente por meio do id.
     * @param idComponente Id do componente a ser buscado.
     * @return Retorna um obejto da classe Componente caso exista no banco de dados caso contrario retorna null.
     */

    public Componente getComponente(long idComponente) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE +
                " WHERE " + DatabaseHelper.COLUMN_ID + " LIKE ?";

        String idString = Long.toString(idComponente);

        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Componente componente = null;

        if (cursor.moveToNext()) {

            componente = criaComponente(cursor);
        }

        return componente;
    }

    /**
     * Metodo que busca e retorna uma lista de objetos da classe Componente caso uma string passada
     * como parametro seja encontrada em qualquer atributo do componente.
     * @param busca String a ser encontrada.
     * @return Retorna uma Lista de obejtos da classe Componente.
     */

    public List<Componente> buscaComponentes(String busca) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE +
                " WHERE " + DatabaseHelper.COLUMN_TIPO + " LIKE ? " +
                "OR "+ DatabaseHelper.COLUMN_COR + " LIKE ? " +
                "OR " + DatabaseHelper.COLUMN_CAPACITANCIA + " LIKE ? " +
                "OR " + DatabaseHelper.COLUMN_RESISTENCIA + " LIKE ?";

        String argumento = "%" + busca + "%";

        String[] argumentos = {argumento, argumento, argumento, argumento};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<Componente> listaComponentes = new ArrayList<>();

        while (cursor.moveToNext()) {

            Componente componente = criaComponente(cursor);

            listaComponentes.add(componente);

        }
        cursor.close();
        db.close();

        return listaComponentes;
    }

    /**
     * Metodo que busca e retorna uma lista de todos os objetos da classe Componente relacionado a um objeto da classe Projeto.
     *
     * @param idProjeto Id do projeto requerido.
     * @return Retorna uma lista dos objetos da classe Componente associado a um objeto da classe Projeto.
     *
     */

    public List<ComponenteQuantidade> getComponentesUnicoProjeto(long idProjeto){

        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE_PROJETO +
                " WHERE " + DatabaseHelper.COLUMN_PROJETO_ID + " LIKE ? ";

        String[] argumentos = {String.valueOf(idProjeto)};

        Cursor cursor = db.rawQuery(comando, argumentos);

        List<ComponenteQuantidade> componentes = new ArrayList<>();

        Componente componente;
        ComponenteQuantidade componenteQuantidade;

        while (cursor.moveToNext()) {

            String idComponenteColumn = DatabaseHelper.COLUMN_COMPONENTE_ID;
            int indexColumnComponenteId = cursor.getColumnIndex(idComponenteColumn);
            long idComponente = cursor.getLong(indexColumnComponenteId);

            String quantidadeColumn = DatabaseHelper.COLUMN_QUANTIDADE;
            int indexColumnQuantidade = cursor.getColumnIndex(quantidadeColumn);
            int quantidade = cursor.getInt(indexColumnQuantidade);

            componenteQuantidade = new ComponenteQuantidade();

            componente = getComponente(idComponente);
            componenteQuantidade.setComponente(componente);
            componenteQuantidade.setQuantidade(quantidade);

            componentes.add(componenteQuantidade);
        }
        cursor.close();
        db.close();

        return componentes;
    }

    /**
     * Metodo que busca o componente com o menor no banco de dados.
     * @param componente Componente a ser encontrado.
     * @return Retorna uma obejto da classe ComponenteLoja.
     * @see ComponenteLoja
     */

    public ComponenteLoja getMinimo(Componente componente) {
        long idComponente = componente.getId();

        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE_LOJA +
                " WHERE " + DatabaseHelper.COLUMN_COMPONENTE_ID + " LIKE ? " +
                "ORDER BY " + DatabaseHelper.COLUMN_PRECO + " ASC";

        String[] argumentos = {String.valueOf(idComponente)};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ComponenteLoja componenteLoja = null;

        if (cursor.moveToNext()) {

            componenteLoja = criaComponenteLoja(cursor);
        }
        cursor.close();
        db.close();

        return componenteLoja;
    }

    /**
     * Metodo que busca e retorna todos os objetoas da classe ComponenteLoja de um determinado objeto da classe Componente.
     * @param componente Objeto Compoenente a ser buscado.
     * @return Retorna uma lista de objetos da classe ComponenteLoja.
     * @see ComponenteLoja
     */

    public List<ComponenteLoja> getComponenteLojas(Componente componente) {
        long idComponente = componente.getId();

        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE_LOJA +
                " WHERE " + DatabaseHelper.COLUMN_COMPONENTE_ID + " LIKE ? " +
                "ORDER BY " + DatabaseHelper.COLUMN_PRECO + " ASC";

        String[] argumentos = {String.valueOf(idComponente)};

        Cursor cursor = db.rawQuery(comando, argumentos);


        ComponenteLoja componenteLoja = null;

        ArrayList<ComponenteLoja> listaComponenteLoja = new ArrayList<>();

        while (cursor.moveToNext()) {

            componenteLoja = criaComponenteLoja(cursor);

            listaComponenteLoja.add(componenteLoja);
        }
        cursor.close();
        db.close();

        return listaComponenteLoja;
    }

    /**
     * Cria um objeto da classe Componente a partir de um cursor.
     *
     * @param cursor Cursor que vai percorrer as colunas da tabela.
     * @return Retorna um objeto da classe Componente.
     */

    public Componente criaComponente(Cursor cursor){

        String idColumn= DatabaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long idComponente = cursor.getLong(indexColumnID);

        String tipoColumn = DatabaseHelper.COLUMN_TIPO;
        int indexColumnTipo = cursor.getColumnIndex(tipoColumn);
        String tipo = cursor.getString(indexColumnTipo);

        String corColumn = DatabaseHelper.COLUMN_COR;
        int indexColumnCor = cursor.getColumnIndex(corColumn);
        String cor = cursor.getString(indexColumnCor);

        String capacitanciaColumn = DatabaseHelper.COLUMN_CAPACITANCIA;
        int indexColumnCapacitancia = cursor.getColumnIndex(capacitanciaColumn);
        String capacitancia = cursor.getString(indexColumnCapacitancia);

        String resistenciaColumn = DatabaseHelper.COLUMN_RESISTENCIA;
        int indexColumnResistencia = cursor.getColumnIndex(resistenciaColumn);
        String resistencia = cursor.getString(indexColumnResistencia);

        Map propriedades = new HashMap();

        if (Objects.equals(tipo, "resistor")) {

            propriedades.put("tipo", ComponenteEnum.ComponenteTipo.RESISTOR.getNome());

            if (Objects.equals(resistencia, "330R")) {
                propriedades.put("resistencia", ComponenteEnum.Resistencia.R330.getNome());
            }

            else if (Objects.equals(resistencia, "220R")) {
                propriedades.put("resistencia", ComponenteEnum.Resistencia.R220.getNome());
            }

        } else if (Objects.equals(tipo, "led")) {

            propriedades.put("tipo", ComponenteEnum.ComponenteTipo.LED.getNome());

            if (Objects.equals(cor, "verde")) {
                propriedades.put("cor", ComponenteEnum.Cor.VERDE.getNome());
            }

            else if(Objects.equals(cor, "vermelho")) {
                propriedades.put("cor", ComponenteEnum.Cor.VERMELHO.getNome());
            }

        } else if (Objects.equals(tipo, "capacitor")) {

            propriedades.put("tipo", ComponenteEnum.ComponenteTipo.CAPACITOR.getNome());

            if (Objects.equals(capacitancia, "100uF")) {
                propriedades.put("capacitancia", ComponenteEnum.Capacitancia.UF100.getNome());
            }

            else if (Objects.equals(capacitancia, "1uF")) {
                propriedades.put("capacitancia", ComponenteEnum.Capacitancia.UF1.getNome());
            }

        }

        ComponenteEspc compSpec = new ComponenteEspc(propriedades);

        Componente componente = new Componente();
        componente.setComponenteEspc(compSpec);
        componente.setId(idComponente);

        return componente;

    }

    /**
     * Cria um objeto da classe ComponenteLoja a partir de um cursor
     *
     * @param cursor Cursor que vai percorrer as colunas da tabela
     * @return Retorna um obejto da classe CompoeneteLoja.
     * @see LojaDAO
     */

    public ComponenteLoja criaComponenteLoja(Cursor cursor){

        String idColumn = DatabaseHelper.COLUMN_ID;
        int indexColumnId = cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnId);

        String idLojaColumn = DatabaseHelper.COLUMN_LOJA_ID;
        int indexColumnLojaId = cursor.getColumnIndex(idLojaColumn);
        long idLoja = cursor.getLong(indexColumnLojaId);

        String idComponenteColumn = DatabaseHelper.COLUMN_COMPONENTE_ID;
        int indexColumnComponenteId = cursor.getColumnIndex(idComponenteColumn);
        long idComponente = cursor.getLong(indexColumnComponenteId);

        String precoColumn = DatabaseHelper.COLUMN_PRECO;
        int indexColumnPreco = cursor.getColumnIndex(precoColumn);
        double preco = cursor.getDouble(indexColumnPreco);

        Loja loja = lojaDAO.getLoja(idLoja);

        Componente componente = getComponente(idComponente);

        ComponenteLoja componenteLoja = new ComponenteLoja();
        componenteLoja.setId(id);
        componenteLoja.setComponente(componente);
        componenteLoja.setLoja(loja);
        componenteLoja.setPreco(preco);

        return componenteLoja;

    }


}




