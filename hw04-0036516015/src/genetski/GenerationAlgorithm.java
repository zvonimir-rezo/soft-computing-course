package genetski;

import genetski.interfaces.ICrossover;
import genetski.interfaces.IFunction;
import genetski.interfaces.IMutation;
import genetski.interfaces.ISelection;

public class GenerationAlgorithm extends GeneticAlgorithm {
	
	private boolean elitism;

	
	public GenerationAlgorithm(int startingPopulationSize, boolean elitism, double errorBreak, int iterations,
			IFunction function, ISelection selection, ICrossover crossover, IMutation mutation) {
		this.population = generatePopulation(startingPopulationSize);
		this.elitism = elitism;
		this.errorBreak = errorBreak;
		this.iterations = iterations;
		this.function = function;
		this.selection = selection;
		this.crossover = crossover;
		this.mutation = mutation;
	}
	
	public void run() {
		int outerIter = 0, bestIndex = -1;
		double currentError = Double.MAX_VALUE;
		double[] currentSolution, fValues;
		while (outerIter < iterations && currentError > errorBreak) {
			fValues = calculateValues(population);
			bestIndex = findBestIndex(fValues);
			double[][] nextPopulation = new double[population.length][population[0].length];
			
			if (fValues[bestIndex] < currentError) {
				currentError = fValues[bestIndex];
				currentSolution = population[bestIndex];
				System.out.println("Rješenje:\n" + toString(currentSolution) + "Error: " + currentError);
			}
			
			if (elitism) nextPopulation[0] = population[bestIndex];
			
			int popLength = population.length;
			for (int i = elitism ? 1 : 0; i < popLength; i += 2) {
				
				double[][] nTours = selection.select(population, fValues);
				double[][] newSolutions = mutation.mutate(crossover.cross(nTours[0], nTours[1]));
				
				if (i == popLength -1) {
					nextPopulation[i] = newSolutions[0];
				} else {
					nextPopulation[i] = newSolutions[0];
					nextPopulation[i+1] = newSolutions[1];
				}
			}
			population = nextPopulation;
			outerIter++;
		}
	}
	
}
