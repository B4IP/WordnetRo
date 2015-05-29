<?php require_once('_header.php'); 

if(isset($_POST["cuvant"]))
	$post=$_POST["cuvant"];
$db=new Dbase();

$sql="Select * from cuvinte_romana where cuvant_rom='".$post."'";
$resultArray=$db->fetchOne($sql);

$id =$resultArray ['ID_cuvant_rom'];
$cuvant=$resultArray;


	
	
		$objForm = new Form();
		$objValid = new Validation($objForm);
		$objUpdate= new Update();
		
			
		if ($objForm->isPost('cuvant_rom')) {
			
			$objValid->_expected = array(
				'ID_cuvant_rom',
				'cuvant_rom',
				'cuvant_eng',
				'glossa',
				'ID_cuvant_eng_FK',
				'modificat',
				'glossa_tradusa',
				'POS'
			);
			
			$objValid->_required = array(
				'ID_cuvant_rom',
				'cuvant_rom',
				'cuvant_eng',
				'glossa',
				'ID_cuvant_eng_FK',
				'modificat',
				'glossa_tradusa',
				'POS'
			);
			$test=0;
			$param="UPDATE `cuvinte_romana` SET ";
			
			if ($objValid->isValid()) {
				
				$arrparam=$objValid->_post;
				foreach ($arrparam as $key=>$value) {
					$param.="`".$key."`='".$value."', ";
				}
				$param=substr($param,0,-2);
				$param.=" WHERE `ID_cuvant_rom` = '".$arrparam['ID_cuvant_rom']."'";
			$db=new Dbase();
			if($db->query($param))
					Helper::Redirect('/');
				
				}
		}
?>

<div class="header-intro header-intro--alternate" >
		<div class="header-info">



<form action="" method="post" enctype="multipart/form-data">
		
		<table class='edit-table' cellpadding="0" cellspacing="0" border="0">
			
						
			<tr>
				<th><label for="ID_cuvant_rom">ID_cuvant_rom: *</label></th>
				<td>
					
					<input type="text" name="ID_cuvant_rom" id="ID_cuvant_rom" 
						value="<?php echo $objForm->stickyText('ID_cuvant_rom', $cuvant['ID_cuvant_rom']); ?>" class="search-input-edit" />
				</td>
			</tr>
			<tr>
			<tr>
				<th><label for="cuvant_rom">Cuvant Romana: *</label></th>
				<td>
					
					<input type="text" name="cuvant_rom" id="cuvant_rom" 
						value="<?php echo $objForm->stickyText('cuvant_rom', $cuvant['cuvant_rom']); ?>" class="search-input-edit" />
				</td>
			</tr>
			<tr>
				<th><label for="cuvant_eng">Cuvant Engleza: *</label></th>
				<td>
					
					<input type="text" name="cuvant_eng" id="cuvant_eng" 
						value="<?php echo $objForm->stickyText('cuvant_eng', $cuvant['cuvant_eng']); ?>" class="search-input-edit" />
				</td>
			</tr>
			
			<tr>
				<th><label for="glossa">Glossa Engleza: *</label></th>
				<td>
					<textarea name="glossa" id="glossa" cols="" rows=""
						class="search-input-edit search-input-edit--block"><?php echo $objForm->stickyText('glossa', $cuvant['glossa']); ?></textarea>
				</td>
			</tr>
			<tr>
				<th><label for="ID_cuvant_eng_FK">ID_cuvant_eng_FK: *</label></th>
				<td>
					
					<input type="text" name="ID_cuvant_eng_FK" id="ID_cuvant_eng_FK" 
						value="<?php echo $objForm->stickyText('ID_cuvant_eng_FK', $cuvant['ID_cuvant_eng_FK']); ?>" class="search-input-edit" />
				</td>
			</tr>
			<tr>
				<th><label for="modificat">Modificat: *</label></th>
				<td>
					
					<input type="text" name="modificat" id="modificat" 
						value="<?php echo $objForm->stickyText('modificat', $cuvant['modificat']); ?>" class="search-input-edit" />
				</td>
			</tr>
			<tr>
				<th><label for="glossa_tradusa">Glossa Romana: *</label></th>
				<td>
					<textarea name="glossa_tradusa" id="glossa_tradusa" cols="" rows=""
						class="search-input-edit search-input-edit--block"><?php echo $objForm->stickyText('glossa_tradusa', $cuvant['glossa_tradusa']); ?></textarea>
				</td>
			</tr>
			<tr>
				<th><label for="POS">POS : *</label></th>
				<td>
					
					<input type="text" name="POS" id="POS" 
						value="<?php echo $objForm->stickyText('POS', $cuvant['POS']); ?>" class="search-input-edit" />
				</td>
			</tr>
			
			<tr>
				<th>&nbsp;</th>
				<td>
					<label for="btn">
						<input type="submit" id="btn" class="submit-button" value="Update" />
					</label>
				</td>
			</tr>
			
			
		</table>
		
	</form>

</div></div>


<?php require_once('_footer.php'); 	?>