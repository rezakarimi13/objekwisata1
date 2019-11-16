package com.example.objekwisata.komentar;

public class konfigurasiK {


    public static final String URL_ADD="http://192.168.1.4/Android/komentar/tambahKmt.php";
    public static final String URL_GET_ALL = "http://192.168.1.4/Android/komentar/tampilSemuaKmt.php";
    public static final String URL_GET_EMP = "http://192.168.1.4/Android/komentar/tampilKmt.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.1.4/Android/komentar/updateKmt.php";
    public static final String URL_DELETE_EMP = "http://192.168.1.4/Android/komentar/hapusKmt.php?id=";
//    public static final String URL_ADD="http://192.168.9.111/Android/pegawai/tambahPgw.php";
//    public static final String URL_GET_ALL = "http://127.0.0.1/Android/pegawai/tampilSemuaPgw.php";
//    public static final String URL_GET_EMP = "http://127.0.0.1/Android/pegawai/tampilPgw.php?id=";
//    public static final String URL_UPDATE_EMP = "http://127.0.0.1/Android/pegawai/updatePgw.php";
//    public static final String URL_DELETE_EMP = "http://127.0.0.1/Android/pegawai/hapusPgw.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_KOMEN = "komen"; //desg itu variabel untuk posisi


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_KOMEN = "komen";


    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";
}
