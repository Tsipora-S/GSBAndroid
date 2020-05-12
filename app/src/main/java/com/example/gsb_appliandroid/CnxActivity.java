package com.example.gsb_appliandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CnxActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase bd;
    Button connexion;
    EditText utilisateur,mdp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_main);
        init();
        bd = openOrCreateDatabase("gsb-android.db", Context.MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS utilisateur(id TEXT primary key," +
                "nom TEXT,prenom TEXT,login TEXT,mdp TEXT,adresse TEXT,cp TEXT,ville TEXT,dateembauche TEXT)");
        bd.execSQL("REPLACE INTO utilisateur(id,nom,prenom,login,mdp,adresse,cp,ville,dateembauche)VALUES" +
                "('c1','Martin','Henri','hmartin','azerty5','3 rue Olivier','75020','Paris','01/04/2001')," +
                "('c2','Dupont','Marie','mdupont','uiopq6','94 avenue de Verdun','77470','Trilport','12/06/2004')," +
                "('c3','Julien','Frederic','fjulien','sdfgh7','54 avenue de Flandre','75019','Paris','01/11/2000')," +
                "('c4','Seguin','Michelle','mseguin','jklmw8','2 rue Gabriel Peri','92600','Asnières','05/03/1998')");
    }

    private void init() {
        utilisateur=(EditText)findViewById(R.id.txtUtilisatuer);
        mdp=(EditText)findViewById(R.id.txtMDP);
        connexion = findViewById(R.id.btnValidCnx);
        connexion.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnValidCnx:
                if (utilisateur.getText().toString().trim().length() == 0 ||
                        mdp.getText().toString().trim().length() == 0) {
                    AfficheMessage("Erreur", "Entrer toutes les valeurs");
                    return;
                } else {
                    DatabaseHelperCnx data=new DatabaseHelperCnx(this);
                    SQLiteDatabase bd = data.getReadableDatabase();
                    String query = "SELECT login,mdp FROM utilisateur WHERE login = '" + utilisateur.getText() + "' AND mdp = " + "'"+mdp.getText()+"'";
                    Cursor cursor = bd.rawQuery(query, null);
                    if (cursor.getCount() != 0) {
                        Intent intent = new Intent(CnxActivity.this, MainActivity.class);
                        intent.putExtra("login",utilisateur.getText().toString());
                        intent.putExtra("mdp",mdp.getText().toString());
                        CnxActivity.this.startActivity(intent);
                    }else{
                        AfficheMessage("Erreur", "Login ou Mot de passe incorrect");
                    }
                }
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
}