package nn;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import nn.model.NeuralNetwork;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Point> points = new ArrayList<>();;
	private List<List<Point>> inputs = new ArrayList<>();
	private static int M = 5;
	private static boolean train;
	private static int letters = 5;
	private int inputGestures = 20;
	private int inputCounter = 0;
	private static NeuralNetwork model;
	
	public Frame() {
		setLocation(500, 500);
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initGUI();
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException {
		train = Boolean.valueOf(args[0]);
		SwingUtilities.invokeLater(() -> new Frame());
		if (!train) {
			model = new NeuralNetwork("input.txt", 0.2, 15000, 0.001, new int[] {M*2, 6, 12, 5});
			model.train();
		}

	}

	private void initGUI() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel(train ? "Write letter Alpha 20 more times" : "Write any letter and I'll guess it");
		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
		add(label, BorderLayout.PAGE_END);
		setTitle("Learn letters");
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				panel.getGraphics().drawLine(e.getX(), e.getY(), e.getX(), e.getY());
				points.add(new Point(e.getX(), e.getY()));
			}
		});
		
		panel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				double avgX = points.stream()
									.mapToDouble(Point::getX)
									.average()
									.orElse(Double.NaN);
				double avgY = points.stream()
									.mapToDouble(Point::getX)
									.average()
									.orElse(Double.NaN);
				points = points.stream()
						.map(p -> new Point(p.getX()-avgX, p.getY()-avgY))
						.collect(Collectors.toList());
				
				double ratioValue = points.stream()
									.mapToDouble(p -> (p.getX() < p.getY()) ? p.getY() : p.getX())
									.max()
									.orElse(Double.NaN);
				points = points.stream()
						.map(p -> new Point(p.getX()/ratioValue, p.getY()/ratioValue))
						.collect(Collectors.toList());
				
				double D = 0;
				for (int i = 0; i < points.size()-1; i++) {
					D += points.get(i).euclideanDistance(points.get(i+1));
				}
				
				List<Point> representativePoints = new ArrayList<>();
				for (int k = 0; k < M; k++) {
					double targetDistance = k*D/(M-1);
					int i = 0;
					Point currentPoint = points.get(0);
					while (targetDistance > 0 && i != points.size()-1) {
						targetDistance -= currentPoint.euclideanDistance(points.get(++i));
						currentPoint = points.get(i);
						if (i == points.size()-1) break;
					}
					representativePoints.add(currentPoint);
//					for (int i = 1; i < points.size(); i++) {
//						double currentDistance = Math.abs(targetDistance - (points.get(i).euclideanDistance(firstPoint)));
//						if (currentDistance == 0) {
//							representativePoints.add(points.get(i));
//							break;
//						}
//						if (currentDistance < currentBestDistance) {
//							currentBestDistance = currentDistance;
//							currentBestPoint = points.get(i);
//						}
//						if (i == points.size()-1)
//							representativePoints.add(currentBestPoint);
//					}
				}
				
				if (train) {	
					inputs.add(representativePoints);
					inputCounter++;
					if (inputCounter == inputGestures) {
						letters--;
						if (letters != 0) {
							inputCounter = 0;
						} else {
							String s = "";
							int letterRbr = 0, gestureRbr = 0;
							for (List<Point> input: inputs) {
								for (Point point: input) {
									s += String.valueOf(point.getX()) + " " + String.valueOf(point.getY()) + " ";
								}
								s += oneHotByClass(letterRbr) + "\n";
								if (gestureRbr == inputGestures-1) {
									gestureRbr = 0;
									letterRbr++;
								} else {
									gestureRbr++;
								}
							}
							try {
//								System.out.println(s);
								Files.write(Paths.get("input.txt"), s.getBytes(StandardCharsets.UTF_8));
							} catch (IOException ex) {
								ex.printStackTrace();
							}
							System.exit(0);
						}
					}
					panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
					label.setText("Write letter " + numberToLetter(6-letters) + " " + String.valueOf(inputGestures-inputCounter) + " more times.");
				} else {
					List<Double> xTest = new ArrayList<>();
					for (Point point: representativePoints) {
						xTest.add(point.getX());
						xTest.add(point.getY());
					}
					panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
					System.out.println(model.predict(xTest));
				}
			}
		});
	}
	
	private static String oneHotByClass(int letterNumber) {
		String s = "";
		for (int i = 0; i < 5; i++) {
			if (i != letterNumber)
				s += "0 ";
			else
				s += "1 ";
		}
		return s.substring(0, s.length()-1);
	}
	
	private String numberToLetter(int number) {
		switch(number) {
		case 1:
			return "Alpha";
		case 2:
			return "Beta";
		case 3:
			return "Gamma";
		case 4:
			return "Delta";
		case 5:
			return "Epsilon";
		default:
			return "";
		}
	}

}
