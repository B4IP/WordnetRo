package extract;

import java.util.Iterator;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.data.list.PointerTargetNode;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

public class WordExtractor {
	
	Dictionary dictionary;
	
	public static void main(String[] args) throws JWNLException {
		WordExtractor we = new WordExtractor(Dictionary.getDefaultResourceInstance());
		we.extractWords("[a](.)*");
		we.extractHyper("[a](.)*");
		we.extractMero("[a](.)*");
	}

	public WordExtractor(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public void extractMero(String regex) throws JWNLException {
		iterateWordsMero(dictionary.getIndexWordIterator(POS.NOUN), regex);	
		iterateWordsMero(dictionary.getIndexWordIterator(POS.VERB), regex);	
		iterateWordsMero(dictionary.getIndexWordIterator(POS.ADJECTIVE), regex);	
		iterateWordsMero(dictionary.getIndexWordIterator(POS.ADVERB), regex);	
	}
	
	private void iterateWordsMero(Iterator<IndexWord> iterator, String regex) throws JWNLException {
		while(iterator.hasNext()) {
			IndexWord indexWord = iterator.next();
    		if(indexWord.getLemma().matches(regex)) {
    			for(int i = 0; i < indexWord.getSenses().size(); i++) {
    				PointerTargetNodeList meronyms = PointerUtils.getMeronyms(indexWord.getSenses().get(i));
    				for(int j = 0; j < meronyms.size(); j++) {
    					for(int k = 0; k < meronyms.get(j).getSynset().getWords().size(); k++) {
    						String lemma = indexWord.getLemma();
    						String mero = meronyms.get(j).getSynset().getWords().get(k).getLemma();
    						if(mero.matches(regex)) {
    							// call build relationships
    							System.out.println(lemma + " => " + mero);
    						}
    					}
    				}
    			}
    		}
		}
	}
	
	public void extractHyper(String regex) throws JWNLException {
		iterateWordsHyper(dictionary.getIndexWordIterator(POS.NOUN), regex);	
		iterateWordsHyper(dictionary.getIndexWordIterator(POS.VERB), regex);	
		iterateWordsHyper(dictionary.getIndexWordIterator(POS.ADJECTIVE), regex);	
		iterateWordsHyper(dictionary.getIndexWordIterator(POS.ADVERB), regex);	
	}
	
	private void iterateWordsHyper(Iterator<IndexWord> iterator, String regex) throws JWNLException {
		while(iterator.hasNext()) {
			IndexWord indexWord = iterator.next();
    		if(indexWord.getLemma().matches(regex)) {
    			for(int i = 0; i < indexWord.getSenses().size(); i++) {
    				PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(indexWord.getSenses().get(i));
    				for(int j = 0; j < hypernyms.size(); j++) {
    					for(int k = 0; k < hypernyms.get(j).getSynset().getWords().size(); k++) {
    						String lemma = indexWord.getLemma();
    						String hyper = hypernyms.get(j).getSynset().getWords().get(k).getLemma();
    						if(hyper.matches(regex)) {
    							// call build relationships
    							System.out.println(lemma + " => " + hyper);
    						}
    					}
    				}
    			}
    		}
		}
	}
	
    public void extractWords(String regex) throws JWNLException {
        iterateWords(dictionary.getIndexWordIterator(POS.NOUN), regex);
        iterateWords(dictionary.getIndexWordIterator(POS.VERB), regex);
        iterateWords(dictionary.getIndexWordIterator(POS.ADJECTIVE), regex);
        iterateWords(dictionary.getIndexWordIterator(POS.ADVERB), regex);
    }
	
    private void iterateWords(Iterator<IndexWord> iterator, String regex) {
    	while(iterator.hasNext()) {
    		IndexWord indexWord = iterator.next();
    		if(indexWord.getLemma().matches(regex)) {
    			for(int i = 0; i < indexWord.getSenses().size(); i++) {
    				String lemma = indexWord.getLemma();
    				String glossa = indexWord.getSenses().get(i).getSynset().getGloss(); 
    				// call translate
    				// call insert into database
    				System.out.println(lemma + " => " + glossa);
    			}
    		}
    	}
    }
    
}
