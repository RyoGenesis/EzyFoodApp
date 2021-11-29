package com.latihan.ezyfood_2301853962.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latihan.ezyfood_2301853962.R;
import com.latihan.ezyfood_2301853962.model.OrderItem;

import java.util.ArrayList;

public class CompletedOrderItemAdapter extends RecyclerView.Adapter<CompletedOrderItemAdapter.ListViewHolder>{

    private final ArrayList<OrderItem> listItemOrder;

    public CompletedOrderItemAdapter(ArrayList<OrderItem> listItem){
        this.listItemOrder = listItem;
    }

    @NonNull
    @Override
    public CompletedOrderItemAdapter.ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complete_order_item,viewGroup,false);
        return new CompletedOrderItemAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedOrderItemAdapter.ListViewHolder holder, int position) {
        OrderItem item = listItemOrder.get(position);

        String name = item.getName();
        double price = item.getPrice();
        String qty = Integer.toString(item.getQty());

        String priceText = "Rp. " + price;

        holder.tvName.setText(name);
        holder.tvPrice.setText(priceText);
        holder.tvQty.setText(qty);
    }

    @Override
    public int getItemCount() {
        return listItemOrder.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvPrice, tvQty;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvQty = itemView.findViewById(R.id.tv_qty);
        }
    }
}
