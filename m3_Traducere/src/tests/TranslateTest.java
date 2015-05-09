package tests;

import translator.BingTranslator;
import translator.GoogleTranslate;
import translator.GoogleTranslate2;
import translator.TranslateAPI;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = new BingTranslator("ro", "en");
		System.out.printf("%s", tr.translate("Azi e luni"));
	}
}
