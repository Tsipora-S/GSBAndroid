package com.example.gsb_appliandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SyntheseActivity extends MainActivity {
    DatabaseHelperHF db;
    DatabaseHelperFF bd;
    ListView leLibelle,leFrais;
    ArrayList<String> listItem;
    ArrayList<String> listItem1;
    ArrayList<Float> list;
    ArrayList<Integer> list1;
    ArrayAdapter adapter,adapter1;
    TextView sommeFF,sommeFHF,montantTotal;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synthese_main);
        db = new DatabaseHelperHF(this);
        bd = new DatabaseHelperFF(this);
        listItem = new ArrayList<>();
        listItem1 = new ArrayList<>();
        list=new ArrayList<>();
        list1=new ArrayList<>();
        init();
        //FHFActivity fhf=new FHFActivity();
        //fhf.viewData();
        viewDataFF();
        viewDataHF();
        getCalculFF();
        getCalculHF();
        getMontantTotal();
        leFrais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                String text = leFrais.getItemAtPosition(i).toString();
                Toast.makeText(SyntheseActivity.this, "" +text, Toast.LENGTH_SHORT).show();
            }
        });
        leLibelle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                String text = leLibelle.getItemAtPosition(i).toString();
                Toast.makeText(SyntheseActivity.this, "" +text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        Button btnRetourMenu = findViewById(R.id.btnRetourMenu);
        btnRetourMenu.setOnClickListener(this);
        leLibelle = findViewById(R.id.listFHF);
        leFrais = findViewById(R.id.listFF);
        sommeFF = findViewById(R.id.txtSommeFF);
        sommeFHF = findViewById(R.id.txtSommeFHF);
        montantTotal = findViewById(R.id.txtMontantTotal);
    }
    private void viewDataHF() {
        final Cursor cursor = db.listeFHF();
        if (cursor.getCount() == 0) {
            Toast.makeText(SyntheseActivity.this, "no data To Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String date = cursor.getString(3);
                String libelle = cursor.getString(1);
                String montant = cursor.getString(2);
                listItem.add(date + "   " + libelle + "     " + montant);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listItem);
            leLibelle.setAdapter(adapter);
            leLibelle.setChoiceMode(leLibelle.CHOICE_MODE_SINGLE);
            leLibelle.setItemChecked(0, true);
            leLibelle.getCheckedItemPosition();
        }
    }
    private void viewDataFF() {
        final Cursor cursor = bd.listeFF();
        if (cursor.getCount() == 0) {
            Toast.makeText(SyntheseActivity.this, "no data To Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String libelle= cursor.getString(1);
                String quantite=cursor.getString(2);
                listItem1.add(libelle+"                          "+quantite);
            }
            //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, listItem1);
            leFrais.setAdapter(adapter1);
            leFrais.setChoiceMode(leFrais.CHOICE_MODE_SINGLE);
            leFrais.setItemChecked(0,true);
            leFrais.getCheckedItemPosition();
        }
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
    private void getCalculFF(){
        getQuantiteFF();
        getMontantFF();
        float somme = 0;
        for(int i=0;i<list.size();i++){
            somme=somme+list.get(i)*list1.get(i);
        }
        sommeFF.setText(String.valueOf(somme));
    }
    private ArrayList<Float> getMontantFF(){
        final Cursor cursor = bd.typeFF();
        while (cursor.moveToNext()) {
            float montant = cursor.getFloat(2);
            list.add(montant);
        }
        return list;
    }
    private ArrayList<Integer> getQuantiteFF(){
        final Cursor cursor = bd.listeFF();
        while (cursor.moveToNext()) {
            int quantite = cursor.getInt(2);
            list1.add(quantite);
        }
        return list1;
    }
    private void getCalculHF(){
        final Cursor cursor = db.listeFHF();
        float sommeHF=0;
        while (cursor.moveToNext()) {
            float montant = cursor.getFloat(2);
            sommeHF=sommeHF+montant;
        }
        sommeFHF.setText(String.valueOf(sommeHF));
    }
    private void getMontantTotal(){
        float mt= Float.parseFloat(sommeFF.getText().toString()) + Float.parseFloat(sommeFHF.getText().toString());
        montantTotal.setText(String.valueOf(mt));
    }
}
