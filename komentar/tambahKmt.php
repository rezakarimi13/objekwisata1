<?php

 /*



 */

	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Mendapatkan Nilai Variable
		$nama = $_POST['nama'];
		$komen = $_POST['komen'];


		//Pembuatan Syntax SQL
		 $sql = "INSERT INTO tb_komentar (nama,komen) VALUES 
		 ('$nama','$komen')";

		//Import File Koneksi database
		require_once('koneksiK.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Komentar';
		}else{
			echo 'Gagal Menambahkan Komentar';
		}

		mysqli_close($con);
	}
?>
