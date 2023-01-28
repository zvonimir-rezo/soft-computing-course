package zad3;

import fuzzy.logic.DomainElement;
import fuzzy.logic.IFuzzySet;

public class COADefuzzifier implements Defuzzifier {
	

	@Override
	public int defuzzify(IFuzzySet set) {
		int i = 0;
		double suma = 0, fValues = 0;
		for (DomainElement e: set.getDomain()) {
			double value = set.getValueAt(e);
			fValues += value;
			suma += value * e.getComponentValue(0);
		}
		return (int) Math.round(suma / fValues);
	}

	
}
