package translate.tests;


import java.io.IOException;
import java.net.MalformedURLException;
import translate.http.HttpGet;

public class HttpGetTest {
	public static void main(String args[]){
		String url = "http://students.info.uaic.ro/~nicolae.berendea/other/alfabet.html";
		String content = null;
		
		try {
			content = HttpGet.download(url);
			System.out.println(content);
		}
		catch (MalformedURLException e){
			System.out.printf("%s is an invalid URL\n", url);
		}
		catch (IOException e){
			System.out.printf("Error transfering %s because %s\n", url, e.getMessage());
		}
	}
}
