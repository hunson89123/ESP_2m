package com.androidapp.hunson.s1410932008m;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    Button btn_mainpage, btn_checkout;
    TextView txv_order_form, txv_order_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle(getString(R.string.app_cart));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        btn_checkout = findViewById(R.id.btn_finish);
        btn_mainpage = findViewById(R.id.btn_orderpage);
        txv_order_form = findViewById(R.id.txv_order_form);
        txv_order_price = findViewById(R.id.txv_order_price);

        txv_order_form.setText(read_list());
        txv_order_price.setText("$"+read_price());


    }

    private String read_list(){
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        return form.getString("shopping_list", "尚無紀錄");
    }

    private int read_price(){
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        return form.getInt("shopping_list_price", 0);
    }

    public void go_oederpage(View v){
        startActivity(new Intent(this, OrderActivity.class));
        finish();
    }

    public void go_checkout(View v){
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.putString("shopping_record", read_list());
        editor.putInt("shopping_record_price", read_price());


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        editor.putString("shopping_record_time", sdf.format(new Date()));

        editor.commit();

        startActivity(new Intent(this, RecordActivity.class));
        finish();
    }
}