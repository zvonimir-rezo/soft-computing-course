package nn.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

	private double learningRate, maxError;
	private int maxIter;
	private int algorithm = 1;
	private int outputLayerSize = 5;
	private int xSize = 5;
	
	private List<List<Neuron>> layers = new ArrayList<>();
	private List<List<Double>> inputs = new ArrayList<>(), outputs = new ArrayList<>();
	
	private String[] letters = {"Alpha", "Beta", "Gamma", "Delta", "Epsilon"};
	
//	public static void main(String[] args) throws IOException {
//			NeuralNetwork model = new NeuralNetwork("input2.txt", 0.1, 1000, new int[] {1, 6, 1});
//			model.learn();
//
//	}
	

	public NeuralNetwork(String filePath, double learningRate, int maxIter, double maxError, int[] networkDimensions) throws IOException {
		if (networkDimensions[networkDimensions.length-1] != 5)
			throw new IllegalArgumentException("Last layer must have size of 5");
		this.learningRate = learningRate;
		this.maxIter = maxIter;
		this.maxError = maxError;
		readInputsFromFile(filePath);
		setLayers(networkDimensions);
	}
	
	
	
	private void readInputsFromFile(String filePath) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(filePath));
		
		for (String line: lines) {
			String[] lineSplit = line.split(" ");
			List<Double> input = new ArrayList<>();
			List<Double> output = new ArrayList<>();
			for (int i = 0; i < lineSplit.length-xSize; i++) {
				input.add(Double.valueOf(lineSplit[i]));
			}
			for (int i = lineSplit.length-xSize; i < lineSplit.length; i++) {
				output.add(Double.valueOf(lineSplit[i]));
			}
			inputs.add(input);
			outputs.add(output);
		}
		
	}
	
	private void setLayers(int[] networkDimensions) {
		for (int i = 0; i < networkDimensions.length-1; i++) {
			layers.add(newLayer(i, networkDimensions[i], networkDimensions[i+1]));
		}
		layers.add(newLayer(networkDimensions.length-1, networkDimensions[networkDimensions.length-1], 0));
	}
	
	private List<Neuron> newLayer(int layerIndex, int nNeurons, int nextLayerSize) {
		List<Neuron> layer = new ArrayList<>();
		for (int i = 0; i < nNeurons; i++) {
			layer.add(new Neuron(layerIndex, i, nextLayerSize, learningRate));
		}
		return layer;
	}
	
	public void calculateNeurons(List<Double> x) {
		for (int i = 0; i < x.size(); i++) {
			layers.get(0).get(i).setOutput(x.get(i));
		}
		for (int i = 1; i < layers.size(); i++) {
			for (Neuron neuron: layers.get(i)) {
				neuron.calculateOutput(layers.get(i-1));
			}
		}
	}
	
	public double calculateError() {
		double error = 0;
        for (int i = 0; i < inputs.size(); i++) {
            calculateNeurons(inputs.get(i));
            for (int j = 0; j < layers.get(layers.size() - 1).size(); j++) {
                error += Math.pow(outputs.get(i).get(j) - layers.get(layers.size() - 1).get(j).getOutput(), 2);
            }
        }
        return error;
	}
	
	public void updateWeights(int batchSize) {
		int cnt = 0;
        for (int i = 0; i < inputs.size(); i++) {
            calculateNeurons(inputs.get(i));
            for (int j = 0; j < outputLayerSize; j++) {
                double out = layers.get(layers.size() - 1).get(j).getOutput();
                layers.get(layers.size() - 1).get(j).setDelta(out * (1 - out) * (outputs.get(i).get(j) - out));
            }
            for (int j = layers.size() - 2; j >= 0; j--) {
            	for (Neuron n: layers.get(j)) {
            		n.updateWeights(layers.get(j+1));
            		n.updateDelta(layers.get(j+1));
            	}
            }
            if (cnt == batchSize-1) {
                setNewWeights();
                cnt = 0;
            } else {
            	cnt++;
            }

        }
        setNewWeights();
	}
	
	private void setNewWeights() {
		for (List<Neuron> l: layers) {
			for (Neuron n: l) {
				n.setNewWeights();
			}
		}
	}
	
	public void backpropagation() {
		updateWeights(inputs.size());
	}
	public void stohasticBackpropagation() {
		updateWeights(1);
	}
	public void miniBatchBackpropagation() {
		int batchGroups = 10;
		Collections.shuffle(inputs, new Random(1));
        Collections.shuffle(outputs, new Random(1));
        updateWeights(inputs.size() / batchGroups);
	}
	
	public void train() {
		for (int i = 0; i < maxIter; i++) {
			double error = calculateError();
			System.out.println("Iter " + i + ": " + error);
			if (error < maxError) break;
//			System.out.println("----------------------------------------------------------------");
			
			switch(algorithm) {
			case 1:
				backpropagation();
			case 2:
				stohasticBackpropagation();
			case 3: 
				miniBatchBackpropagation();
			}
		}
	}
	
	public String predict(List<Double> xTest) {
		calculateNeurons(xTest);
		int maxIndex = 0;
		List<Neuron> lastLayer = layers.get(layers.size()-1);
		double maxOutput = lastLayer.get(0).getOutput();
		for (int i = 1; i < 5; i++) {
			double currentOutput = lastLayer.get(i).getOutput();
			
			if (currentOutput > maxOutput) {
				maxIndex = i;
				maxOutput = currentOutput;
				
			}
		}
		System.out.println(maxOutput);
		return letters[maxIndex];
	}
	
}
