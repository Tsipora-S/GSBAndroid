package com.example.gsb_appliandroid;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends CnxActivity implements View.OnClickListener {
    Button fraisForfait,synthese,envoi,parametres,fraisHorsForfait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {

        fraisForfait = findViewById(R.id.btnFF);
        synthese = findViewById(R.id.btnSynt);
        envoi = findViewById(R.id.btnEnvoi);
        parametres = findViewById(R.id.btnParam);
        fraisHorsForfait = findViewById(R.id.btnFHF);
        fraisForfait.setOnClickListener(this);
        synthese.setOnClickListener(this);
        envoi.setOnClickListener(this);
        parametres.setOnClickListener(this);
        fraisHorsForfait.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnFF:
                        Intent intent = new Intent(MainActivity.this, FFActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.btnSynt:
                        Intent intent2 = new Intent(MainActivity.this, SyntheseActivity.class);
                        MainActivity.this.startActivity(intent2);
                        break;
                    case R.id.btnEnvoi:
                        Intent intent3 = new Intent(MainActivity.this, EnvoiActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.btnParam:
                        Intent intent4 = new Intent(MainActivity.this, ParametresActivity.class);
                        String loginnn = getIntent().getStringExtra("login");
                        String mdppp = getIntent().getStringExtra("mdp");
                        intent4.putExtra("login",loginnn);
                        intent4.putExtra("mdp",mdppp);
                        startActivity(intent4);
                        break;
                    case R.id.btnFHF:
                        Intent intent5 = new Intent(MainActivity.this, FHFActivity.class);
                        MainActivity.this.startActivity(intent5);
                        break;


                }

            }
    public void AfficheMessage(String titre,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
