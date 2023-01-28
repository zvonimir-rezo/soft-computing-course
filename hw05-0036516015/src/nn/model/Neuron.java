package nn.model;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

	private double output, delta=0, learningRate;
	private int positionInLayer;
	private List<Double> weights, nextWeights;
	
	public Neuron(int layerNumber, int positionInLayer, int nextLayerSize, double learningRate) {
		this.positionInLayer = positionInLayer;
		this.learningRate = learningRate;
		this.weights = new ArrayList<>();
		if (layerNumber == 0)
			this.output = -0.1 + Math.random() * 0.2;
		for (int i = 0; i < nextLayerSize; i++) {
			this.weights.add(-0.1 + Math.random() * 0.2);
		}
		this.nextWeights = new ArrayList<>(weights);

	}
	
	public double getOutput() {
		return output;
	}
	
	public void setOutput(double value) {
		this.output = value;
	}
	
	public double getDelta() {
		return delta;
	}
	
	public void setDelta(double value) {
		this.delta = value;
	}
	
	public List<Double> getWeights() {
		return weights;
	}
	
	public void calculateOutput(List<Neuron> previousLayer) {
		double r = 0;
        for (Neuron n : previousLayer) {
            r += n.getOutput() * n.weights.get(positionInLayer);
        }
        output = 1/(1 + Math.exp(-r));
	}
	
	public void updateWeights(List<Neuron> nextLayer) {
		for (int i = 0; i < nextLayer.size(); i++) {
            nextWeights.set(i, nextWeights.get(i) + nextLayer.get(i).getDelta() * getOutput() * learningRate);
        }
	}
	
	public void updateDelta(List<Neuron> nextLayer) {
		delta = 0;
        for (int i = 0; i < nextLayer.size(); i++) {
        	double out = getOutput();
            delta += out * (1 - out) * weights.get(i) * nextLayer.get(i).getDelta();
        }
	}
	
	public void setNewWeights() {
		weights = new ArrayList<>(nextWeights);
	}
	
}
