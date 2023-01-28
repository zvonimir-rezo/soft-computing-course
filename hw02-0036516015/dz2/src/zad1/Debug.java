package zad1;

import zad2.IFuzzySet;

public class Debug {

	public static void print(IFuzzySet set, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}
		int i = 0;
		for (DomainElement e : set.getDomain()) {
			System.out.println("d(" + i + ")=" + set.getValueAt(e));
			i++;
		}
		System.out.println("Kardinalitet domene je: " + set.getDomain().getCardinality());
		System.out.println();
	}

}
