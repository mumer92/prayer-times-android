package com.metinkale.prayerapp.kaza;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.metinkale.prayer.R;
import com.metinkale.prayerapp.BaseActivity;

public class Main extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaza_main);

        ViewGroup vg = (ViewGroup) findViewById(R.id.main);

        int ids[] = new int[]{R.string.sabah, R.string.ogle, R.string.ikindi, R.string.aksam, R.string.yatsi, R.string.vitr};
        for (int i = 0; i < 6; i++) {
            View v = vg.getChildAt(i);
            TextView name = (TextView) v.findViewById(R.id.text);
            final EditText nr = (EditText) v.findViewById(R.id.nr);
            Button plus = (Button) v.findViewById(R.id.plus);
            Button minus = (Button) v.findViewById(R.id.minus);

            name.setText(ids[i]);

            plus.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    int i = 0;
                    try {
                        i = Integer.parseInt(nr.getText().toString());
                    } finally {
                        i++;
                    }

                    nr.setText(i + "");

                }
            });

            minus.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    int i = 0;
                    try {
                        i = Integer.parseInt(nr.getText().toString());
                    } finally {
                        i--;
                    }

                    nr.setText(i + "");
                }
            });

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        ViewGroup vg = (ViewGroup) findViewById(R.id.main);
        SharedPreferences prefs = getSharedPreferences("kaza", 0);
        for (int i = 0; i < 6; i++) {
            View v = vg.getChildAt(i);
            EditText nr = (EditText) v.findViewById(R.id.nr);
            nr.setText(prefs.getString("count" + i, "0"));

        }
    }

    @Override
    public void onPause() {
        super.onPause();

        ViewGroup vg = (ViewGroup) findViewById(R.id.main);
        SharedPreferences.Editor edit = getSharedPreferences("kaza", 0).edit();
        for (int i = 0; i < 6; i++) {
            View v = vg.getChildAt(i);
            EditText nr = (EditText) v.findViewById(R.id.nr);
            edit.putString("count" + i, nr.getText().toString());

        }

        edit.apply();
    }

    @Override
    public boolean setNavBar() {
        setNavBarColor(0xffffffff);
        return true;
    }
}
