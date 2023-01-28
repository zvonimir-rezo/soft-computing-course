package zad3;

import fuzzy.logic.CalculatedFuzzySet;
import fuzzy.logic.IDomain;
import fuzzy.logic.IFuzzySet;
import fuzzy.logic.SimpleDomain;
import fuzzy.logic.StandardFuzzySets;

public class BoatRules {

	private static final IDomain ACCELERATION = new SimpleDomain(-50, 51);
	private static final IDomain ANGLE = new SimpleDomain(-90, 91);
    private static final IDomain DISTANCE_TO_SHORE = new SimpleDomain(0, 1301);
    private static final IDomain VELOCITY = new SimpleDomain(0, 501);
    
    public static final IFuzzySet SLOW = new CalculatedFuzzySet(VELOCITY, StandardFuzzySets.lFunction(30, 50));
    public static final IFuzzySet FAST = new CalculatedFuzzySet(VELOCITY, StandardFuzzySets.gammaFunction(50, 70));
    
    public static final IFuzzySet CLOSE_TO_SHORE = new CalculatedFuzzySet(DISTANCE_TO_SHORE, StandardFuzzySets.lFunction(30, 60));
    public static final IFuzzySet MEGA_CLOSE_TO_SHORE = new CalculatedFuzzySet(DISTANCE_TO_SHORE, StandardFuzzySets.lFunction(20, 30));
    
    
    public static final IFuzzySet SLIGHT_LEFT = new CalculatedFuzzySet(ANGLE, StandardFuzzySets.lFunction(-30, -10));
    public static final IFuzzySet SLIGHT_RIGHT= new CalculatedFuzzySet(ANGLE, StandardFuzzySets.gammaFunction(10, 30));
    public static final IFuzzySet HARD_LEFT = new CalculatedFuzzySet(ANGLE, StandardFuzzySets.lFunction(-50, -30));
    public static final IFuzzySet HARD_RIGHT= new CalculatedFuzzySet(ANGLE, StandardFuzzySets.gammaFunction(30, 50));
    
    public static final IFuzzySet SPEED_UP = new CalculatedFuzzySet(ACCELERATION, StandardFuzzySets.gammaFunction(55, 70));
    public static final IFuzzySet SLOW_DOWN = new CalculatedFuzzySet(ACCELERATION, StandardFuzzySets.lFunction(30, 45));
    
}
