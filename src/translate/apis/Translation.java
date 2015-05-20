package translate.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Translation implements Iterable<String>{
	private String src;
	private ArrayList<String> dst;
	private HashMap<String, Integer> dst_cpy;
	
	public Translation(String src){
		this.src = src;
		dst = new ArrayList<>();
		dst_cpy = new HashMap<>();
	}
	
	public String getSrc() {
		return src;
	}
	
	public void add(String translation){
		if (!dst.contains(translation))
			dst.add(translation);
		
		if (dst_cpy.containsKey(translation)){
			dst_cpy.put(translation, dst_cpy.get(translation)+1);
		}
		else{
			dst_cpy.put(translation, 1);
		}
	}
	
	public void add(ArrayList<String> translations){
		for (String translation : translations){
			add(translation);
		}
	}
	
	public void add(Translation translations){
		for (String translation : translations.dst_cpy.keySet()){
			if (dst_cpy.containsKey(translation)){
				dst_cpy.put(translation, dst_cpy.get(translation)+translations.dst_cpy.get(translation));
			}
			else{
				dst_cpy.put(translation, translations.dst_cpy.get(translation));
			}
		}
	}

	@Override
	public Iterator<String> iterator() {
		List<String> list = new ArrayList<String>(dst_cpy.keySet());
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return dst_cpy.get(arg0)-dst_cpy.get(arg1);
			}
		};
		Collections.sort(list, comp);
		return list.iterator();
	}
    
	@Override
	public String toString() {
        String buffer = "Source: " + this.src + "\nTraslations: ";
        for (String translation : this.dst) {
            buffer += translation + ", ";
        }
        buffer = buffer.substring(0, buffer.length() - 2);
        return buffer;
    }
}
