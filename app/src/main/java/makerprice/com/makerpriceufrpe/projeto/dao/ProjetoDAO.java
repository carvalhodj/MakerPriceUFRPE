package makerprice.com.makerpriceufrpe.projeto.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ProjetoDAO {
    private DatabaseHelper helper;

    public ProjetoDAO(Context context) { helper = new DatabaseHelper(context); }

    public long inserir(Projeto projeto){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String nameColumn = DatabaseHelper.COLUMN_NAME;
        String nome = projeto.getNome();

        String descricaoColumn = DatabaseHelper.COLUMN_DESCRICAO;
        String descricao = projeto.getDescricao();

        String plataformaColumn = DatabaseHelper.COLUMN_PLATAFORMA;
        String plataforma = projeto.getPlataforma();

        String aplicacaoColumn = DatabaseHelper.COLUMN_APLICACAO;
        String aplicacao = projeto.getAplicacao();

        String comp1Column = DatabaseHelper.COLUMN_COMP1;
        String comp1 = projeto.getComponente_1();

        String comp2Column = DatabaseHelper.COLUMN_COMP2;
        String comp2 = projeto.getComponente_2();

        String comp3Column = DatabaseHelper.COLUMN_COMP3;
        String comp3 = projeto.getComponente_3();


        values.put(nameColumn, nome);
        values.put(descricaoColumn, descricao);
        values.put(plataformaColumn, plataforma);
        values.put(aplicacaoColumn, aplicacao);
        values.put(comp1Column, comp1);
        values.put(comp2Column, comp2);
        values.put(comp3Column, comp3);

        String tabela = DatabaseHelper.TABLE_PROJETO;

        long id = db.insert(tabela, null, values);

        db.close();
        return id;
    }

    public Projeto getProjeto(String nome){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_PROJETO +
                " WHERE " + DatabaseHelper.COLUMN_NAME + " LIKE ?";

        String[] argumentos = {nome};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Projeto projeto = null;

        if (cursor.moveToNext()) {

            String descricaoColumn= DatabaseHelper.COLUMN_DESCRICAO;
            int indexColumnDescricao= cursor.getColumnIndex(descricaoColumn);
            String descricao = cursor.getString(indexColumnDescricao);

            String plataformaColumn= DatabaseHelper.COLUMN_PLATAFORMA;
            int indexColumnPlataforma= cursor.getColumnIndex(plataformaColumn);
            String plataforma = cursor.getString(indexColumnPlataforma);

            String aplicacaoColumn= DatabaseHelper.COLUMN_APLICACAO;
            int indexColumnAplicacao= cursor.getColumnIndex(aplicacaoColumn);
            String aplicacao = cursor.getString(indexColumnAplicacao);

            String comp1Column= DatabaseHelper.COLUMN_COMP1;
            int indexColumnComp1= cursor.getColumnIndex(comp1Column);
            String comp1 = cursor.getString(indexColumnComp1);

            String comp2Column = DatabaseHelper.COLUMN_COMP2;
            int indexColumnComp2= cursor.getColumnIndex(comp2Column);
            String comp2 = cursor.getString(indexColumnComp2);

            String comp3Column= DatabaseHelper.COLUMN_COMP3;
            int indexColumnComp3= cursor.getColumnIndex(comp3Column);
            String comp3 = cursor.getString(indexColumnComp3);

            projeto = new Projeto();
            projeto.setNome(nome);
            projeto.setDescricao(descricao);
            projeto.setPlataforma(plataforma);
            projeto.setAplicacao(aplicacao);
            projeto.setComponente_1(comp1);
            projeto.setComponente_2(comp2);
            projeto.setComponente_3(comp3);
        }
        cursor.close();
        db.close();

        return projeto;
    }

    public ArrayList<Projeto> getTodosProjetos(){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_PROJETO;

        Cursor cursor = db.rawQuery(comando, null);

        ArrayList<Projeto> listaProjetos = new ArrayList<>();

        while (cursor.moveToNext()) {

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

            String comp1Column= DatabaseHelper.COLUMN_COMP1;
            int indexColumnComp1= cursor.getColumnIndex(comp1Column);
            String comp1 = cursor.getString(indexColumnComp1);

            String comp2Column = DatabaseHelper.COLUMN_COMP2;
            int indexColumnComp2= cursor.getColumnIndex(comp2Column);
            String comp2 = cursor.getString(indexColumnComp2);

            String comp3Column= DatabaseHelper.COLUMN_COMP3;
            int indexColumnComp3= cursor.getColumnIndex(comp3Column);
            String comp3 = cursor.getString(indexColumnComp3);

            Projeto projeto = new Projeto();
            projeto.setNome(nome);
            projeto.setDescricao(descricao);
            projeto.setPlataforma(plataforma);
            projeto.setAplicacao(aplicacao);
            projeto.setComponente_1(comp1);
            projeto.setComponente_2(comp2);
            projeto.setComponente_3(comp3);

            listaProjetos.add(projeto);

        }
        cursor.close();
        db.close();

        return listaProjetos;
    }

}
