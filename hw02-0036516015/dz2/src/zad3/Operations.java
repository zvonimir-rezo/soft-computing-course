package zad3;

import zad1.DomainElement;
import zad1.IDomain;
import zad2.IFuzzySet;
import zad2.MutableFuzzySet;

public class Operations {

	public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction function) {
		IDomain domain = set.getDomain();
		MutableFuzzySet mutableSet = new MutableFuzzySet(domain);
		for (DomainElement e: domain) {
			mutableSet.set(e, function.valueAt(set.getValueAt(e)));
		}
		return mutableSet;
		
	}

	public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction function) {
		IDomain domain = set1.getDomain();
		MutableFuzzySet mutableSet = new MutableFuzzySet(domain);
		for (DomainElement e: domain) {
			mutableSet.set(e, function.valueAt(set1.getValueAt(e), set2.getValueAt(e)));
		}
		return mutableSet;
	}

	public static IUnaryFunction zadehNot() {
		return e -> 1 - e;
	}

	public static IBinaryFunction zadehAnd() {
		return Math::min;
	}

	public static IBinaryFunction zadehOr() {
		return Math::max;
	}

	public static IBinaryFunction hamacherTNorm(double d) {
		return (a, b) -> (a * b) / (d + (1 - d) * (a + b - a * b));
	}

	public static IBinaryFunction hamacherSNorm(double d) {
		return (a, b) -> (a + b - (2 - d) * (2 * a * b) )/ (1 - (1 - d) * a * b);
	}

}
