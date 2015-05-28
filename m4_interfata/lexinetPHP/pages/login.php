<?php 

$objForm = new Form();
$objValid = new Validation($objForm);
$objUser = new User();


// login form
if ($objForm->isPost('login_email')) {
	if (
		$objUser->isUser(
			$objForm->getPost('login_email'), 
			$objForm->getPost('login_password')
		)
	) {
		Login::loginFront($objUser->_id, "?page=preedit");
	} else {
		$objValid->add2Errors('login');
	}
}


	




require_once('_header.php'); 
?>
<div class="header-intro" >
		<div class="header-info">

<h1>Login</h1>
<form action="" method="post">
	
				<?php echo $objValid->validate('login')."<br>"; ?>
				<label for="login_email">Email:</label>
				<input type="text" name="login_email" 
					id="login_email" class="menu-input" value="" />
				<label for="login_password">Parola:</label>
				<input type="password" name="login_password"
					id="login_password" class="menu-input" value=""  />
				<label for="btn_login" >
					<input type="submit" id="btn_login" 
						class="" value="Login" />
				</label>
			
	
</form>	


</div></div>


<?php require_once('_footer.php'); ?>