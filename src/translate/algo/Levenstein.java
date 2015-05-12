package algo;

public class Levenstein {
	static private int d1[]=null, d2[]=null;
	
	static public int getDistance(String str1, String str2){
		d1 = new int[str2.length()+1];
		d2 = new int[str2.length()+1];
		
		for (int j=0; j<=str2.length(); ++j){
			d1[j] = j;
		}
		
		for (int i=1; i<=str1.length(); ++i){
			d1[0] = i-1;
			for (int j=1; j<=str2.length(); ++j){
				int cost = str1.charAt(i-1)==str2.charAt(j-1) ? 0 : 1;
				d2[j] = Math.min(d1[j]+1, d2[j-1]+1, d1[j-1]+cost);
			}
			for (int j=0; j<str2.length(); ++j)
				d1[j] = d2[j];
		}
		return d2[str2.length()];
	}
	
	public double getNormalisedDistance(String str1, String str2){
		double dist = getDistance(str1, str2);
		return 1-dist/Math.max(str1.length(), str2.length());
	}
}
