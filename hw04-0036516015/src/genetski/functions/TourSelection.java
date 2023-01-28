package genetski.functions;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import genetski.interfaces.ISelection;

public class TourSelection implements ISelection {
	
	private int n;
	
	public TourSelection(int n) {
		this.n = n;
	}

	@Override
	public double[][] select(double[][] population, double[] fValues) {
		Random r = new Random();
		Set<Integer> indexesList = new HashSet<>();
		int cnt = 0;
		while (cnt < n) {
			int index = r.nextInt(population.length);
			if (!indexesList.contains(index)) {
				indexesList.add(index);
				cnt++;
			}
		}
		int[] indexes = indexesList.stream().mapToInt(x->x).toArray();
		double[][] pop = new double[n][population[0].length];
		double[] values = new double[n];
		
		for (int i = 0; i < n; i++) {
			pop[i] = population[indexes[i]];
			values[i] = fValues[indexes[i]];
		}
		
		insertionSort(pop, values);
		return pop;

//		Random random = new Random();
//		Set<Integer> randomIndexes = new HashSet<>();
//		while (randomIndexes.size() < n) randomIndexes.add(random.nextInt(population.length));
//		int[] indexesArray = randomIndexes.stream().mapToInt(x->x).toArray();
//		
//		Map<Double, double[]> map = new TreeMap<Double, double[]>();
//		for (int i = 0; i < n; i++) {
//			map.put(fValues[indexesArray[i]], population[indexesArray[i]]);
//		}
//		
//		double[][] retArray = new double[map.size()][population[0].length];
//		ArrayList<double[]> l = new ArrayList<double[]>(map.values());
//		for (int i = 0; i < l.size(); i++) {
//			retArray[i] = l.get(i);
//		}
//		return retArray;
		
	}
	
	void insertionSort(double[][] population, double[] values) {
		for (int i = 0; i < population.length; i++) {
			int j = i;
			while (j > 0 && values[j-1] > values[j]) {
				double[] tmpPop = population[j];
				double tmpValue = values[j];
				population[j] = population[j-1];
				values[j] = values[j-1];
				population[j-1] = tmpPop;
				values[j-1] = tmpValue;
				j--;
			}
		}
	}

}
