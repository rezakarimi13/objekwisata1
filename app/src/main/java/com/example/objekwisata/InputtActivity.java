package com.example.objekwisata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;
import com.example.objekwisata.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
public class InputtActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSalary;
    private EditText editTextJenis;
    private EditText editTextJarak;
    private EditText editTextKeramaian;
    private EditText editTextAlamat;
    private EditText editTextGambar;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private ImageView imgview;
    private Button imgbtn;
    Bitmap bitmap, decoded;
    int success;
    int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100

    private static final String TAG = InputtActivity.class.getSimpleName();
    private String UPLOAD_URL = "http://192.168.1.4/Android/pegawai/upload.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";



    String tag_json_obj = "json_obj_req";
    private Button buttonAdd;


//
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputt);
        //Inisialisasi dari View
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextDesg = (EditText) findViewById(R.id.editTextDesg);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextJarak = (EditText) findViewById(R.id.editTextJarak);
        editTextKeramaian = (EditText) findViewById(R.id.editTextKeramaian);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        editTextGambar = (EditText) findViewById(R.id.editTextGambar);
        editTextLatitude = (EditText)findViewById(R.id.editTextLatitude);
        editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);


        imgview = (ImageView) findViewById(R.id.imgview);
        imgbtn = (Button) findViewById(R.id.imgbtn);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });



        buttonAdd.setOnClickListener(this);

    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void uploadImage() {
        //menampilkan progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            success = jObj.getInt(TAG_SUCCESS);

                            if (success == 1) {
                                Log.e("v Add", jObj.toString());

                                Toast.makeText(InputtActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                                kosong();

                            } else {
                                Toast.makeText(InputtActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //menghilangkan progress dialog
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //menghilangkan progress dialog
                        loading.dismiss();

                        //menampilkan toast
                        Toast.makeText(InputtActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                //membuat parameters
                Map<String, String> params = new HashMap<String, String>();

                //menambah parameter yang di kirim ke web servis
                params.put("name", editTextName.getText().toString().trim());
                params.put("desg", editTextDesg.getText().toString().trim());
                params.put("sal", editTextSalary.getText().toString().trim());
                params.put("jen", editTextJenis.getText().toString().trim());
                params.put("jar", editTextJarak.getText().toString().trim());
                params.put("ker", editTextKeramaian.getText().toString().trim());
                params.put("alam", editTextAlamat.getText().toString().trim());
                params.put("lat", editTextLatitude.getText().toString().trim());
                params.put("longi", editTextLongitude.getText().toString().trim());
                params.put("image", getStringImage(decoded));
                params.put("nameImage", editTextGambar.getText().toString().trim());

                //kembali ke parameters
                Log.e(TAG, "" + params);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }
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
        imgview.setImageResource(0);
        editTextGambar.setText(null);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        imgview.setImageBitmap(decoded);
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



    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
//    private void addEmployee() {
//
//        final String name = editTextName.getText().toString().trim();
//        final String desg = editTextDesg.getText().toString().trim();
//        final String sal = editTextSalary.getText().toString().trim();
//        final String jen = editTextJenis.getText().toString().trim();
//        final String jar = editTextJarak.getText().toString().trim();
//        final String ker = editTextKeramaian.getText().toString().trim();
//        final String alam = editTextAlamat.getText().toString().trim();
//        final String lat = editTextLatitude.getText().toString().trim();
//        final String longi = editTextLongitude.getText().toString().trim();
//
//
//        class AddEmployee extends AsyncTask<Void, Void, String> {
//
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(InputtActivity.this, "Menambahkan...", "Tunggu...", false, false);
//
//
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                Toast.makeText(InputtActivity.this, s, Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            protected String doInBackground(Void... v) {
//                HashMap<String, String> params = new HashMap<>();
//                params.put(konfigurasi.KEY_EMP_NAMA, name);
//                params.put(konfigurasi.KEY_EMP_POSISI, desg);
//                params.put(konfigurasi.KEY_EMP_GAJIH, sal);
//                params.put(konfigurasi.KEY_EMP_JENIS, jen);
//                params.put(konfigurasi.KEY_EMP_JARAK, jar);
//                params.put(konfigurasi.KEY_EMP_KERAMAIAN, ker);
//                params.put(konfigurasi.KEY_EMP_ALAMAT, alam);
//                params.put(konfigurasi.KEY_EMP_LATITUDEE, lat);
//                params.put(konfigurasi.KEY_EMP_LONGITUDEE, longi);
//
//
//
//                RequestHandler rh = new RequestHandler();
//                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
//                return res;
//            }
//        }
//
//        AddEmployee ae = new AddEmployee();
//        ae.execute();
//    }
//



    @Override
    public void onClick(View v) {

//        if (editTextName.getText().toString().isEmpty()) {
//            editTextName.setError("Masukkan Data");
//        }
//        if (editTextDesg.getText().toString().isEmpty()) {
//            editTextDesg.setError("Masukkan Data");
//        }
//        if (editTextSal.getText().toString().isEmpty()) {
//            editTextSal.setError("Masukkan Data");
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
        if (v == buttonAdd) {
            uploadImage();
//            addEmployee();

                startActivity(new Intent(this, TampilSemuaPgw.class));


        }

    }

}


//



