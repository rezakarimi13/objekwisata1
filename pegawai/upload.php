 <?php
	include_once "koneksi.php";
	
	class emp{}
	
	$image = $_POST['image'];
	$nameImage = $_POST['nameImage'];
	$name = $_POST['name'];
	$desg = $_POST['desg'];
	$sal = $_POST['sal'];
	$jen = $_POST['jen'];
	$jar = $_POST['jar'];
	$ker = $_POST['ker'];
	$alam = $_POST['alam'];
	$lat = $_POST['lat'];
	$longi = $_POST['longi'];
	
	if (empty($image)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Please dont empty Name."; 
		die(json_encode($response));
	} else {
		$random = random_word(20);
		
		$path = "images/".$random.".png";
		
		// sesuiakan ip address laptop/pc atau URL server
		$actualpath = "http://192.168.1.4//Android/pegawai/$path";
		
		$query = mysqli_query($con, "INSERT INTO tb_data (fhoto,nama_fhoto,objekwisata,posisi,gajih,jenis,jarak,keramaian,alamat,latitude,longitude) VALUES ('$actualpath','$nameImage','$name','$desg','$sal','$jen','$jar','$ker','$alam','$lat','$longi')");
		// $query = mysqli_query($con, "INSERT INTO tb_data (fhoto,nama_fhoto,objekwisata,) VALUES ('$actualpath','$nameImage','$name')");
		
		if ($query){
			file_put_contents($path,base64_decode($image));
			
			$response = new emp();
			$response->success = 1;
			$response->message = "Successfully Uploaded";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Upload image";
			die(json_encode($response)); 
		}
	}	
	
	// fungsi random string pada gambar untuk menghindari nama file yang sama
	function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}

	mysqli_close($con);
	
 ?>	