package zad3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import fuzzy.logic.IFuzzySet;
import fuzzy.logic.Operations;

public abstract class FuzzySystem {
	
	protected List<Rule> rules = new ArrayList<>();
	private Defuzzifier defuzzifier;
//	private static final int MAX_SPEED = 1000, SPEEDUP = 10;
//	
//	private static final int MAX_DISTANCE = 8000 / SPEEDUP;
//	protected IFuzzySet slow;
//	protected IFuzzySet mid;
//	protected IFuzzySet fast;
//	protected IFuzzySet negativeRelativeDistance;
//	protected IFuzzySet aroundZeroRelativeDistance;
//	protected IFuzzySet positiveRelativeDistance;
//	public boolean verbose;
	
	public FuzzySystem(Defuzzifier defuzzifier) {
		this.defuzzifier = defuzzifier;
	}
	
	protected void addRule(List<IFuzzySet> a, IFuzzySet c) {
		rules.add(new Rule(a, c));
	}
	
	public Rule getRule(int index) {
		return rules.get(index);
	}
	
	public int infer(int[] values) {
		List<IFuzzySet> done = new ArrayList<>();
		for (Rule rule: rules) {
			done.add(rule.apply(values));
		}
		IFuzzySet ret = done.get(0); // take first and then do binary operations on all elements
		for (int i = 1; i < done.size(); i++) {
			ret = Operations.binaryOperation(ret, done.get(i), Operations.zadehOr());
		}
		return defuzzifier.defuzzify(ret);
	}
	
	
}
