package com.example.objekwisata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.objekwisata.info.TampilSemuaInfo;
import com.example.objekwisata.komentar.InputkActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private Button btnAdmin;
    private FloatingActionButton btnKomentar;
    private Button btnSpk;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnKomentar = (FloatingActionButton) findViewById(R.id.btnKomentar);
        btnSpk = (Button) findViewById(R.id.btnSpk);
        btnInfo = (Button) findViewById(R.id.btnInfo);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TampilSemuaInfo();
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TampilSemuaPgw();
            }
        });

    btnKomentar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            KomentarActivity();
        }
    });
    }

    public void TampilSemuaInfo(){
        Intent intent = new Intent(this, TampilSemuaInfo.class);
        startActivity(intent);
    }
    public void KomentarActivity(){
        Intent intent = new Intent(this, InputkActivity.class);
        startActivity(intent);
    }
    public void TampilSemuaPgw(){
        Intent intent  = new Intent(this, TampilSemuaPgw.class);
        startActivity(intent);
    }
//    public void TesActivity(){
//        Intent intent  = new Intent(this, TesActivity.class);
//        startActivity(intent);
//    }

}
