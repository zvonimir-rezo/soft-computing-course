package fuzzy.logic;

public class StandardFuzzySets {

	public StandardFuzzySets() {

	}

	public static IIntUnaryFunction lFunction(int a, int b) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int x) {
				if (x < a) {
					return 1;
				} else if (x >= b) {
					return 0;
				} else {
					return ((b - x) / (1.0 * (b - a)));
				}
			}
		};
	}

	public static IIntUnaryFunction gammaFunction(int a, int b) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int x) {
				if (x < a) {
					return 0;
				} else if (x >= b) {
					return 1;
				} else {
					return ((x - a) / (1.0 * (b - a)));
				}
			}
		};
	}

	public static IIntUnaryFunction lambdaFunction(int a, int b, int c) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int x) {
				if (x < a) {
					return 0;
				} else if (x >= a && x < b) {
					return ((x - a) / (1.0 * (b - a)));
				} else if (x >= b && x < c) {
					return ((c - x) / (1.0 * (c - b)));
				}
				return 0;
			}
		};
	}

}
