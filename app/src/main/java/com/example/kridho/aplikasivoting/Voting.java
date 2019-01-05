package com.example.kridho.aplikasivoting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Voting extends AppCompatActivity {

    ImageView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        b = findViewById(R.id.mpm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(Voting.this, Form_voting.class);
                startActivity(pindah);
            }
        });

        b = findViewById(R.id.dpm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(Voting.this, from_voting_dpm.class);
                startActivity(pindah);
            }
        });

    }
}
