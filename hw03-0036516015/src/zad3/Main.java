package zad3;

import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		Defuzzifier defuzzifier = new COADefuzzifier();
		FuzzySystem fsAkcel = new AkcelFuzzySystemMin(defuzzifier);
		FuzzySystem fsRudder = new RudderFuzzySystemMin(defuzzifier);
		
		
		
		int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0, A, K;
		String line;
		while (true) {
			if (sc.hasNext()) {
				line = sc.next();
				if (line.contains("KRAJ")) break;
				Scanner s = new Scanner(line);
				L = s.nextInt();
				D = s.nextInt();
				LK = s.nextInt();
				DK = s.nextInt();
				V = s.nextInt();
				S = s.nextInt();
			}
			A = fsAkcel.infer(new int[] {L, D, LK, DK, V, S});
			K = fsRudder.infer(new int[] {L, D, LK, DK, V, S});
			System.out.println(A + " " + K);
			System.out.flush();
		}
	}
}
