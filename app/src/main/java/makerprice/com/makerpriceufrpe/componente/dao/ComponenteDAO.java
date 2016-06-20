package makerprice.com.makerpriceufrpe.componente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEnum;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEspc;
import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ComponenteDAO {

    private DatabaseHelper helper;

    public ComponenteDAO(Context context) {
        helper = new DatabaseHelper(context);

    }

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

    public Componente getComponente(long idComponente) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE +
                " WHERE " + DatabaseHelper.COLUMN_ID + " LIKE ?";

        String idString = Long.toString(idComponente);

        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Componente componente = null;

        if (cursor.moveToNext()) {

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
                propriedades.put("resistencia", ComponenteEnum.Resistencia.R330.getNome());

            } else if (Objects.equals(tipo, "led")) {

                propriedades.put("tipo", ComponenteEnum.ComponenteTipo.LED.getNome());
                propriedades.put("cor", ComponenteEnum.Cor.VERDE.getNome());

            } else if (Objects.equals(tipo, "capacitor")) {

                propriedades.put("tipo", ComponenteEnum.ComponenteTipo.CAPACITOR.getNome());
                propriedades.put("capacitancia", ComponenteEnum.Capacitancia.UF100.getNome());

            }

            ComponenteEspc compSpec = new ComponenteEspc(propriedades);

            componente = new Componente();
            componente.setComponenteEspc(compSpec);
            componente.setId(idComponente);

        }

        return componente;
    }

    public List<Componente> getTodosComponentesProjeto(long idProjeto){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE_PROJETO +
                " WHERE " + DatabaseHelper.COLUMN_PROJETO_ID + " LIKE ?";

        String idString = Long.toString(idProjeto);

        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        ArrayList<Componente> listaComponentesProjeto = new ArrayList<>();

        String idComponenteColumn = DatabaseHelper.COLUMN_COMPONENTE_ID;
        int indexColumnComponente = cursor.getColumnIndex(idComponenteColumn);

        while (cursor.moveToNext()) {

            long id = cursor.getLong(indexColumnComponente);

            Componente componente = getComponente(id);

            listaComponentesProjeto.add(componente);

        }
        cursor.close();
        db.close();

        return listaComponentesProjeto;

    }

    public void vincularProjetoComponentes(Projeto projeto){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String idProjetoColumn = DatabaseHelper.COLUMN_PROJETO_ID;
        String idComponenteColumn = DatabaseHelper.COLUMN_COMPONENTE_ID;
        String tabela = DatabaseHelper.TABLE_COMPONENTE_PROJETO;

        ArrayList<Componente> componentes = projeto.getComponentes();
        long idProjeto = projeto.getId();

        for (Componente comp : componentes){

            long idComponente = comp.getId();

            values.put(idProjetoColumn, idProjeto);
            values.put(idComponenteColumn, idComponente);

            long id = db.insert(tabela, null, values);

        }

        db.close();

    }

    public List<Componente> getComponentesUnicoProjeto(long idProjeto){

        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE_PROJETO +
                " WHERE " + DatabaseHelper.COLUMN_PROJETO_ID + " LIKE ? ";

        String[] argumentos = {String.valueOf(idProjeto)};

        Cursor cursor = db.rawQuery(comando, argumentos);

        List<Componente> componentes = new ArrayList<>();
        Componente componente;

        while (cursor.moveToNext()) {

            String idComponenteColumn = DatabaseHelper.COLUMN_COMPONENTE_ID;
            int indexColumnComponenteId = cursor.getColumnIndex(idComponenteColumn);
            long idComponente = cursor.getLong(indexColumnComponenteId);

            componente = getComponente(idComponente);

            componentes.add(componente);
        }
        cursor.close();
        db.close();

        return componentes;
    }
}




