package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.usuario.dominio.PessoaFisica;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 * Classe de persistencia de dados da classe PessoaFisica.
 */

public class PessoaFisicaDAO {
    private DatabaseHelper helper;
    private UsuarioDAO usuarioDAO;
    //

    /**
     * Construtor
     *
     * @param context
     */

    public PessoaFisicaDAO(Context context) {
        helper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    /**
     * Metodo que busca uma Pessoa Fisica na tabela TABLE_PESSOA_FISICA do banco de dados
     *
     * @param id Id da PessoaFisica a ser encontrada.
     * @return Retorna um objeto da classe PessoaFisica.
     * @see UsuarioDAO
     */

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

    /**
     * Busca e retorna um objeto da classe PessoaFisica na tabela TABLE_PESSOA_FISICA passando um objeto da classe Usuario.
     *
     * @param usuario Objeto Usuario a ser buscado.
     * @return Retorna um objeto da classe PessoaFisica caso exista, caso contrario retorna null.
     */

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

    /**
     *
     * Insere um obejto da classe PessoaFisica na tabela TABLE_PESSOA_FISICA do banco de dados.
     *
     * @param pessoaFisica Objeto da classe PessoaFisica a ser inserido.
     * @return Retorna o id da PessoaFisica Inserida.
     */

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

    /**
     * Cria um objeto PessoaFisica a partir de um cursor
     *
     * @param cursor Cursor que vai percorrer as colunas da tabela
     * @return Retorna um obejto PessoaFisica
     */

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
