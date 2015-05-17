package translate.apis;

import java.util.ArrayList;
import java.util.Iterator;

public class Translation implements Iterable<String>{
	private String src;
	private ArrayList<String> dst; 
	
	public Translation(String src){
		this.src = src;
		dst = new ArrayList<>();
	}
	
	public String getSrc() {
		return src;
	}
	
	public void add(String translation){
		if (!dst.contains(translation))
			dst.add(translation);
	}
	
	public void add(ArrayList<String> translations){
		for (String translation : translations){
			add(translation);
		}
	}

	@Override
	public Iterator<String> iterator() {
		return dst.iterator();
	}
}
