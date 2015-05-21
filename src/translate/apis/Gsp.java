package translate.apis;


import java.io.*;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import translate.http.HttpGet;

public class Gsp implements IWordTranslator{
	String source, target;
	
	public Gsp(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str) {
		String encodedStr = null;

		try {
			encodedStr = URLEncoder.encode(str,
			java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Charset not suported: %s\n", str); // translate.google.com/#auto/ro/car
			return null;
		}
		return String.format("http://%s-%s.gsp.ro/index.php?d=e&q=%s", target, source, encodedStr);
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
		

		Translation translation = new Translation(word);

		Elements elem = doc.select("table>tbody>tr");
		if (elem==null)
			return translation;

		for (Element el : elem) {
			Elements tds = el.children();
			if (tds.get(1).text().equals(word))
				translation.add(tds.get(3).text());
		}
		
		return translation;
	}

}
