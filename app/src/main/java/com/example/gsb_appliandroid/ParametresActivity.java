package com.example.gsb_appliandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParametresActivity extends MainActivity {
    DatabaseHelperCnx db;
    TextView txtNom,txtPrenom,txtID,txtLogin,txtMdp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametres_main);
        init();
        getInfosUtilisateur();


    }

    private void init(){
        txtNom=(TextView)findViewById(R.id.recupNom);
        txtPrenom=(TextView) findViewById(R.id.recupPrenom);
        txtID=(TextView) findViewById(R.id.recupID);
        txtLogin=(TextView) findViewById(R.id.recupLogin);
        txtMdp=(TextView) findViewById(R.id.recupMDP);
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
    public void getInfosUtilisateur(){
        Intent intent4 = getIntent();
        String loginn=intent4.getStringExtra("login");
        String mdpp=intent4.getStringExtra("mdp");
        DatabaseHelperCnx db=new DatabaseHelperCnx(this);
        final Cursor cursor = db.recupLoginMdpSaisis(loginn,mdpp);
        while(cursor.moveToNext()){
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String id = cursor.getString(0);
            String login =cursor.getString(3);
            String mdp = cursor.getString(4);
            txtNom.setText(nom);
            txtPrenom.setText(prenom);
            txtID.setText(id);
            txtLogin.setText(login);
            txtMdp.setText(mdp);
        }
    }
}
