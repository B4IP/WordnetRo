package translate.apis;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import translate.http.HttpGet;

public class Gsp implements WordTranslator{
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
		return String.format("http://%s-%s.gsp.ro/index.php?q=%s", source, target, encodedStr);
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

		Elements elem = doc.select("table>tbody>tr");

		for (Element el : elem) {
			Elements tds = el.children();
			// System.out.println(tds.get(3).text());
			//if (!translation.contains(tds.get(3).text())) 
			translation.add(tds.get(3).text());
		}
		
		return translation;
	}

}
