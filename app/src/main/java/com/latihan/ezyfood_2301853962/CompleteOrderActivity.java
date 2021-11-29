package com.latihan.ezyfood_2301853962;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
public class CompleteOrderActivity extends AppCompatActivity {

    Button btnMainMenu;
    TextView tvTotal;
    RecyclerView rvOrder;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);

        Objects.requireNonNull(getSupportActionBar()).setTitle("EzyFoody : Order Complete");

        btnMainMenu = findViewById(R.id.btn_main_menu);
        tvTotal = findViewById(R.id.tv_total);
        rvOrder = findViewById(R.id.rv_listOrder);

        showOrderItem();
        String totalText = "Total: Rp. " + getIntent().getIntExtra("total",0);
        tvTotal.setText(totalText);

        dbHelper.cleanOrder();

        btnMainMenu.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
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
            OrderItem orderItem = new OrderItem(id,name, price, qty);
            listOrderItem.add(orderItem);
        }

        return listOrderItem;
    }

    private void showOrderItem() {
        ArrayList<OrderItem> list = getData();
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
        CompletedOrderItemAdapter adapter = new CompletedOrderItemAdapter(list);
        rvOrder.setAdapter(adapter);
    }
}