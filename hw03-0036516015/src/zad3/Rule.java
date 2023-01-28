package zad3;

import java.util.List;

import fuzzy.logic.DomainElement;
import fuzzy.logic.IFuzzySet;

public class Rule {

	private List<IFuzzySet> antecedent;
	private IFuzzySet consequent;
	
	public Rule(List<IFuzzySet> antecedent, IFuzzySet consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}
	
	public IFuzzySet apply(int... values) {
		double tmp = 1;
		for (int i = 0; i < antecedent.size(); i++) {
			tmp = Math.min(tmp, antecedent.get(i).getValueAt(DomainElement.of(values[i])));
		}
		return consequent.halt(tmp);
	}
	
}
