package com.example.objekwisata;

public class konfigurasi {


        //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
        //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
        //dimana File PHP tersebut berada
        //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
        public static final String URL_ADD="http://192.168.1.4/Android/pegawai/tambahPgw.php";
        public static final String URL_GET_ALL = "http://192.168.1.4/Android/pegawai/tampilSemuaPgw.php";
        public static final String URL_GET_EMP = "http://192.168.1.4/Android/pegawai/tampilPgw.php?id=";
        public static final String URL_UPDATE_EMP = "http://192.168.1.4/Android/pegawai/updatePgw.php";
        public static final String URL_DELETE_EMP = "http://192.168.1.4/Android/pegawai/hapusPgw.php?id=";
//        public static final String URL_ADD="http://127.0.0.1/Android/pegawai/tambahPgw.php";
//        public static final String URL_GET_ALL = "http://127.0.0.1/Android/pegawai/tampilSemuaPgw.php";
//        public static final String URL_GET_EMP = "http://127.0.0.1/Android/pegawai/tampilPgw.php?id=";
//        public static final String URL_UPDATE_EMP = "http://127.0.0.1/Android/pegawai/updatePgw.php";
//        public static final String URL_DELETE_EMP = "http://127.0.0.1/Android/pegawai/hapusPgw.php?id=";


        //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
        public static final String KEY_EMP_ID = "id";
        public static final String KEY_EMP_NAMA = "name";
        public static final String KEY_EMP_POSISI = "desg"; //desg itu variabel untuk posisi
        public static final String KEY_EMP_GAJIH = "sal";
        public static final String KEY_EMP_JENIS = "jen";//salary itu variabel untuk gajih
        public static final String KEY_EMP_JARAK ="jar";
        public static final String KEY_EMP_KERAMAIAN = "ker";
        public static final String KEY_EMP_ALAMAT = "alam";
        public static final String KEY_EMP_NAMAFHOTO ="nama_fhoto";
        public static final String KEY_EMP_LATITUDEE ="lat";
        public static final String KEY_EMP_LONGITUDEE ="longi";


        //JSON Tags
        public static final String TAG_JSON_ARRAY="result";
        public static final String TAG_ID = "id";
        public static final String TAG_NAMA = "name";
        public static final String TAG_POSISI = "desg";
        public static final String TAG_GAJIH = "sal";
        public static final String TAG_JENIS = "jen";
        public static final String TAG_JARAK = "jar";
        public static final String TAG_KERAMAIAN = "ker";
        public static final String TAG_ALAMAT = "alam";
        public static final String TAG_NAMAFHOTO = "nama_fhoto";
        public static final String TAG_LATITUDEE = "lat";
        public static final String TAG_LONGITUDEE = "longi";
        public static final String TAG_FHOTO = "image";
//        public static final String TAG_LAT = "latitude";
//        public static final String TAG_LONG = "longitude";
//        public static final String TAG_FHOTOVIEW = "fhoto";

        //ID karyawan
        //emp itu singkatan dari Employee
        public static final String EMP_ID = "emp_id";
}
