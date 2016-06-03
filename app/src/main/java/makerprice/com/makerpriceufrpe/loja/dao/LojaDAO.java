package makerprice.com.makerpriceufrpe.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class LojaDAO {

    private DatabaseHelper helper;

    public LojaDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    public Loja getLoja(String email, String senha) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER_LOJA +
                " WHERE " + DatabaseHelper.getColumnEmail() + " LIKE ? AND " +
                DatabaseHelper.getColumnSenha() + " LIKE ?";

        String[] argumentos = {email, senha};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            String nameColumn= DatabaseHelper.getColumnNome();
            int indexColumnName= cursor.getColumnIndex(nameColumn);

            String nome = cursor.getString(indexColumnName);

            loja = new Loja();
            loja.getUsuario().setEmail(email);
            loja.getUsuario().setPass(senha);
        }
        cursor.close();
        db.close();

        return loja;
    }

    public Loja getLoja(String email){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER_LOJA +
                " WHERE " + DatabaseHelper.getColumnEmail() + " LIKE ?";

        String[] argumentos = {email};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            String nameColumn= DatabaseHelper.COLUMN_NAME;
            int indexColumnName= cursor.getColumnIndex(nameColumn);
            String nome = cursor.getString(indexColumnName);

            String senhaColumn= DatabaseHelper.COLUMN_PASS;
            int indexColumnSenha= cursor.getColumnIndex(senhaColumn);
            String senha = cursor.getString(indexColumnSenha);

            loja = new Loja();
            loja.getUsuario().setEmail(email);
            loja.getUsuario().setPass(senha);

        }
        cursor.close();
        db.close();

        return loja;
    }

    public long inserir(Loja loja){
        SQLiteDatabase db = helper.getWritableDatabase();

        String email,name,pass,cnpj,ajson,imagem;

        name = loja.getNome();
        email=loja.getUsuario().getEmail();
        pass=loja.getUsuario().getPass();
        cnpj=loja.getCnpj();
        ajson=loja.getBancoJson();
        imagem=loja.getImagem();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.getColumnEmail(), email);
        values.put(DatabaseHelper.getColumnNome(), name);
        values.put(DatabaseHelper.getColumnSenha(), pass);
        values.put(DatabaseHelper.getColumnCnpj(), cnpj);
        //values.put(DatabaseHelper.getColumnAjson(), ajson);
        //values.put(DatabaseHelper.getColumnLinkimagem(), imagem);

        long id = db.insert(DatabaseHelper.TABLE_USER_LOJA, null, values);

        db.close();
        return id;

    }

}


