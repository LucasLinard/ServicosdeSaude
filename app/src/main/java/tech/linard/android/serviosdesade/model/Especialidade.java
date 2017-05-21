package tech.linard.android.serviosdesade.model;

/**
 * Created by lucas on 16/05/2017.
 */

public class Especialidade {
    private String descricaoHabilitacao;
    private String descricaoGrupo;
    private long codUnidade;


    public String getDescricaoHabilitacao() {
        return descricaoHabilitacao;
    }

    public void setDescricaoHabilitacao(String descricaoHabilitacao) {
        this.descricaoHabilitacao = descricaoHabilitacao;
    }

    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }

    public long getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(long codUnidade) {
        this.codUnidade = codUnidade;
    }
}
