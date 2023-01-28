package genetski.functions;

import genetski.interfaces.IFunction;

public class Function1 implements IFunction{

	private double[] constArray;
	private double[][] input;
	
	public Function1(double[] constArray, double[][] input) {
		this.constArray = constArray;
		this.input = input;
	}
	
	@Override
	public double valueAt(double[] beta) {
		double b0 = beta[0];
		double b1 = beta[1];
		double b2 = beta[2];
		double b3 = beta[3];
		double b4 = beta[4];
	
		double suma = 0;
		for (int i = 0; i < input.length; i++) {	
			double x = input[i][0];
			double y = input[i][1];
			double k = constArray[i];
			double rez = Math.sin(b0 + b1 * x) + b2 * Math.cos(x * (b3 + y)) * 1 / (1 + Math.exp(Math.pow(x-b4, 2)));
			suma += Math.pow(rez, 2);
		}
		return Math.sqrt(suma) / input.length;
	}
}
