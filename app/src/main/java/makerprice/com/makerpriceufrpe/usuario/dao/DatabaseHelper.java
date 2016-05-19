package makerprice.com.makerpriceufrpe.usuario.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import makerprice.com.makerpriceufrpe.usuario.dominio.Usuario;

/**
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "makerprice.db";

    private static final String TABLE_USER = "usuario";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_USER + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_PASS + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_USER;
        db.execSQL(query);
        this.onCreate(db);
    }

    public static String getColumnEmail(){
        return COLUMN_EMAIL;
    }
    public static String getColumnNome(){
        return COLUMN_NAME;
    }
    public static String getColumnSenha(){
        return COLUMN_PASS;
    }
    public static String getColumnID(){
        return COLUMN_ID;
    }

    public static String getTableUser(){
        return TABLE_USER;
    }
}