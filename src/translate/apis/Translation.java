package translate.apis;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Translation implements Iterable<String>{
    private String src;
    private HashMap<String, Integer> dst;
    
    public Translation(String src){
        this.src = src;
        dst = new HashMap<>();
    }
    
    public String getSrc() {
        return src;
    }
    
    public void add(String translation){
        translation = filter(translation);
        if (dst.containsKey(translation)){
            dst.put(translation, dst.get(translation)+1);
        }
        else{
            dst.put(translation, 1);
        }
    }
    
    public void add(ArrayList<String> translations){
        for (String translation : translations){
            add(translation);
        }
    }
    
    public void add(Translation translations){
    	if (translations==null)
    		return;
    	
        for (String translation : translations.dst.keySet()){
            if (dst.containsKey(translation)){
                dst.put(translation, dst.get(translation)+translations.dst.get(translation));
            }
            else{
                dst.put(translation, translations.dst.get(translation));
            }
        }
    }
    
    @Override
    public Iterator<String> iterator() {
        List<String> list = new ArrayList<String>(dst.keySet());
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                return dst.get(arg0)-dst.get(arg1);
            }
        };
        Collections.sort(list, comp);
        return list.iterator();
    }
    
    @Override
    public String toString() {
        String buffer = "Source: " + this.src + "\nTraslations: ";
        for (String translation : dst.keySet()) {
            buffer += translation + ", ";
        }
        buffer = buffer.substring(0, buffer.length() - 2);
        return buffer;
    }
    
    public boolean hasTranslations() {
        return !(this.dst.isEmpty());
    }
    
    private static String filter(String text){
        /**
         * This function replaces the diacritics.
         */
        String normalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedString).replaceAll("");
    }
}
