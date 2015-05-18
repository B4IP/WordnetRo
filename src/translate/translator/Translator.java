package translate.translator;

import java.util.HashMap;

import net.sf.extjwnl.data.POS;
import translate.apis.SentenceTranslator;
import translate.apis.GoogleTranslate;

public class Translator{
	private HashMap<POS, String> prefix, sufix;
	
	public Translator() {
		prefix = new HashMap<POS, String>();
		prefix.put(POS.ADJECTIVE, "The meaning of word ");
		prefix.put(POS.ADVERB, "A ");
		prefix.put(POS.NOUN, "a ");
		prefix.put(POS.VERB, "to ");
		sufix = new HashMap<POS, String>();
		sufix.put(POS.ADJECTIVE, " is ");
		sufix.put(POS.ADVERB, " ");
		sufix.put(POS.NOUN, " is a ");
		sufix.put(POS.VERB, "means");
	}
	
	public static String translateFromDefinition(String word, POS type, String definition){
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
