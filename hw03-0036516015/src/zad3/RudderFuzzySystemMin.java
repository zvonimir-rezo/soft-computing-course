package zad3;

import java.util.Arrays;

public class RudderFuzzySystemMin extends FuzzySystem {

	public RudderFuzzySystemMin(Defuzzifier defuzzifier) {
		super(defuzzifier);
		
		addRule(Arrays.asList(null, null, BoatRules.CLOSE_TO_SHORE, null, null, null), BoatRules.SLIGHT_RIGHT);
		addRule(Arrays.asList(null, null, null, BoatRules.CLOSE_TO_SHORE, null, null), BoatRules.SLIGHT_LEFT);
		
		addRule(Arrays.asList(null, null, BoatRules.MEGA_CLOSE_TO_SHORE, null, null, null), BoatRules.HARD_RIGHT);
		addRule(Arrays.asList(null, null, null, BoatRules.MEGA_CLOSE_TO_SHORE, null, null), BoatRules.HARD_LEFT);
	
		
	}
	
	
	
}
