<?php
class User extends Application {
	
	private $_table = "utilizatori";
	public $_id;
	
	
	
	
	
	
	
	
	
	public function isUser($email, $password) {
		$sql = "SELECT * FROM `{$this->_table}`
				WHERE `email` = '".$this->db->escape($email)."'
				AND `parola` = '".$this->db->escape($password)."'
				";
		$result = $this->db->fetchOne($sql);
		if (!empty($result)) {
			$this->_id = $result['id'];
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	public function addUser($params = null, $password = null) {
	
		if (!empty($params) && !empty($password)) {
			$this->db->prepareInsert($params);
			if ($this->db->insert($this->_table)) {
				
				return true;
				
			}
			return false;
		}
		return false;
	
	}
	
	
	
	
	
	
	
	
	public function getByEmail($email = null) {
		if (!empty($email)) {
			$sql = "SELECT `id` FROM `{$this->_table}`
					WHERE `email` = '".$this->db->escape($email)."'
					";
			return $this->db->fetchOne($sql);
		}
	}
	
	
	
	
	
	
	
	public function getUser($id = null) {
		if (!empty($id)) {
			$sql = "SELECT * FROM `{$this->_table}`
					WHERE `id` = '".$this->db->escape($id)."'";
			return $this->db->fetchOne($sql);
		}
	}
	
	

}