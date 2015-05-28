<?php 



$objForm = new Form();
$objValid = new Validation($objForm);
$objUser = new User();




// registration form
if ($objForm->isPost('username')) {
	
	$objValid->_exptected = array(
		'username',
		'email',
		'parola'
	);
	
	$objValid->_required = array(
	'username',
		'email',
		'parola'
	);
	
	$objValid->_special = array(
		'email' => 'email'
	);
	
		
	
	
	
	$email = $objForm->getPost('email');
	$user = $objUser->getByEmail($email);
	
	if (!empty($user)) {
		$objValid->add2Errors('email_duplicate');
	}
	
	
	if ($objValid->isValid()) {
		
		
		
		if ($objUser->addUser($objValid->_post, $objForm->getPost('parola'))) {
			Helper::redirect('/');
		} else {
			$objValid->add2Errors('register');
		}
		
	}
	
	
}




require_once('_header.php'); 
?>
<div class="header-intro" >
		<div class="header-info">

<h1>Register</h1>
<form action="" method="post">
	
				<label for="username">Username:</label>
				<?php echo $objValid->validate('login'); ?>	
				<input type="text" name="username" 
					id="username" class="menu-input" value="" />
				<label for="email">Email:</label>
				<input type="text" name="email" 
					id="email" class="menu-input" value="" />
				<label for="parola">Parola:</label>
				<input type="password" name="parola"
					id="parola" class="menu-input" value=""  />
				<label for="btn_login" >
					<input type="submit" id="btn_register" 
						class="" value="Register" />
				</label>
			
	
</form>
</div></div>


<?php require_once('_footer.php'); ?>