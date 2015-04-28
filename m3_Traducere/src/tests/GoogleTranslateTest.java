package tests;

import translator.GoogleTranslate;
import translator.GoogleTranslate2;
import translator.TranslateAPI;

public class GoogleTranslateTest {
	public static void main(String args[]){
		TranslateAPI tr = new GoogleTranslate("ro", "en");
		tr.translate("Azi e luni");
	}
}
