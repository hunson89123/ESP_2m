package com.androidapp.hunson.s1410932008m;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class HomeActivity extends AppCompatActivity {
    Button btn_record, btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        btn_record = findViewById(R.id.btn_record);
        btn_start = findViewById(R.id.btn_order);
    }

    public void btnRecord(View v) {
        startActivity(new Intent(this,RecordActivity.class));
        finish();
    }

    public void btnOrder(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.remove("shopping_list");
        editor.remove("shopping_list_price");
        editor.commit();
        startActivity(new Intent(this, OrderActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}