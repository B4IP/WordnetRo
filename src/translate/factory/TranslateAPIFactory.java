
package translate.factory;

import translate.apis.Dictio;
import translate.apis.Gsp;
import translate.apis.Hallo;
import translate.apis.IWordTranslator;
import translate.apis.BingTranslator;
import translate.apis.GoogleTranslate;
import translate.apis.Translation;
import translate.apis.WordReference;

public class TranslateAPIFactory {
    /**
     * Method used to get a new instance of TranslateAPI which will use a certain engine to translate.
     * @param type The engine to be used for translation. Can be GoogleTranslate, GoogleTranslate or GoogleTranslate2
     * @param fromLang Must be the two letter abbreviation for the target language (ex: English will be "en", Spanish will be "es")
     * @param toLang Same as fromLang parameter
     * @return a new Instance which will translate all input from fromLang to toLang.
     */
    public static IWordTranslator getAPIInstance(API type, String fromLang, String toLang) {
        switch (type) {
            /*case Bing://removed because it returns a single word
                return new BingTranslator(fromLang, toLang);*/
            case Google:
                return new GoogleTranslate(fromLang, toLang);
            case Gsp:
            	return new Gsp(fromLang, toLang);
            case Hallo:
            	return new Hallo(fromLang, toLang);
            case WordReference:
            	return new WordReference(fromLang, toLang);
            case Dictio:
            	return new Dictio(fromLang, toLang);
            default:
                throw new IllegalArgumentException();
        }
    }
    
    /**
     * 
     * @param word
     * @param engine
     * @param fromLang
     * @param toLang
     * @return 
     */
    public static Translation atomicTranslate(String word, API engine, String fromLang, String toLang) {
        IWordTranslator instance = getAPIInstance(engine, fromLang, toLang);
        return instance.getCandidates(word);
    }
    
}
