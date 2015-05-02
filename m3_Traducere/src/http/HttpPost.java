package http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class HttpPost {
	public static String download(String link) throws MalformedURLException, IOException{
		URL url = new URL(link);
		String user="berendeanicolae@gmail.com", passwd="IGCynGBUnY83apRHqQ2fut9OqUdw0x7oEl0SZNyxJR8";
		String encodedCredentials = Base64.getEncoder().encodeToString((user+":"+passwd).getBytes());
		URLConnection con = url.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Authorization", "Basic "+encodedCredentials);
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		out.write("");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		StringBuilder content = new StringBuilder();
		int read;
		while ((read=in.read())!=-1){
			content.append((char)read);
		}
		return content.toString();
	}
}
