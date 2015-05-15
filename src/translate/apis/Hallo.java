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

public class Hallo implements TranslateAPI {
	private String source, target;
	
	public Hallo(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str) {
		String encodedStr = null;
		String url = "http://hallo.ro/search.do?d=%s&l=%s&type=both&query=%s";

		try {
			encodedStr = URLEncoder.encode(str,
					java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Charset not suported: %s\n", str); // translate.google.com/#auto/ro/car
			return null;
		}
		return String.format(url, target, source, encodedStr);
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
		Elements elem = doc.select("td.t3");
		Translation translation = new Translation(word);

		for (Element el : elem) {
			List<String> temp = new ArrayList<String>();
			Elements links = el.select("a");
			String w = "";
			for (Element link : links) {
				temp.add(link.text());
			}
			for (String item : temp) {
				w = w + ' ' + item;
			}
			w = w.replace("null ", "");

			translation.add(word);
		}

		//translation.remove(0);
		return translation;
	}
}
