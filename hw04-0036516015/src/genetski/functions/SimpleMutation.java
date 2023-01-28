package genetski.functions;

import java.util.Random;

import genetski.interfaces.IMutation;

public class SimpleMutation implements IMutation {
	
	private double p;
	
	public SimpleMutation(double p) {
		this.p = p;
	}
	
	@Override
	public double[][] mutate(double[][] solutions) {
		Random random = new Random();
		int len1 = solutions.length;
		for (int i = 0; i < len1; i++) {
			int len2 = solutions[i].length;
			if (random.nextDouble() < p) {
				for (int j = 0; j < len2; j++) {
					if (random.nextDouble() < 0.3)
						solutions[i][j] += (2 * random.nextDouble() - 1);
				}
			}
			
		}
		return solutions;
	}
}
