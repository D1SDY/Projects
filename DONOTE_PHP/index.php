<?php
	require "db.php";//conect to db file
	require "header.php";//conect to header file
	$data=$_POST;
	if(!isset($_SESSION['logged_user'])) {
		if(isset($data['do_login']))
		{
			$errors=array();//creating array with errors
			$stripLogin=htmlspecialchars($data['login'],ENT_QUOTES);
			$user=R::findOne('users','login=?',array($stripLogin));//find one user with redbeanphp methods
				if($user)
				{
					if (password_verify($data['password'],$user->password))
					{
						//all ok,login in
						$_SESSION['logged_user']=$user;//add $user to session
						echo "<script> window.location.reload();</script>";//reload page

					}else{
						$errors[]='Incorrect login or password';
					}
				}else{
					$errors[]='Incorrect login or password';
				}
				if(!empty($errors))//show the last error in errors array
				{
					echo '<div class="alert alert-danger" role="alert">'.array_shift($errors).'</div>';
				}
		
	}
}

?>


	<div class="container">
  		<div class="row">
  			<?php if(isset($_SESSION['logged_user'])):?>
  			<?php else:?>
    		<div class="col-sm login-form">
      			<fieldset>
					<form class="form" action="index.php" method="POST">
						<label>Login
							<br>
							<input type="text" name="login" id="login" value="<?php
						
							echo @$stripLogin;?>" required>
						</label>
						<br>
						<label>Password
							<br>
							<input type="password" name="password" id="password" required>
						</label>
						<br>
						<a class="register" href="reg.php">Registration</a>
						<br>
						<input type="submit" name="do_login" id="do_login" value="login" class="btn btn-primary btn-sm" >
					</form>
				</fieldset>
    		</div>
    		<?php endif;?>
   			<div class="col-sm">
    		</div>
    		<div class="col-sm">
    		</div>
    		<div class="col-sm">
    		</div>
    		<div class="col-sm-4">
      			<h3>How to make a note:</h3>
      			<p>Open donote.com</p>
      			<p>@</p>
      			<p>Make a note</p>
      			<p>@</p>
      			<p>Forget about a note</p>
      			<p>@</p>
      			<p>pffffffff</p>
      			<p>@</p>
      			<p>Profit</p>
    		</div>
    		<div class="col-sm">
    		</div>
    		<div class="col-sm">
    		</div>
    		
  		</div>
	</div>
<?php require "footer.php" ?>
</body>
</html>
