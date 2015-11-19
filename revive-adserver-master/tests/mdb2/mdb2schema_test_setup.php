<?php

/*
+---------------------------------------------------------------------------+
| Revive Adserver                                                           |
| http://www.revive-adserver.com                                            |
|                                                                           |
| Copyright: See the COPYRIGHT.txt file.                                    |
| License: GPLv2 or later, see the LICENSE.txt file.                        |
+---------------------------------------------------------------------------+
*/

require_once SCHEMA_PATH.'testcases.php';

// use a user that has full permissions on a database named "driver_test"
$db = array(
    'dsn' => array(
        'phptype' => '%db.type%',
        'username' => '%db.username%',
        'password' => '%db.password%',
        'hostspec' => '%db.host%',
        'port' => %db.port%
    ),
    %options%
);

$dbarray = array($db);
?>
