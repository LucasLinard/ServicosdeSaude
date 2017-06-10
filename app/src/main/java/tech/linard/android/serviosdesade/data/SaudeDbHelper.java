package tech.linard.android.serviosdesade.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tech.linard.android.serviosdesade.data.SaudeContract.EstabelecimentoEntry;

import static tech.linard.android.serviosdesade.data.SaudeContract.*;

/**
 * Created by lucas on 21/05/2017.
 */

public class SaudeDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "estabelecimento.db";


    public SaudeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Criar tabela para armezenar cache de estabelecimentos pesquisados
        // utilizando API do TCU. O tipo dos campos reflete, na medida do possível,
        // o retorno da API.

        final String SQL_CREATE_ESTABELECIMENTO_TABLE = "CREATE TABLE " +
                EstabelecimentoEntry.TABLE_NAME + " (" +
                EstabelecimentoEntry.COLUMN_COD_UNIDADE +  " INTEGER PRIMARY KEY," +
                EstabelecimentoEntry.COLUMN_COD_IBGE + " INTEGER," +
                EstabelecimentoEntry.COLUMN_COD_CNES + " INTEGER," +
                EstabelecimentoEntry.COLUMN_NOME_FANTASIA + " TEXT," +
                EstabelecimentoEntry.COLUMN_NATUREZA + " TEXT," +
                EstabelecimentoEntry.COLUMN_TIPO_UNIDADE + " TEXT," +
                EstabelecimentoEntry.COLUMN_ESFERA_ADMINISTRATIVA + " TEXT," +
                EstabelecimentoEntry.COLUMN_VINCULO_SUS +
                EstabelecimentoEntry.COLUMN_RETENCAO + " TEXT," +
                EstabelecimentoEntry.COLUMN_FLUXO_CLIENTELA + " TEXT," +
                EstabelecimentoEntry.COLUMN_ORIGEM_GEOGRAFICA + " TEXT," +
                EstabelecimentoEntry.COLUMN_TEM_ATENDIMENTO_URGENCIA + " INTEGER NOT NULL," +
                EstabelecimentoEntry.COLUMN_TEM_ATENDIMENTO_AMBULATORIAL + " INTEGER NOT NULL," +
                EstabelecimentoEntry.COLUMN_TEM_CENTRO_CIRURGICO + " INTEGER NOT NULL," +
                EstabelecimentoEntry.COLUMN_TEM_OBSTETRA + " INTEGER NOT NULL," +
                EstabelecimentoEntry.COLUMN_TEM_NEONATAL + " INTEGER NOT NULL," +
                EstabelecimentoEntry.COLUMN_TEM_DIALISE + " INTEGER NOT NULL," +
                EstabelecimentoEntry.COLUMN_DESCRICAO_COMPLETA + " TEXT," +
                EstabelecimentoEntry.COLUMN_TIPO_UNIDADE_CNES + " TEXT," +
                EstabelecimentoEntry.COLUMN_CATEGORIA_UNIDADE + " TEXT," +
                EstabelecimentoEntry.COLUMN_LOGRADOURO + " TEXT," +
                EstabelecimentoEntry.COLUMN_NUMERO + " TEXT," +
                EstabelecimentoEntry.COLUMN_BAIRRO + " TEXT," +
                EstabelecimentoEntry.COLUMN_CIDADE + " TEXT," +
                EstabelecimentoEntry.COLUMN_UF + " TEXT," +
                EstabelecimentoEntry.COLUMN_CEP + " TEXT," +
                EstabelecimentoEntry.COLUMN_TURNO_ATENDIMENTO + " TEXT," +
                EstabelecimentoEntry.COLUMN_LAT + " REAL," +
                EstabelecimentoEntry.COLUMN_LONG + " REAL," +
                EstabelecimentoEntry.COLUMN_TELEFONE + " TEXT," +
                EstabelecimentoEntry.COLUMN_CNPJ + " TEXT" +
                " UNIQUE ( " + EstabelecimentoEntry.COLUMN_COD_UNIDADE + " ) " +
                " ON CONFLICT REPLACE" +
                " );";

        final String SQL_CREATE_ESPECIALIDADE_TABLE = "CREATE TABLE " +
                EspecialidadeEntry.TABLE_NAME + " (" +
                EspecialidadeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EspecialidadeEntry.COLUMN_COD_UNIDADE + " INTEGER NOT NULL," +
                EspecialidadeEntry.COLUMN_DESCRICAO_GRUPO + " TEXT," +
                EspecialidadeEntry.COLUMN_DESCRICAO_HABILITACAO + " TEXT," +
                " FOREIGN KEY (" + EspecialidadeEntry.COLUMN_COD_UNIDADE + ") REFERENCES " +
                EstabelecimentoEntry.TABLE_NAME +
                " (" + EstabelecimentoEntry.COLUMN_COD_UNIDADE + "), "
                + " );";

        final String SQL_CREATE_SERVICO_TABLE  =  "CREATE TABLE " +
                ServicoEntry.TABLE_NAME + " (" +
                ServicoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ServicoEntry.COLUMN_COD_UNIDADE + " INTEGER NOT NULL," +
                ServicoEntry.COLUMN_DESCRICAO + " TEXT," +
                ServicoEntry.COLUMN_DESCRICAO_CLASSIFICACAO_SERVICO + " TEXT," +
                " FOREIGN KEY (" + ServicoEntry.COLUMN_COD_UNIDADE + ") REFERENCES " +
                EstabelecimentoEntry.TABLE_NAME +
                " (" + EstabelecimentoEntry.COLUMN_COD_UNIDADE + "), "
                + " );";

        final String SQL_CREATE_PROFISSIONAL_TABLE = "CREATE TABLE " +
                ProfisionalEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProfisionalEntry.COLUMN_COD_UNIDADE + " INTEGER NOT NULL," +
                ProfisionalEntry.COLUMN_DESCRICAO_ATIVIDADE_PROFISSIONAL + " TEXT," +
                ProfisionalEntry.COLUMN_QUANTIDADE_PROFISSIONAIS + " TEXT," +
                " FOREIGN KEY (" + ProfisionalEntry.COLUMN_COD_UNIDADE + ") REFERENCES " +
                EstabelecimentoEntry.TABLE_NAME +
                " (" + EstabelecimentoEntry.COLUMN_COD_UNIDADE + "), "
                + " );";

        db.execSQL(SQL_CREATE_ESTABELECIMENTO_TABLE);
        db.execSQL(SQL_CREATE_ESPECIALIDADE_TABLE);
        db.execSQL(SQL_CREATE_SERVICO_TABLE);
        db.execSQL(SQL_CREATE_PROFISSIONAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(" DROP TABLE IF EXISTS " + ProfisionalEntry.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + ServicoEntry.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + EspecialidadeEntry.TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + EstabelecimentoEntry.TABLE_NAME);
        onCreate(db);
    }
}

