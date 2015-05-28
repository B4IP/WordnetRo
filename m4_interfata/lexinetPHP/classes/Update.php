<?php
class Update extends Application
{


public function getCuvant($cuv){
$sql="Select * from cuvinte_romana where cuvant_rom='".$cuv."'";
$resultArray=$db->fetchOne($sql);
return $resultArray;
}
}