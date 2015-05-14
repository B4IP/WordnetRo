package translate.apis;

import java.io.*;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class WordReference {
	static List<String> result = new ArrayList<String>();
	
	private static String buildQuery(String str){
		String encodedStr = null;
		
		try{
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Charset not suported: %s\n", str); //translate.google.com/#auto/ro/car
			return null;
		}
		return String.format("http://www.wordreference.com/enro/%s", encodedStr);
	}
	
	public static String extract(String str){
		String url = buildQuery(str);
		String content = null;
		Document doc = null;
		
		try{
			doc = Jsoup.connect(url).get();
			System.out.println(url);
			content = doc.toString();
			String item = null;
			Elements elem = doc.select("td.ToWrd");
			for (Element el : elem){
				item = el.text().replaceAll(" s.f.| s.n.", "");
				if (!result.contains(item))
					result.add(item);
			}
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
		}
		
		System.out.println(result);
		return "";
	}
	
}