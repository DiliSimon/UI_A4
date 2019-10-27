package com.uima.joanne.navfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ConfirmActivity extends AppCompatActivity {

    DecimalFormat money_format = new DecimalFormat("$0.00");
    final int DEPOSIT = 1;
    final int WITHDRAW = 2;
    String deposit_statement = " will be deposited into your account";
    String withdraw_statement = " will be withdrawn from your account";
    double new_balance = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        TextView change_amount_view = (TextView) findViewById(R.id.withdraw_amount);
        TextView balance_amount_view = (TextView) findViewById(R.id.balance);

        Intent intent = getIntent();
        final double change_amount = intent.getDoubleExtra("change_amount", 0.0);
        final int transaction_type = intent.getIntExtra("transaction_type", 0);

        SharedPreferences sharedPref = this.getSharedPreferences("balance_value", Context.MODE_PRIVATE);
        double balance = Double.parseDouble(sharedPref.getString("balance", "0.00"));


        // Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter;
        if(transaction_type == DEPOSIT) {
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.deposit_array, android.R.layout.simple_spinner_item);
        } else {
            adapter = ArrayAdapter.createFromResource(this,
                    R.array.withdraw_array, android.R.layout.simple_spinner_item);
        }
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        if (transaction_type == DEPOSIT) {

            new_balance = balance + change_amount;
            change_amount_view.setText(money_format.format(change_amount) + deposit_statement);
            balance_amount_view.setText(money_format.format(new_balance));

        } else if (transaction_type == WITHDRAW) {

            //we don't want to automatically change new_balance in the case that we get a negative
            //balance, so first set it as our original balance

            new_balance = balance;
            if (balance - change_amount > 0) {

                new_balance = balance - change_amount; //since it's a valid amount, actually perform transaction
                change_amount_view.setText(money_format.format(change_amount) + withdraw_statement);
                balance_amount_view.setText(money_format.format(new_balance));

            } else {

                change_amount_view.setText("Insufficient balance, will not withdraw");
                balance_amount_view.setText(money_format.format(new_balance));

            }

        } else {
            //This should never happen unless you change the constants declared or make your own transaction,
            //neither of which you should be doing.
            try {
                invalidTransaction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
         * Code for button onClickListeners
         */

        final ImageButton confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> amount = getArrayPrefs("history");
                ArrayList<String> date = getDateArray("date");
                ArrayList<String> type = getTypeArray("type");
                String history_amount;
                if(transaction_type == DEPOSIT){
                    history_amount = Double.toString(change_amount);
                }else {
                    history_amount = "-";
                    history_amount += Double.toString(change_amount);
                }

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date d = new Date();
                Spinner mySpinner = (Spinner) findViewById(R.id.spinner2);
                String t = mySpinner.getSelectedItem().toString();

                SharedPreferences sharedPref = getSharedPreferences("balance_value", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("balance", Double.toString(new_balance));
                editor.commit();

                amount.add(history_amount);
                date.add(formatter.format(d));
                type.add(t);
                setArrayPrefs("history", amount);
                setDateArray("date", date);
                setTypeArray("type", type);
                startActivity(new Intent(ConfirmActivity.this, MainActivity.class));
            }
        });

        final ImageButton cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ConfirmActivity.this, MainActivity.class));
            }
        });

    }

    public void invalidTransaction() throws IOException {
        throw new IOException("An invalid transaction was attempted. Something went terribly wrong, " +
                "tell the TAs :(");
    }

    public void setArrayPrefs(String arrayName, ArrayList<String> array) {
        SharedPreferences prefs = getSharedPreferences("transaction_history", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public void setDateArray(String arrayName, ArrayList<String> array) {
        SharedPreferences prefs = getSharedPreferences("transaction_date", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public void setTypeArray(String arrayName, ArrayList<String> array) {
        SharedPreferences prefs = getSharedPreferences("transaction_type", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.apply();
    }

    public ArrayList<String> getArrayPrefs(String arrayName) {
        SharedPreferences prefs = getSharedPreferences("transaction_history", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }

    public ArrayList<String> getDateArray(String arrayName) {
        SharedPreferences prefs = getSharedPreferences("transaction_date", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }

    public ArrayList<String> getTypeArray(String arrayName) {
        SharedPreferences prefs = getSharedPreferences("transaction_type", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        ArrayList<String> array = new ArrayList<>(size);
        for(int i=0;i<size;i++)
            array.add(prefs.getString(arrayName + "_" + i, null));
        return array;
    }


}
