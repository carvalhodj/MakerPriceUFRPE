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
        helper = new DatabaseHelper(context, "usuarioLoja");
    }

    public Loja getLoja(String email, String senha) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.getTableUserLoja() +
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
            loja.getPessoaJuridica().setEmail(email);
            loja.getPessoaJuridica().setName(nome);
            loja.getPessoaJuridica().setPass(senha);
        }
        cursor.close();
        db.close();

        return loja;
    }

    public Loja getLoja(String email){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.getTableUserLoja() +
                " WHERE " + DatabaseHelper.getColumnEmail() + " LIKE ?";

        String[] argumentos = {email};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            String nameColumn= DatabaseHelper.getColumnNome();
            String senhaColumn= DatabaseHelper.getColumnSenha();
            int indexColumnName= cursor.getColumnIndex(nameColumn);
            int indexColumnSenha= cursor.getColumnIndex(senhaColumn);


            String nome = cursor.getString(indexColumnName);
            String senha = cursor.getString(indexColumnSenha);

            loja = new Loja();
            loja.getPessoaJuridica().setEmail(email);
            loja.getPessoaJuridica().setName(nome);
            loja.getPessoaJuridica().setPass(senha);
        }
        cursor.close();
        db.close();

        return loja;
    }

    public long inserir(Loja loja){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.getColumnEmail(), loja.getPessoaJuridica().getEmail());
        values.put(DatabaseHelper.getColumnNome(), loja.getPessoaJuridica().getName());
        values.put(DatabaseHelper.getColumnSenha(), loja.getPessoaJuridica().getPass());

        long id = db.insert(DatabaseHelper.getTableUser(), null, values);

        db.close();
        return id;

    }

}


