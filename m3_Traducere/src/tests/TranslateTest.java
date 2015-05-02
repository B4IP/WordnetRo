package tests;

import translator.GoogleTranslate;
import translator.GoogleTranslate2;
import translator.TranslateAPI;

public class TranslateTest {
	public static void main(String args[]){
		TranslateAPI tr = new GoogleTranslate2("ro", "en");
		tr.translate("Azi e luni");
	}
}
