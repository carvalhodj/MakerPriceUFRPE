package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class UsuarioDAO {
    private DatabaseHelper helper;

    public UsuarioDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    public Usuario getUsuario(String email, String senha) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER +
                " WHERE " + DatabaseHelper.COLUMN_EMAIL + " LIKE ? AND " +
                DatabaseHelper.COLUMN_PASS + " LIKE ?";

        String[] argumentos = {email, senha};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {
            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();

        return usuario;
    }

    public Usuario getUsuario(String email){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER +
                " WHERE " + DatabaseHelper.COLUMN_EMAIL + " LIKE ?";

        String[] argumentos = {email};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {

            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();

        return usuario;
    }

    public Usuario getUsuario(long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER +
                " WHERE " + DatabaseHelper.COLUMN_ID + " LIKE ?";

        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {

            usuario = criaUsuario(cursor);
        }
        cursor.close();
        db.close();

        return usuario;

    }

    public long inserir(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String emailColumn = DatabaseHelper.COLUMN_EMAIL;
        String email = usuario.getEmail();

        String senhaColumn = DatabaseHelper.COLUMN_PASS;
        String senha = usuario.getPass();

        values.put(emailColumn, email);
        values.put(senhaColumn, senha);

        String tabela = DatabaseHelper.TABLE_USER;

        long id = db.insert(tabela, null, values);

        db.close();
        return id;

    }

    public Usuario criaUsuario(Cursor cursor){

        String idColumn= DatabaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnID);

        String emailColumn= DatabaseHelper.COLUMN_EMAIL;
        int indexColumnEmail= cursor.getColumnIndex(emailColumn);
        String email = cursor.getString(indexColumnEmail);

        String senhaColumn= DatabaseHelper.COLUMN_PASS;
        int indexColumnSenha= cursor.getColumnIndex(senhaColumn);
        String senha = cursor.getString(indexColumnSenha);

        Usuario usuario = new Usuario();
        usuario.setID(id);
        usuario.setEmail(email);
        usuario.setPass(senha);

        return usuario;

    }
}
