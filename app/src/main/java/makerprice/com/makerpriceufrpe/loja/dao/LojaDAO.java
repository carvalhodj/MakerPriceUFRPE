package makerprice.com.makerpriceufrpe.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import makerprice.com.makerpriceufrpe.infra.DatabaseHelper;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;
import makerprice.com.makerpriceufrpe.usuario.dao.UsuarioDAO;
import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

public class LojaDAO {

    private DatabaseHelper helper;
    private UsuarioDAO usuarioDAO;

    public LojaDAO(Context context) {
        helper = new DatabaseHelper(context);
        usuarioDAO = new UsuarioDAO(context);
    }

    public Loja getLoja(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String comando = "SELECT * FROM " + DatabaseHelper.TABLE_USER_LOJA +
                " WHERE " + DatabaseHelper.COLUMN_ID + " LIKE ?";


        String idString = Long.toString(id);
        String[] argumentos = {idString};

        Cursor cursor = db.rawQuery(comando, argumentos);

        Loja loja = null;

        if (cursor.moveToNext()) {

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

            loja = new Loja();
            loja.setId(id);
            loja.setNome(nome);
            loja.setCnpj(cnpj);
            loja.setUsuario(usuario);
        }
        cursor.close();
        db.close();

        return loja;
    }

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
            String idColumn=DatabaseHelper.COLUMN_ID;
            int indexColumnId=cursor.getColumnIndex(idColumn);
            int idLoja=cursor.getInt(indexColumnId);

            String nomeColumn= DatabaseHelper.COLUMN_NAME;
            int indexColumnNome= cursor.getColumnIndex(nomeColumn);
            String nome = cursor.getString(indexColumnNome);

            String cnpjColumn= DatabaseHelper.COLUMN_CNPJ;
            int indexColumnCnpj= cursor.getColumnIndex(cnpjColumn);
            String cnpj = cursor.getString(indexColumnCnpj);


            loja = new Loja();
            loja.setId(idLoja);
            loja.setNome(nome);
            loja.setCnpj(cnpj);
            loja.setUsuario(usuario);
        }
        cursor.close();
        db.close();


        return loja;


    }

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

}