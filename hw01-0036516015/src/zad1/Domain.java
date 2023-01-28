package zad1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public abstract class Domain implements IDomain {
	
	List<DomainElement> elements = new ArrayList<>();
	
	public Domain() {
		
	}
	

	public static IDomain intRange(int from, int to) {
		return new SimpleDomain(from, to);		
	}
	
	public static Domain combine(IDomain d1, IDomain d2) {
		SimpleDomain[] domains = {(SimpleDomain) d1, (SimpleDomain) d2};
		return new CompositeDomain(domains);
	}

	@Override
    public int indexOfElement(DomainElement e) {
        return elements.indexOf(e);
    }

    @Override
    public DomainElement elementForIndex(int index) {
        return elements.get(index);
    }
    
    @Override
    public Iterator<DomainElement> iterator() {
        return elements.iterator();
    }
}
