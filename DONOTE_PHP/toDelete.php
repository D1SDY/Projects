<?php
	require "db.php";//conect db phile
	$user=$_SESSION['logged_user']->login;
	if($user==htmlspecialchars($user)){

	}else{
		$user="trash";
	}
	
	$victim=$_POST["victim"];//todo wich we need to delete
	$toDelete=R::findAll($user);//load all users bean from db
	foreach($toDelete as $key){
		if(htmlspecialchars($key->case)==htmlspecialchars($victim)){
			$id=$key->id;//find id of our victim
		}else{

		}
	}
	$toDelete=R::load($user,$id);//load our victim
	R::trash($toDelete);//delete victim



?>

