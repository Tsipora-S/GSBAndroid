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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;


public class FFActivity extends MainActivity implements View.OnClickListener {//,AdapterView.OnItemSelectedListener
    SQLiteDatabase bd;
    DatabaseHelperFF db;
    private Spinner spinner;
    EditText txtRepas,txtETP,txtNuitee,txtKM;
    Button btnAjouter,btnSuppr,btnModif,btnRetourMenu;
    CheckBox checkBox,checkBox1;
    ListView leFrais;
    ArrayList<String> listItem;
    ArrayAdapter adapter,adapter1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ff_main);
        db = new DatabaseHelperFF(this);
        listItem = new ArrayList<>();
        init();
        viewData();
        bd = openOrCreateDatabase("gsb-android.db",Context.MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS fraisForfait(id integer primary key autoincrement," +
                "libelle TEXT,quantite TEXT)");
        bd.execSQL("CREATE TABLE IF NOT EXISTS typeFraisForfait(id TEXT primary key," +
                "libelle TEXT,montant TEXT)");
        bd.execSQL("REPLACE INTO typeFraisForfait(id,libelle,montant) VALUES('ETP','Forfait Etape','110.00')," +
                "('KM','Frais Kilometrique','0.62'),('NUI','Nuitée Hôtel','80.00'),('REP','Repas Restaurant','25.00')");
        leFrais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                String text = leFrais.getItemAtPosition(i).toString();
                //Toast.makeText(FHFActivity.this, "" +text, Toast.LENGTH_SHORT).show();
                //view.getTag();
                //on créé une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(FFActivity.this);
                adb.setTitle("Sélection Item");
                final EditText input = new EditText(FFActivity.this);
                input.setText(text);
                adb.setView(input);
                adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String edit = input.getText().toString();
                        listItem.set(i,edit);
                    }
                });
                adb.setNegativeButton("Annuler",null);
                adb.show();
            }
        });
        //db = new DatabaseHelperHF(this);
    //    remplissageSpinner();
        // Spinner click listener
       // spinner.setOnItemSelectedListener(this);
//**        loadSpinnerData();

    }
    /*public void remplissageSpinner(){
        spinner = (Spinner) findViewById(R.id.listeDeroulante);
        ArrayList<String> ListPro = getAllFrais();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ListPro);
        spinner.setAdapter(adapter);
    }*/
    /**
     * Function to load the spinner data from SQLite database
     * */
/*    private void loadSpinnerData() {
        // Spinner Drop down elements
        List<String> lables = (List<String>) getAllFrais();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, lables);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }*/
    private void viewData() {
        final Cursor cursor = db.listeFF();
        if (cursor.getCount() == 0) {
            Toast.makeText(FFActivity.this, "no data To Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String libelle= cursor.getString(1);
                String quantite=cursor.getString(2);
                listItem.add(libelle+"                          "+quantite);
                /*final CheckBox check = new CheckBox(FFActivity.this);
                check.setSelected(true);
                check.setVisibility(View.VISIBLE);*/
            }
            //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice, listItem);
            leFrais.setAdapter(adapter);
            leFrais.setChoiceMode(leFrais.CHOICE_MODE_SINGLE);
            leFrais.setItemChecked(0,true);
            leFrais.getCheckedItemPosition();
        }
    }

    public int Id(final int i){
        String text = leFrais.getItemAtPosition(i).toString();
        int longueur = text.length();
        final Cursor cursor = db.listeFF();
        cursor.moveToPosition(i);
        int id = cursor.getInt(0);
        return id;
    }

    private void init() {
        txtKM = (EditText)findViewById(R.id.txtKm);
        txtRepas=(EditText)findViewById(R.id.txtRepas);
        txtETP=(EditText)findViewById(R.id.txtETP);
        txtNuitee=(EditText)findViewById(R.id.txtNuitee);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnModif = (Button) findViewById(R.id.btnModif2);
        btnSuppr = (Button) findViewById(R.id.btnSupprimer2);
        btnRetourMenu = (Button)findViewById(R.id.btnRetourMenu);
        btnAjouter.setOnClickListener(this);
        btnModif.setOnClickListener(this);
        btnSuppr.setOnClickListener(this);
        btnRetourMenu.setOnClickListener(this);
        leFrais = findViewById(R.id.listFF);
        //checkBox=(CheckBox)findViewById(R.id.checkBox3);
        //checkBox1=(CheckBox)findViewById(R.id.checkBox);
    }

             @Override
             public void onClick(View v) {
                 switch (v.getId()) {
                     case R.id.btnAjouter:
                         if (txtKM.getText().toString().trim().length() == 0 ||
                                 txtRepas.getText().toString().trim().length() == 0 ||
                                 txtETP.getText().toString().trim().length() == 0||
                                 txtNuitee.getText().toString().trim().length() == 0) {
                             AfficheMessage("Erreur", "Entrer toutes les valeurs");
                             return;
                         }
                         bd.execSQL("INSERT INTO fraisForfait(libelle,quantite) VALUES('ETP','" + txtETP.getText()+ "')," +
                                 "('KM','" + txtKM.getText()+"'),('NUI','" + txtNuitee.getText() + "'),('REP','"+ txtRepas.getText() + "')");
                         AfficheMessage("Succès", "Information ajoutée");
                         videTexte();
                         break;
                     case R.id.btnModif2:
                         int positionCheck1 = leFrais.getCheckedItemPosition();
                         String fraisCheck1=leFrais.getItemAtPosition(positionCheck1).toString();
                         if(fraisCheck1.equals("")){
                             AfficheMessage("Erreur", "Entrer les informations");
                             return;
                         }else{
                             int longueur=fraisCheck1.length();
                             String libelle=fraisCheck1.substring(0, longueur-22);
                             String quantite=fraisCheck1.substring(longueur-3, longueur);
                             int id = Id(positionCheck1);
                             bd.execSQL("UPDATE fraisForfait SET libelle='"+libelle+"' , quantite='"+quantite+"' WHERE id='"+id+"'");
                             adapter.notifyDataSetChanged();
                             AfficheMessage("Succès", "Informations modifiées");
                         }
                         break;
                     case R.id.btnSupprimer2:
                         int positionCheck = leFrais.getCheckedItemPosition();
                         String fraisCheck=leFrais.getItemAtPosition(positionCheck).toString();
                         adapter.remove(fraisCheck);
                         int id = Id(positionCheck);
                         bd.execSQL("DELETE FROM fraisForfait WHERE id='"+id+"'");
                         adapter.notifyDataSetChanged();
                         AfficheMessage("Succès","Informations supprimées");
                         break;
                     case R.id.btnRetourMenu:
                         MainActivity retour = new MainActivity();
                         retour.setVisible(true);
                         finish();
                         break;
                 }
             }
    public void videTexte()
    {
        txtKM.setText("");
        txtRepas.setText("");
        txtETP.setText("");
        txtNuitee.setText("");
    }
};


