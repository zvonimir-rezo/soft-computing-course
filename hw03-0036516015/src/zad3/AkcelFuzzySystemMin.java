package zad3;

import java.util.Arrays;

public class AkcelFuzzySystemMin extends FuzzySystem {

	public AkcelFuzzySystemMin(Defuzzifier defuzzifier) {
		super(defuzzifier);
		addRule(Arrays.asList(null, null, null, null, BoatRules.SLOW, null), BoatRules.SPEED_UP);
		addRule(Arrays.asList(null, null, null, null, BoatRules.FAST, null), BoatRules.SLOW_DOWN);
	}
	
}
