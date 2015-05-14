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

public class HalloExtract implements TranslateAPI {
	static List<String> result = new ArrayList<String>();

	private static String buildQuery(String str) {
		String encodedStr = null;

		try {
			encodedStr = URLEncoder.encode(str,
					java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.printf("Charset not suported: %s\n", str); // translate.google.com/#auto/ro/car
			return null;
		}
		return String.format(
				"http://hallo.ro/search.do?d=en&l=ro&type=both&query=%s",
				encodedStr);
	}

	public String translate(String str) {
		String url = buildQuery(str);
		String content = null;

		try {
			content = HttpGet.download(url);
		} catch (MalformedURLException e) {
			System.out.printf("Could not encode %s\n", str);
			return null;
		} catch (IOException e) {
			System.out.printf("Error while downloading %s (%s)\n", url,
					e.getMessage());
			return null;
		}

		Document doc = Jsoup.parse(content);
		Elements elem = doc.select("td.t3");
		
		for (Element el : elem) {
			List<String> temp = new ArrayList<String>();
			Elements links = el.select("a");
			String word = "";
			for (Element link : links) {
				temp.add(link.text());
			}
			for (String item : temp) {
				word = word + ' ' + item;
			}
			word = word.replace("null ", "");

			if (!result.contains(word))
				result.add(word);
		}

		result.remove(0);
		System.out.println(result);
		return "";
	}
}
