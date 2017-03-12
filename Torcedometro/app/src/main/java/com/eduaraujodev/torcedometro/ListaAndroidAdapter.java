package com.eduaraujodev.torcedometro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduaraujodev.torcedometro.model.Torcedor;

import java.util.List;

public class ListaAndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Torcedor> torcedores;

    public ListaAndroidAdapter(Context context, List<Torcedor> torcedores) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.torcedores = torcedores;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new AndroidItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AndroidItemHolder androidItemHolder = (AndroidItemHolder) holder;
        androidItemHolder.tvTorcedore.setText(torcedores.get(position).getNome());
        androidItemHolder.tvClube.setText(torcedores.get(position).getClube().getNome());
    }

    @Override
    public int getItemCount() {return torcedores.size();}

    private class AndroidItemHolder extends RecyclerView.ViewHolder {
        TextView tvTorcedore;
        TextView tvClube;

        public AndroidItemHolder(View itemView) {
            super(itemView);

            tvTorcedore = (TextView) itemView.findViewById(R.id.tvTorcedore);
            tvClube = (TextView) itemView.findViewById(R.id.tvClube);
        }
    }
}