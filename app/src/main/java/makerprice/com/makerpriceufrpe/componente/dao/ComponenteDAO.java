package makerprice.com.makerpriceufrpe.componente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Objects;

import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEspc;
import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;

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
     /*
        String tipoColumn = DatabaseHelper.COLUMN_TIPO;
        String tipo = componente.getComponenteEspc().getPropriedade("tipo").toString();

        String corColumn = DatabaseHelper.COLUMN_COR;

        String resistenciaColumn = DatabaseHelper.COLUMN_RESISTENCIA;
        String resistencia = null;
        if (componente.getComponenteEspc().getPropriedade("resistencia") != null) {
            resistencia = componente.getComponenteEspc().getPropriedade("resistencia").toString();
        }

        values.put(corColumn,cor);
        values.put(resistenciaColumn,resistencia);
*/
        String tabela = DatabaseHelper.TABLE_COMPONENTE;

        long id=db.insert(tabela,null,values);
        db.close();
        return id;

    }

    public ArrayList<String> getTodosComponentesString() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_COMPONENTE;

        Cursor cursor = db.rawQuery(comando, null);

        ArrayList<String> listaComponentes = new ArrayList<String>();

        while (cursor.moveToNext()) {

            String tipoColumn= DatabaseHelper.COLUMN_TIPO;
            int indexColumnTipo = cursor.getColumnIndex(tipoColumn);
            String tipo = cursor.getString(indexColumnTipo);

            String corColumn= DatabaseHelper.COLUMN_COR;
            int indexColumnCor= cursor.getColumnIndex(corColumn);
            String cor = cursor.getString(indexColumnCor);

            String resistenciaColumn= DatabaseHelper.COLUMN_RESISTENCIA;
            int indexColumnResistencia= cursor.getColumnIndex(resistenciaColumn);
            String resistencia = cursor.getString(indexColumnResistencia);

            String itemComponente = null;

            if (Objects.equals(tipo, "resistor")) {
                itemComponente = tipo + " " + resistencia;
            } else if (Objects.equals(tipo, "led")) {
                itemComponente = tipo + " " + cor;
            }

            listaComponentes.add(itemComponente);
        }
        cursor.close();
        db.close();

        return listaComponentes;
    }



}
