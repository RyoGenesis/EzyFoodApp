package com.latihan.ezyfood_2301853962.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latihan.ezyfood_2301853962.R;
import com.latihan.ezyfood_2301853962.model.OrderItem;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ListViewHolder> {

    private final ArrayList<OrderItem> listItemOrder;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public OrderItemAdapter(ArrayList<OrderItem> listItem){
        this.listItemOrder = listItem;
    }

    public ArrayList<OrderItem> getListItemOrder() {
        return listItemOrder;
    }

    @NonNull
    @Override
    public OrderItemAdapter.ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item_layout,viewGroup,false);
        return new OrderItemAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderItemAdapter.ListViewHolder holder, int position) {
        OrderItem item = listItemOrder.get(position);

        String name = item.getName();
        double price = item.getPrice();
        String qty = Integer.toString(item.getQty());

        String priceText = "Rp. " + price;

        holder.tvName.setText(name);
        holder.tvPrice.setText(priceText);
        holder.tvQty.setText(qty);

        holder.btnHapus.setOnClickListener(view -> {
            onItemClickCallback.onItemClicked(listItemOrder.get(position),position);
        });
    }

    @Override
    public int getItemCount() {
        return listItemOrder.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvPrice, tvQty;
        Button btnHapus;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvQty = itemView.findViewById(R.id.tv_qty);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(OrderItem item, int position);
    }
}
