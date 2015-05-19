package translate.tests;

import translate.translator.Translator;

public class TranslatorTest {

	public static void main(String[] args) {
		String word_translated = null;
		
		word_translated = Translator.translateFromSentence("game", "The game ended as tie.");
		System.out.println(word_translated);
		
		word_translated = Translator.translateFromSentence("tie", "The game ended as tie.");
		System.out.println(word_translated);
		
		word_translated = Translator.translateFromSentence("little", "Mary had a little lamb.");
		System.out.println(word_translated);
		
		word_translated = Translator.translateFromSentence("santa", "santa and tooth fairy aren't real.");
		System.out.println(word_translated);
	}

}
