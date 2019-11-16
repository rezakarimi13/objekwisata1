package com.example.objekwisata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.objekwisata.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TampilPegawai extends AppCompatActivity implements View.OnClickListener {
    private TextView editTextIdu;
    private EditText editTextNameu;
    private EditText editTextDesgu;
    private EditText editTextSalaryu;
    private EditText editTextJenisu;
    private EditText editTextJaraku;
    private EditText editTextKeramaianu;
    private EditText editTextAlamatu;
    private EditText editTextGambaru;
    private EditText editTextLatitudeu;
    private EditText editTextLongitudeu;
    private ImageView imgviewu;
    private Button imgbtnu;
    private Button buttonUpdate;
    private Button buttonDelete;
    private String id;

    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100

    private static final String TAG = TampilPegawai.class.getSimpleName();
    private String UPLOAD_URL = "http://192.168.1.4/Android/pegawai/update.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";



    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pegawai);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);

        editTextIdu = (TextView) findViewById(R.id.editTextIdu);
        editTextNameu = (EditText) findViewById(R.id.editTextNameu);
        editTextDesgu = (EditText) findViewById(R.id.editTextDesgu);
        editTextSalaryu = (EditText) findViewById(R.id.editTextSalaryu);
        editTextJenisu = (EditText) findViewById(R.id.editTextJenisu);
        editTextJaraku = (EditText) findViewById(R.id.editTextJaraku);
        editTextKeramaianu = (EditText) findViewById(R.id.editTextKeramaianu);
        editTextAlamatu = (EditText) findViewById(R.id.editTextAlamatu);
        editTextGambaru = (EditText) findViewById(R.id.editTextGambaru);
        editTextLatitudeu = (EditText) findViewById(R.id.editTextLatitudeu);
        editTextLongitudeu = (EditText) findViewById(R.id.editTextLongitudeu);

        imgviewu = (ImageView) findViewById(R.id.imgviewu);
        imgbtnu = (Button) findViewById(R.id.imgbtnu);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        imgbtnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });


        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextIdu.setText(id);

        getEmployee();
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(konfigurasi.TAG_NAMA);
            String desg = c.getString(konfigurasi.TAG_POSISI);
            String sal = c.getString(konfigurasi.TAG_GAJIH);
            String jen = c.getString(konfigurasi.TAG_JENIS);
            String jar = c.getString(konfigurasi.TAG_JARAK);
            String ker = c.getString(konfigurasi.TAG_KERAMAIAN);
            String alam = c.getString(konfigurasi.TAG_ALAMAT);
            String nama_fhoto = c.getString(konfigurasi.TAG_NAMAFHOTO);
            String lat = c.getString(konfigurasi.TAG_LATITUDEE);
            String longi = c.getString(konfigurasi.TAG_LONGITUDEE);
            String image = c.getString(konfigurasi.TAG_FHOTO);


            editTextNameu.setText(name);
            editTextDesgu.setText(desg);
            editTextSalaryu.setText(sal);
            editTextJenisu.setText(jen);
            editTextJaraku.setText(jar);
            editTextKeramaianu.setText(ker);
            editTextAlamatu.setText(alam);
            editTextGambaru.setText(nama_fhoto);
