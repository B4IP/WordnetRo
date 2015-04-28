package translator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleTranslate2 implements TranslateAPI{
	String source, target;

	public GoogleTranslate2(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str){
		String encodedStr = null;
		
		try{
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Charset not suported: %s\n", str);
			return null;
		}
		return String.format("http://translate.google.com/translate_a/single?client=t&sl=%s&tl=%s&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&q=%s", source, target, encodedStr);
	}
	
	public String translate(String str){
		String url = buildQuery(str);
		
		System.out.printf("Connecting to %s\n", url);
		return "";
	}

}
