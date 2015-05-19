package translate.apis;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import translate.http.HttpGet;

import java.util.ArrayList;
import java.util.List;

public class Hallo implements WordTranslator {
	private String source, target;
	
	public Hallo(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str) {
		String encodedStr = null;
		String url = "http://hallo.ro/search.do?d=%s&l=%s&query=%s";

		try {
			encodedStr = URLEncoder.encode(str,
					java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Charset not suported: %s\n", str); // translate.google.com/#auto/ro/car
			return null;
		}
		return String.format(url, source, target, encodedStr);
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
				StringBuilder w = new StringBuilder();
				for (Element i : to.getElementsByTag("a")){
					w.append(i.text() + " ");
					if (i.nextSibling()!=null && i.nextSibling().toString().contains("("))
						break;
				}
				translation.add(w.toString().trim());
			}
		}

		return translation;
	}
}
