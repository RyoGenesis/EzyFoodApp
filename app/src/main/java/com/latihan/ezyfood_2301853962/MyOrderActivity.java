package com.latihan.ezyfood_2301853962;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.latihan.ezyfood_2301853962.adapters.CompletedOrderItemAdapter;
import com.latihan.ezyfood_2301853962.adapters.OrderItemAdapter;
import com.latihan.ezyfood_2301853962.model.OrderItem;

import java.util.ArrayList;
import java.util.Objects;
//Made by Rio - 2301853962
public class MyOrderActivity extends AppCompatActivity {

    Button btnPayNow;
    TextView tvTotal;
    RecyclerView rvOrder;
    private int total = 0;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        Objects.requireNonNull(getSupportActionBar()).setTitle("EzyFoody : My Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPayNow = findViewById(R.id.btn_pay_now);
        tvTotal = findViewById(R.id.tv_total);
        rvOrder = findViewById(R.id.rv_listOrder);

        showOrderItem();
        setTotal();

        btnPayNow.setOnClickListener(view -> {
            Intent intent = new Intent(this, CompleteOrderActivity.class);
            intent.putExtra("total",total);
            startActivity(intent);
            finish();
        });
    }

    private ArrayList<OrderItem> getData(){
        Cursor cursor = dbHelper.getDataItemOrder();
        ArrayList<OrderItem> listOrderItem = new ArrayList<>();
        if (cursor.getCount() <= 0){
            return listOrderItem;
        }

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int qty = cursor.getInt(3);
            total += price * qty;
            OrderItem orderItem = new OrderItem(id,name, price, qty);
            listOrderItem.add(orderItem);
        }

        return listOrderItem;
    }

    private void showOrderItem() {
        ArrayList<OrderItem> list = getData();
        if(list.size() < 1){
            btnPayNow.setEnabled(false);
        }
        else{
            btnPayNow.setEnabled(true);
        }
        OrderItemAdapter adapter = new OrderItemAdapter(list);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        rvOrder.setAdapter(adapter);

        adapter.setOnItemClickCallback((item, position) -> {
            boolean isDataDeleted = dbHelper.deleteData(item.getId());
            if(isDataDeleted) {
                total -= item.getPrice() * item.getQty();
                setTotal();
                adapter.getListItemOrder().remove(item);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount() - position);
                if (adapter.getItemCount()<1) btnPayNow.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Item order berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getApplicationContext(),"Item order gagal dihapus", Toast.LENGTH_SHORT).show();
        });
    }

    private void setTotal(){
        String totalText = "Total: Rp. " + total;
        tvTotal.setText(totalText);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}