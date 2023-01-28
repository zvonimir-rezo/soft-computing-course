package fuzzy.logic;


public class SimpleDomain extends Domain {

	private int first;
	private int last;
	
	
	public SimpleDomain(int first, int last) {
		this.first =  first;
		this.last = last;
		for (int i = first; i < last; i++) {
			elements.add(DomainElement.of(i));
		}
	}
	
	@Override
	public int getCardinality() {
		return last - first;
	}
	
	@Override
	public IDomain getComponent(int index) {
		return this;
	}

	@Override
	public int getNumberOfComponents() {
		return 1;
	}
	
	public int getFirst() {
		return first;
	}
	
	public int getLast() {
		return last;
	}


	@Override
	public int indexOfElement(DomainElement e) {
		int value = e.getComponentValue(0);
		if (value < first || value > last) {
			return -1;
		} else {
			return value - first;
		}
	}

	
	
}
