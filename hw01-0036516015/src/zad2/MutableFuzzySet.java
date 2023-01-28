package zad2;

import zad1.DomainElement;
import zad1.IDomain;

public class MutableFuzzySet implements IFuzzySet {

	IDomain domain;
	double[] memberships;
	
	public MutableFuzzySet(IDomain domain) {
		this.domain = domain;
		memberships = new double[domain.getCardinality()];
	}
	
	@Override
	public IDomain getDomain() {
		return domain;
	}
	
	@Override
	public double getValueAt(DomainElement e) {
		int index = domain.indexOfElement(e);
		return memberships[index];
	}
	
	public MutableFuzzySet set(DomainElement e, double mu) {
		int index = domain.indexOfElement(e);
		memberships[index] = mu;
		return this;
	}
	
}
