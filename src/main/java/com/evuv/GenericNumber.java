package com.evuv;

public class GenericNumber extends Number implements Comparable<GenericNumber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Number number;
	boolean hasFloatingPoint;
	
	public GenericNumber(Object n) {
		if (! (n instanceof Number)) {
			throw new RuntimeException ("Generic number got non Number value = " + n);
		}
		this.number = (Number)n;
		hasFloatingPoint = (number instanceof Float) || (number instanceof Double);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Number)) {
			return false;
		}
		Number other = (Number)o;
		if ( hasFloatingPoint ) {
			return compareTo(number.doubleValue(), other.doubleValue()) == 0;
		}
		return compareTo(number.longValue(), other.longValue())  == 0;
		
	}
	
	@Override
	public int compareTo(GenericNumber o) {
		if ( hasFloatingPoint ) {
			return compareTo(number.doubleValue(), o.doubleValue());
		}
		return compareTo(number.longValue(), o.longValue());
	}
	
	private static int compareTo(Long number1, Long number2) {
		return number1.compareTo(number2);
	}
	
	private static int compareTo(Double number1, Double number2) {
		return number1.compareTo(number2);
	}


	@Override
	public int intValue() {
		return number.intValue();
	}


	@Override
	public long longValue() {
		return number.longValue();
	}


	@Override
	public float floatValue() {
		return number.floatValue();
	}


	@Override
	public double doubleValue() {
		return number.doubleValue();
	}

}
