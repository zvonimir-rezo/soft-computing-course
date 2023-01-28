package fuzzy.logic;


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

	@Override
	public IFuzzySet halt(double mi) {
		MutableFuzzySet ret = new MutableFuzzySet(domain);
		for (DomainElement element: domain) {
			ret = ret.set(element, getValueAt(element)*mi);
		}
		return ret;
	}
	
}
