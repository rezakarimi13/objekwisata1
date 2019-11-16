package com.example.objekwisata.info;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.objekwisata.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailInfo extends AppCompatActivity implements View.OnClickListener {
    private TextView Textnamai;
    private TextView Textbiayai;
    private TextView Textfasilitasi;
    private TextView Textjenisi;
    private TextView Textjaraki;
    private TextView Textkeramaiani;
    private TextView Textalamati;

    private TextView Textidi;
    private FloatingActionButton btnMaps;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasiInfo.EMP_ID);
        Textidi = (TextView) findViewById(R.id.TextIdi);
        Textnamai = (TextView) findViewById(R.id.Textnamai);
        Textbiayai = (TextView) findViewById(R.id.Textbiayai);
        Textfasilitasi = (TextView) findViewById(R.id.Textfasilitasi);
        Textjenisi = (TextView) findViewById(R.id.Textjenisi);
        Textjaraki = (TextView) findViewById(R.id.Textjaraki);
        Textkeramaiani = (TextView) findViewById(R.id.Textkeramaiani);
        Textalamati = (TextView) findViewById(R.id.Textalamati);
        btnMaps = (FloatingActionButton) findViewById(R.id.btnMaps);
        Textidi.setText(id);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MapsActivity();
            }
        });

        getEmployee();
    }
    public void MapsActivity(){
        Intent inten1 = new Intent(this, MapsActivity.class);
        startActivity(inten1);
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(com.example.objekwisata.info.DetailInfo.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandlerInfo rh = new RequestHandlerInfo();
                String s = rh.sendGetRequestParam(konfigurasiInfo.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasiInfo.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(konfigurasiInfo.TAG_ID);
            String name = c.getString(konfigurasiInfo.TAG_NAMA);
            String desg = c.getString(konfigurasiInfo.TAG_POSISI);
            String sal = c.getString(konfigurasiInfo.TAG_GAJIH);
            String jen = c.getString(konfigurasiInfo.TAG_JENIS);
            String jar = c.getString(konfigurasiInfo.TAG_JARAK);
            String ker = c.getString(konfigurasiInfo.TAG_KERAMAIAN);
            String alam = c.getString(konfigurasiInfo.TAG_ALAMAT);

            Textidi.setText(id);
            Textnamai.setText(name);
            Textbiayai.setText(desg);
            Textfasilitasi.setText(sal);
            Textjenisi.setText(jen);
            Textjaraki.setText(jar);
            Textkeramaiani.setText(ker);
            Textalamati.setText(alam);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void onClick(View v) {
    }
}

