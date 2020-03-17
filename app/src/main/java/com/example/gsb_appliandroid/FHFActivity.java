package com.example.gsb_appliandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FHFActivity extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ff_main);
        Button btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
