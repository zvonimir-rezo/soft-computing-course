package zad1;


public interface IDomain extends Iterable<DomainElement> {
	
	public DomainElement elementForIndex(int index);
	
	public int indexOfElement(DomainElement e);
	
	public int getCardinality();
	
	public IDomain getComponent(int index);
	
	public int getNumberOfComponents();
	
}
