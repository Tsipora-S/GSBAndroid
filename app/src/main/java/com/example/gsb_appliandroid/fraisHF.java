package com.example.gsb_appliandroid;

import java.io.Serializable;

public class fraisHF implements Serializable {
    private int id;
    private String libelle;
    private String montant;
    private String date;

    public fraisHF(int id, String libelle, String montant,String date){
        this.id=id;
        this.libelle=libelle;
        this.montant=montant;
        this.date=date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDate() {
        return montant;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString()  {
        return this.libelle;
    }

}
