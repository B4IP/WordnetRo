package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpGet{
	public static String download(String link) throws MalformedURLException, IOException{
		System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");
		URL url = new URL(link);
		URLConnection con = url.openConnection();
		con.setRequestProperty("Accept-Charset", "UTF-8");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		StringBuilder content = new StringBuilder();

		int read;
		while ((read=in.read())!=-1){
			content.append((char)read);
		}
		in.close();
		
		return content.toString();
	}
}
