<?php 

require "rb.php";//conect redbeanphp library
R::setup( 'mysql:host=localhost;dbname=striaant',//conect to db
        'striaant', 'webove aplikace' );
session_start();//staring session
?>