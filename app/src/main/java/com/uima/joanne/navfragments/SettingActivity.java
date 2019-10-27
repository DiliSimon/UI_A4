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

        Button confirm = (Button) findViewById(R.id.setting_confirm);
        Button cancel = (Button) findViewById(R.id.setting_cancel);
        final EditText min_balance = (EditText) findViewById((R.id.editText2));
        final EditText max_withdrawl = (EditText) findViewById(R.id.max_withdrawl);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                int min = Integer.parseInt(min_balance.getText().toString());
                int max = Integer.parseInt(max_withdrawl.getText().toString());
                editor.putInt("min_balance", min);
                editor.putInt("max_withdral", max);
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
