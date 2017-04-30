package tech.linard.android.serviosdesade.utils;

import android.text.TextUtils;

import org.json.JSONObject;

import tech.linard.android.serviosdesade.model.Estabelecimento;

/**
 * Created by lucas on 28/04/2017.
 */

public class JSONUtils {
    public static Estabelecimento jsonToEstabelecimento(JSONObject jsonObject) {
        Estabelecimento currentEstabelecimento = new Estabelecimento();

        currentEstabelecimento.setBairro(jsonObject.optString("bairro"));
        currentEstabelecimento.setCategoriaUnidade(jsonObject.optString("categoriaUnidade"));
        currentEstabelecimento.setCep(jsonObject.optString("cep"));
        currentEstabelecimento.setCidade(jsonObject.optString("cidade"));
        currentEstabelecimento.setCnpj(jsonObject.optString(""));

        currentEstabelecimento.setCodCnes(Long.valueOf(jsonObject.optString("codCnes")));
        currentEstabelecimento.setCodIbge(Long.valueOf(jsonObject.optString("codIbge")));
        currentEstabelecimento.setCodUnidade(Long.valueOf(jsonObject.optString("codUnidade")));

        currentEstabelecimento.setDescricaoCompleta(jsonObject.optString("descricaoCompleta"));
        currentEstabelecimento.setEmail(jsonObject.optString("email"));
        currentEstabelecimento.setEsferaAdministrativa(jsonObject.optString("esferaAdministrativa"));
        currentEstabelecimento.setFluxoClientela(jsonObject.optString("fluxoClientela"));
        currentEstabelecimento.setGrupo(jsonObject.optString("grupo"));

        currentEstabelecimento.setLatitude(jsonObject.optDouble("lat", 0));

        currentEstabelecimento.setLogradouro(jsonObject.optString("logradouro"));

        currentEstabelecimento.setLongitude(jsonObject.optDouble("long", 0));

        currentEstabelecimento.setNatureza(jsonObject.optString("natureza"));
        currentEstabelecimento.setNomeFantasia(jsonObject.optString("nomeFantasia"));
        currentEstabelecimento.setNumero(jsonObject.optString("numero"));
        currentEstabelecimento.setOrigemGeografica(jsonObject.optString("origemGeografica"));
        currentEstabelecimento.setRetencao(jsonObject.optString("retencao"));
        currentEstabelecimento.setTelefone(jsonObject.optString("telefone"));

        if (jsonObject.optString("temAtendimentoAmbulatorial").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setTemAtendimentoAmbulatorial(true);
        } else {
            currentEstabelecimento.setTemAtendimentoAmbulatorial(false);
        }

        if (jsonObject.optString("temAtendimentoUrgencia").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setTemAtendimentoUrgencia(true);
        } else {
            currentEstabelecimento.setTemAtendimentoUrgencia(false);
        }

        if (jsonObject.optString("temCentroCirurgico").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setTemCentroCirurgico(true);
        } else {
            currentEstabelecimento.setTemCentroCirurgico(false);
        }

        if (jsonObject.optString("temDialise").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setTemDialise(true);
        } else {
            currentEstabelecimento.setTemDialise(false);
        }

        if (jsonObject.optString("temNeoNatal").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setTemNeoNatal(true);
        } else {
            currentEstabelecimento.setTemNeoNatal(false);
        }


        if (jsonObject.optString("temObstetra").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setTemObstetra(true);
        } else {
            currentEstabelecimento.setTemObstetra(false);
        }

        currentEstabelecimento.setTipoUnidade(jsonObject.optString("tipoUnidade"));
        currentEstabelecimento.setTipoUnidadeCnes(jsonObject.optString("tipoUnidadeCnes"));
        currentEstabelecimento.setTurnoAtendimento(jsonObject.optString("turnoAtendimento"));
        currentEstabelecimento.setUf(jsonObject.optString("uf"));

        if (jsonObject.optString("vinculoSus").equalsIgnoreCase("SIM")) {
            currentEstabelecimento.setVinculoSus(true);
        } else {
            currentEstabelecimento.setVinculoSus(false);
        }

        return currentEstabelecimento;
    }

}
