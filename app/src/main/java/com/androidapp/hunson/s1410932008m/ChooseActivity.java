package com.androidapp.hunson.s1410932008m;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class ChooseActivity extends AppCompatActivity {
    Button btn_cancel, btn_continue, btn_check;
    TextView txv_quantity, txv_drinkName;
    RadioGroup radiogrp_sweet, radiogrp_ice;

    int quantity = 0, Original_price =0, send_price = 0;
    String sweet, ice, send, drink, Original_string, send_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        setTitle(getString(R.string.app_choose));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Bundle extras = getIntent().getExtras();

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_continue = findViewById(R.id.btn_continue);
        txv_quantity = findViewById(R.id.txv_quantity);
        txv_drinkName = findViewById(R.id.txv_drinkName);
        radiogrp_ice = findViewById(R.id.radiogrp_ice);
        radiogrp_sweet = findViewById(R.id.radiogrp_sweet);

        Intent intent = getIntent();
        drink = intent.getStringExtra("drink_type");

        if (extras != null) {
            String value = extras.getString("drink_type");
            value = value.substring(4);
            txv_drinkName.setText(value);
        }

        radiogrp_ice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkCompleteOrder();
            }
        });

        radiogrp_sweet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                checkCompleteOrder();
            }
        });
        checkCompleteOrder();
    }

    public void go_cancel(View v) {
        startActivity(new Intent(this, OrderActivity.class));
        finish();
    }

    public boolean go_continue(View v){
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.putString("shopping_list", send_list());
        editor.putInt("shopping_list_price", send_price());
        startActivity(new Intent(this, OrderActivity.class));
        finish();
        return editor.commit();
    }

    public void add(View v) {
        txv_quantity.setText(String.valueOf(++quantity));
        checkCompleteOrder();
    }

    public void minus(View v) {
        if (quantity > 0) {
            txv_quantity.setText(String.valueOf(--quantity));
            checkCompleteOrder();
        }
    }

    public int send_price(){
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        Original_price = form.getInt("shopping_list_price", 0);
        if(Original_price == 0){
            send_price = Integer.parseInt(drink.substring(1,3)) * quantity;
        }else{
            send_price = Original_price + (Integer.parseInt(drink.substring(1,3)) * quantity);
        }

        return send_price;
    }

    public String send_list(){
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        Original_string = form.getString("shopping_list", "");
        if(Original_string == ""){
            send_string = order_data();
        }else{
            send_string = Original_string + "\n" + order_data();
        }

        return send_string;
    }

    public String order_data(){
        switch (radiogrp_sweet.getCheckedRadioButtonId()){
            case R.id.radiobtn_normal:
                sweet = "全糖";
                break;
            case R.id.radiobtn_eight:
                sweet = "少糖";
                break;
            case R.id.radiobtn_half:
                sweet = "半糖";
                break;
            case R.id.radiobtn_three:
                sweet = "微糖";
                break;
            case R.id.radiobtn_zero:
                sweet = "無糖";
                break;
        }

        switch (radiogrp_ice.getCheckedRadioButtonId()) {
            case R.id.radiobtn_normal_ice:
                ice = "正常";
                break;
            case R.id.radiobtn_less:
                ice = "少冰";
                break;
            case R.id.radiobtn_micro:
                ice = "微冰";
                break;
            case R.id.radiobtn_no:
                ice = "去冰";
                break;
        }
        send = drink.substring(4,drink.length()) + "  " + sweet + "  " + ice + "  " + String.valueOf(quantity) + "杯" + " $" +  String.valueOf(Integer.parseInt(drink.substring(1,3)) * quantity);

        return send;
    }

    public void checkCompleteOrder(){
        send_string = order_data();
        if(ice!=null && sweet!=null && quantity!=0)
            btn_continue.setEnabled(true);
        else btn_continue.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,OrderActivity.class));
        finish();
    }
}