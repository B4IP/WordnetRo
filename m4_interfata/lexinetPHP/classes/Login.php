<?php
class Login {

	public static $_login_page_front = "/?page=login";
	public static $_dashboard_front = "/";
	public static $_login_front = "cid";
	
	public static $_valid_login = "valid";
	
	public static $_referrer = "refer";
	
	
	
	public static function loginFront($id = null, $url = null) {
		if (!empty($id)) {
			$url = !empty($url) ? $url : self::$_dashboard_front;
			$_SESSION[self::$_login_front] = $id;
			$_SESSION[self::$_valid_login] = 1;
			Helper::redirect($url);
		}
	}
	
	
	

}