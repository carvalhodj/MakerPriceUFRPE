package makerprice.com.makerpriceufrpe.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 *  Classe de persistencia para classe Loja
 */

public class LojaDAO {

    private DatabaseHelper helper;
    private UsuarioDAO usuarioDAO;

    /**
     * Construtor
     * @param context
     */

    public LojaDAO(Context context) {
        helper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    /**
     * Metodo que busca e retorna uma Loja na tabela TABLE_LOJA pelo deu Id.
     *
     * @param id Id da loja a ser buscada.
     * @return Retorna um objeto da classe Loja.
     */

    public Loja getLoja(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER_LOJA +
                " WHERE " + DatabaseHelper.COLUMN_ID + " LIKE ?";


        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            loja = criaLoja(cursor);
        }
        cursor.close();
        db.close();

        return loja;
    }

    /**
     *
     * Metodo que busca e retorna uma Loja na tabela TABLE_LOJA passando um Usuario associado.
     *
     * @param usuario Usuario associado a Loja.
     * @return Retorna um obejto da classe Loja.
     */

    public Loja getLoja(Usuario usuario){
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER_LOJA +
                " WHERE " + DatabaseHelper.COLUMN_USUARIO_ID + " LIKE ?";

        long idUsuario = usuario.getID();
        String idUsuarioString = Long.toString(idUsuario);
        String[] argumentos = {idUsuarioString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

            loja = criaLoja(cursor);

        }
        cursor.close();
        db.close();


        return loja;


    }

    /**
     * Metodo que insere uma objeto da classe Loja na tabela TABLE_LOJA do banco de dados.
     *
     * @param loja Objeto da classe Loja a ser inserido.
     * @return Id do objeto Loja inserido.
     */

    public long inserir(Loja loja){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values=new ContentValues();

        String nomeColumn=DatabaseHelper.COLUMN_NAME;
        String nome=loja.getNome();

        String cnpjColumn=DatabaseHelper.COLUMN_CNPJ;
        String cnpj=loja.getCnpj();

        String usuarioIdColumn=DatabaseHelper.COLUMN_USUARIO_ID;
        Usuario usuario=loja.getUsuario();
        long idUsario=usuario.getID();

        values.put(nomeColumn,nome);
        values.put(cnpjColumn,cnpj);
        values.put(usuarioIdColumn,idUsario);

        String tabela=DatabaseHelper.TABLE_USER_LOJA;

        long id=db.insert(tabela,null,values);
        db.close();
        return id;

    }

    /**
     * Metodo que cria e retorna um objeto da classe Loja a partir de um objeto cursor.
     * @param cursor Curso que aponta para a tabela TABLE_LOJA.
     * @return Retorna um objeto da classe Loja.
     */

    public Loja criaLoja(Cursor cursor){

        String idColumn= DatabaseHelper.COLUMN_ID;
        int indexColumnID= cursor.getColumnIndex(idColumn);
        long id = cursor.getLong(indexColumnID);

        String nomeColumn= DatabaseHelper.COLUMN_NAME;
        int indexColumnNome= cursor.getColumnIndex(nomeColumn);
        String nome = cursor.getString(indexColumnNome);

        String usuarioIDColumn= DatabaseHelper.COLUMN_USUARIO_ID;
        int indexColumnLojaID= cursor.getColumnIndex(usuarioIDColumn);
        long idUsuario = cursor.getLong(indexColumnLojaID);

        String cnpjColumn= DatabaseHelper.COLUMN_CNPJ;
        int indexColumnCnpj= cursor.getColumnIndex(cnpjColumn);
        String cnpj = cursor.getString(indexColumnCnpj);

        Usuario usuario = usuarioDAO.getUsuario(idUsuario);

        Loja loja = new Loja();
        loja.setId(id);
        loja.setNome(nome);
        loja.setCnpj(cnpj);
        loja.setUsuario(usuario);

        return loja;

    }

}