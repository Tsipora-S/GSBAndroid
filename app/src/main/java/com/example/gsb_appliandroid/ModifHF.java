package com.example.gsb_appliandroid;

import android.os.Bundle;
import android.widget.EditText;

public class ModifHF extends FHFActivity {
    EditText editerHF;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_fhf);
        editerHF = (EditText) findViewById(R.id.editText);
    }
}
