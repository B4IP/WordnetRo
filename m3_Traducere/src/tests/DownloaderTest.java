package tests;

import java.io.IOException;
import java.net.MalformedURLException;

import translator.Downloader;

public class DownloaderTest {
	public static void main(String args[]){
		String url = "http://www.google.com";
		String content = null;
		
		try {
			content = Downloader.download(url);
			System.out.println(content);
		}
		catch (MalformedURLException e){
			System.out.printf("%s is an invalid URL\n", url);
		}
		catch (IOException e){
			System.out.printf("Error transfering %s\n", url);
		}
	}
}
