package com.example.gsb_appliandroid;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class FFActivity extends MainActivity {
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.FFActivity);
         Spinner listeDeroulante = (Spinner) findViewById(R.id.listeDeroulante);
         String [] itemArray = {"element1","element2","element3"};
         Spinner spin = (Spinner) findViewById(R.id.listeDeroulante);
         ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);
         spin.setAdapter(adapter2);
         spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                 Toast.makeText(getApplicationContext(), "id selectionné : " + String.valueOf(id), Toast.LENGTH_LONG).show();
                 @Override
                 public void onNothingSelected(AdapterView<?> adapterView) { } });
             Button btnAjouter = (Button) findViewById(R.id.btnAjouter);
             Button btnModif = (Button) findViewById(R.id.btnModif);
             Button btnSuppr = (Button) findViewById(R.id.btnSuppr);
         btnAjouter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         })
         btnModif.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     finish();
                 }
             })
         btnSuppr.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     finish();
                 }
             })
         }
     }
}