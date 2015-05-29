<?php require_once('_header.php'); 



if(isset($_POST["search"]))
	$post=$_POST["search"];
$db=new Dbase();

$sql="Select * from cuvinte_romana where cuvant_rom='".$post."'";
$resultArray=$db->fetchAll($sql);

?>





<section class='header-intro'>
<div class="search-results-wrapper">
		<h1 class="search-title">Rezultate returnate pentru:</h1>
		<h1 class="search-title-word"><?php echo $post;?></h1>
		

		<!-- <h1 class="word-type">
		<?php  if($resultArray[0]['POS']=="noun") echo "Substantiv"; else echo $resultArray[0]['POS'];?>
		</h1> -->

<?php 
foreach($resultArray as $key)
{
		echo  "<div class=\"search-result\">";
			echo "<p class=\"searched-word\">".$key['cuvant_rom']."</p>";
			echo "<p class=\"word-definition\">".$key['glossa_tradusa']."</p>";
		echo "</div>";

		$sql="SELECT * FROM cuvinte_romana WHERE ID_cuvant_eng_FK IN (SELECT ID_meronim_eng_FK FROM meronime WHERE ID_cuvant_eng_FK=".$key['ID_cuvant_eng_FK'].");";
		$meroArr = $db->fetchAll($sql);

		if (count($meroArr) > 0) {
			echo "<h1 class=\"word-type\">Meronime</h1>";
			foreach($meroArr as $mero) {
				echo  "<div class=\"search-result\">";
					echo "<p class=\"searched-word\">".$mero['cuvant_rom']."</p>";
					echo "<p class=\"word-definition\">".$mero['glossa_tradusa']."</p>";
				echo "</div>";
			}
		}

		$sql="SELECT * FROM cuvinte_romana WHERE ID_cuvant_eng_FK IN (SELECT ID_hiperonim_eng_FK FROM hiperonime WHERE ID_cuvant_eng_FK=".$key['ID_cuvant_eng_FK'].");";
		$hipeArr = $db->fetchAll($sql);

		if (count($hipeArr) > 0) {
			echo "<h1 class=\"word-type\">Hiperonime</h1>";
			foreach($hipeArr as $hipe) {
				echo  "<div class=\"search-result\">";
					echo "<p class=\"searched-word\">".$hipe['cuvant_rom']."</p>";
					echo "<p class=\"word-definition\">".$hipe['glossa_tradusa']."</p>";
				echo "</div>";
			}
		}
}

?>

	</div>
</section>



	<?php require_once('_footer.php'); ?>