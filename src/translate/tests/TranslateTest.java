package translate.tests;

import apis.TranslateAPI;
import translate.apis.BingTranslator;
import translate.apis.GoogleTranslate;
import translate.apis.GoogleTranslate2;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = new BingTranslator("en", "ro");
		System.out.printf("%s", tr.translate("I love my blue car."));
	}
}
