package genetski.functions;

import java.util.Random;

import genetski.interfaces.ICrossover;

public class UniformCrossover implements ICrossover {

	@Override
	public double[][] cross(double[] parent1, double[] parent2) {
		double[] child1 = new double[parent1.length];
		double[] child2 = new double[parent1.length];
		
		Random random = new Random();
		for (int i = 0; i < parent1.length; i++) {
			if (random.nextBoolean()) {
				child1[i] = parent1[i];
				child2[i] = parent2[i];
			} else {
				child1[i] = parent2[i];
				child2[i] = parent1[i];
			}
		}
		return new double[][] {child1, child2};
	}

}
