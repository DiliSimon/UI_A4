package com.uima.joanne.navfragments;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String transaction_amount = intent.getStringExtra("transaction_amount");
        final String transaction_type = intent.getStringExtra("transaction_type");
        final String transaction_date = intent.getStringExtra("transaction_date");

        TextView transaction_amount_view = (TextView) findViewById(R.id.transaction_amount);
        TextView transaction_type_view = (TextView) findViewById(R.id.transaction_type);
        TextView transaction_date_view = (TextView) findViewById(R.id.transaction_date);

        transaction_amount_view.setText(transaction_amount);
        transaction_date_view.setText(transaction_date);
        transaction_type_view.setText(transaction_type);

        final Button finish = findViewById(R.id.finish_button);
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
