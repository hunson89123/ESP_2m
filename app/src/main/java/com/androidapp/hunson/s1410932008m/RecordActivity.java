package com.androidapp.hunson.s1410932008m;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {
    TextView txv_order_record, txv_price_record, txv_time_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setTitle(getString(R.string.app_record));
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        txv_order_record = findViewById(R.id.txv_order_record);
        txv_price_record = findViewById(R.id.txv_price_record);
        txv_time_record = findViewById(R.id.txv_time_record);

        txv_order_record.setText(read());
        txv_price_record.setText("$" + read_price());
        txv_time_record.setText("上次點餐時間："+getOrderTime());
    }

    public String read() {
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        return form.getString("shopping_record", "尚無紀錄");
    }

    public int read_price() {
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        return form.getInt("shopping_record_price", 0);
    }

    public String getOrderTime(){
        SharedPreferences form = getSharedPreferences("Data", MODE_PRIVATE);
        return form.getString("shopping_record_time", "----/--/-- --:--:--");
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
