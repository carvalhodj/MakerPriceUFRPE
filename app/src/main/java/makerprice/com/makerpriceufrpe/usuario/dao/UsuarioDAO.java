package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/***
 * Classe de Persistencia para Usuario
 *
 * @author Equipe MakerPrice
 *
 */

public class UsuarioDAO {
    private DatabaseHelper helper;

    /**
     * Construtor
     *
     * @param context Contexto do Usuario
     */

    public UsuarioDAO(Context context) {
        helper = new DatabaseHelper(context);
    }

    /**
     * Busca um Usuario na tabela TABLE_USER do banco de dados
     *
     * @param email Email do Usuario a ser buscado no Banco de dados.
     * @param senha Senha do Usurio a ser buscada no Banco de dados.
     * @return Retorna um objeto da Classe Usuario caso exista um Usuario cadastrado ou Null caso não exista.
     */

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

    /**
     * Busca um Usuario na tabela TABLE_USER do banco de dados
     *
     * @param email Email do Usuario a ser buscado no Banco de dados.
     * @return Retorna um objeto da Classe Usuario caso exista um Usuario cadastrado ou Null caso não exista.
     */

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

    /**
     * Busca o Usuario na tabela TABLE_USER no Banco de dados atravez de seu ID
     *
     * @param id Id do Usuario a ser buscado
     * @return Retorna um objeto da Classe Usuario caso exista um Usuario cadastrado ou Null caso não exista.
     */

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

    /**
     * Insere um novo Usuario na tabela TABLE_USER do Banco de dados caso não exista um Usuario com e-mail  identico cadastrado.
     *
     * @param usuario Passa uma objeto do tipo Usuario.
     * @return ??
     */

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

    /**
     * Cria um objeto Usuario a partir de um cursor
     *
     * @param cursor Cursor que vai percorrer as colunas da tabela
     * @return Retorna um Usuario
     */

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
