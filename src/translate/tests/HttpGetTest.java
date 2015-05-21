package translate.tests;

import java.io.IOException;

import org.jsoup.nodes.Document;

import translate.http.HttpGet;

public class HttpGetTest {
	public static void main(String args[]){
		String url = "http://students.info.uaic.ro/~nicolae.berendea/other/alfabet.html";
		Document doc = null;
		
		try {
			doc = HttpGet.download(url);
			System.out.println(doc);
		}
		catch (IOException e){
			System.out.printf("Error transfering %s because %s\n", url, e.getMessage());
		}
	}
}
