package translate.http;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HttpGet{
	static String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36";
	
	public static Document download(String url) throws IOException{
		Document doc = null;
		Connection con = Jsoup.connect(url).userAgent(userAgent);
		con.timeout(10000).ignoreHttpErrors(true).followRedirects(true);
		Connection.Response resp = con.execute();
		if (resp.statusCode() == 200)
			doc = con.get();
		else
			throw new IOException(resp.statusMessage());
		
		return doc;
	}
}
