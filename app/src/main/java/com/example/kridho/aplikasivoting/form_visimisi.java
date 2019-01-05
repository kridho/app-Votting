package com.example.kridho.aplikasivoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.kridho.aplikasivoting.fromdpm.visimisi_dpm;

public class form_visimisi extends AppCompatActivity {

    ImageView visi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_visimisi);

        visi = findViewById(R.id.mpm);
        visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(form_visimisi.this, Main2Activity.class);
                startActivity(pindah);
            }
        });

        visi = findViewById(R.id.dpm);
        visi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(form_visimisi.this, visimisi_dpm.class);
                startActivity(pindah);
            }
        });
    }
}
