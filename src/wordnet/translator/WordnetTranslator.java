package wordnet.translator;


import net.sf.extjwnl.data.POS;
import translate.apis.GoogleTranslate;
import wordnet.database.WordnetDatabase;
import translate.translator.Translator;

/**
 *
 * @author Mihai Cimpan
 */
public class WordnetTranslator {

    public static void translateWords() {
        
        System.out.println("Called translateWords");
        int idCuvantRomana = WordnetDatabase.getContiunePosition();
        // probleme cu 6637, 9213, 9214, 9216, 11377, 12453, 12454, 12455, 13248, 14390, 17377
        
        
        int stopPosition = WordnetDatabase.getStopPosition();
        System.out.println("Start: " + idCuvantRomana + "\nEnd: " + stopPosition);

        String word;
        String gloss;
        String glossa_tradusa;
        POS pos;
        String traducere;
        int succes;
        
        Translator translateWord = new Translator();
        GoogleTranslate tranlateGloss = new GoogleTranslate("en", "ro");
       
        while (idCuvantRomana != stopPosition) {
            word = WordnetDatabase.getEnglishWord(idCuvantRomana);
            pos = WordnetDatabase.getEnglishPOS(idCuvantRomana);
            gloss = WordnetDatabase.getEnglishGloss(idCuvantRomana);
            
            System.out.println("____________________________________________"
                    + "____________________________________________");
            System.out.println(idCuvantRomana + " - " + word);
            
            traducere = translateWord.translate(word, pos, gloss);
            glossa_tradusa = tranlateGloss.translateSentence(gloss);       
            
            if (glossa_tradusa == null) {
                glossa_tradusa = gloss;
            }
            if (traducere == null) {
                traducere = word;
                System.out.println("   %%%%%%%%%%%%%%%%%    ");
                System.out.println("     Nu s-a tradus     ");
                System.out.println("   %%%%%%%%%%%%%%%%%    ");
                succes = -1;
            } else {
               
                succes = 1;
                System.out.println("    ---------------------");
                System.out.println("      " + "+" + traducere + "+");
                System.out.println("    ---------------------");
            }
            System.out.println("     --> glossa engleza: @ " + gloss + " @    ");
            System.out.println("     --> glossa romana : @ " + glossa_tradusa + " @    ");
            WordnetDatabase.updateRomaninWord(idCuvantRomana, traducere, glossa_tradusa, succes);
            idCuvantRomana++;
        }
    }
    
    public static void translateFailedTranslations() {

        System.out.println("Called translateFailedTranslations");
        int idCuvantRomana = WordnetDatabase.getContiunePosition();
        int listOfFailedTraslations[] = WordnetDatabase.getFailedToTranslateIDs();

        String word;
        String gloss;
        String glossa_tradusa;
        POS pos;
        String traducere;
        int succes;     
        Translator translateWord = new Translator();
        GoogleTranslate tranlateGloss = new GoogleTranslate("en", "ro");
         
        for (int i=0;i<listOfFailedTraslations.length;i++)
        {
            idCuvantRomana = listOfFailedTraslations[i];
            word = WordnetDatabase.getEnglishWord(idCuvantRomana);
            pos = WordnetDatabase.getEnglishPOS(idCuvantRomana);
            gloss = WordnetDatabase.getEnglishGloss(idCuvantRomana);
            
            System.out.println("____________________________________________"
                    + "____________________________________________");
            System.out.println(idCuvantRomana + " - " + word);
            
            traducere = translateWord.translate(word, pos, gloss);
            glossa_tradusa = tranlateGloss.translateSentence(gloss);       
            
            if (glossa_tradusa == null) {
                glossa_tradusa = gloss;
            }
            if (traducere == null) {
                traducere = word;
                System.out.println("   %%%%%%%%%%%%%%%%%    ");
                System.out.println("     Nu s-a tradus     ");
                System.out.println("   %%%%%%%%%%%%%%%%%    ");
                succes = -1;
            } else {
               
                succes = 1;
                System.out.println("    ---------------------");
                System.out.println("      " + "+" + traducere + "+");
                System.out.println("    ---------------------");
            }
            System.out.println("     --> glossa engleza: @ " + gloss + " @    ");
            System.out.println("     --> glossa romana : @ " + glossa_tradusa + " @    ");
            WordnetDatabase.updateRomaninWord(idCuvantRomana, traducere, glossa_tradusa, succes);    
        }      
    }
}
