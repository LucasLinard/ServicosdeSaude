package tech.linard.android.serviosdesade.activities;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tech.linard.android.serviosdesade.R;
import tech.linard.android.serviosdesade.data.SaudeContract;

/**
 * Created by llinard on 16/06/17.
 */

class EstabelecimentoAdapter extends CursorAdapter {


    public EstabelecimentoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public static class ViewHolder{
        public final ImageView iconView;
        public final TextView txtDescricaoPrincipal;
        public final TextView txtDescricaoSecundaria;

        public ViewHolder(View view){
            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
            txtDescricaoPrincipal = (TextView) view.findViewById(R.id.list_item_txt_principal);
            txtDescricaoSecundaria = (TextView) view.findViewById(R.id.list_item_txt_secundario);
        }

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int layoutId = R.layout.list_item_estabelecimento;

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();


        String descricao = cursor
                .getString(cursor
                        .getColumnIndex(SaudeContract.EstabelecimentoEntry
                                .COLUMN_NOME_FANTASIA));
        viewHolder.txtDescricaoPrincipal.setText(descricao);

        String descricaoCompleta = cursor
                .getString(cursor
                        .getColumnIndex(SaudeContract.EstabelecimentoEntry
                                .COLUMN_DESCRICAO_COMPLETA));
        viewHolder.txtDescricaoSecundaria.setText(descricaoCompleta);

        viewHolder.iconView.setImageResource(R.drawable.ic_menu_gallery);

    }
}
