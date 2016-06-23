package makerprice.com.makerpriceufrpe.infra;

import android.content.ContentValues;
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
    public static final String COLUMN_PESSOAFISICA_ID = "criador";


    public static final String TABLE_PESSOA_FISICA = "pessoa_fisica";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USUARIO_ID = "usuario_id";

    public static final String TABLE_IMAGEM = "imagem";
    public static final String COLUMN_CAMINHO = "caminho";
    public static final String COLUMN_PROJETO_ID = "projeto_id";

    public static final String TABLE_COMPONENTE = "componente";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_COR = "cor";
    public static final String COLUMN_CAPACITANCIA = "capacitancia";
    public static final String COLUMN_RESISTENCIA = "resistencia";

    public static final String[] COLUMNS_COMPONENTE_SPEC = {
            COLUMN_TIPO, COLUMN_COR, COLUMN_CAPACITANCIA, COLUMN_RESISTENCIA
    };

    public static final String TABLE_COMPONENTE_PROJETO = "componente_projeto";
    public static final String COLUMN_COMPONENTE_ID = "componente_id";

    public static final String TABLE_COMPONENTE_LOJA = "componente_loja";
    public static final String COLUMN_LOJA_ID = "loja_id";
    public static final String COLUMN_PRECO = "preco";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_USER + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_EMAIL + " TEXT NOT NULL, " +
                        COLUMN_PASS + " TEXT NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER_LOJA + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_CNPJ + " TEXT NOT NULL, "+
                COLUMN_USUARIO_ID + " INTEGER);");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PROJETO + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DESCRICAO + " TEXT NOT NULL, " +
                COLUMN_PLATAFORMA + " TEXT NOT NULL, " +
                COLUMN_APLICACAO + " TEXT NOT NULL, " +
                COLUMN_PESSOAFISICA_ID + " INTEGER);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_PESSOA_FISICA + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_USUARIO_ID + " INTEGER);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_IMAGEM + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_CAMINHO + " TEXT NOT NULL, " +
                        COLUMN_PROJETO_ID + " INTEGER);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_COMPONENTE + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_TIPO + " TEXT, " +
                        COLUMN_COR + " TEXT, " +
                        COLUMN_CAPACITANCIA + " TEXT, " +
                        COLUMN_RESISTENCIA + " TEXT);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_COMPONENTE_PROJETO + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_PROJETO_ID + " INTEGER NOT NULL, " +
                        COLUMN_COMPONENTE_ID + " INTEGER NOT NULL);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_COMPONENTE_LOJA + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_LOJA_ID + " INTEGER NOT NULL, " +
                        COLUMN_COMPONENTE_ID + " INTEGER NOT NULL, " +
                        COLUMN_PRECO + " REAL NOT NULL);");

        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (1, 1, 0.10);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (2, 1, 0.05);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (3, 1, 0.30);");


        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (1, 2, 0.25);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (2, 2, 0.20);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (3, 2, 0.18);");


        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (1, 3, 2.00);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (2, 3, 1.50);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (3, 3, 1.75);");


        //////////////////////////////////////////////////////////////////////////////////////


        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (1, 4, 1.35);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (2, 4, 1.45);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (3, 4, 1.50);");


        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (1, 5, 2.00);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (2, 5, 2.20);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (3, 5, 1.70);");


        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (1, 6, 2.10);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (2, 6, 1.35);");
        sqLiteDatabase.execSQL(
                "INSERT INTO " + TABLE_COMPONENTE_LOJA + " (loja_id, componente_id, preco) " +
                        "VALUES (3, 6, 1.75);");

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