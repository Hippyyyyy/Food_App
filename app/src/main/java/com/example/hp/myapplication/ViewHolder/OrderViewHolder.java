package com.example.hp.myapplication.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hp.myapplication.Interface.ItemClickListener;
import com.example.hp.myapplication.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtorder_id, txtorder_status, txtorder_phone, txtorder_address;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        txtorder_address = (TextView) itemView.findViewById(R.id.order_address);
        txtorder_id = (TextView) itemView.findViewById(R.id.order_id);
        txtorder_phone =(TextView)  itemView.findViewById(R.id.order_phone);
        txtorder_status = (TextView) itemView.findViewById(R.id.order_status);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
