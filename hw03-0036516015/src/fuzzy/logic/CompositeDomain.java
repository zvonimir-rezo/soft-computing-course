package fuzzy.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeDomain extends Domain {
	
	List<SimpleDomain> components = new ArrayList<SimpleDomain>();

	public CompositeDomain(SimpleDomain[] domains) {
		components = Arrays.asList(domains);
		
		for (int i = 0; i < components.size(); i++) {
			for (int j = 1; j < components.size(); j++) {
				if (!(i >= j)) {
					for (DomainElement e1: components.get(i)) {
						for (DomainElement e2: components.get(j)) {
							int[] l = new int[e1.getNumberOfComponents() + e2.getNumberOfComponents()];
							int cnt = 0;
							for (int a: e1.getValues()) {
								l[cnt++] = a;
							}
							for (int a: e2.getValues()) {
								l[cnt++] = a;
							}
							elements.add(DomainElement.of(l));
						}
					}
				}
			}
		}
	}


	@Override
	public int getCardinality() {
		return elements.size();
	}

	@Override
	public IDomain getComponent(int index) {
		return components.get(index);
	}

	@Override
	public int getNumberOfComponents() {
		return components.size();
	}
	
}
