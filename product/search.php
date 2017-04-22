<?php 
//$server = '192.168.56.102';
$server = 'localhost';
$user = 'prod';
$pwd = 'admp@ssw0!d';
$db = 'productdb';

$conn = new mysqli($server,$user,$pwd,$db); 
if ($conn->connect_error) { 
	die('Could not connect to MySQL: '.$conn->connect_error); 
} 

if (!empty($_POST['names'])) {
	$names = explode("|", $_POST['names']);
}
if (!empty($_POST['merchant'])) {
	$merchant = $_POST['merchant'];
}
if (!empty($_GET['names'])) {
	$names = explode("|", $_GET['names']);
}
if (!empty($_GET['merchant'])) {
	$merchant = $_GET['merchant'];
}

$items = array();
foreach ($names as $n )
{
	$query = "SELECT title,link,image_link,price FROM product p join merchant m on p.id_merchant = m.id where m.name = '" . $merchant . 
	"' and MATCH(p.title,p.description) AGAINST ('" . $n . "' IN NATURAL LANGUAGE MODE) LIMIT 2" ;
	if ($result = $conn->query($query)) {
	
		/* fetch associative array */
		while ($row = $result->fetch_assoc()) {
			$items[] = array (
					'title'  => $row["title"],
					'link'   => $row["link"],
					'image'  => $row["image_link"],
					'price'  => $row["price"]
			);
		}
	
		/* free result set */
		$result->free();
	}
}
echo json_encode($items);
$conn->close(); 
?> 