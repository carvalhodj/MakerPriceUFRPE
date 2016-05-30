package makerprice.com.makerpriceufrpe.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private String tabela;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "makerprice.db";


    public static final String TABLE_USER = "usuario";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";

    public static final String TABLE_USER_LOJA = "usuarioLoja";
    public static final String COLUMN_CNPJ = "cnpj";
    public static final String COLUMN_AJSON = "ajson";
    public static final String COLUMN_LINKIMAGEM = "linkImagem";


    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getTabela() {
        return this.tabela;
    }

    public String criarTabelaUsuario() {
        String ctu = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PASS + " TEXT NOT NULL);";
        return ctu;
    }

    public String criarTabelaUsuarioLoja() {
        String ctul = "CREATE TABLE " + TABLE_USER_LOJA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PASS + " TEXT NOT NULL);" +
                COLUMN_CNPJ + "TEXT NOT NULL)" +
                COLUMN_AJSON + "TEXT NOT NULL" +
                COLUMN_LINKIMAGEM + "TEXT NOT NULL);";
        return ctul;
    }


    public DatabaseHelper(Context context, String tabela) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        switch (tabela) {
            case "usuario":
                this.setTabela(this.criarTabelaUsuario());
                break;
            case "usuarioLoja":
                this.setTabela(this.criarTabelaUsuarioLoja());
                break;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.getTabela());
                /*
                (
                "CREATE TABLE " + TABLE_USER + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EMAIL + " TEXT NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_PASS + " TEXT NOT NULL);"); */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_USER;
        db.execSQL(query);
        this.onCreate(db);
    }

    public static String getColumnEmail() {
        return COLUMN_EMAIL;
    }

    public static String getColumnNome() {
        return COLUMN_NAME;
    }

    public static String getColumnSenha() {
        return COLUMN_PASS;
    }
}