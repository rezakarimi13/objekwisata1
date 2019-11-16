<?php

 /*



 */

 //Mendapatkan Nilai ID
 $id = $_GET['id'];

 //Import File Koneksi Database
 require_once('koneksiK.php');

 //Membuat SQL Query
 $sql = "DELETE FROM tb_komentar WHERE id=$id;";


 //Menghapus Nilai pada Database
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Komentar';
 }else{
 echo 'Gagal Komentar';
 }

 mysqli_close($con);
 ?>
