package translator;

import http.HttpGet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

public class GoogleTranslate implements TranslateAPI{
	String source, target;

	public GoogleTranslate(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str){
		String encodedStr = null;
		String url = "https://translate.google.com/m?hl=%s&sl=%s&q=%s";
		
		try{
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Charset not suported: %s\n", str);
			return null;
		}
		return String.format(url, target, source, encodedStr);
	}
	
	public String translate(String str){
		String url = buildQuery(str);
		String content = null;
		
		try{
			content = HttpGet.download(url);
			System.out.println(content);
		}
		catch (MalformedURLException e){
			System.out.printf("Could not encode %s\n", str);
			return null;
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
			return null;
		}
		
		return content;
	}
}
