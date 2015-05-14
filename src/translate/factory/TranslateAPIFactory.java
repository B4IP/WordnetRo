
package translate.factory;

import translate.apis.TranslateAPI;
import translate.apis.BingTranslator;
import translate.apis.GoogleTranslate;
import translate.apis.GoogleTranslate2;
import translate.exception.InvalidTranslatorOptionException;

public class TranslateAPIFactory {
    /**
     * Method used to get a new instance of TranslateAPI which will use a certain engine to translate.
     * @param type The engine to be used for translation. Can be GoogleTranslate, GoogleTranslate or GoogleTranslate2
     * @param fromLang Must be the two letter abbreviation for the target language (ex: English will be "en", Spanish will be "es")
     * @param toLang Same as fromLang parameter
     * @return a new Instance which will translate all input from fromLang to toLang.
     */
    public static TranslateAPI getAPIInstance(TranslateAPIType type, String fromLang, String toLang) {
        switch (type) {
            case BingTranslate:
                return new BingTranslator(fromLang, toLang);
            case GoogleTranslate:
                return new GoogleTranslate(fromLang, toLang);
            case GoogleTranslate2:
                return new GoogleTranslate2(fromLang, toLang);
            default:
                throw new InvalidTranslatorOptionException();
        }
    }
    
    /**
     * 
     * @param phrase
     * @param engine
     * @param fromLang
     * @param toLang
     * @return 
     */
    public static String atomicTranslate(String phrase, TranslateAPIType engine, String fromLang, String toLang) {
        TranslateAPI instance = getAPIInstance(engine, fromLang, toLang);
        return instance.translate(phrase);
    }
    
}
