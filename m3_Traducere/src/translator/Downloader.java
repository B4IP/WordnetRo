package translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader{
	private static char[] buffer = new char[1024];
	/*Downloader(String link) throws MalformedURLException, IOException{
		url = new URL(link);
	}*/
	
	public static String download(String link) throws MalformedURLException, IOException{
		URL url = new URL(link);
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuilder content = new StringBuilder();
		
		while (in.read(buffer, 0, buffer.length)>0){
			content.append(buffer);
		}
		in.close();
		
		return content.toString();
	}
}
