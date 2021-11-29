package com.latihan.ezyfood_2301853962;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.latihan.ezyfood_2301853962.dataset.OrderableItemData;
import com.latihan.ezyfood_2301853962.model.Item;

import java.util.ArrayList;
import java.util.Objects;
//Made by Rio - 2301853962
public class OrderItemActivity extends AppCompatActivity {

    Button btnMyOrder, btnOrderMore;
    ImageView imgItem;
    TextView tvName, tvPrice;
    EditText etQty;
    DBHelper dbHelper = new DBHelper(this);

    String name;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Item");

        setBinding();
        setLayoutContent();

        btnMyOrder.setOnClickListener(view -> {
            addToOrder(name);
            Intent intent = new Intent(this, MyOrderActivity.class);
            startActivity(intent);
            finish();
        });

        btnOrderMore.setOnClickListener(view -> {
            addToOrder(name);
            finish();
        });

    }

    private void addToOrder(String name) {
        if (!etQty.getText().toString().isEmpty() && !etQty.getText().toString().equals("0")){
            Cursor cursor = dbHelper.searchDataFromItemOrder(name);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                dbHelper.updateData(name, cursor.getInt(3) + Integer.parseInt(etQty.getText().toString()));
            }
            else{
                dbHelper.insertData(name, price, Integer.parseInt(etQty.getText().toString()));
            }
            Toast.makeText(this, "Item tersimpan ke order",Toast.LENGTH_SHORT).show();
            cursor.close();
        }
    }

    private void setLayoutContent() {
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position",0);
        String type = intent.getStringExtra("type");

        Item item = OrderableItemData.getData(type).get(pos);

        name = item.getName();
        price = item.getPrice();
        String priceText = "Rp. " + price;
        int imgId = item.getImg();

        tvName.setText(name);
        tvPrice.setText(priceText);
        imgItem.setImageResource(imgId);
    }

    private void setBinding() {
        btnMyOrder = findViewById(R.id.btn_my_order);
        btnOrderMore = findViewById(R.id.btn_order_more);
        imgItem = findViewById(R.id.img_item);
        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        etQty = findViewById(R.id.et_qty);
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