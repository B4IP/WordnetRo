package translate.translator;

import translate.apis.SentenceTranslator;
import translate.apis.GoogleTranslate;

public class Translator{
	public static String translateFromDefinition(String word, String definition){
		SentenceTranslator api = new GoogleTranslate("en", "ro");
		String sentence = null;
		if (definition.trim().startsWith("a"))
			sentence = String.format("A %s is %s.", word, definition);
		else
			sentence = String.format("A %s is a %s.", word, definition);
		
		String tr = api.translateSentence(sentence);
		int index = tr.indexOf("este");
		String trWord = tr.substring(0, index).trim();
		
		if (trWord.indexOf(' ')<=1){
			index =  trWord.indexOf(' ');
			trWord = trWord.substring(index).trim();
		}
		
		return trWord;
	}
}
