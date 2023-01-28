package zad2;

import zad1.DomainElement;
import zad1.IDomain;

public class CalculatedFuzzySet implements IFuzzySet{
	
	IDomain domain;
	IIntUnaryFunction function;
	
	
	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction function) {
		this.domain = domain;
		this.function = function;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getValueAt(DomainElement e) {
		return function.valueAt(domain.indexOfElement(e));
	}

	
	
}
