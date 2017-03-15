package com.eduaraujodev.trabalho01.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eduaraujodev.trabalho01.R;
import com.eduaraujodev.trabalho01.model.Celular;

import java.util.List;

public class ListaCelularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private List<Celular> celulares;
    private CelularOnClickListener celularOnClickListener;

    public ListaCelularAdapter(Context context, List<Celular> celulares, CelularOnClickListener celularOnClickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.celulares = celulares;
        this.celularOnClickListener = celularOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);

        return new CelularItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        CelularItemHolder celularItemHolder = (CelularItemHolder) holder;
        celularItemHolder.tvMarca.setText(celulares.get(position).getMarca());
        celularItemHolder.tvModelo.setText(celulares.get(position).getModelo());
        celularItemHolder.tvVersaoAndroid.setText(celulares.get(position).getVersaoAndroid().getNome());

        Glide.with(context)
                .load(celulares.get(position).getVersaoAndroid().getUrlImagem())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(celularItemHolder.ivIcone);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                celularOnClickListener.onLongClickCelular(holder.itemView, position);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return celulares.size();
    }

    private class CelularItemHolder extends RecyclerView.ViewHolder {
        ImageView ivIcone;

        TextView tvMarca, tvModelo, tvVersaoAndroid;

        public CelularItemHolder(View itemView) {
            super(itemView);

            ivIcone = (ImageView) itemView.findViewById(R.id.ivIcone);
            tvMarca = (TextView) itemView.findViewById(R.id.tvMarca);
            tvModelo = (TextView) itemView.findViewById(R.id.tvModelo);
            tvVersaoAndroid = (TextView) itemView.findViewById(R.id.tvVersaoAndroid);
        }
    }

    public interface CelularOnClickListener {
        void onLongClickCelular(View view, int posicao);
    }
}