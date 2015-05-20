package translate.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.sf.extjwnl.data.POS;
import translate.apis.SentenceTranslator;
import translate.apis.GoogleTranslate;
import translate.apis.Translation;
import translate.apis.WordReference;

public class Translator{
	private HashMap<POS, String> prefix, sufix;
	private HashMap<POS, ArrayList<String>> separator;
	
	public Translator() {
		prefix = new HashMap<POS, String>();
		prefix.put(POS.ADJECTIVE, "The meaning of word ");
		prefix.put(POS.ADVERB, "The meaning of word ");
		prefix.put(POS.NOUN, "The meaning of word ");
		prefix.put(POS.VERB, "To ");
		
		sufix = new HashMap<POS, String>();
		sufix.put(POS.ADJECTIVE, " is ");
		sufix.put(POS.ADVERB, " is ");
		sufix.put(POS.NOUN, " is ");
		sufix.put(POS.VERB, "means");
		
		separator = new HashMap<>();
		separator.put(POS.ADJECTIVE, new ArrayList<>());
		separator.get(POS.ADJECTIVE).add("este");
		separator.get(POS.ADJECTIVE).add("e");
	}
	
	public String translateFromDefinition(String word, POS type, String definition){
		/**
		 * Function takes a word, and a definition.
		 * It returns the translated word.
		 */
		SentenceTranslator api = new GoogleTranslate("en", "ro");
		String sentence = null;
		sentence = String.format("%s%s%s%s.");

		String tr = api.translateSentence(sentence);
		int index = tr.indexOf("este");
		String trWord = tr.substring(0, index).trim();
		
		if (trWord.indexOf(' ')<=1){
			index =  trWord.indexOf(' ');
			trWord = trWord.substring(index).trim();
		}
		
		return trWord;
	}
	
	public String translateFromSentence(String word, POS type, String sentence){
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
	
	public String translate(String word, POS type, String gloss){
		return "";
	}
}
