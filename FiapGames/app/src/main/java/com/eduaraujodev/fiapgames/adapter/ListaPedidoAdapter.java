package com.eduaraujodev.fiapgames.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eduaraujodev.fiapgames.R;
import com.eduaraujodev.fiapgames.model.Pedido;

import java.util.List;

public class ListaPedidoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Pedido> pedidos;
    private PedidoOnClickListener pedidoOnClickListener;

    public ListaPedidoAdapter(Context context, List<Pedido> pedidos, PedidoOnClickListener pedidoOnClickListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.pedidos = pedidos;
        this.pedidoOnClickListener = pedidoOnClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);

        return new PedidoItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        PedidoItemHolder pedidoItemHolder = (PedidoItemHolder) holder;
        pedidoItemHolder.tvNomeCliente.setText(pedidos.get(position).getNomeCliente());
        pedidoItemHolder.tvDataHora.setText(pedidos.get(position).getData());
        pedidoItemHolder.tvProduto.setText(pedidos.get(position).getProduto().getDescricao());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                pedidoOnClickListener.onLongClickPedido(holder.itemView, position);

                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedidoOnClickListener.onClickPedido(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    private class PedidoItemHolder extends RecyclerView.ViewHolder {
        ImageView ivImg;

        TextView tvNomeCliente, tvDataHora, tvProduto;

        public PedidoItemHolder(View itemView) {
            super(itemView);

            ivImg = (ImageView) itemView.findViewById(R.id.ivImg);
            tvNomeCliente = (TextView) itemView.findViewById(R.id.tvNomeCliente);
            tvDataHora = (TextView) itemView.findViewById(R.id.tvDataHora);
            tvProduto = (TextView) itemView.findViewById(R.id.tvProduto);
        }
    }

    public interface PedidoOnClickListener {
        void onLongClickPedido(View view, int posicao);
        void onClickPedido(View view, int posicao);
    }
}