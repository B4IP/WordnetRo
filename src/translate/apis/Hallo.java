package translate.apis;

import java.io.*;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import translate.http.HttpGet;

public class Hallo implements IWordTranslator {
	private String source, target;
	
	public Hallo(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str) {
		String encodedStr = null;
		String url = "http://hallo.ro/search.do?d=%s&l=%s&query=%s";

		try {
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Charset not suported: %s\n", str);
			return null;
		}
		return String.format(url, source, target, encodedStr);
	}

	@Override
	public Translation getCandidates(String word) {
		String url = buildQuery(word);
		Document doc = null;
		
		try{
			doc = HttpGet.download(url);
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
			return null;
		}
		

		Element table = doc.getElementsByClass("main").first();
		Translation translation = new Translation(word);

		for (Element row : table.getElementsByTag("tr")) {
			Element from = row.getElementsByClass("t2").first();
			Element to = row.getElementsByClass("t3").first();

			if (from==null || to==null)
				continue;
			if (from.childNodeSize()==0 || from.childNode(0).childNodeSize()==0)
				continue;
			
			if (from.text().equals(word)){
				String  w = to.text();
				w = w.split("[(\\[]")[0];
				translation.add(w.trim());
			}
		}

		return translation;
	}
}
