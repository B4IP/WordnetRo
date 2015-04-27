package tests;

import translator.GoogleTranslate;

public class GoogleTranslateTest {
	public static void main(String args[]){
		GoogleTranslate tr = new GoogleTranslate("ro", "en");
		tr.translate("Azi e luni");
	}
}
