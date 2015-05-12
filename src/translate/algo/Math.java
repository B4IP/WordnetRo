package algo;

public class Math {
	public static<T extends Comparable<T>> T min(T ... elements){
		T minimum = elements[0];
		
		for (T el : elements){
			if (minimum.compareTo(el)>0)
				minimum = el;
		}
		return minimum;
	}
	
	public static<T extends Comparable<T>> T max(T ... elements){
		T maxim = elements[0];
		
		for (T el : elements){
			if (maxim.compareTo(el)<0)
				maxim = el;
		}
		return maxim;
	}
}
