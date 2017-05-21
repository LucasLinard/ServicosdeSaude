package tech.linard.android.serviosdesade.model;

/**
 * Created by lucas on 16/05/2017.
 */

public class Servico {
    private String descricaoClassificacaoServico;
    private String descricao;
    private long codUnidade;

    public String getDescricaoClassificacaoServico() {
        return descricaoClassificacaoServico;
    }

    public void setDescricaoClassificacaoServico(String descricaoClassificacaoServico) {
        this.descricaoClassificacaoServico = descricaoClassificacaoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(long codUnidade) {
        this.codUnidade = codUnidade;
    }
}
