package genetski;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import genetski.functions.Function1;
import genetski.functions.SimpleMutation;
import genetski.functions.TourSelection;
import genetski.functions.UniformCrossover;
import genetski.interfaces.ICrossover;
import genetski.interfaces.IFunction;
import genetski.interfaces.IMutation;
import genetski.interfaces.ISelection;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int numberOfVariables = 2;
//	    zad4-dataset1.txt 200 0.000001 10000 true 0.2 false
//	    zad4-dataset1.txt 200 0.000001 10000 false 0.5 false
//	    zad4-dataset1.txt 20 0.000001 10000 true 0.2 true
		
		List<String> rows = Files.readAllLines(Paths.get(args[0]));
		
		double[] constArray = new double[rows.size()];
		double[][] input = new double[rows.size()][numberOfVariables];
		
		for (int i = 0; i < rows.size(); i++) {
			String[] row = rows.get(i).split("\t");
			constArray[i] = Double.valueOf(row[row.length-1]);
			for (int j = 0; j < row.length - 1; j++) {
				input[i][j] = Double.valueOf(row[j]);
			}
		}
		
		boolean generation = Boolean.valueOf(args[6]);
		int startingPopulationSize = Integer.valueOf(args[1]);
		double errorBreak = Double.valueOf(args[2]);
		int iterations = Integer.valueOf(args[3]);
		boolean elitism = Boolean.valueOf(args[4]);
		IFunction function = new Function1(constArray, input);
		ISelection selection = new TourSelection(4);
		ICrossover crossover = new UniformCrossover();
		IMutation mutation = new SimpleMutation(Double.valueOf(args[5]));
		
		GenerationAlgorithm generationAlgorithm = new GenerationAlgorithm(startingPopulationSize, elitism, errorBreak, iterations, function, selection, crossover, mutation);
		EliminationAlgorithm eliminationAlgorithm = new EliminationAlgorithm(startingPopulationSize, errorBreak, iterations, function, selection, crossover, mutation);
		
		if (generation)
			generationAlgorithm.run();
		else
			eliminationAlgorithm.run();
		
	}

}
