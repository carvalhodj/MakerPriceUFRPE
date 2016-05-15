package makerprice.com.makerpriceufrpe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by d3jota on 15/05/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY NOT NULL , " +
            COLUMN_NAME+" TEXT NOT NULL , "+
            COLUMN_EMAIL+" TEXT NOT NULL , "+
            COLUMN_PASS+" TEXT NOT NULL"+")";

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void inserirUsuario(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String usuario) {
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

        return passUsuario;
    }
}
