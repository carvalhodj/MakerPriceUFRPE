package makerprice.com.makerpriceufrpe.Componente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.Componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class ComponenteDAO {

    private DatabaseHelper helper;

    public ComponenteDAO(Context context) {
        helper = new DatabaseHelper(context);

    }

    public long inserir(Componente componente){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String tipoColumn = DatabaseHelper.COLUMN_TIPO;
        String tipo = componente.getComponenteEspc().getPropriedade("tipo").toString();

        String corColumn = DatabaseHelper.COLUMN_COR;
        String cor = null;
        if (componente.getComponenteEspc().getPropriedade("cor") != null) {
            cor = componente.getComponenteEspc().getPropriedade("cor").toString();
        }

        String resistenciaColumn = DatabaseHelper.COLUMN_RESISTENCIA;
        String resistencia = componente.getComponenteEspc().getPropriedade("resistencia").toString();

        values.put(tipoColumn,tipo);
        values.put(corColumn,cor);
        values.put(resistenciaColumn,resistencia);

        String tabela = DatabaseHelper.TABLE_COMPONENTE;

        long id=db.insert(tabela,null,values);
        db.close();
        return id;

    }


}
