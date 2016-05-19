package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 * Created by J.Vitor on 19/05/2016.
 */
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

        Usuario usuario = new Usuario();
        usuario = null;
        if (cursor.moveToNext()) {
            String nome = cursor.getString(
                    cursor.getColumnIndex(
                            DatabaseHelper.getColumnNome()));

            usuario.setEmail(email);
            usuario.setName(nome);
            usuario.setPass(senha);
        }
        cursor.close();
        db.close();

        return usuario;
    }
}
