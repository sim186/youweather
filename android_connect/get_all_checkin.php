<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET['localita'])) {
    $localita = $_GET['localita'];

    // get all products from products table
    $result = mysql_query("SELECT * FROM checkin WHERE localita = '$localita'") or die(mysql_error());

    // check for empty result
    if (mysql_num_rows($result) > 0) {
        // looping through all results
        // products node
        $response["checkin"] = array();
    
        while ($row = mysql_fetch_array($result)) {
            // temp user array
            $checkin = array();
            $checkin["_id"] = $row["_id"];
            $checkin["nome"] = $row["nome"];
            $checkin["localita"] = $row["localita"];
            $checkin["temperatura"] = $row["temperatura"];
            $checkin["condizioni"] = $row["condizioni"];

            // push single product into final response array
            array_push($response["checkin"], $checkin);
        }
    // success
    $response["success"] = 1;
    // echoing JSON response
    echo json_encode($response);
    } else {
        // no products found
        $response["success"] = 0;
        $response["message"] = "No checkin found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
