<?php
$server		= "localhost"; // adjust the server name
	$user		= "root"; // adjust the username
	$password	= ""; // adjust the password
	$database	= "db_android"; // adjust the target databese
	
	$con = mysqli_connect($server, $user, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Failed to connect MySQL: " . mysqli_connect_error();
	}

	$query = mysqli_query($con, "SELECT * FROM tb_data ORDER BY id ASC");
	
	$json = '{"id": [';

	
	// create looping dech array in fetch
	while ($row = mysqli_fetch_array($query)){

	// quotation marks (") are not allowed by the json string, we will replace it with the` character
	// strip_tag serves to remove html tags on strings
		$char ='"';

		$json .= 
		'{
			"id":"'.str_replace($char,'`',strip_tags($row['id'])).'", 
			"name":"'.str_replace($char,'`',strip_tags($row['objekwisata'])).'",
			"lat":"'.str_replace($char,'`',strip_tags($row['latitude'])).'",
			"longi":"'.str_replace($char,'`',strip_tags($row['longitude'])).'"
		},';
	}

	// omitted commas at the end of the array
	$json = substr($json,0,strlen($json)-1);

	$json .= ']}';

	// print json
	echo $json;
	
	mysqli_close($con);
?>