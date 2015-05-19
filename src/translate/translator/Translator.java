package translate.translator;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.extjwnl.data.POS;
import translate.apis.SentenceTranslator;
import translate.apis.GoogleTranslate;
import translate.apis.Translation;
import translate.apis.WordReference;

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
	
	public static String translateFromSentence(String word, String sentence){
		//function takes a word and a sentence with that word and returns the word translated with that same meaning as in the sentence
		SentenceTranslator api = new GoogleTranslate("en", "ro");
		WordReference wr = new WordReference("en", "ro");
		String word_translated = null;
		
		String sentence_translated = api.translateSentence(sentence);
		Translation translation = new Translation(word);
		
		
		String[] words = sentence_translated.split(" ");
		for (int i=0; i < words.length; i++)
		{
			words[i] = words[i].toLowerCase();
		}
		
	    System.out.println();
	    translation = wr.getCandidates(word);
	    Iterator<String> itr1 = translation.iterator();
	    while(itr1.hasNext()) {
	         Object element = itr1.next();
	         System.out.print(element+"; ");
	         for (int i=0; i < words.length; i++)
	 		{
	        	 if (words[i].contains((String)element))
		         {
		        	return (String)element;
		         }
	 		}
	    }
	    
		return word_translated;
	}
}
