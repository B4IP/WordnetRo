package translate.tests;

import translate.translator.Translator;

public class TranslatorTest {

	public static void main(String[] args) {
		String word_translated = null;
		Translator tr = new Translator();
		
		/*
		 * word_translated = tr.translateFromSentence("game", null, "The game ended as tie.");
		 * System.out.println(word_translated);
		*/
		
		/*
		word_translated = tr.translateFromSentence("tie", null, "The game ended as tie.");
		System.out.println(word_translated);
		*/
		/*
		word_translated = tr.translateFromSentence("little", null, "Mary had a little lamb.");
		System.out.println(word_translated);
		*/
		/*
		word_translated = tr.translateFromSentence("award-winning", null, "This award-winning bridge spans a distance of five miles.");
		System.out.println(word_translated);
		*/
		/*
		word_translated = tr.translateFromSentence("avant-garde", null, "The avant-garde also promotes radical social reforms.");
		System.out.println(word_translated);
		*/
		
		word_translated = tr.translateFromSentence("autumn-blooming", null, "Not all autumn-blooming flowers are white.");
		System.out.println(word_translated);
		
	}

}
