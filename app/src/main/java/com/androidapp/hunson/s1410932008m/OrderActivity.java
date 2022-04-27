package com.androidapp.hunson.s1410932008m;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class OrderActivity extends AppCompatActivity {
    Button btn_goCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle(getString(R.string.app_order));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        btn_goCart = findViewById(R.id.btn_goCart);

        SharedPreferences data = getSharedPreferences("Data", MODE_PRIVATE);
        if(!data.getString("shopping_list","").equals("")){
            btn_goCart.setVisibility(View.VISIBLE);
        }
    }

    public void drinkClick(View v) {
        Button btn = (Button) v;
        String value = btn.getText().toString();
        Intent i = new Intent(this, ChooseActivity.class);
        i.putExtra("drink_type", value);
        startActivity(i);
        finish();
    }

    public void go_shopping_cart(View v) {
        startActivity(new Intent(this, CartActivity.class));
        finish();
    }

    public void go_firstpage(View v){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }
}