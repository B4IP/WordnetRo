package translate.tests;

import translate.apis.TranslateAPI;
import java.util.List;
import translate.apis.GoogleTranslate2;
import translate.util.entity.Translation;

public class TranslateTest{
	public static void main(String args[]){
		TranslateAPI tr = null;
		
		tr = new GoogleTranslate2("en", "ro");
		System.out.printf("%s\n", tr.translate("Santa and tooth fairy aren't real"));
                
                if (tr instanceof GoogleTranslate2){
                   List<Translation> partialTranslations =((GoogleTranslate2) tr)
                           .getPartialTranslations("Santa and tooth fairy aren't real"); 
                   for (Translation t: partialTranslations) {
                       System.out.println(t);
                   }
                }
                
                
        }
}
