<?php
class Form {





	public function isPost($field = null) {
		if (!empty($field)) {
			if (isset($_POST[$field])) {
				return true;
			}
			return false;			
		} else {
			if (!empty($_POST)) {
				return true;
			}
			return false;
		}
	}
	
	
	
	
	
	
	
	
	public function getPost($field = null) {
		if (!empty($field)) {
			return $this->isPost($field) ? 
					strip_tags($_POST[$field]) : 
					null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public function stickySelect($field, $value, $default = null) {
		if ($this->isPost($field) && $this->getPost($field) == $value) {
			return " selected=\"selected\"";
		} else {
			return !empty($default) && $default == $value ?
					" selected=\"selected\"" :
					null;
		}
	}
	
	
	
	
	
	
	
	
	public function stickyText($field, $value = null) {
		if ($this->isPost($field)) {
			return stripslashes($this->getPost($field));
		} else {
			return !empty($value) ? $value : null;
		}
	}
	
	




	
	
	public function getPostArray($expected = null) {
		$out = array();
		if ($this->isPost()) {
			foreach($_POST as $key => $value) {
				if (!empty($expected)) {
					if (in_array($key, $expected)) {
						$out[$key] = strip_tags($value);
					}
				} else {
					$out[$key] = strip_tags($value);
				}
			}
		}
		return $out;
	}
	
	
	
	
	
	
	




}