package com.example.objekwisata.komentar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.objekwisata.MainActivity;
import com.example.objekwisata.R;

import java.util.HashMap;

public class InputkActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama;
    private EditText editTextKomen;

    private Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputk);
        //Inisialisasi dari View
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextKomen = (EditText) findViewById(R.id.editTextKomen);


        btnKirim = (Button) findViewById(R.id.btnKirim);



        btnKirim.setOnClickListener(this);


    }


    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee() {

        final String nama = editTextNama.getText().toString().trim();
        final String komen = editTextKomen.getText().toString().trim();



        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputkActivity.this,"Menambahkan...","Tunggu...",false,false);



            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputkActivity.this, s, Toast.LENGTH_LONG).show();

            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasiK.KEY_EMP_NAMA,nama);
                params.put(konfigurasiK.KEY_EMP_KOMEN,komen);



                RequestHandlerK rh = new RequestHandlerK();
                String res = rh.sendPostRequest(konfigurasiK.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }

    @Override
    public void onClick(View v) {

        if (editTextNama.getText().toString().isEmpty()) {
            editTextNama.setError("Masukkan Data");
        }
        if (editTextKomen.getText().toString().isEmpty()) {
            editTextKomen.setError("Masukkan Data");

        }else {
            if (v == btnKirim) {
                addEmployee();
                startActivity(new Intent(this, MainActivity.class));
            }


        }


    }
}
