package tech.linard.android.serviosdesade.activities;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tech.linard.android.serviosdesade.R;
import tech.linard.android.serviosdesade.data.SaudeContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstabelecimentosFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String LOG_TAG = EstabelecimentosFragment.class.getSimpleName();
    private EstabelecimentoAdapter mEstabelecimentoAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;

    private static final String SELECTED_KEY = "selected_position";

    private static final int ESTABELECIMENTO_LOADER = 1;


    public EstabelecimentosFragment() {
        // Required empty public constructor

    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(Uri dateUri);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mEstabelecimentoAdapter = new EstabelecimentoAdapter(getActivity(), null, 0);
        View rootView = inflater.inflate(R.layout.fragment_estabelecimentos, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        mListView = (ListView) rootView.findViewById(R.id.listview_estabelecimentos);
        mListView.setAdapter(mEstabelecimentoAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(ESTABELECIMENTO_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri estabelecimentosUri = SaudeContract.EstabelecimentoEntry.CONTENT_URI;
        return new CursorLoader(getActivity(),
                estabelecimentosUri,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mEstabelecimentoAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mEstabelecimentoAdapter.swapCursor(null);
    }
}
