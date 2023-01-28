package genetski;

import genetski.interfaces.ICrossover;
import genetski.interfaces.IFunction;
import genetski.interfaces.IMutation;
import genetski.interfaces.ISelection;

public class EliminationAlgorithm extends GeneticAlgorithm{
	
	public EliminationAlgorithm(int startingPopulationSize, double errorBreak, int iterations, IFunction function,
			ISelection selection, ICrossover crossover, IMutation mutation) {
		this.population = generatePopulation(startingPopulationSize);
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
			
			if (fValues[bestIndex] < currentError) {
				currentError = fValues[bestIndex];
				currentSolution = population[bestIndex];
				System.out.println("Rješenje:\n" + toString(currentSolution) + "Error: " + currentError);
			}
			double[][] nTours = selection.select(population, fValues);
			double[][] children = mutation.mutate(crossover.cross(nTours[0], nTours[1]));
			double[] newSolution = function.valueAt(children[0]) < function.valueAt(children[1]) ? children[0] : children[1];
			
			for (int i = 0; i < population.length; i++) {
				if (population[i] == nTours[2]) {
					population[i] = newSolution;
					break;
				}
			}
			outerIter++;
		}
	}
	
}
