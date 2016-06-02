package makerprice.com.makerpriceufrpe.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "makerprice.db";

    public static final String TABLE_USER = "usuario";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";

    public static final String TABLE_USER_LOJA = "usuarioLoja";
    public static final String COLUMN_CNPJ = "cnpj";
    public static final String COLUMN_AJSON = "ajson";
    public static final String COLUMN_LINKIMAGEM = "linkImagem";

    public static final String TABLE_PROJETO = "projeto";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_PLATAFORMA = "plataforma";
    public static final String COLUMN_APLICACAO = "aplicacao";
    public static final String COLUMN_COMP1 = "componente_1";
    public static final String COLUMN_COMP2 = "componente_2";
    public static final String COLUMN_COMP3 = "componente_3";

    public static final String TABLE_PESSOA_FISICA = "pessoa_fisica";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USUARIO_ID = "usuario_id";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_USER + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EMAIL + " TEXT NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_PASS + " TEXT NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER_LOJA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_PASS + " TEXT NOT NULL, " +
                COLUMN_CNPJ + " TEXT NOT NULL, " +
                COLUMN_AJSON + " TEXT NOT NULL, " +
                COLUMN_LINKIMAGEM + " TEXT NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PROJETO + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DESCRICAO + " TEXT NOT NULL, " +
                COLUMN_PLATAFORMA + " TEXT NOT NULL, " +
                COLUMN_APLICACAO + " TEXT NOT NULL, " +
                COLUMN_COMP1 + " TEXT NOT NULL, " +
                COLUMN_COMP2 + " TEXT NOT NULL, " +
                COLUMN_COMP3 + " TEXT NOT NULL);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_PESSOA_FISICA + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_USUARIO_ID + " INTEGER);");
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

    public static String getColumnCnpj(){ return COLUMN_CNPJ;}

    public static String getColumnLinkimagem() {return  COLUMN_LINKIMAGEM;}

    public static String getColumnAjson() {return COLUMN_AJSON;}
}