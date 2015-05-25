package translate.apis;

import java.io.*;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import translate.http.HttpGet;

public class WordReference implements IWordTranslator {
	private String source, target;

	public WordReference(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str){
		String encodedStr = null;
		
		try{
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Charset not suported: %s\n", str); //translate.google.com/#auto/ro/car
			return null;
		}
		return String.format("http://www.wordreference.com/%s%s/%s", source, target, encodedStr);
	}
	
	@Override
	public Translation getCandidates(String word) {
		String url = buildQuery(word);
		Document doc = null;
		
		try{
			doc = HttpGet.download(url);
			if (doc==null)
				return null;
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
			return null;
		}
		

		Translation translation = new Translation(word);
		
		Element table = doc.getElementsByClass("WRD").first();
		if (table==null)
			return translation;
		
		String childOf = null;
		for (Element el : table.getElementsByTag("tr")){
			if (el.getElementsByTag("td").size()!=3)
				continue;
			/*if (!"even|odd".contains(el.attr("class")))
				continue;*/
			
			Element from = el.getElementsByClass("FrWrd").first();
			Element to = el.getElementsByClass("ToWrd").first();
			
			if (from!=null){
				childOf = from.child(0).text();
			}
			if (!childOf.equals(word))
				break;
			for (String w : to.childNode(0).toString().split(","))
				translation.add(w.trim());
		}
		
		return translation;
	}
	
}