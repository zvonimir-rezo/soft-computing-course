package zad1;

import java.util.Arrays;

public class DomainElement {
	
	private int[] values;
	
	public DomainElement(int[] values) {
		this.values = values;
	}
	
	public int getNumberOfComponents() {
		return values.length;
	}
	
	public int getComponentValue(int index) {
		return values[index];
	}
	
	public static DomainElement of(int... values) {
		return new DomainElement(values);
	}
	

	public int[] getValues() {
		return values;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainElement other = (DomainElement) obj;
		return Arrays.equals(values, other.values);
	}

	@Override
	public String toString() {
		String s = "(" + String.valueOf(values[0]);
		for (int i = 1; i < values.length; i++) {
			s += ", " + String.valueOf(values[i]);
		}
		s += ")";
		return s;
	}


}
