package com.uima.joanne.navfragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class ContentFrag1 extends Fragment {
    double curr_input = 0.0;
    DecimalFormat decimal_format = new DecimalFormat("0.00");
    DecimalFormat money_format = new DecimalFormat("$0.00");
    double balance_value = 0.0;
    final int DEPOSIT = 1;
    final int WITHDRAW = 2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sharedPref = this.getActivity().getSharedPreferences("balance_value", Context.MODE_PRIVATE);
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_frag1, container, false);
        final TextView balance = (TextView) v.findViewById(R.id.balance);
        final EditText curr_input_view = (EditText) v.findViewById(R.id.input_amount);
        curr_input_view.setText(decimal_format.format(curr_input));

        String stored_balance = sharedPref.getString("balance", "0.00");
        balance_value = Double.parseDouble(stored_balance);
        balance.setText(money_format.format(balance_value));

        ImageButton one_cent_btn = v.findViewById(R.id.one_cent);
        one_cent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOneCent(curr_input_view);
                //make your toast here
            }
        });

        ImageButton five_cent_btn = v.findViewById(R.id.five_cent);
        five_cent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFiveCent(curr_input_view);
                //make your toast here
            }
        });

        ImageButton ten_cent_btn = v.findViewById(R.id.ten_cent);
        ten_cent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTenCent(curr_input_view);
                //make your toast here
            }
        });

        ImageButton twentyfive_cent_btn = v.findViewById(R.id.twentyfive_cent);
        twentyfive_cent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTwentyfiveCent(curr_input_view);
                //make your toast here
            }
        });

        ImageButton one_dollar_btn = v.findViewById(R.id.one_dollar);
        one_dollar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOneDollar(curr_input_view);
                //make your toast here
            }
        });


        ImageButton five_dollar_btn = v.findViewById(R.id.five_dollar);
        five_dollar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFiveDollar(curr_input_view);
                //make your toast here
            }
        });

        ImageButton ten_dollar_btn = v.findViewById(R.id.ten_dollar);
        ten_dollar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTenDollar(curr_input_view);
                //make your toast here
            }
        });

        final ImageButton deposit = v.findViewById(R.id.deposit);
        deposit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int transaction_type = DEPOSIT;
                curr_input = Double.parseDouble(curr_input_view.getText().toString());
                //necessary to catch manually entered amounts
                Intent intent = new Intent(getActivity(), ConfirmActivity.class);

                intent.putExtra("change_amount", curr_input); //amount to change balance by
                intent.putExtra("transaction_type", transaction_type); //what type of transaction

                startActivity(intent);
            }
        });

        final ImageButton withdraw = v.findViewById(R.id.withdraw);
        withdraw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int transaction_type = WITHDRAW;
                curr_input = Double.parseDouble(curr_input_view.getText().toString());
                //necessary to catch manually entered amounts
                Intent intent = new Intent(getActivity(), ConfirmActivity.class);

                intent.putExtra("change_amount", curr_input);
                intent.putExtra("transaction_type", transaction_type);

                startActivity(intent);
            }
        });

        return v;


    }
    // Called at the start of the visible lifetime.
    @Override
    public void onStart(){
        super.onStart();
        Log.d ("Content Fragment", "onStart");
        // Apply any required UI change now that the Fragment is visible.
    }

    // Called at the start of the active lifetime.
    @Override
    public void onResume(){
        super.onResume();
        Log.d ("Content Fragment", "onResume");
        // Resume any paused UI updates, threads, or processes required
        // by the Fragment but suspended when it became inactive.
    }

    // Called at the end of the active lifetime.
    @Override
    public void onPause(){
        Log.d ("Content Fragment", "onPause");
        // Suspend UI updates, threads, or CPU intensive processes
        // that don't need to be updated when the Activity isn't
        // the active foreground activity.
        // Persist all edits or state changes
        // as after this call the process is likely to be killed.
        super.onPause();
    }

    // Called to save UI state changes at the
    // end of the active lifecycle.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d ("Content Fragment", "onSaveInstanceState");
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate, onCreateView, and
        // onCreateView if the parent Activity is killed and restarted.
        super.onSaveInstanceState(savedInstanceState);
    }

    // Called at the end of the visible lifetime.
    @Override
    public void onStop(){
        Log.d ("Content Fragment", "onStop");
        // Suspend remaining UI updates, threads, or processing
        // that aren't required when the Fragment isn't visible.
        super.onStop();
    }

    // Called when the Fragment's View has been detached.
    @Override
    public void onDestroyView() {
        Log.d ("Content Fragment", "onDestroyView");
        // Clean up resources related to the View.
        super.onDestroyView();
    }

    // Called at the end of the full lifetime.
    @Override
    public void onDestroy(){
        Log.d ("Content Fragment", "onDestroy");
        // Clean up any resources including ending threads,
        // closing database connections etc.
        super.onDestroy();
    }

    // Called when the Fragment has been detached from its parent Activity.
    @Override
    public void onDetach() {
        Log.d ("Content Fragment", "onDetach");
        super.onDetach();
    }

    public void addOneCent(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 0.01;
        curr_input_view.setText(decimal_format.format(curr_input));
    }

    public void addFiveCent(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 0.05;
        curr_input_view.setText(decimal_format.format(curr_input));
    }

    public void addTenCent(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 0.1;
        curr_input_view.setText(decimal_format.format(curr_input));
    }

    public void addTwentyfiveCent(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 0.25;
        curr_input_view.setText(decimal_format.format(curr_input));
    }

    public void addOneDollar(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 1.00;
        curr_input_view.setText(decimal_format.format(curr_input));
    }

    public void addFiveDollar(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 5.00;
        curr_input_view.setText(decimal_format.format(curr_input));
    }

    public void addTenDollar(EditText curr_input_view) {
        //final EditText input = (EditText) view.findViewById(R.id.input_amount);
        curr_input += 10.00;
        curr_input_view.setText(decimal_format.format(curr_input));
    }
}


