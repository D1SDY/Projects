<!DOCTYPE HTML>
<html lang="en">
  <head>
	 <meta charset="UTF-8">
	 <title>DONOTE</title>
	 <link rel="stylesheet" type="text/css" href="bootstrap.css"><!-- bootstrap css file-->
	 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous"><!-- font awesome icons-->
	 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
   <!-- bootstrap js file-->
	 <link rel="stylesheet" type="text/css" href="main.css"><!-- my css file-->
	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script><!-- connect to jquery -->
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script><!-- connect to ajax-->
	 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
  <!-- bootstap js file -->
</head>
<body>
  <header>
	<nav class="navbar navbar-expand-sm navbar-light navbar-inverse navbar-fixed-top">
  		<a class="navbar-brand" href="index.php">DONOTE <i class="far fa-sticky-note"></i></a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
   			 <span class="navbar-toggler-icon"></span>
  		</button>

  		<div class="collapse navbar-collapse " id="navbarTogglerDemo02">
    		<ul class="navbar-nav mr-auto">
      			<li class="nav-item active">
        			<a class="nav-link" href="about.php">ABOUT<span class="sr-only">(current)</span></a>
      			</li>
            <?php if(isset($_SESSION['logged_user'])):?>
            <li class="dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <?php echo $_SESSION['logged_user']->login;?> <i class="fas fa-user-secret"></i>
                 </a>
                  <div class="dropdown-menu " aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="todo.php">TODO</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="logout.php">Logout <i class="fas fa-sign-out-alt"></i></a>
                  </div>
                  </li>
              <?php else:?>
              <?php endif;?>
    		</ul>
  		</div>
	</nav>
</header>
	<br>
	<br>
