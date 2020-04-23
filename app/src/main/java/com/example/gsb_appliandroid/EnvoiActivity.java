package com.example.gsb_appliandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnvoiActivity extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.envoi_main);
        init();
    }

    private void init(){
        Button btnRetourMenu = (Button) findViewById(R.id.btnRetourMenu);
        btnRetourMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRetourMenu:
                MainActivity retour = new MainActivity();
                retour.setVisible(true);
                finish();
                break;
        }
    }
}