// a se vedea metoda iterateWords pentru un exemplu de interactiune
// intre WordExtractor (ce foloseste API-ul Wordnetului) si baza de date 
package extract;

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
            throws FileNotFoundException, JWNLException, CloneNotSupportedException, SQLException {
        WordExtractor we = new WordExtractor(Dictionary.getDefaultResourceInstance());
         we.extractWords("[a](.)*");       
         we.extractHyper("[a](.)*");
         we.extractMero("[a](.)*");
        // WordnetDataBase.runDataBaseScript(); 
        WordnetDataBase.closeDataBaseConnection();
    }

    public WordExtractor(Dictionary dictionary) throws SQLException {
        this.dictionary = dictionary;
    }

    public void extractWords(String regex) throws JWNLException, SQLException {
        Iterator<IndexWord> nounIterator = getDictionary().getIndexWordIterator(POS.NOUN);
        Iterator<IndexWord> verbIterator = getDictionary().getIndexWordIterator(POS.VERB);
        Iterator<IndexWord> adjectiveIterator = getDictionary().getIndexWordIterator(POS.ADJECTIVE);
        Iterator<IndexWord> adverbeIterator = getDictionary().getIndexWordIterator(POS.ADVERB);

        iterateWords(findStartPosition(nounIterator, regex), regex);
        iterateWords(findStartPosition(verbIterator, regex), regex);
        iterateWords(findStartPosition(adjectiveIterator, regex), regex);
        iterateWords(findStartPosition(adverbeIterator, regex), regex);
    }

    public void extractHyper(String regex) throws JWNLException, SQLException {
        Iterator<IndexWord> nounIterator = getDictionary().getIndexWordIterator(POS.NOUN);
        Iterator<IndexWord> verbIterator = getDictionary().getIndexWordIterator(POS.VERB);
        Iterator<IndexWord> adjectiveIterator = getDictionary().getIndexWordIterator(POS.ADJECTIVE);
        Iterator<IndexWord> adverbeIterator = getDictionary().getIndexWordIterator(POS.ADVERB);

        iterateWordsHyper(findStartPosition(nounIterator, regex), regex);
        iterateWordsHyper(findStartPosition(verbIterator, regex), regex);
        iterateWordsHyper(findStartPosition(adjectiveIterator, regex), regex);
        iterateWordsHyper(findStartPosition(adverbeIterator, regex), regex);
    }

    public void extractMero(String regex) throws JWNLException, SQLException {
        Iterator<IndexWord> nounIterator = getDictionary().getIndexWordIterator(POS.NOUN);
        Iterator<IndexWord> verbIterator = getDictionary().getIndexWordIterator(POS.VERB);
        Iterator<IndexWord> adjectiveIterator = getDictionary().getIndexWordIterator(POS.ADJECTIVE);
        Iterator<IndexWord> adverbeIterator = getDictionary().getIndexWordIterator(POS.ADVERB);

        iterateWordsMero(findStartPosition(nounIterator, regex), regex);
        iterateWordsMero(findStartPosition(verbIterator, regex), regex);
        iterateWordsMero(findStartPosition(adjectiveIterator, regex), regex);
        iterateWordsMero(findStartPosition(adverbeIterator, regex), regex);
    }

    private void iterateWords(Iterator<IndexWord> iterator, String regex) throws SQLException {
        int idEnglishWord = WordnetDataBase.getIdCuvantEngleza();
        int idCuvantRomana = WordnetDataBase.getIdCuvantRomana();
        IndexWord indexWord;
        String word;
        String gloss;
        String traducere = "Traducere";
        while (iterator.hasNext()) {
            indexWord = iterator.next();
            word = indexWord.getLemma();
            if (word.matches(regex)) {
                WordnetDataBase.insertEnglishWords(idEnglishWord, word);
                System.out.println("---------------------------");
                System.out.println(idEnglishWord + "  " + word);
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    gloss = indexWord.getSenses().get(i).getSynset().getGloss();
                    // traducere = modul3Traducere(word, gloss);
                    WordnetDataBase.insertRomanianWords(idCuvantRomana, traducere, word, gloss, idEnglishWord);
                    
                    System.out.print("     ");
                    System.out.print("@ " + idCuvantRomana + " @    ");
                    System.out.print("@ " + traducere + " @    ");
                    System.out.print("@ " + word + " @    ");                    
                    System.out.println("@ " + idEnglishWord + " @    ");
                    System.out.print("     --> glossa: @" + gloss + " @    ");
                    System.out.println();
                    idCuvantRomana++;
                }
                idEnglishWord++;
            } else {
                break;
            }
        }
        WordnetDataBase.setIdCuvantEngleza(idEnglishWord);
        WordnetDataBase.setIdCuvantRomana(idCuvantRomana);
    }

    private void iterateWordsHyper(Iterator<IndexWord> iterator, String regex) throws JWNLException, SQLException {
        String lemma;
        IndexWord indexWord;
        while (iterator.hasNext()) {
            indexWord = iterator.next();           
            if (indexWord.getLemma().matches(regex)) {
                lemma = indexWord.getLemma();
                System.out.println("------------------");
                System.out.println("WordHyper: " + lemma);
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(indexWord.getSenses().get(i));                 
                    for (int j = 0; j < hypernyms.size(); j++) {
                        for (int k = 0; k < hypernyms.get(j).getSynset().getWords().size(); k++) {                          
                            String hyper = hypernyms.get(j).getSynset().getWords().get(k).getLemma();
                            if (hyper.matches(regex)) {
                                // call build relationships
                                System.out.println("      Hypernym: " + hyper);
                                WordnetDataBase.insertHypernyms(lemma, hyper);
                            }
                        }
                    }
                }
            }
        }
    }

    private void iterateWordsMero(Iterator<IndexWord> iterator, String regex) throws JWNLException, SQLException {
        String lemma;
        IndexWord indexWord;
        while (iterator.hasNext()) {
            indexWord = iterator.next();
            if (indexWord.getLemma().matches(regex)) {
                lemma = indexWord.getLemma();
                System.out.println("------------------");
                System.out.println("WordMero: " + lemma);
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    PointerTargetNodeList meronyms = PointerUtils.getMeronyms(indexWord.getSenses().get(i));
                    for (int j = 0; j < meronyms.size(); j++) {
                        for (int k = 0; k < meronyms.get(j).getSynset().getWords().size(); k++) {
                            String mero = meronyms.get(j).getSynset().getWords().get(k).getLemma();
                            if (mero.matches(regex)) {
                                System.out.println("      Meronym: " + mero);
                                WordnetDataBase.insertMeronyms(lemma, mero);                          
                                // System.out.println(lemma + " => " + mero);
                            }
                        }
                    }
                }
            }
        }
    }

    private Iterator<IndexWord> findStartPosition(Iterator<IndexWord> iterator, String regex)
            throws SQLException {
        while (iterator.hasNext()) {
            if (iterator.next().getLemma().matches(regex)) {
                break;
            }
        }
        return iterator;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

}
