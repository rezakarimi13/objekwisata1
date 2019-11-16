<?php

 /*



 */

	//Import File Koneksi Database
	require_once('koneksiK.php');

	//Membuat SQL Query
	$sql = "SELECT * FROM tb_komentar";

	//Mendapatkan Hasil
	$r = mysqli_query($con,$sql);

	//Membuat Array Kosong
	$result = array();

	while($row = mysqli_fetch_array($r)){

		//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
		array_push($result,array(
			"id"=>$row['id'],
			"nama"=>$row['nama'],
			"komen"=>$row['komen']
		));
	}

	//Menampilkan Array dalam Format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($con);
?>
