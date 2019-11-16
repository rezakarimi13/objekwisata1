package com.example.objekwisata.komentar;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.objekwisata.R;
import com.example.objekwisata.RequestHandler;
import com.example.objekwisata.TampilSemuaPgw;
import com.example.objekwisata.konfigurasi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilK extends AppCompatActivity implements View.OnClickListener {
    private TextView Textnama;
    private TextView Textkomen;
    private TextView Textid;
    private FloatingActionButton btnHapus;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_k);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasiK.EMP_ID);
        Textid = (TextView) findViewById(R.id.TextId);
        Textnama = (TextView) findViewById(R.id.Textnama);
        Textkomen = (TextView) findViewById(R.id.Textkomen);


        btnHapus = (FloatingActionButton) findViewById(R.id.btnHapus);

        btnHapus.setOnClickListener(this);

        Textid.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(com.example.objekwisata.komentar.TampilK.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerK rh = new RequestHandlerK();
                String s = rh.sendGetRequestParam(konfigurasiK.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasiK.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(konfigurasiK.TAG_NAMA);
            String komen = c.getString(konfigurasiK.TAG_KOMEN);


            Textnama.setText(nama);
            Textkomen.setText(komen);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(com.example.objekwisata.komentar.TampilK.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(com.example.objekwisata.komentar.TampilK.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerK rh = new RequestHandlerK();
                String s = rh.sendGetRequestParam(konfigurasiK.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(com.example.objekwisata.komentar.TampilK.this, TampilSemuaPgwK.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {

        if(v == btnHapus){
            confirmDeleteEmployee();

        }


    }
}

