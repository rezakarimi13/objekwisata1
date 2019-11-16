package com.example.objekwisata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.objekwisata.komentar.TampilSemuaPgwK;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemuaPgw extends AppCompatActivity implements android.widget.ListView.OnItemClickListener {
    private android.widget.ListView listView;
    private FloatingActionButton btnFloating;
    private String JSON_STRING;
    private FloatingActionButton ListKomentar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_pgw);
        btnFloating = (FloatingActionButton) findViewById(R.id.buttonTambah);
        ListKomentar = (FloatingActionButton) findViewById(R.id.ListKomentar);
        ListKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListKomentarActivity();
            }
        });

        btnFloating.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputtActivity();
            }
        });

        listView = (android.widget.ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }
    public void ListKomentarActivity(){
        Intent intent = new Intent(this, TampilSemuaPgwK.class);
        startActivity(intent);
    }

    public void InputtActivity(){
        Intent intent = new Intent(this, InputtActivity.class);
        startActivity(intent);
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                String name = jo.getString(konfigurasi.TAG_NAMA);
                String desg = jo.getString(konfigurasi.TAG_POSISI);
                String sal = jo.getString(konfigurasi.TAG_GAJIH);
                String jen = jo.getString(konfigurasi.TAG_JENIS);
                String jar = jo.getString(konfigurasi.TAG_JARAK);
                String ker = jo.getString(konfigurasi.TAG_KERAMAIAN);
                String alam = jo.getString(konfigurasi.TAG_ALAMAT);
                String image =jo.getString(konfigurasi.TAG_FHOTO);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_ID,id);
                employees.put(konfigurasi.TAG_NAMA,name);
                employees.put(konfigurasi.TAG_POSISI,desg);
                employees.put(konfigurasi.TAG_GAJIH,sal);
                employees.put(konfigurasi.TAG_JENIS,jen);
                employees.put(konfigurasi.TAG_JARAK,jar);
                employees.put(konfigurasi.TAG_KERAMAIAN,ker);
                employees.put(konfigurasi.TAG_ALAMAT,alam);
                employees.put(konfigurasi.TAG_FHOTO,image);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemuaPgw.this, list, R.layout.activity_list_item,
                new String[]{konfigurasi.TAG_ID,konfigurasi.TAG_NAMA,konfigurasi.TAG_POSISI,konfigurasi.TAG_GAJIH,
                konfigurasi.TAG_JENIS,konfigurasi.TAG_JARAK,konfigurasi.TAG_KERAMAIAN,konfigurasi.TAG_ALAMAT,konfigurasi.TAG_FHOTO},
                new int[]{R.id.id, R.id.name, R.id.biaya, R.id.fasilitas, R.id.jenis, R.id.jarak, R.id.keramaian, R.id.alamat, R.id.imgview});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemuaPgw.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilPegawai.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi.TAG_ID).toString();
        intent.putExtra(konfigurasi.EMP_ID,empId);
        startActivity(intent);
    }
}
