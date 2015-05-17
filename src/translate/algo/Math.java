package translate.algo;

public class Math {
	public static int min(int ... elements){
		if (elements.length==0)
			throw new IllegalArgumentException();
		
		int minim = elements[0];
		for (int el : elements){
			if (minim>el)
				minim = el;
		}
		return minim;
	}

	public static double min(double ... elements){
		if (elements.length==0)
			throw new IllegalArgumentException();
		
		double minim = elements[0];
		for (double el : elements){
			if (minim>el)
				minim = el;
		}
		return minim;
	}
	
	public static int max(int ... elements){
		if (elements.length==0)
			throw new IllegalArgumentException();

		int maxim = elements[0];
		for (int el : elements){
			if (maxim<el)
				maxim = el;
		}
		return maxim;
	}

	public static double max(double ... elements){
		if (elements.length==0)
			throw new IllegalArgumentException();

		double maxim = elements[0];
		for (double el : elements){
			if (maxim<el)
				maxim = el;
		}
		return maxim;
	}
	
	public static double mean(int ... elements){
		if (elements.length==0)
			throw new IllegalArgumentException();
		
		double m = 0;
		for (int el : elements){
			m += el;
		}
		return m/elements.length;
	}
	
	public static double mean(double ... elements){
		if (elements.length==0)
			throw new IllegalArgumentException();
		
		double m = 0;
		for (double el : elements){
			m += el;
		}
		return m/elements.length;
	}
}
