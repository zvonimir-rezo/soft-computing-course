package zad2;

import zad1.DomainElement;
import zad1.IDomain;

public interface IFuzzySet {

	public IDomain getDomain();
	
	public double getValueAt(DomainElement e);
	
}
