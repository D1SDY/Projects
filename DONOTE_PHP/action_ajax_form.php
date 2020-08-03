<?php
	require "db.php";//conect db file
	$user=$_SESSION['logged_user']->login;
    if($user==htmlspecialchars($user,ENT_QUOTES)){

    }else{
        $user=R::findOne('users','login=?',array("trash"));
        $user=$user->login;
    }
    $t=substr($_POST['todo'],0,60);
    $temp=$t;
    $t=strip_tags($t);
        if (isset($t) ) {
            $result = array(
            'todo' => $t,
            );
            echo json_encode($result);//send $result with json method
            $case=R::dispense($user);//create new table in db with name $uset
            if(trim($t)!=''){//cheking if 'todo' is not empty string
                $case->case=$t;//add new todo to db
                R::store($case);//save changes  
            }
        }

    
?>



