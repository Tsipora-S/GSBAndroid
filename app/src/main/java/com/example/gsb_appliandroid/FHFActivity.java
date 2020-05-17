package com.example.gsb_appliandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class FHFActivity extends MainActivity implements View.OnClickListener {
    SQLiteDatabase bd;
    DatabaseHelperHF db;
    EditText txtLibelle,txtMontant,txtDate;
    Button btnAjouter, btnModifier, btnSupprimer, btnRetourMenu;
    ListView leLibelle;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fhf_main);
        db = new DatabaseHelperHF(this);
        listItem = new ArrayList<>();
        init();
        viewData();
        bd = openOrCreateDatabase("gsb-android.db", Context.MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS fraisHorsForfait(id integer primary key autoincrement," +
                "libelle TEXT,montant TEXT,date TEXT);");
        leLibelle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                String text = leLibelle.getItemAtPosition(i).toString();
                AlertDialog.Builder adb = new AlertDialog.Builder(FHFActivity.this);
                adb.setTitle("Sélection Item");
                final EditText input = new EditText(FHFActivity.this);
                input.setText(text);
                adb.setView(input);
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String edit = input.getText().toString();
                        listItem.set(i, edit);
                    }
                });
                adb.setNegativeButton("Annuler", null);
                adb.show();
            }
        });
    }

    private void init() {
        txtLibelle = (EditText) findViewById(R.id.txtLibelle);
        leLibelle = findViewById(R.id.listFHF);
        txtMontant = (EditText) findViewById(R.id.txtMontant);
        txtDate = (EditText) findViewById(R.id.txtDate);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnModifier = (Button) findViewById(R.id.btnModif);
        btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
        btnRetourMenu = (Button) findViewById(R.id.btnRetourMenu);
        btnAjouter.setOnClickListener(this);
        btnModifier.setOnClickListener(this);
        btnSupprimer.setOnClickListener(this);
        btnRetourMenu.setOnClickListener(this);
    }

    private void viewData() {
        //listItem.setEditable(true);
        /*leLibelle.clearFocus();
        leLibelle.setFocusable(true);
        leLibelle.setEnabled(true);*/
        final Cursor cursor = db.listeFHF();
        if (cursor.getCount() == 0) {
            Toast.makeText(FHFActivity.this, "no data To Show", Toast.LENGTH_SHORT).show();
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
            //adapter.setNotifyOnChange(true);
            //adapter.notifyDataSetChanged();
        }
    }

    public int Id(final int i){
        final Cursor cursor = db.listeFHF();
        cursor.moveToPosition(i);
        int id = cursor.getInt(0);
        return id;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAjouter:
                if (txtLibelle.getText().toString().trim().length() == 0 ||
                        txtMontant.getText().toString().trim().length() == 0 ||
                        txtDate.getText().toString().trim().length() == 0) {
                    AfficheMessage("Erreur", "Entrer toutes les valeurs");
                    return;
                }
                bd.execSQL("INSERT INTO fraisHorsForfait(libelle,montant,date) VALUES('" + txtLibelle.getText() +
                        "','" + txtMontant.getText() + "','" + txtDate.getText() + "');");
                AfficheMessage("Succès", "Information ajoutée");
                videTexte();
                break;
            case R.id.btnModif:
                int positionCheck1 = leLibelle.getCheckedItemPosition();
                String fraisCheck1 = leLibelle.getItemAtPosition(positionCheck1).toString();
                if (fraisCheck1.equals("")) {
                    AfficheMessage("Erreur", "Entrer les informations");
                    return;
                } else {
                    String date = fraisCheck1.substring(0, 10);
                    int longueur = fraisCheck1.length();
                    String libelle = fraisCheck1.substring(13, longueur - 10);
                    String montant = fraisCheck1.substring(longueur - 5, longueur);
                    int id = Id(positionCheck1);
                    bd.execSQL("UPDATE fraisHorsForfait SET libelle='"+libelle+"', date= '"+date+"', montant= '"+montant+"' WHERE id= '"+id+"'");
                    adapter.notifyDataSetChanged();
                    AfficheMessage("Succès", "Informations modifiées");
                }
                break;
            case R.id.btnSupprimer:
                int positionCheck = leLibelle.getCheckedItemPosition();
                String fraisCheck = leLibelle.getItemAtPosition(positionCheck).toString();
                adapter.remove(fraisCheck);
                int id = Id(positionCheck);
                bd.execSQL("DELETE FROM fraisHorsForfait WHERE id='" + id + "'");
                adapter.notifyDataSetChanged();
                AfficheMessage("Succès", "Informations supprimées");
                break;
            case R.id.btnRetourMenu:
                MainActivity retour = new MainActivity();
                retour.setVisible(true);
                finish();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void videTexte() {
        txtLibelle.setText("");
        txtMontant.setText("");
        txtDate.setText("");
    }

    /*   public void getId(){
           int positionCheck1 = leLibelle.getCheckedItemPosition();
           String fraisCheck1=leLibelle.getItemAtPosition(positionCheck1).toString();
           String date=fraisCheck1.substring(0, 10);
           String libelle=fraisCheck1.substring(12, 27);
           int longueur=fraisCheck1.length();
           String montant=fraisCheck1.substring(longueur-5, longueur);
           //bd.execSQL("SELECT id FROM fraisHorsForfait WHERE date='"+date+"',libelle='"+libelle+"',montant='"+montant+"'");
       }*/
    public Integer getIdFHF(int positionCheck1) {
     /*DatabaseHelperHF data=new DatabaseHelperHF(this);
     SQLiteDatabase bd = data.getReadableDatabase();
     String query = "SELECT id FROM fraisHorsForfait WHERE libelle = '" + libelle +  "' AND date = '" + date + "' AND montant = '"+ montant +"'";
     Cursor cursor = bd.rawQuery(query, null);
     cursor.moveToFirst();
     String id=cursor.getString(0);
     AfficheMessage("coucou"," "+id);*/
        final Cursor cursor = db.listeFHF();
        int id1 = 0;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            AfficheMessage("coucou", " " + id);
            //if(cposition==positionCheck1){

            //}else{
            //    AfficheMessage("erreur",":-(");
            //}
         /*String idd = String.valueOf(cursor.moveToPosition(positionCheck1));
         if(id == idd) {
             AfficheMessage("coucou", "" + id+idd);
         }else{
             AfficheMessage("erreur",":-(");
         }*/
            id1=id;//-->stocker ici lid de litem coché et nn le dernier id!!
        }
        //int cposition = cursor.getPosition();---> renvoie 3 =derniere position qu'il a parcouru= ne sert a rien!!!
        AfficheMessage("coucou", " " + id1);
        return  id1;
    }
}
