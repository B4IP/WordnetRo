package tests;

import apis.BingTranslator;
import apis.GoogleTranslate;
import apis.GoogleTranslate2;
import apis.TranslateAPI;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = new BingTranslator("en", "ro");
		System.out.printf("%s", tr.translate("I love my blue car."));
	}
}
