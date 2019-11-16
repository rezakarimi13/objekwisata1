 <?php

 /*


 */
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable
		$id = $_POST['id'];
		$name = $_POST['name'];
		$desg = $_POST['desg'];
		$sal = $_POST['sal'];
		$jen = $_POST['jen'];
		$jar = $_POST['jar'];
		$ker = $_POST['ker'];
		$alam = $_POST['alam'];
		$namaImage = $_POST['nama_fhoto'];
		// $image = $_POST['image'];
		$lat =$_POST['lat'];
		$longi =$_POST['longi'];
		// $nfhoto = $_POST['nfhoto'];


		//import file koneksi database
		require_once('koneksi.php');
			// include_once "koneksi.php";


		//Membuat SQL Query
		$sql = "UPDATE tb_data SET objekwisata = '$name', posisi = '$desg', gajih = '$sal', jenis = '$jen' ,jarak = '$jar', 
		keramaian = '$ker', alamat ='$alam', nama_fhoto ='$namaImage', latitude ='$lat', longitude= '$longi'  WHERE  id = $id";
		
		//Meng-update Database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Objek Wisata';
		}else{
			echo 'Gagal Update Data Objek Wisata';
		}

		mysqli_close($con);
	}
 ?>


