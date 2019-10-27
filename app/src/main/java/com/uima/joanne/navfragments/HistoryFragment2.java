package com.uima.joanne.navfragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HistoryFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment2 newInstance(String param1, String param2) {
        HistoryFragment2 fragment = new HistoryFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String[] transaction_history = getArrayPrefs("history");
        final String[] transaction_date = getDateArray("date");
        final String[] transaction_type = getTypeArray("type");
        for(int i = 0; i < transaction_history.length; i++){
            try {
                transaction_history[i] += "\t" + transaction_date[i];
            }
            catch (Exception e){
                transaction_history[i] += "\t" + "N.A.";
            }
        }

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_fragment2, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, transaction_history);
        ListView listView = (ListView) v.findViewById(R.id.history_list);
        listView.setAdapter(adapter);

        final String[] transaction_history_2 = getArrayPrefs("history");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("transaction_amount", transaction_history_2[i]); //amount to change balance by
                intent.putExtra("transaction_type", transaction_type[i]);
                intent.putExtra("transaction_date", transaction_date[i]);
                startActivity(intent);
            }
        });

        return v;
    }

    public String[] getArrayPrefs(String arrayName) {
        SharedPreferences prefs = getActivity().getSharedPreferences("transaction_history", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String[] array = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

    public String[] getDateArray(String arrayName) {
        SharedPreferences prefs = getActivity().getSharedPreferences("transaction_date", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String[] array = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

    public String[] getTypeArray(String arrayName) {
        SharedPreferences prefs = getActivity().getSharedPreferences("transaction_type", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String[] array = new String[size];
        for(int i=0;i<size;i++)
            array[i] = (prefs.getString(arrayName + "_" + i, null));
        return array;
    }

}
