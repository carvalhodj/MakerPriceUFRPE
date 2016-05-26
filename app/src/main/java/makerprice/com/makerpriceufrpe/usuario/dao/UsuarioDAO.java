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

        String comando = "SELECT * FROM " + DatabaseHelper.getTableUser() +
                " WHERE " + DatabaseHelper.getColumnEmail() + " LIKE ? AND " +
                DatabaseHelper.getColumnSenha() + " LIKE ?";

        String[] argumentos = {email, senha};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {
            String nome = cursor.getString(
                    cursor.getColumnIndex(
                            DatabaseHelper.getColumnNome()));
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

        String comando = "SELECT * FROM " + DatabaseHelper.getTableUser() +
                " WHERE " + DatabaseHelper.getColumnEmail() + " LIKE ?";

        String[] argumentos = {email};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Usuario usuario = null;

        if (cursor.moveToNext()) {

            String nameColumn= DatabaseHelper.getColumnNome();
            String senhaColumn= DatabaseHelper.getColumnSenha();
            int indexColumnName= cursor.getColumnIndex(nameColumn);
            int indexColumnSenha= cursor.getColumnIndex(senhaColumn);


            String nome = cursor.getString(indexColumnName);
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
        values.put(DatabaseHelper.getColumnEmail(), usuario.getEmail());
        values.put(DatabaseHelper.getColumnNome(), usuario.getName());
        values.put(DatabaseHelper.getColumnSenha(), usuario.getPass());

        long id = db.insert(DatabaseHelper.getTableUser(), null, values);

        db.close();
        return id;

    }

}
