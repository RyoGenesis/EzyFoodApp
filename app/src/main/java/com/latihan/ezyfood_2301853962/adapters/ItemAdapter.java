package com.latihan.ezyfood_2301853962.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.latihan.ezyfood_2301853962.OrderItemActivity;
import com.latihan.ezyfood_2301853962.R;
import com.latihan.ezyfood_2301853962.model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ListViewHolder> {

    private final ArrayList<Item> listItem;
    private Context context;

    public ItemAdapter(Context context,ArrayList<Item> listItem){
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Item item = listItem.get(position);

        String name = item.getName();
        int price = item.getPrice();
        int img = item.getImg();

        String priceText = "Rp. " + price;

        holder.tvName.setText(name);
        holder.tvPrice.setText(priceText);
        holder.imgItem.setImageResource(img);

        holder.itemView.setOnClickListener(v ->
                {
                    Intent intent = new Intent(context, OrderItemActivity.class);
                    intent.putExtra("position",position);
                    intent.putExtra("type", item.getType());
                    context.startActivity(intent);
                });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        ImageView imgItem;
        TextView tvName, tvPrice;

        public ListViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
