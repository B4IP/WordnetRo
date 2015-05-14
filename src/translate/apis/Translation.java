package translate.apis;

import java.util.ArrayList;

public class Translation {
	private String src;
	private ArrayList<String> dst; 
	
	public Translation(String src){
		this.src = src;
		dst = new ArrayList<>();
	}
	
	public String getSrc() {
		return src;
	}
	
	public void addTranslation(String translation){
		dst.add(translation);
	}
	
	public void addTranslations(ArrayList<String> translations){
		for (String translation : translations){
			addTranslation(translation);
		}
	}
}
