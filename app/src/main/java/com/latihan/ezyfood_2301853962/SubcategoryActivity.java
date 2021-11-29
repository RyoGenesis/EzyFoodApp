package com.latihan.ezyfood_2301853962;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.latihan.ezyfood_2301853962.adapters.ItemAdapter;
import com.latihan.ezyfood_2301853962.dataset.OrderableItemData;
import com.latihan.ezyfood_2301853962.model.Item;

import java.util.ArrayList;
import java.util.Objects;
//Made by Rio - 2301853962
public class SubcategoryActivity extends AppCompatActivity {

    Button btnMyOrder;
    RecyclerView rvOrderableItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        btnMyOrder = findViewById(R.id.btn_my_order);
        rvOrderableItem = findViewById(R.id.rv_orderableItem);
        String viewType = getIntent().getStringExtra("type");

        ArrayList<Item> listOfItem = new ArrayList<>();

        switch (viewType){
            case "drinks":
                listOfItem.addAll(OrderableItemData.getDrinksData());
                getSupportActionBar().setTitle(R.string.drinks);
                break;
            case "snacks":
                listOfItem.addAll(OrderableItemData.getSnacksData());
                getSupportActionBar().setTitle(R.string.snacks);
                break;
            default: //foods
                listOfItem.addAll(OrderableItemData.getFoodsData());
                getSupportActionBar().setTitle(R.string.foods);
                break;
        }

        btnMyOrder.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyOrderActivity.class);
            startActivity(intent);
        });
        
        setData(listOfItem);
    }

    private void setData(ArrayList<Item> listOfItem) {
        ItemAdapter adapter = new ItemAdapter(this,listOfItem);
        rvOrderableItem.setAdapter(adapter);
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