
package translate.util;

import java.util.ArrayList;
import java.util.List;
import translate.util.entity.Translation;

public class GoogleTranslate2ResultParser {
    
    public static String extractMainResult(String rawResponse) {
        String[] splitResult = rawResponse.split("\"");
        
        return splitResult[1];
    }
    
    public static List<Translation> extractPartialTranslations(String rawResponse) {
        List<Translation> resultList = new ArrayList<>();
        String[] splitResult = rawResponse.split("[\\[\\]]");
        splitResult[0] = "";
        splitResult[1] = "";
        splitResult[2] = "";
        splitResult[3] = "";
        
        boolean flag = true;
        String originalPhrase = null;
        String translatedPhrase = null;
        for (String s: splitResult){
            if(s.startsWith("\"")) {
                String[] tempParse = s.split(",");
                if(tempParse.length == 2) {
                    originalPhrase = tempParse[0].replace('\"', ' ').trim();
                    flag = false;
                }
                if(tempParse.length == 4) {
                    translatedPhrase = tempParse[0].replace('\"', ' ').trim();
                    flag = true;
                }
                if(flag) {
                    Translation temp = new Translation();
                    temp.setOriginalPhrase(originalPhrase);
                    temp.setTranslatedePhrase(translatedPhrase);
                    resultList.add(temp);
                }
            }
        }
        
        return resultList;
    }
    
}
