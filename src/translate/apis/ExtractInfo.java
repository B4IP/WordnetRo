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

public class ExtractInfo {
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
		return String.format("http://ro-en.gsp.ro/index.php?q=%s", encodedStr);
	}

	public static String extract(String str) {
		String url = buildQuery(str);
		String content = null;
		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
			content = doc.toString();
			Elements elem = doc.select("table>tbody>tr");

			for (Element el : elem) {
				Elements tds = el.children();
				// System.out.println(tds.get(3).text());
				if (!result.contains(tds.get(3).text())) 
					result.add(tds.get(3).text());
			}
		} catch (IOException e) {
			System.out.printf("Error while downloading %s (%s)\n", url,
					e.getMessage());
		}

		for (int i = 0; i < result.size(); i++) {
			String tempName = result.get(i);
			if (tempName.equals("")) {
				result.remove(i);
			}
		}

		System.out.println(result);
		return "";
	}

}
