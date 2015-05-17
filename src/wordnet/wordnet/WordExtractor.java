// a se vedea metoda iterateWords pentru un exemplu de interactiune
// intre WordExtractor (ce foloseste API-ul Wordnetului) si baza de date 


package wordnet;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

import java.io.FileNotFoundException;
import java.util.Iterator;

import database.WordnetDataBase;
import java.sql.SQLException;


/**
 *
 * @author Andrei Pricop
 * @author Mihai Cimpan (a adus mici modificari )
 */
public class WordExtractor {

    private Dictionary dictionary;
    
    public static void main(String[] args) 
        throws FileNotFoundException, JWNLException, CloneNotSupportedException, SQLException  {
        WordExtractor we = new WordExtractor(Dictionary.getDefaultResourceInstance());
        we.extractWords("[a](.)*");
        WordnetDataBase.closeDataBaseConnection();
       //we.extractHyper("[a](.)*");
       //we.extractMero("[a](.)*");
    }
       
    public WordExtractor(Dictionary dictionary) throws SQLException {
        this.dictionary = dictionary;
    }

    public void extractMero(String regex) throws JWNLException {
        iterateWordsMero(getDictionary().getIndexWordIterator(POS.NOUN), regex);
        iterateWordsMero(getDictionary().getIndexWordIterator(POS.VERB), regex);
        iterateWordsMero(getDictionary().getIndexWordIterator(POS.ADJECTIVE), regex);
        iterateWordsMero(getDictionary().getIndexWordIterator(POS.ADVERB), regex);
    }

    private void iterateWordsMero(Iterator<IndexWord> iterator, String regex) throws JWNLException {
        while (iterator.hasNext()) {
            IndexWord indexWord = iterator.next();
            if (indexWord.getLemma().matches(regex)) {
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    PointerTargetNodeList meronyms = PointerUtils.getMeronyms(indexWord.getSenses().get(i));
                    for (int j = 0; j < meronyms.size(); j++) {
                        for (int k = 0; k < meronyms.get(j).getSynset().getWords().size(); k++) {
                            String lemma = indexWord.getLemma();
                            String mero = meronyms.get(j).getSynset().getWords().get(k).getLemma();
                            if (mero.matches(regex)) {
                                // call build relationships                              
                               // System.out.println(lemma + " => " + mero);
                            }
                        }
                    }
                }
            }
        }
    }

    public void extractHyper(String regex) throws JWNLException {
        iterateWordsHyper(getDictionary().getIndexWordIterator(POS.NOUN), regex);
        iterateWordsHyper(getDictionary().getIndexWordIterator(POS.VERB), regex);
        iterateWordsHyper(getDictionary().getIndexWordIterator(POS.ADJECTIVE), regex);
        iterateWordsHyper(getDictionary().getIndexWordIterator(POS.ADVERB), regex);
    }

    private void iterateWordsHyper(Iterator<IndexWord> iterator, String regex) throws JWNLException {
        while (iterator.hasNext()) {
            IndexWord indexWord = iterator.next();
            if (indexWord.getLemma().matches(regex)) {
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(indexWord.getSenses().get(i));
                    for (int j = 0; j < hypernyms.size(); j++) {
                        for (int k = 0; k < hypernyms.get(j).getSynset().getWords().size(); k++) {
                            String lemma = indexWord.getLemma();
                            String hyper = hypernyms.get(j).getSynset().getWords().get(k).getLemma();
                            if (hyper.matches(regex)) {
                                // call build relationships
                                System.out.println(lemma + " => " + hyper);
                            }
                        }
                    }
                }
            }
        }
    }

    public void extractWords(String regex) throws JWNLException, SQLException {
        iterateWords(getDictionary().getIndexWordIterator(POS.NOUN), regex);
        iterateWords(getDictionary().getIndexWordIterator(POS.VERB), regex);
        iterateWords(getDictionary().getIndexWordIterator(POS.ADJECTIVE), regex);
       // iterateWords(getDictionary().getIndexWordIterator(POS.ADVERB), regex);      
    }

    private void iterateWords(Iterator<IndexWord> iterator, String regex) throws SQLException {
        
        int idEng = WordnetDataBase.getIdCuvantEngleza();
        int idRom = WordnetDataBase.getIdCuvantRomana();
        String traducere = "Traducere";
        while (iterator.hasNext()) {
            IndexWord indexWord = iterator.next();
            String lemma = indexWord.getLemma();
            if (indexWord.getLemma().matches(regex)) {
                 WordnetDataBase.insertEnglishWords(idEng, lemma);  
                 System.out.println(idEng);
                for (int i = 0; i < indexWord.getSenses().size(); i++) {                  
                    String glossa = indexWord.getSenses().get(i).getSynset().getGloss();
                     // call translate (modulul 3)
                    // traducere = modulu3Traducere(lemma, glossa);
                    WordnetDataBase.insertRomanianWords(idRom, traducere, lemma, glossa, idEng);
                    idRom++;                    		                  
                    //System.out.println(lemma + " => " + glossa);
                }
                idEng++;
            }
           // else ...
           // trebuie sa facem aici o metoda mai eficienta pentru a nu parcurge
           // de fiecare data toate cuvintele din wordent
        }      
        WordnetDataBase.setIdCuvantEngleza(idEng);
        WordnetDataBase.setIdCuvantRomana(idRom);
    }
  
    public Dictionary getDictionary() {
        return dictionary;
    }
    
}
