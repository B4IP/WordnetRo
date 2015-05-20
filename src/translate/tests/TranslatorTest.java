package translate.tests;

import translate.translator.Translator;

public class TranslatorTest {

	public static void main(String[] args) {
		String word_translated = null;
		Translator tr = new Translator();
		
		word_translated = tr.translateFromSentence("game", null, "The game ended as tie.");
		System.out.println(word_translated);
		
		word_translated = tr.translateFromSentence("tie", null, "The game ended as tie.");
		System.out.println(word_translated);
		
		word_translated = tr.translateFromSentence("little", null, "Mary had a little lamb.");
		System.out.println(word_translated);
		
		word_translated = tr.translateFromSentence("santa", null, "santa and tooth fairy aren't real.");
		System.out.println(word_translated);
	}

}
