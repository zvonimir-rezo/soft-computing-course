package dz2;

import zad1.CompositeDomain;
import zad1.Domain;
import zad1.DomainElement;
import zad1.IDomain;
import zad1.SimpleDomain;
import zad2.IFuzzySet;
import zad2.MutableFuzzySet;

public class Relations {

	public static boolean isUtimesURelation(IFuzzySet relation) {
		IDomain domain = relation.getDomain();
		SimpleDomain component1 = (SimpleDomain) domain.getComponent(0);
		SimpleDomain component2 = (SimpleDomain) domain.getComponent(1);
		if (domain.getNumberOfComponents() == 2 && component1.getCardinality() == component2.getCardinality()) {
			for (int i = 0; i < component1.getCardinality(); i++) {
				if (component1.elementForIndex(i) != component2.elementForIndex(i)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isSymmetric(IFuzzySet relation) {
		if (!isUtimesURelation(relation))
			return false;

		CompositeDomain domain = (CompositeDomain) relation.getDomain();

		SimpleDomain simpleDomain = (SimpleDomain) domain.getComponent(0);
		int size = simpleDomain.getCardinality();

		for (DomainElement e : domain) {
			int x = simpleDomain.indexOfElement(DomainElement.of(e.getComponentValue(0)));
			int y = simpleDomain.indexOfElement(DomainElement.of(e.getComponentValue(1)));
			if (relation.getValueAt(e) != relation.getValueAt(domain.elementForIndex(x + y * size)))
				return false;
		}
		return true;

	}

	public static boolean isReflexive(IFuzzySet relation) {
		if (!isUtimesURelation(relation))
			return false;

		IDomain domain = relation.getDomain();

		for (DomainElement e : domain) {
			if (e.getComponentValue(0) == e.getComponentValue(1) && relation.getValueAt(e) != 1)
				return false;
		}
		return true;
	}

	public static boolean isMaxMinTransitive(IFuzzySet relation) {
		if (!isUtimesURelation(relation))
			return false;

		CompositeDomain domain = (CompositeDomain) relation.getDomain();

		SimpleDomain simpleDomain = (SimpleDomain) domain.getComponent(0);
		int size = simpleDomain.getCardinality();

		for (DomainElement e : domain) {
			int x = simpleDomain.indexOfElement(DomainElement.of(e.getComponentValue(0)));
			int z = simpleDomain.indexOfElement(DomainElement.of(e.getComponentValue(1)));

			double maxVal = 0;
			for (int y = 0; y < size; y++) {
				double minVal =  Math.min(relation.getValueAt(domain.elementForIndex(y + x * size)),
						relation.getValueAt(domain.elementForIndex(z + y * size)));
				maxVal = Math.max(maxVal, minVal);
			}
			if (maxVal > relation.getValueAt(e))
				return false;
		}
		return true;

	}

	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet r1, IFuzzySet r2) {
		CompositeDomain d1 = (CompositeDomain) r1.getDomain();
		CompositeDomain d2 = (CompositeDomain) r2.getDomain();
		
		MutableFuzzySet composition = new MutableFuzzySet(Domain.combine(d1.getComponent(0), d2.getComponent(1)));

		int sizeU = d1.getComponent(0).getCardinality();
		int sizeV = d1.getComponent(1).getCardinality();
		int sizeW = d2.getComponent(1).getCardinality();

		for (int i = 0; i < sizeU; i++) {
			for (int j = 0; j < sizeW; j++) {
				double maxVal = 0;
				for (int k = 0; k < sizeV; k++) {
					double minVal = Math.min(r1.getValueAt(d1.elementForIndex(sizeV * i + k)),
							r2.getValueAt(d2.elementForIndex(sizeW * k + j)));
					maxVal = Math.max(maxVal, minVal);
				}
				composition.set(composition.getDomain().elementForIndex(sizeW * i + j), maxVal);
			}
		}
		return composition;

	}
	
	public static boolean isFuzzyEquivalence(IFuzzySet r) {
		return isReflexive(r) && isSymmetric(r) && isMaxMinTransitive(r);
	}

}
