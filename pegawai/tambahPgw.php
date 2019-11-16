// <?php

//  /*


//  */

// 	if($_SERVER['REQUEST_METHOD']=='POST'){

// 		//Mendapatkan Nilai Variable
// 		$name = $_POST['name'];
// 		$desg = $_POST['desg'];
// 		$sal = $_POST['sal'];
// 		$jen = $_POST['jen'];
// 		$jar = $_POST['jar'];
// 		$ker = $_POST['ker'];
// 		$alam = $_POST['alam'];
// 		$lat = $_POST['lat'];
// 		$longi = $_POST['longi'];
// 		// $gambar = $_POST['gambar'];

		




// 		//Pembuatan Syntax SQL
// 		 $sql = "INSERT INTO tb_data (objekwisata,posisi,gajih,jenis,jarak,keramaian,alamat,latitude,longitude) VALUES 
// 		 ('$name','$desg','$sal','$jen','$jar','$ker','$alam','$lat','$longi')";

// 		//Import File Koneksi database
// 		require_once('koneksi.php');

// 		//Eksekusi Query database
// 		if(mysqli_query($con,$sql)){
// 			echo 'Berhasil Menambahkan Data Objek Wisata';
// 		}else{
// 			echo 'Gagal Menambahkan Data Objek Wisata';
// 		}

// 		mysqli_close($con);
// 	}
// ?>
