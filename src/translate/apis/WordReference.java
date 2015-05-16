package translate.apis;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import translate.http.HttpGet;


public class WordReference implements WordTranslator{
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
		return String.format("http://www.wordreference.com/%s%s/%s", target, source, encodedStr);
	}
	
	@Override
	public Translation getCandidates(String word) {
		String url = buildQuery(word);
		String content = null;
		
		try{
			content = HttpGet.download(url);
		}
		catch (MalformedURLException e){
			System.out.printf("Could not encode %s\n", word);
			return null;
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
			return null;
		}
		

		Document doc = Jsoup.parse(content);
		Translation translation = new Translation(word);
		
		String item = null;
		Elements elem = doc.select("td.ToWrd");
		for (Element el : elem){
			item = el.text().replaceAll(" s.f.| s.n.", "");
			//if (!translation.contains(item))
			translation.add(item);
		}
		
		return translation;
	}
	
}