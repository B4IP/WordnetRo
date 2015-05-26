package translate.translator;

import java.util.Arrays;
import java.util.HashMap;

import net.sf.extjwnl.data.POS;
import translate.algo.Levenstein;
import translate.apis.ISentenceTranslator;
import translate.apis.GoogleTranslate;
import translate.apis.Translation;
import translate.apis.WordReference;
import translate.factory.API;
import translate.factory.TranslateAPIFactory;

public class Translator{
	private HashMap<POS, String> prefix, sufix;
	private HashMap<POS, String> separator, transPref;
	
	
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
		separator.put(POS.ADJECTIVE, "este");
		separator.put(POS.ADVERB, "este");
		separator.put(POS.NOUN, "este");
		separator.put(POS.VERB, "inseamna");
		
		transPref = new HashMap<>();
		transPref.put(POS.ADJECTIVE, "Sensul cuvântului");
		transPref.put(POS.ADVERB, "Sensul cuvântului");
		transPref.put(POS.NOUN, "Sensul cuvântului");
		transPref.put(POS.VERB, "A");
	}
	
	public String translateFromDefinition(String word, POS type, String definition){
		/**
		 * Function takes a word, and a definition.
		 * It returns the translated word.
		 */
		ISentenceTranslator api = new GoogleTranslate("en", "ro");
		String sentence = null;
		sentence = String.format("%s%s%s%s.", prefix.get(type), word, sufix.get(type), definition);

		String tr = api.translateSentence(sentence);
		if (tr==null)
			return null;
		int index = tr.indexOf(separator.get(type));
		if (index<0)
			return translateFromSentence(word, type, sentence);
		
		String trWord = tr.substring(0, index).trim();
		if (!trWord.startsWith(transPref.get(type)))
			return translateFromSentence(word, type, sentence);
		trWord = trWord.substring(transPref.get(type).length()).trim();
		if (trWord.length()==0)
			return translateFromSentence(word, type, sentence);
		
		return trWord;
	}
	
	public String translateFromSentence(String word, POS type, String sentence){
		//function takes a word and a sentence with that word and returns the word translated with that same meaning as in the sentence
		
		Translation translation = new Translation(word);
		for (API api : API.values()){
			translation.add(TranslateAPIFactory.atomicTranslate(word, api, "en", "ro"));
		}
		ISentenceTranslator api = new GoogleTranslate("en", "ro");
		String word_translated = null;
		
		String sentence_translated = api.translateSentence(sentence);
		if (sentence_translated==null)
			return null;
		//Translation translation = new Translation(word);
		
		
		String[] words = sentence_translated.split(" ");
		for (int i=0; i < words.length; i++)
		{
			words[i] = words[i].toLowerCase();
			if (words[i].endsWith("ul"))
			{
				words[i] = words[i].substring(0,words[i].length() - 2);
			}
			if (words[i].endsWith("ului"))
			{
				words[i] = words[i].substring(0,words[i].length() - 4);
			}
			
			
		}
		
		double score = 0, max_score = 0;
	    for (String element : translation){
	    	if (element.length()<3)
	    		continue;
	    	//System.out.print(element + "-");
			for (int i=0; i<words.length; i++)
			{
				 if (words[i].contains((String)element))
			     {
			    	return (String)element;
			     }
				 else
				 {
					score = Levenstein.getNormalisedDistance(words[i], (String)element);
					if (score > max_score)
					{
						max_score = score;
						word_translated = (String)element;
					}
				 }
			}
	    }
		return word_translated;
		
		
	}
	
	public String translate(String word, POS type, String gloss){
		String parts[] = gloss.split(";");
		String definition = parts[0], translatedDef;
		String sentences[] = Arrays.copyOfRange(parts, 1, parts.length), translatedSent[] = null;
		
		
		translatedDef = translateFromDefinition(word, type, definition);
		if (sentences.length>0){
			translatedSent = new String[sentences.length];
			for (int i=0; i<sentences.length; ++i){
				//translatedSent[i] = translateFromSentence(word, type, sentences[i]);
			}
		}
		return translatedDef;
	}
}
