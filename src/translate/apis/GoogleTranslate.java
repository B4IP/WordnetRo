package translate.apis;

import translate.apis.TranslateAPI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import translate.http.HttpGet;

public class GoogleTranslate implements TranslateAPI{
	String source, target;

	public GoogleTranslate(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str){
		String encodedStr = null;
		String url = "https://translate.google.com/m?hl=%s&sl=%s&q=%s";
		
		try{
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Charset not suported: %s\n", str);
			return null;
		}
		return String.format(url, target, source, encodedStr);
	}
	
	public Translation getCandidates(String word){
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
		translation.add(doc.getElementsByClass("t0").text());
		Elements eList = doc.getElementsByClass("thead");
		Element header = null;
		for (Element e : eList){
			if (e.text().length()>0)
				header = e;
		}
		if (header!=null){
			Element container = header.parent();
			for (Element br : container.getElementsByTag("br")){
				Node sibling = br.nextSibling();
				if (sibling==null)
					continue;
				String text = sibling.toString().trim();
				if (text.length()>0)
					if (!text.equalsIgnoreCase("<br>"))
						translation.add(text);
			}
		}
		return translation;
	}
}
