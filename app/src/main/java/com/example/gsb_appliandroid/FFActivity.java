package com.example.gsb_appliandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class FFActivity extends MainActivity implements View.OnClickListener {//,AdapterView.OnItemSelectedListener
    SQLiteDatabase bd;
    DatabaseHelperHF db;
    private Spinner spinner;
    EditText txtRepas,txtETP,txtNuitee,txtKM;
    Button btnAjouter,btnSuppr,btnModif,btnRetourMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ff_main);
        init();
        bd = openOrCreateDatabase("gsb-android.db",Context.MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS fraisForfait(id integer primary key autoincrement," +
                "libelle TEXT,quantite TEXT)");
        bd.execSQL("CREATE TABLE IF NOT EXISTS typeFraisForfait(id TEXT primary key," +
                "libelle TEXT,montant TEXT)");
        bd.execSQL("REPLACE INTO typeFraisForfait(id,libelle,montant) VALUES('ETP','Forfait Etape','110.00')," +
                "('KM','Frais Kilometrique','0.62'),('NUI','Nuitée Hôtel','80.00'),('REP','Repas Restaurant','25.00')");
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

/*    private ArrayList<String> getAllFrais() {
        ArrayList<String> list = new ArrayList<String>();

        bd.beginTransaction();
        try {
            String selectQuery = "SELECT * FROM typeFraisForfait";

            Cursor cursor = bd.rawQuery(selectQuery, null);

            if (cursor.getCount() > 0) {

                while (cursor.moveToNext()) {
                    String pid = cursor.getString(cursor.getColumnIndex("id"));
                    String p_libelle = cursor.getString(cursor.getColumnIndex("libelle"));
                    String p_montant = cursor.getString(cursor.getColumnIndex("montant"));
                    list.add(pid);
                    list.add(p_libelle);
                    list.add(p_montant);
                }
            }
            bd.setTransactionSuccessful();
        } catch(Exception e){
            e.printStackTrace();
        }

        finally{
            bd.endTransaction();
            bd.close();
        }

        return list;
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

    private void init() {
        txtKM = (EditText)findViewById(R.id.txtKm);
        txtRepas=(EditText)findViewById(R.id.txtRepas);
        txtETP=(EditText)findViewById(R.id.txtETP);
        txtNuitee=(EditText)findViewById(R.id.txtNuitee);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnModif = (Button) findViewById(R.id.btnModif);
        btnSuppr = (Button) findViewById(R.id.btnSupprimer);
        btnRetourMenu = (Button)findViewById(R.id.btnRetourMenu);
        btnAjouter.setOnClickListener(this);
        btnModif.setOnClickListener(this);
        btnSuppr.setOnClickListener(this);
        btnRetourMenu.setOnClickListener(this);
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
                         bd.execSQL("INSERT INTO fraisForfait(libelle,quantite) VALUES('KM','" + txtKM.getText()+"'" +
                                 "),('ETP','" + txtETP.getText()+ "'),('REP','"+ txtRepas.getText() + "'),('NUI','" + txtNuitee.getText() + "')");
                         //INSERT INTO fraisForfait(libelle,montant) VALUES("KM","5"),("ETP","5"),("NUI","5"),("REP","5")
                         AfficheMessage("Succès", "Information ajoutée");
                         videTexte();
                         break;
                     case R.id.btnModif:
                         break;
                     case R.id.btnSupprimer:
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


