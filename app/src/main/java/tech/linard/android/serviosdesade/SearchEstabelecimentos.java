package tech.linard.android.serviosdesade;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import tech.linard.android.serviosdesade.utils.VolleySingleton;

public class SearchEstabelecimentos extends AppCompatActivity
    implements View.OnClickListener {
    private final String BASE_URL_ESTABELECIMENTOS =
            "http://mobile-aceite.tcu.gov.br:80/mapa-da-saude/rest/estabelecimentos?";

    Spinner spinnerEstado;
    Spinner spinnerEspecialidades;
    Spinner spinnerCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_estabelecimentos);
        // COnfigure Spinners

        spinnerEstado = (Spinner) findViewById(R.id.spinner_estado);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this,
                R.array.estados,
                android.R.layout.simple_spinner_item);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapterEstado);

        spinnerEspecialidades = (Spinner) findViewById(R.id.spinner_especialidade);
        ArrayAdapter<CharSequence> adapterEspecialidade = ArrayAdapter.createFromResource(this,
                R.array.especialidades,
                android.R.layout.simple_spinner_item);
        adapterEspecialidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidades.setAdapter(adapterEspecialidade);

        spinnerCategorias = (Spinner) findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(this,
                R.array.categorias,
                android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapterCategoria);
    }

    void startSearch(){
        String mURL = getEstabelecimentosURL();

        if (mURL != null) {
            fetchEstabelecimentosFormNetwork(mURL);
        }

    }

    String getEstabelecimentosURL(){
        String url = null;

        String prmMunicipio = "";



        String prmUf = null;
        if (spinnerEstado.getSelectedItemPosition() != 0){
            prmUf = spinnerEstado.getSelectedItem().toString();
        } else {
            prmUf = "";
        }

        String prmCategoria = null;
        if (spinnerCategorias.getSelectedItemPosition() != 0){
            prmCategoria = spinnerCategorias.getSelectedItem().toString();
        } else {
            prmCategoria = "";
        }

        String prmEspecialidade = null;
        if(spinnerEspecialidades.getSelectedItemPosition() !=0 ) {
            prmEspecialidade = spinnerEspecialidades.getSelectedItem().toString();
        } else {
            prmEspecialidade = "";
        }

        String prmViculoSUS = "";
        String prmPagina = "1";

        Uri baseUri = Uri.parse(BASE_URL_ESTABELECIMENTOS);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("quantidade", "30");
        uriBuilder.appendQueryParameter("municipio", prmMunicipio);
        uriBuilder.appendQueryParameter("uf", prmUf);
        uriBuilder.appendQueryParameter("categoria", prmCategoria);
        uriBuilder.appendQueryParameter("especialidade", prmEspecialidade);
        uriBuilder.appendQueryParameter("vinculoSus", prmViculoSUS);
        uriBuilder.appendQueryParameter("pagina", prmPagina);

        // todo: Alterar API_KEY - Save value in gradle properties.
        uriBuilder.appendQueryParameter("api_key", String.valueOf(446));

        url = uriBuilder.toString();

        return url;
    }

    void fetchEstabelecimentosFormNetwork(String url) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(SearchEstabelecimentos.this, "SUCESS!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchEstabelecimentos.this, "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_estabelecimento:
                break;
        }
    }
}
