package com.example.objekwisata.info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.objekwisata.InputtActivity;
import com.example.objekwisata.MainActivity;
import com.example.objekwisata.R;
import com.example.objekwisata.RequestHandler;
import com.example.objekwisata.TampilPegawai;
import com.example.objekwisata.komentar.TampilSemuaPgwK;
import com.example.objekwisata.konfigurasi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemuaInfo extends AppCompatActivity implements android.widget.ListView.OnItemClickListener {
    private android.widget.ListView listView;
    private String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_info);


        listView = (android.widget.ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
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
                String salary = jo.getString(konfigurasi.TAG_GAJIH);
                String jen = jo.getString(konfigurasi.TAG_JENIS);
                String jar = jo.getString(konfigurasi.TAG_JARAK);
                String ker = jo.getString(konfigurasi.TAG_KERAMAIAN);
                String alam = jo.getString(konfigurasi.TAG_ALAMAT);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasi.TAG_ID,id);
                employees.put(konfigurasi.TAG_NAMA,name);
                employees.put(konfigurasi.TAG_POSISI,desg);
                employees.put(konfigurasi.TAG_GAJIH,salary);
                employees.put(konfigurasi.TAG_JENIS,jen);
                employees.put(konfigurasi.TAG_JARAK,jar);
                employees.put(konfigurasi.TAG_KERAMAIAN,ker);
                employees.put(konfigurasi.TAG_ALAMAT,alam);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                com.example.objekwisata.info.TampilSemuaInfo.this, list, R.layout.activity_list_view_info,
                new String[]{konfigurasi.TAG_ID,konfigurasi.TAG_NAMA,konfigurasi.TAG_POSISI,konfigurasi.TAG_GAJIH,
                        konfigurasi.TAG_JENIS,konfigurasi.TAG_JARAK,konfigurasi.TAG_KERAMAIAN,konfigurasi.TAG_ALAMAT},
                new int[]{R.id.id, R.id.name, R.id.biaya, R.id.fasilitas, R.id.jenis, R.id.jarak, R.id.keramaian, R.id.alamat});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(com.example.objekwisata.info.TampilSemuaInfo.this,"Mengambil Data","Mohon Tunggu...",false,false);
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
        Intent intent = new Intent(this, DetailInfo.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasi.TAG_ID).toString();
        intent.putExtra(konfigurasi.EMP_ID,empId);
        startActivity(intent);
    }
}
