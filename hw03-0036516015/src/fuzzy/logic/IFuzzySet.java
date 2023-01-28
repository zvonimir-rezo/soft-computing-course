package fuzzy.logic;

public interface IFuzzySet {

	public IDomain getDomain();
	
	public double getValueAt(DomainElement e);
	
	public IFuzzySet halt(double m);
	
}
