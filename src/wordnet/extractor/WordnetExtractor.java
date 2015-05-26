package wordnet.extractor;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

import java.io.FileNotFoundException;
import java.util.Iterator;

import wordnet.database.WordnetDatabase;
import wordnet.translator.WordnetTranslator;
import translate.translator.Translator;

/**
 *
 * @author Andrei Pricop
 * @author Mihai Cimpan
 */
public class WordnetExtractor {

    private Dictionary dictionary;

    public static void main(String[] args)
            throws FileNotFoundException, JWNLException, CloneNotSupportedException {
        
        /*
        WordnetExtractor we = new WordnetExtractor(Dictionary.getDefaultResourceInstance());
        we.extractWords("[a-b](.)*");    
         WordnetDatabase.setIdCuvantEngleza(1);
         we.extractHyper("[a-b](.)*");
         WordnetDatabase.setIdCuvantEngleza(1);
         we.extractMero("[a-b](.)*");       
          */
        
        //WordnetTranslator.translateWords();
        WordnetTranslator.translateFailedTranslations();
        WordnetDatabase.closeDataBaseConnection();
    }

    public WordnetExtractor(Dictionary dictionary){
        this.dictionary = dictionary;
    }

    public void extractWords(String regex) throws JWNLException {
        Iterator<IndexWord> nounIterator = getDictionary().getIndexWordIterator(POS.NOUN);
        Iterator<IndexWord> verbIterator = getDictionary().getIndexWordIterator(POS.VERB);
        Iterator<IndexWord> adjectiveIterator = getDictionary().getIndexWordIterator(POS.ADJECTIVE);
        Iterator<IndexWord> adverbeIterator = getDictionary().getIndexWordIterator(POS.ADVERB);

        iterateWords(findStartPosition(nounIterator, regex), regex);
        iterateWords(findStartPosition(verbIterator, regex), regex);
        iterateWords(findStartPosition(adjectiveIterator, regex), regex);
        iterateWords(findStartPosition(adverbeIterator, regex), regex);
    }

    public void extractHyper(String regex) throws JWNLException {
        Iterator<IndexWord> nounIterator = getDictionary().getIndexWordIterator(POS.NOUN);
        Iterator<IndexWord> verbIterator = getDictionary().getIndexWordIterator(POS.VERB);
        Iterator<IndexWord> adjectiveIterator = getDictionary().getIndexWordIterator(POS.ADJECTIVE);
        Iterator<IndexWord> adverbeIterator = getDictionary().getIndexWordIterator(POS.ADVERB);

        iterateWordsHyper(findStartPosition(nounIterator, regex), regex);
        iterateWordsHyper(findStartPosition(verbIterator, regex), regex);
        iterateWordsHyper(findStartPosition(adjectiveIterator, regex), regex);
        iterateWordsHyper(findStartPosition(adverbeIterator, regex), regex);
    }

    public void extractMero(String regex) throws JWNLException {
        Iterator<IndexWord> nounIterator = getDictionary().getIndexWordIterator(POS.NOUN);
        Iterator<IndexWord> verbIterator = getDictionary().getIndexWordIterator(POS.VERB);
        Iterator<IndexWord> adjectiveIterator = getDictionary().getIndexWordIterator(POS.ADJECTIVE);
        Iterator<IndexWord> adverbeIterator = getDictionary().getIndexWordIterator(POS.ADVERB);

        iterateWordsMero(findStartPosition(nounIterator, regex), regex);
        iterateWordsMero(findStartPosition(verbIterator, regex), regex);
        iterateWordsMero(findStartPosition(adjectiveIterator, regex), regex);
        iterateWordsMero(findStartPosition(adverbeIterator, regex), regex);
    }

