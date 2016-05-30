package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


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

            String nameColumn = DatabaseHelper.COLUMN_NAME;
            int indexColumnName= cursor.getColumnIndex(nameColumn);

            String nome = cursor.getString(indexColumnName);

            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setName(nome);
            usuario.setPass(senha);
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

            String nameColumn= DatabaseHelper.COLUMN_NAME;
            int indexColumnName= cursor.getColumnIndex(nameColumn);
            String nome = cursor.getString(indexColumnName);

            String senhaColumn= DatabaseHelper.COLUMN_PASS;
            int indexColumnSenha= cursor.getColumnIndex(senhaColumn);
            String senha = cursor.getString(indexColumnSenha);

            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setName(nome);
            usuario.setPass(senha);
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

        String nameColumn = DatabaseHelper.COLUMN_NAME;
        String nome = usuario.getName();

        String senhaColumn = DatabaseHelper.COLUMN_PASS;
        String senha = usuario.getPass();


        values.put(emailColumn, email);
        values.put(nameColumn, nome);
        values.put(senhaColumn, senha);

        String tabela = DatabaseHelper.TABLE_USER;

        long id = db.insert(tabela, null, values);

        db.close();
        return id;

    }

}
