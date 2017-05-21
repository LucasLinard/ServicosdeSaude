package tech.linard.android.serviosdesade.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;

import tech.linard.android.serviosdesade.R;
import tech.linard.android.serviosdesade.utils.VolleySingleton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment

        implements View.OnClickListener {
    private final String BASE_URL_ESTABELECIMENTOS =
            "http://mobile-aceite.tcu.gov.br:80/mapa-da-saude/rest/estabelecimentos?";

    Spinner spinnerEstado;
    Spinner spinnerEspecialidades;
    Spinner spinnerCategorias;
    Button btnBusca;
    CheckBox checkBoxVinculoSus;
    int contadorDeSucesso = 1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configureSpinners();

        btnBusca = (Button) getActivity().findViewById(R.id.btn_search_estabelecimento);
        btnBusca.setOnClickListener(this);

        checkBoxVinculoSus = (CheckBox) getActivity().findViewById(R.id.chk_txt_vinculo_sus);
        checkBoxVinculoSus.setOnClickListener(this);

    }

    private void configureSpinners() {
        spinnerEstado = (Spinner) getActivity().findViewById(R.id.spinner_estado);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(getActivity(),
                R.array.estados,
                android.R.layout.simple_spinner_item);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapterEstado);

        spinnerEspecialidades = (Spinner) getActivity().findViewById(R.id.spinner_especialidade);
        ArrayAdapter<CharSequence> adapterEspecialidade = ArrayAdapter.createFromResource(getActivity(),
                R.array.especialidades,
                android.R.layout.simple_spinner_item);
        adapterEspecialidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidades.setAdapter(adapterEspecialidade);

        spinnerCategorias = (Spinner) getActivity().findViewById(R.id.spinner_categoria);
        ArrayAdapter<CharSequence> adapterCategoria = ArrayAdapter.createFromResource(getActivity(),
                R.array.categorias,
                android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(adapterCategoria);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_estabelecimento:
                startSearch();
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void startSearch(){
        for (int pagina = 0; pagina <= 10; pagina++) {
            String mURL = getEstabelecimentosURL(pagina);
            if (mURL != null) {
                fetchEstabelecimentosFormNetwork(mURL);
            }
        }
    }

    String getEstabelecimentosURL(int pagina){
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
        String prmVinculoSUS = "";

        if (checkBoxVinculoSus.isChecked()) {
            prmVinculoSUS = "sim";
        } else {
            prmVinculoSUS = "";
        }

        //TODO: store and recover this value on sharedPrefs
        int quantidade = 10;

        String prmQuantidade = String.valueOf(quantidade);
        String prmPagina = String.valueOf(pagina);

        Uri baseUri = Uri.parse(BASE_URL_ESTABELECIMENTOS);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("quantidade", prmQuantidade);
        uriBuilder.appendQueryParameter("municipio", prmMunicipio);
        uriBuilder.appendQueryParameter("uf", prmUf);
        uriBuilder.appendQueryParameter("categoria", prmCategoria);
        uriBuilder.appendQueryParameter("especialidade", prmEspecialidade);
        uriBuilder.appendQueryParameter("vinculoSus", prmVinculoSUS);
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
                        Toast.makeText(getActivity(), "SUCESS!"
                                 + String.valueOf(contadorDeSucesso)
                                , Toast.LENGTH_SHORT).show();
                        contadorDeSucesso++;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "ERROR!", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsonArrayRequest);
    }


}
