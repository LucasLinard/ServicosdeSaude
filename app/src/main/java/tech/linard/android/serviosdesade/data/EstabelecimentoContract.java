package tech.linard.android.serviosdesade.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by lucas on 16/05/2017.
 */

public class EstabelecimentoContract  {

    public static final String CONTENT_AUTHORITY = "tech.linard.android.serviosdesade";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ESTABELECIMENTO = "estabelecimento";
    public static final String PATH_ESPECIALIDADE = "especialidade";
    public static final String PATH_PROFISSIONAL = "profissional";
    public static final String PATH_SERVICO = "servico";


    public static final class EstabelecimentoEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ESTABELECIMENTO).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ESTABELECIMENTO;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ESTABELECIMENTO;

        // Table name
        public static final String TABLE_NAME = "estabelecimento";

        // Colunas
        public static final String COLUMN_COD_CNES = "codCnes";

        // chave primaria da tabela
        public static final String COLUMN_COD_UNIDADE  =   "codUnidade";
        public static final String COLUMN_COD_IBGE  =  "codIbge" ;
        public static final String COLUMN_NOME_FANTASIA =  "nomeFantasia";
        public static final String COLUMN_NATUREZA =  "natureza";
        public static final String COLUMN_TIPO_UNIDADE =  "tipoUnidade";
        public static final String COLUMN_ESFERA_ADMINISTRATIVA =  "esferaAdministrativa";
        public static final String COLUMN_VINCULO_SUS =  "vinculoSus";
        public static final String COLUMN_RETENCAO =  "retencao";
        public static final String COLUMN_FLUXO_CLIENTELA =  "fluxoClientela";
        public static final String COLUMN_ORIGEM_GEOGRAFICA =  "origemGeografica";
        public static final String COLUMN_TEM_ATENDIMENTO_URGENCIA =  "temAtendimentoUrgencia";
        public static final String COLUMN_TEM_ATENDIMENTO_AMBULATORIAL =  "temAtendimentoAmbulatorial" ;
        public static final String COLUMN_TEM_CENTRO_CIRURGICO =  "temCentroCirurgico";
        public static final String COLUMN_TEM_OBSTETRA =  "temObstetra";
        public static final String COLUMN_TEM_NEONATAL =  "temNeoNatal";
        public static final String COLUMN_TEM_DIALISE =  "temDialise";
        public static final String COLUMN_DESCRICAO_COMPLETA =  "descricaoCompleta";
        public static final String COLUMN_TIPO_UNIDADE_CNES =  "tipoUnidadeCnes";
        public static final String COLUMN_CATEGORIA_UNIDADE =  "categoriaUnidade";
        public static final String COLUMN_LOGRADOURO =  "logradouro";
        public static final String COLUMN_NUMERO =  "numero";
        public static final String COLUMN_BAIRRO =  "bairro";
        public static final String COLUMN_CIDADE =  "cidade";
        public static final String COLUMN_UF =  "uf";
        public static final String COLUMN_CEP =  "cep";
        public static final String COLUMN_TURNO_ATENDIMENTO =  "turnoAtendimento";
        public static final String COLUMN_LAT =  "lat";
        public static final String COLUMN_LONG =  "long";
        public static final String COLUMN_TELEFONE =  "telefone";
        public static final String COLUMN_CNPJ =  "cnpj";

        public static Uri buildEstabelecimentoUri(long codUnidade) {
            return ContentUris.withAppendedId(CONTENT_URI, codUnidade);
        }
    }

    public static final class EspecialidadeEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ESPECIALIDADE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ESPECIALIDADE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ESPECIALIDADE;

        // Chave estrangeira tabela referencia COLUMN_COD_UNIDADE em estabelecimento
        public static final String COLUMN_ESPECIALIDADE_ID = "especialidade_id";
        public static final String COLUMN_COD_UNIDADE =  "codUnidade";
        public static final String COLUMN_DESCRICAO_HABILITACAO =  "descricaoHabilitacao";
        public static final String COLUMN_DESCRICAO_GRUPO =  "descricaoGrupo";

        public static Uri buildEspecialidadeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class ProfisionalEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROFISSIONAL).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROFISSIONAL;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROFISSIONAL;

        public static final String COLUMN_COD_UNIDADE =  "codUnidade";
        public static final String COLUMN_DESCRICAO_ATIVIDADE_PROFISSIONAL
                =  "descricaoAtividadeProfissional";
        public static final String COLUMN_QUANTIDADE_PROFISSIONAIS = "quantidadeProfissionais";

        public static Uri buildProfissionalUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class ServicoEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SERVICO).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SERVICO;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SERVICO;

        public static final String COLUMN_COD_UNIDADE =  "codUnidade";
        public static final String COLUMN_DESCRICAO_CLASSIFICACAO_SERVICO
                =  "descricaoClassificacaoServico";
        public static final String COLUMN_DESCRICAO
                =  "descricao";


        public static Uri buildServicoUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}