    private void iterateWords(Iterator<IndexWord> iterator, String regex) {
        int idEnglishWord = WordnetDatabase.getIdCuvantEngleza();
        int idCuvantRomana = WordnetDatabase.getIdCuvantRomana();
        IndexWord indexWord;       
        String word;
        String gloss;
        String traducere = "traducere_lipsa";
        String glossa_tradusa = "glossa_lipsa";
        
        Translator translate = new Translator();
        while (iterator.hasNext()) {
            indexWord = iterator.next();        
            word = indexWord.getLemma();
            String tip = indexWord.getPOS().getLabel();
            if (word.matches(regex)) {
                
               WordnetDatabase.insertEnglishWords(idEnglishWord, word);                
                System.out.println("---------------------------");
                System.out.println(idEnglishWord + " WordWord : "  + word + " tip: " + tip );
                
                for (int i = 0; i < indexWord.getSenses().size(); i++) {                
                    gloss = indexWord.getSenses().get(i).getSynset().getGloss(); 
                    WordnetDatabase.insertRomanianWords (idCuvantRomana, traducere, word, gloss, idEnglishWord, 0, glossa_tradusa, tip);  
                    
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
        WordnetDatabase.setIdCuvantEngleza(idEnglishWord);
        WordnetDatabase.setIdCuvantRomana(idCuvantRomana);
    }

    private void iterateWordsHyper(Iterator<IndexWord> iterator, String regex) throws JWNLException {
        String lemma;
        IndexWord indexWord;
        int ID = WordnetDatabase.getIdCuvantEngleza();
        while (iterator.hasNext()) {
            indexWord = iterator.next();           
            if (indexWord.getLemma().matches(regex)) {
                lemma = indexWord.getLemma();
                System.out.println("------------------");
                System.out.println(ID + " WordHyper: " + lemma);
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(indexWord.getSenses().get(i));                 
                    for (int j = 0; j < hypernyms.size(); j++) {
                        for (int k = 0; k < hypernyms.get(j).getSynset().getWords().size(); k++) {                          
                            String hyper = hypernyms.get(j).getSynset().getWords().get(k).getLemma();
                            if (hyper.matches(regex)) {
                                
                                String glossa = hypernyms.get(j).getSynset().getGloss();                    
                                System.out.println("      Hypernym: " + hyper);
                                System.out.println("      ---> Glossa: " + glossa);
                                WordnetDatabase.insertHypernyms(ID, hyper, glossa );
                               
                            }
                        }
                    }
                }
                 ID++;
            }
        }
        WordnetDatabase.setIdCuvantEngleza(ID);
    }

    private void iterateWordsMero(Iterator<IndexWord> iterator, String regex) throws JWNLException {
        String lemma;
        IndexWord indexWord;
        int ID = WordnetDatabase.getIdCuvantEngleza();
        while (iterator.hasNext()) {
            indexWord = iterator.next();
            if (indexWord.getLemma().matches(regex)) {
                lemma = indexWord.getLemma();
                System.out.println("------------------");
                System.out.println(ID + " WordMero: " + lemma);
                for (int i = 0; i < indexWord.getSenses().size(); i++) {
                    PointerTargetNodeList meronyms = PointerUtils.getMeronyms(indexWord.getSenses().get(i));
                    for (int j = 0; j < meronyms.size(); j++) {
                        for (int k = 0; k < meronyms.get(j).getSynset().getWords().size(); k++) {
                            String mero = meronyms.get(j).getSynset().getWords().get(k).getLemma();
                            if (mero.matches(regex)) {
                                String glossa = meronyms.get(j).getSynset().getGloss();                                                                                
                                System.out.println("      Meronym: " + mero);
                                System.out.println("      ---> Glossa: " + glossa);
                                WordnetDatabase.insertMeronyms(ID, mero, glossa);                               
                            }
                        }
                    }
                }
                ID++;
            }
        }
         WordnetDatabase.setIdCuvantEngleza(ID);
    }
    
            
    private Iterator<IndexWord> findStartPosition(Iterator<IndexWord> iterator, String regex) {
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
