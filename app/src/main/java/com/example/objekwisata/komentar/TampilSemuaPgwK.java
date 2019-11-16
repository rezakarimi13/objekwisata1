package com.example.objekwisata.komentar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.objekwisata.MainActivity;
import com.example.objekwisata.R;
import com.example.objekwisata.komentar.RequestHandlerK;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemuaPgwK extends AppCompatActivity implements android.widget.ListView.OnItemClickListener {
    private android.widget.ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_pgw_k);

        listView = (android.widget.ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasiK.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasiK.TAG_ID);
                String nama = jo.getString(konfigurasiK.TAG_NAMA);
                String komen = jo.getString(konfigurasiK.TAG_KOMEN);

                HashMap<String,String> employees = new HashMap<>();
                employees.put(konfigurasiK.TAG_ID,id);
                employees.put(konfigurasiK.TAG_NAMA,nama);
                employees.put(konfigurasiK.TAG_KOMEN,komen);
                list.add(employees);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemuaPgwK.this, list, R.layout.activity_list_view_k,
                new String[]{konfigurasiK.TAG_ID,konfigurasiK.TAG_NAMA,konfigurasiK.TAG_KOMEN},
                new int[]{R.id.id, R.id.nama, R.id.komen});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemuaPgwK.this,"Mengambil Data","Mohon Tunggu...",false,false);
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
                RequestHandlerK rh = new RequestHandlerK();
                String s = rh.sendGetRequest(konfigurasiK.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilK.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(konfigurasiK.TAG_ID).toString();
        intent.putExtra(konfigurasiK.EMP_ID,empId);
        startActivity(intent);
    }
}
