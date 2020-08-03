<?php
	require "db.php";//conect to db file
	require "header.php";//conect to header file
	require "footer.php";//conect to footer file

	$data=$_POST;
	$errors=array();
	if(isset($data['do_signup']))
	{
		$stripLogin=htmlspecialchars($data['login'],ENT_QUOTES);
			if (trim($stripLogin)=='')//checking insert data
			{
				$errors[]='Input username';
			}
			if ($data['password']=='')
			{
				$errors[]='Input password';
			} 
			if ($data['password_2']!=$data['password'])
			{
				$errors[]='Second password is incorrect';
			} 
			if (R::count('users',"login=?",array($stripLogin))>0)
			{
				$errors[]='User already exist';
			} 
			if(empty($errors))
			{	//all ok, creating new user
				$user=R::dispense('users');
				$user->login=$stripLogin;
				$user->password=password_hash($data['password'],PASSWORD_DEFAULT);
				R::store($user);
			//header('Location:index.php');
				echo "<script>window.location.href='index.php';</script>";
			}else
			{	//show the last error
				echo '<div class="alert alert-danger" role="alert">'.array_shift($errors).'</div>
				<hr>';
			}
	}
?>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Registration</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<script>
		function validateForm(){
			var form=document.getElementById("form");
			form.addEventListener("submit",validateLogin);
		}
		function validateLogin(e){
		var lg=document.getElementByName("login")
			if (lg.value.lenght>5){

			}else{
				e.preventDefault();
				var div=document.createElement('div');
				div.classList.add('alert alert-danger');
				div.innerHTML="Login is too short";
				lg.parentNode.insertBefore(div,lb.nextSibling);
			}
		}			
	</script>

		<div class=" container-fluid reg-form mx-auto">
				<fieldset>
				<form action="reg.php" method="POST" id="form">
					<label>Login
						<br>
						<input type="text" name="login" value="<?php
						if (strip_tags(@$data['login'])==@$data['login']){
							echo @$data['login'];
						}else{
						}?>" required>
					</label>
					<br>
					<label>Password
						<br>
						<input type="password" name="password" required>
					</label>
					<br>
					<label>Dublicate the password
						<br>
						<input type="password" name="password_2" required>
					</label>
					<br>
					<input type="submit" name="do_signup" value="Sign Up" class="btn btn-primary btn-sm" onclick="validateLogin()">
				</form>
			</fieldset>
</div>
<script>
	validateForm();
</script>	
</body>
</html>