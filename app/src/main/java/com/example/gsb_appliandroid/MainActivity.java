package com.example.gsb_appliandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button fraisForfait,synthese,envoi,parametres,fraisHorsForfait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fraisForfait=findViewById(R.id.btnFF);
        synthese=findViewById(R.id.btnSynt);
        envoi=findViewById(R.id.btnEnvoi);
        parametres=findViewById(R.id.btnParam);
        fraisHorsForfait=findViewById(R.id.btnFHF);

        fraisForfait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnFF:
                        Intent intent = new Intent(MainActivity.this, FFActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnSynt:
                        Intent intent2 = new Intent(MainActivity.this, SyntheseActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.btnEnvoi:
                        Intent intent3 = new Intent(MainActivity.this, EnvoiActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.btnParam:
                        Intent intent4 = new Intent(MainActivity.this, ParametresActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.btnFHF:
                        Intent intent5 = new Intent(MainActivity.this, FHFActivity.class);
                        startActivity(intent5);
                        break;


                }

            }
        });


    }
}