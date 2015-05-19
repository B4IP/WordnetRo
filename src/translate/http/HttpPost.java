package translate.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class HttpPost {
	public static String download(String link, String user, String passwd) throws MalformedURLException, IOException{
		URL url = new URL(link);
		URLConnection con = url.openConnection();
		con.setRequestProperty("Accept-Charset", "UTF-8");
		con.setDoOutput(true);

		String encodedCredentials = Base64.getEncoder().encodeToString((user+":"+passwd).getBytes());
		con.setRequestProperty("Authorization", "Basic "+encodedCredentials);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		StringBuilder content = new StringBuilder();
		
		int read;
		while ((read=in.read())!=-1){
			content.append((char)read);
		}
		in.close();
		
		return content.toString();
	}
}
