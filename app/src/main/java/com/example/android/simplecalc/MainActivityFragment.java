package com.example.android.simplecalc;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private ArrayAdapter<String> optionsAdapter;
    private String string1="0", string2="0", operator="#";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] ops = {
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "+",
                "-",
                "/",
                "*",
                "="
        };
        List<String> opti = new ArrayList<String>(Arrays.asList(ops));
        optionsAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.calc_textview,
                R.id.text_ops,
                opti
        );
        GridView optionsView = (GridView) rootView.findViewById(R.id.options_gridview);
        optionsView.setAdapter(optionsAdapter);

        optionsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView display = (TextView) rootView.findViewById(R.id.display_textview);
                String input = optionsAdapter.getItem(position);
                char c = input.charAt(0);

                if (c <= '9' && c >= '0' && operator.equals("#")) {
                    string1 = string1 + input;
                    display.setText(string1.substring(1));
                } else if (c == '=') {
                    int a1 = Integer.parseInt(string1);
                    int a2 = Integer.parseInt(string2);
                    float res=0;
                    //Toast.makeText(getActivity(), string1 + ' ' + string2, Toast.LENGTH_SHORT).show();
                    if (operator.equals("/")) {
                        if (a2 == 0) {
                            display.setText("Divide by Zero!");
                        } else {
                            res = (float)a1 / a2;
                            display.setText(Float.toString(res));
                        }
                    } else {
                        if (operator.equals("+")) {
                            res = a1 + a2;
                            display.setText(Float.toString(res));
                        } else if (operator.equals("-")) {
                            res = a1 - a2;
                        }
                        else if (operator.equals("*")) {

                            res = a1*a2;
                        }
                        display.setText(Float.toString(res));
                    }
                    string1 = "0";
                    string2 = "0";
                    operator = "#";

                } else if (operator.equals("#")) {
                    operator = input;
                    display.setText(string1.substring(1)+operator);
                } else {
                    string2 = string2 + input;
                    display.setText(string1.substring(1)+operator+string2.substring(1));
                }
            }
        });
        return rootView;
    }
}
