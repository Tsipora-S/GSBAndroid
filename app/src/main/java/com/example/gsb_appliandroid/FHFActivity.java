package com.example.gsb_appliandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FHFActivity extends MainActivity implements View.OnClickListener {
    SQLiteDatabase bd;
    DatabaseHelperHF db;
    EditText txtLibelle,txtLibelle1,txtLibelle2,txtMontant,txtMontant1,txtMontant2,txtDate,txtDate1,txtDate2;
    CheckBox checkBox,checkBox1;
    Button btnAjouter,btnModifier,btnSupprimer,btnRetourMenu;
    ListView leLibelle;
 /*   FHFActivity frais= new FHFActivity();
    List<FHFActivity> FraisHorsForfait;
    FraisHorsForfait=new ArrayList<FHFActivity>();*/

    fraisHF fraisHF;
    private EditText libelle1;
    private EditText montant1;
    private EditText date1;
    private boolean needRefresh;
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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = leLibelle.getItemAtPosition(i).toString();
                Toast.makeText(FHFActivity.this, "" +text, Toast.LENGTH_SHORT).show();
            }
        });
        //    this.libelle1 = (EditText)this.findViewById(R.id.txtLibelle1);
        //    this.montant1 = (EditText)this.findViewById(R.id.txtMontant1);
        //    this.date1= (EditText)this.findViewById(R.id.txtDate1);
   /*     Intent intent = this.getIntent();
       this.fraisHF = (fraisHF) intent.getSerializableExtra("fraisHF");
       this.libelle1.setText(fraisHF.getLibelle());
       this.montant1.setText(fraisHF.getMontant());
       this.date1.setText(fraisHF.getDate());*/
        //   new GetEdittext().execute();
        //  remplissageEditText();
    }
 /*   private class GetEdittext extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // dialog start
            Log.e("Currency", "3");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ArrayList<String> arr_ = new ArrayList<String>();
            arr_ = db.fetch_Edittext();
            return null;
        }

        @Override
        protected void onPostExecute(Void resul) {
            super.onPostExecute(resul);
            // dialog dismiss
            //hear set your edit text
        }
    }

    
    public void remplissageEditText(){
    try{
        String queryString1 = "SELECT libelle,montant,date from fraisHorsForfait where id='1'";
        Connection con = null;
        Statement stm1 = con.createStatement();
        ResultSet rst1 = stm1.executeQuery(queryString1);
        while (rst1.next()) {
            this.txtLibelle1.setText(rst1.getString("libelle"));
            this.txtMontant1.setText(rst1.getString("montant"));
            this.txtDate1.setText(rst1.getString("date"));
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    }*/
/*    public List<String> getAllFHF() {
        List<String> list = new ArrayList <String> ();

        Cursor c = bd.rawQuery("SELECT * FROM fraisHorsForfait", null);
        while (c.moveToNext()) {
            String libelle = c.getString(c.getColumnIndex("libelle"));
            String montant = c.getString(c.getColumnIndex("montant"));
            String date = c.getString(c.getColumnIndex("date"));
            list.add(libelle);
            list.add(montant);
            list.add(date);
        }
        return list;
        txtLibelle1=list.getText();
    }*/

    private void init() {
        txtLibelle =(EditText) findViewById(R.id.txtLibelle);
        //txtLibelle1 =(EditText) findViewById(R.id.txtLibelle1);
        leLibelle = findViewById(R.id.listFHF);
    //    final Editable leLibelle = (Editable) this.leLibelle;
       /*articleBdd.open();
        Cursor c = bd.getAllFHF();
        String[] columns = new String[]{libelle, montant, date};
        int[] to = new int[]{R.id.txtLibelle1, R.id.txtMontant1, R.id.txtDate1};
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.activity_main, c, columns, to);
        listViewArticles.setAdapter(dataAdapter);
        articleBdd.close();*/
        /*sql0=bd.execSQL("select * from fraisHorsForfait");
        new String[] {sql0};
        for(int k=1;k<=tabFrais.size();k++){
            for(int i=txtDate1;i<=txtMontant2;i++){
                String sql = "select libelle from fraisHorsForfait where id="+k;
                i=sql.toString.getText();
            }
        }*/
/*        Cursor sql =bd.rawQuery("select libelle,montant,date from fraisHorsForfait where id='1'",null);
        txtLibelle1.setText((CharSequence) sql);
        Cursor mCur=bd.rawQuery(sql,null);
        mCur.moveToFirst();
        while ( !mCur.isAfterLast()) {
            txtLibelle1= mCur.getString(mCur.getColumnIndex("libelle"));
            txtMontant1= mCur.getString(mCur.getColumnIndex("montant"));
            (String)txtDate1= mCur.getString(mCur.getColumnIndex("date"));
            mCur.moveToNext();
        }*/
//        String sql1="select montant from fraisHorsForfait where id='1'";
//        txtMontant1.setText(sql1);
//        String sql2="select date from fraisHorsForfait where id='1'";
//        txtDate1.setText(sql2);
        //txtLibelle2 =(EditText) findViewById(R.id.txtLibelle2);
        txtMontant=(EditText)findViewById(R.id.txtMontant);
        //txtMontant1=(EditText)findViewById(R.id.txtMontant1);
        //txtMontant2=(EditText) findViewById(R.id.txtMontant2);
        txtDate=(EditText)findViewById(R.id.txtDate);
        //txtDate1=(EditText)findViewById(R.id.txtDate1);
        //txtDate2=(EditText)findViewById(R.id.txtDate2);
        checkBox=findViewById(R.id.checkBox6);
        checkBox1=findViewById(R.id.checkBox5);
        btnAjouter = (Button) findViewById(R.id.btnAjouter);
        btnModifier =(Button)findViewById(R.id.btnModif);
        btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
        btnRetourMenu= (Button)findViewById(R.id.btnRetourMenu);
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
        Cursor cursor = db.listeFHF();
        if (cursor.getCount() == 0) {
            Toast.makeText(FHFActivity.this, "no data To Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(3));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            leLibelle.setAdapter(adapter);
            adapter.setNotifyOnChange(true);
            //adapter.registerDataSetObserver(observer);
            adapter.notifyDataSetChanged();
            //leLibelle.setEnabled(leLibelle.isEnabled());
            //      listItem.setEditable(true);
            //      listItem.setCellFactory(TextFieldListCell.forListView());
        }

        //leLibelle.setItemsCanFocus(true);
        //leLibelle.setOnItemClickListener((AdapterView.OnItemClickListener) listItem);
        //leLibelle.setClickable(true);
    }
    protected Object value;

    public void setValue(Object newValue) {
        this.value = newValue;
        adapter.notifyDataSetChanged();
    }
    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
           // setMealTotal();
        }
    };

    /*private void updateFHF() {
        Cursor cursor = db.updateHF(item);
    }*/

   /* Editable ancienneValeur1=txtLibelle.getText();
    Editable ancienneValeur2=txtMontant.getText();
    Editable ancienneValeur3=txtDate.getText();*/
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
                if(!checkBox.isChecked()&&!checkBox1.isChecked())
                {
                    AfficheMessage("Erreur", "Cocher une case");
                    return;
                }
                /*DatabaseHelperHF db = new DatabaseHelperHF(this);

                String libelle = this.libelle1.getText().toString();
                String montant = this.montant1.getText().toString();
                String date = this.date1.getText().toString();

                if(libelle.equals("") || montant.equals("")|| date.equals("")) {
                    AfficheMessage("Erreur", "Entrer les informations");
                    return;
                }
                this.fraisHF.setLibelle(libelle);
                this.fraisHF.setMontant(montant);
                this.fraisHF.setDate(date);
                db.updateHF(fraisHF);
                this.needRefresh = true;*/
               /* Editable newValeur1=txtLibelle.getText();
                Editable newValeur2=txtMontant.getText();
                Editable newValeur3=txtDate.getText();
                bd.execSQL("UPDATE fraisHorsForfait SET libelle="+newValeur1+"montant="+newValeur2+"date="+newValeur3+
                        "WHERE libelle="+ancienneValeur1+"AND montant="+ancienneValeur2+" and date="+ancienneValeur3+ "');");
                AfficheMessage("Succès", "Information modifiée");
                videTexte();*/
                break;
            case R.id.btnSupprimer:
                if(!checkBox.isChecked()&&!checkBox1.isChecked())
                {
                    AfficheMessage("Erreur", "Cocher une case");
                    return;
                }
               /* else{
                    if(checkBox.isChecked()) {
                        Cursor c = bd.rawQuery("SELECT * FROM FraisHorsForfait WHERE libelle=" + txtLibelle1.getText(), null);
                        if(c.moveToFirst())
                        {
                            bd.execSQL("DELETE FROM FraisHorsForfait WHERE libelle=" + txtLibelle1.getText() );
                            AfficheMessage("Succès", "Information détruite");
                        }
                        else
                        {
                            AfficheMessage("Erreur", "frais inexistant");
                        }
                    }else if(checkBox1.isChecked()){
                        Cursor c = bd.rawQuery("SELECT * FROM FraisHorsForfait WHERE libelle=" + txtLibelle2.getText(), null);
                        if(c.moveToFirst())
                        {
                            bd.execSQL("DELETE FROM FraisHorsForfait WHERE libelle=" + txtLibelle2.getText() );
                            AfficheMessage("Succès", "Information détruite");
                        }
                        else
                        {
                            AfficheMessage("Erreur", "frais inexistant");
                        }
                    }
                }
                videTexte();*/
                break;
            case R.id.btnRetourMenu:
                MainActivity retour = new MainActivity();
                retour.setVisible(true);
                finish();
                break;
            /*case R.id.listFHF:
                Intent intent = new Intent(FHFActivity.this, ModifHF.class);
                FHFActivity.this.startActivity(intent);
                break;*/
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void videTexte()
    {
        txtLibelle.setText("");
        txtMontant.setText("");
        txtDate.setText("");
    }
}
