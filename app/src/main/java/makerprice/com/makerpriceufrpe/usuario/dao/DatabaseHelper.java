package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "makerprice.db";

    private static final String TABLE_NAME = "usuario";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";

    SQLiteDatabase db;

    private static final String TABLE_USUARIO_CREATE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY NOT NULL , " +
            COLUMN_NAME+" TEXT NOT NULL , "+
            COLUMN_EMAIL+" TEXT NOT NULL , "+
            COLUMN_PASS+" TEXT NOT NULL"+")";

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USUARIO_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void inserirUsuario(Usuario usuario) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, usuario.getName());
        values.put(COLUMN_EMAIL, usuario.getEmail());
        values.put(COLUMN_PASS, usuario.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();
        cursor.close();
    }

    public String procurarSenha(String usuario) {
        db = this.getReadableDatabase();

        String query = "SELECT "+COLUMN_EMAIL+", "+COLUMN_PASS+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String nomeUsuario, passUsuario;
        passUsuario = "Não encontrado";
        if (cursor.moveToFirst()){
            do {
                nomeUsuario = cursor.getString(0);

                if (nomeUsuario.equals(usuario)) {
                    passUsuario = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return passUsuario;
    }

    /*public String nomeUsuario(String email){
        db = this.getReadableDatabase();

        String query = "SELECT "+COLUMN_EMAIL+", "+COLUMN_NAME+" FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String nomeUsuario, emailUsuario;
        nomeUsuario = "";
        if (cursor.moveToFirst()){
            do{
                emailUsuario = cursor.getString(0);

                if(emailUsuario.equals(email)){
                    nomeUsuario = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        return nomeUsuario;
    }*/
}