//            imgviewu.setImageURI(image);
            editTextLatitudeu.setText(lat);
            editTextLongitudeu.setText(longi);




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
//    private void uploadImage() {
//        //menampilkan progress dialog
//        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e(TAG, "Response: " + response.toString());
//
//                        try {
//                            JSONObject jObj = new JSONObject(response);
//                            success = jObj.getInt(TAG_SUCCESS);
//
//                            if (success == 1) {
//                                Log.e("v Add", jObj.toString());
//
//                                Toast.makeText(TampilPegawai.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//
//                                kosong();
//
//                            } else {
//                                Toast.makeText(TampilPegawai.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        //menghilangkan progress dialog
//                        loading.dismiss();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //menghilangkan progress dialog
//                        loading.dismiss();
//
//                        //menampilkan toast
//                        Toast.makeText(TampilPegawai.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
//                        Log.e(TAG, error.getMessage().toString());
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() {
//                //membuat parameters
//                Map<String, String> params = new HashMap<String, String>();
//
////                menambah parameter yang di kirim ke web servis
//                params.put("name", editTextNameu.getText().toString().trim());
//                params.put("desg", editTextDesgu.getText().toString().trim());
//                params.put("sal", editTextSalaryu.getText().toString().trim());
//                params.put("jen", editTextJenisu.getText().toString().trim());
//                params.put("jar", editTextJaraku.getText().toString().trim());
//                params.put("ker", editTextKeramaianu.getText().toString().trim());
//                params.put("alam", editTextAlamatu.getText().toString().trim());
//                params.put("lat", editTextLatitudeu.getText().toString().trim());
//                params.put("longi", editTextLongitudeu.getText().toString().trim());
////                    params.put("image", getStringImage(decoded));
//                params.put("nameImage", editTextGambaru.getText().toString().trim());
//
////                kembali ke parameters
//                Log.e(TAG, "" + params);
//                return params;
//            }
//        };
//
//        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
//    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //mengambil fambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // 512 adalah resolusi tertinggi setelah image di resize, bisa di ganti.
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void kosong() {
        imgviewu.setImageResource(0);
        editTextGambaru.setText(null);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imgviewu.setImageBitmap(decoded);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }



    private void updateEmployee(){
        final String name = editTextNameu.getText().toString().trim();
        final String desg = editTextDesgu.getText().toString().trim();
        final String sal = editTextSalaryu.getText().toString().trim();
        final String jen = editTextJenisu.getText().toString().trim();
        final String jar = editTextJaraku.getText().toString().trim();
        final String ker = editTextKeramaianu.getText().toString().trim();
        final String alam =editTextAlamatu.getText().toString().trim();
        final String nama_fhoto = editTextGambaru.getText().toString().trim();
        final String lat = editTextLatitudeu.getText().toString().trim();
        final String longi = editTextLongitudeu.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPegawai.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID,id);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,name);
                hashMap.put(konfigurasi.KEY_EMP_POSISI,desg);
                hashMap.put(konfigurasi.KEY_EMP_GAJIH,sal);
                hashMap.put(konfigurasi.KEY_EMP_JENIS,jen);
                hashMap.put(konfigurasi.KEY_EMP_JARAK,jar);
                hashMap.put(konfigurasi.KEY_EMP_KERAMAIAN,ker);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT,alam);
                hashMap.put(konfigurasi.KEY_EMP_NAMAFHOTO,nama_fhoto);
                hashMap.put(konfigurasi.KEY_EMP_LATITUDEE,lat);
                hashMap.put(konfigurasi.KEY_EMP_LONGITUDEE,longi);


                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPegawai.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
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
                        startActivity(new Intent(TampilPegawai.this,TampilSemuaPgw.class));
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
//        if (editTextName.getText().toString().isEmpty()) {
//            editTextName.setError("Masukkan Data");
//        }
//        if (editTextDesg.getText().toString().isEmpty()) {
//            editTextDesg.setError("Masukkan Data");
//        }
//        if (editTextSalary.getText().toString().isEmpty()) {
//            editTextSalary.setError("Masukkan Data");
//        }
//        if (editTextJenis.getText().toString().isEmpty()) {
//            editTextJenis.setError("Masukkan Data");
//        }
//        if (editTextJarak.getText().toString().isEmpty()) {
//            editTextJarak.setError("Masukkan Data");
//        }
//        if (editTextKeramaian.getText().toString().isEmpty()) {
//            editTextKeramaian.setError("Masukkan Data");
//        }
//        if (editTextAlamat.getText().toString().isEmpty()) {
//            editTextAlamat.setError("Masukkan Data");

//        } else {
            if (v == buttonUpdate) {
                updateEmployee();
//                uploadImage();
                startActivity(new Intent(this, TampilSemuaPgw.class));
//            }
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}

