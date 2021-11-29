package com.latihan.ezyfood_2301853962;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
//Made by Rio - 2301853962
public class MainActivity extends AppCompatActivity {

    Button btnMyOrder;
    LinearLayout drinks, snacks, foods, topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMyOrder = findViewById(R.id.btn_my_order);
        drinks = findViewById(R.id.drinks);
        snacks = findViewById(R.id.snacks);
        foods = findViewById(R.id.foods);
        topup = findViewById(R.id.top_up);

        btnMyOrder.setOnClickListener(view -> {
            Intent intent = new Intent(this, MyOrderActivity.class);
            startActivity(intent);
        });

        drinks.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubcategoryActivity.class);
            intent.putExtra("type","drinks");
            startActivity(intent);
        });

        snacks.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubcategoryActivity.class);
            intent.putExtra("type","snacks");
            startActivity(intent);
        });

        foods.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubcategoryActivity.class);
            intent.putExtra("type","foods");
            startActivity(intent);
        });

        topup.setOnClickListener(view -> {
            //simulasi aksi top up asumsi akan redirect ke service bca untuk melakukan transfer
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.klikbca.com/"));
            startActivity(intent);
        });

    }
}