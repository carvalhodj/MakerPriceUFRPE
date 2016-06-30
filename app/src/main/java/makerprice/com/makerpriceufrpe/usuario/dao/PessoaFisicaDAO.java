package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class PessoaFisicaDAO {
    private DatabaseHelper helper;
    private UsuarioDAO usuarioDAO;
    //

    public PessoaFisicaDAO(Context context) {
        helper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public PessoaFisica getPessoaFisica(long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_PESSOA_FISICA +
                " WHERE " + DatabaseHelper.COLUMN_ID + " LIKE ?";


        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        PessoaFisica pessoaFisica = null;

        if (cursor.moveToNext()) {

            pessoaFisica = criaPessoaFisica(cursor);

        }
        cursor.close();
        db.close();

        return pessoaFisica;
    }

    public PessoaFisica getPessoaFisica(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_PESSOA_FISICA +
                " WHERE " + DatabaseHelper.COLUMN_USUARIO_ID + " LIKE ?";

        long idUsuario = usuario.getID();
        String idUsuarioString = Long.toString(idUsuario);
        String[] argumentos = {idUsuarioString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        PessoaFisica pessoaFisica = null;

        if (cursor.moveToNext()) {

            pessoaFisica = criaPessoaFisica(cursor);
        }
        cursor.close();
        db.close();

        return pessoaFisica;
    }

    public long inserir(PessoaFisica pessoaFisica){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        String nameColumn = DatabaseHelper.COLUMN_NAME;
        String nome = pessoaFisica.getNome();

        Usuario usuario = pessoaFisica.getUsuario();
        String usuarioIDColumn = DatabaseHelper.COLUMN_USUARIO_ID;
        long idUsuario = usuario.getID();

        values.put(nameColumn, nome);
        values.put(usuarioIDColumn, idUsuario);

        String tabela = DatabaseHelper.TABLE_PESSOA_FISICA;

        long id = db.insert(tabela, null, values);

        db.close();
        return id;

    }

    public PessoaFisica criaPessoaFisica(Cursor cursor){

        String idColumn= DatabaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnID);

        String nomeColumn= DatabaseHelper.COLUMN_NAME;
        int indexColumnNome= cursor.getColumnIndex(nomeColumn);
        String nome = cursor.getString(indexColumnNome);

        String usuarioIDColumn= DatabaseHelper.COLUMN_USUARIO_ID;
        int indexColumnUsuarioID= cursor.getColumnIndex(usuarioIDColumn);
        long idUsuario = cursor.getLong(indexColumnUsuarioID);

        Usuario usuario = usuarioDAO.getUsuario(idUsuario);

        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setID(id);
        pessoaFisica.setNome(nome);
        pessoaFisica.setUsuario(usuario);

        return pessoaFisica;

    }
}
