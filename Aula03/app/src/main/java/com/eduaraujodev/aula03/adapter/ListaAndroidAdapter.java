package com.eduaraujodev.aula03.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eduaraujodev.aula03.R;
import com.eduaraujodev.aula03.model.AndroidVersao;

import java.util.List;

public class ListaAndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private List<AndroidVersao> versoes;

    public ListaAndroidAdapter(Context context, List<AndroidVersao> versoes) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.versoes = versoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);

        return new AndroidItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AndroidItemHolder androidItemHolder = (AndroidItemHolder) holder;
        androidItemHolder.tvAPI.setText(versoes.get(position).getApi());
        androidItemHolder.tvNome.setText(versoes.get(position).getNome());
        androidItemHolder.tvVersao.setText(versoes.get(position).getVersao());

        Glide.with(context)
                .load(versoes.get(position).getUrlImagem())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(androidItemHolder.ivIcone);
    }

    @Override
    public int getItemCount() {
        return versoes.size();
    }

    private class AndroidItemHolder extends RecyclerView.ViewHolder{
        ImageView ivIcone;

        TextView tvNome, tvAPI, tvVersao;

        public AndroidItemHolder(View itemView) {
            super(itemView);

            ivIcone = (ImageView) itemView.findViewById(R.id.ivIcone);
            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvAPI = (TextView) itemView.findViewById(R.id.tvAPI);
            tvVersao = (TextView) itemView.findViewById(R.id.tvVersao);
        }
    }
}