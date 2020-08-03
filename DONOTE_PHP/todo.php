<?php
	require "db.php";//conect db file
	require "header.php";//conect header file
	$user=htmlspecialchars_decode($_SESSION['logged_user']->login);
	if ($user==$_SESSION['logged_user']->login){
	}else{
		$user=R::findOne('users','login=?',array("trash"));
		$user=$user->login;
	}	//$user is signed user
	if (!isset($user)){
		echo "<script>window.location.href='index.php';</script>";
	}
	?>
	<div class="mx-auto" style="width: 500px;">
  		<h5 class="centered"><?php echo $_SESSION['logged_user']->login;?> TODO List</h5>
  		<form method="post" id="ajax_form" action="#">
  			<input type="text" id="myInput" placeholder="smth what u want todo (max length 60 symbols)" class="donote" name="todo" autofocus required>
  			<input type="submit" id="btn" value="ADD" class="addBtn">
  		</form>
	</div>
	<br>
	<br>
	<br>
	<div class="mx-auto" style="width: 500px;">
		<ul id="myUL" class="todo">
			<?php
			$case=R::findAll($user);
			$limit=5;
			$total=count($case);
			if ($total>0){
				$pages=ceil($total/$limit);
				$page = min($pages, filter_input(INPUT_GET, 'page', FILTER_VALIDATE_INT, array(
					'options' => array(
						'default'   => 1,
						'min_range' => 1,
					),
				)));
				$offset=($page-1)*$limit;
				$userCase=[];
				foreach ($case as $c) {
					array_push($userCase,$c->case);
				}
				$last=min(($offset+$limit),count($userCase));
				$C=[];
				for($i=$offset;$i<$last;$i++){
					array_push($C,$userCase[$i]);
				}
				$CASES=$C;
				foreach ($CASES as $c){
					echo '<li class="lp">'.$c.'</li>';//load todo from db
				}
			}
			?>
		</ul>
		<?php
			if($total>0){
				if ($page > 1) {
					$prevlink = '<a href="?page=1">&laquo;</a> <a href="?page='.($page-1).'">&lsaquo;</a>';
				} else {
					$prevlink = "&laquo; &lsaquo;";
				}
				$links = "";
				for ($i=0; $i < $pages; $i++) {
					if ($i == $page-1 ) {
						$links .= " ".($i+1)." ";
					} else {
						$links .= " <a href=\"?page=".($i+1)."\">".($i+1)."</a> ";
					}
				}
				
				
				if ($page < $pages) {
					$nextlink = '<a href="?page='.($page+1).'">&rsaquo;</a> <a href="?page='.$pages.'">&raquo;</a>';
				} else {
					$nextlink = "&rsaquo; &raquo;";
				}
				echo '<div style="text-align:center;">'.$prevlink, $links, $nextlink.'</div>';
				
			}
		?>
	</div>
<?php require "footer.php" ?>
<script src="ajax.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
</body>
</html>
