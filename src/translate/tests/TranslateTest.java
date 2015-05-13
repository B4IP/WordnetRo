package tests;

import translator.BingTranslator;
import translator.GoogleTranslate;
import translator.GoogleTranslate2;
import translator.TranslateAPI;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = new BingTranslator("en", "ro");
		System.out.printf("%s", tr.translate("I love my blue car."));
	}
}
