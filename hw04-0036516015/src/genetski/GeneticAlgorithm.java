package genetski;

import java.util.Random;

import genetski.interfaces.ICrossover;
import genetski.interfaces.IFunction;
import genetski.interfaces.IMutation;
import genetski.interfaces.ISelection;

public abstract class GeneticAlgorithm {
	
	protected double[][] population;
	protected double errorBreak;
	protected int iterations;
	protected IFunction function;
	protected ISelection selection;
	protected ICrossover crossover;
	protected IMutation mutation;

	protected static double[][] generatePopulation(int populationSize) {
        double[][] ret = new double[populationSize][6];
        for (int i = 0; i < populationSize; i++) {
            ret[i] = generateSolution();
        }
        return ret;
    }
	
	protected static double[] generateSolution() {
        Random random = new Random();
        double[] solution = new double[5];
        for (int i = 0; i < 5; i++) {
            solution[i] = random.nextDouble() * 8 - 4;
        }
        return solution;
    }
	
	protected double[] calculateValues(double[][] population) {
		double[] ret = new double[population.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = function.valueAt(population[i]);
		}
		return ret;
	}
	
	protected int findBestIndex(double[] fValues) {
		int eliteIndex = 0;
		double mini = Double.MAX_VALUE;
		for (int i = 0; i < fValues.length; i++) {
			if (fValues[i] < mini) {
				eliteIndex = i;
				mini = fValues[i];
			}
		}
		return eliteIndex;
	}
	
	protected String toString(double[] solution) {
		String s = "";
		for (int i = 0; i < solution.length; i++) {
			s += solution[i] + "  ";
		}
		return s += "\n";
	}
	
}
