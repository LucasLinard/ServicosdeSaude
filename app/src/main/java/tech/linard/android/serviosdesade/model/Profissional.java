package tech.linard.android.serviosdesade.model;

/**
 * Created by lucas on 16/05/2017.
 */

public class Profissional {
    private String descricaoAtividadeProfissional;
    private String quantidadeProfissionais;
    private long codUnidade;


    public String getDescricaoAtividadeProfissional() {
        return descricaoAtividadeProfissional;
    }

    public void setDescricaoAtividadeProfissional(String descricaoAtividadeProfissional) {
        this.descricaoAtividadeProfissional = descricaoAtividadeProfissional;
    }

    public String getQuantidadeProfissionais() {
        return quantidadeProfissionais;
    }

    public void setQuantidadeProfissionais(String quantidadeProfissionais) {
        this.quantidadeProfissionais = quantidadeProfissionais;
    }

    public long getCodUnidade() {
        return codUnidade;
    }

    public void setCodUnidade(long codUnidade) {
        this.codUnidade = codUnidade;
    }
}
