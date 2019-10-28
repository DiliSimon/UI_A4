package com.uima.joanne.navfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final SharedPreferences sharedPref = this.getSharedPreferences("setting", Context.MODE_PRIVATE);
        int max = sharedPref.getInt("max_withdraw", 0);

        Button confirm = (Button) findViewById(R.id.setting_confirm);
        Button cancel = (Button) findViewById(R.id.setting_cancel);
        final EditText min_balance = (EditText) findViewById((R.id.editText2));
        final EditText max_withdraw = (EditText) findViewById(R.id.max_withdrawl);
        //max_withdraw.setHint(max);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                String input_1 = min_balance.getText().toString();
                String input_2 = max_withdraw.getText().toString();
                if(input_1.isEmpty()){
                    input_1 = "100";
                }
                if(input_2.isEmpty()){
                    input_2 = "10";
                }
                int min = Integer.parseInt(input_1);
                int max = Integer.parseInt(input_2);
                editor.putInt("min_balance", min);
                editor.putInt("max_withdraw", max);
                editor.commit();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
