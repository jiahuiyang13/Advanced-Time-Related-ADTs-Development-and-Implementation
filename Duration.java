package edu.uwm.cs351;

import java.util.Objects;

//Jiahui Yang

/**
 * An amount of time, always positive.
 * To create a duration, scale an existing duration.
 */
public class Duration implements Comparable<Duration> {
	private final long extent; // this must remain private.  Do NOT add a getter!
	
	// this constructor must remain private:
	private Duration(long milliseconds) {
		extent = milliseconds;
	}
	
	
	// public static final Duration CONSTANT = new Duration(...);
	//CONSTANTS
	public static final Duration INSTANTANEOUS = new Duration(0);
	public static final Duration MILLISECOND = new Duration(1);
	public static final Duration SECOND = new Duration(1000L);
	public static final Duration MINUTE = new Duration(1000L * 60);
	public static final Duration HOUR = new Duration(1000L * 3600);
	public static final Duration DAY = new Duration(1000L * 3600 * 24);
	public static final Duration YEAR = new Duration((long) (1000L * 3600 * 24 * 365.25));
	
	// If you are overriding a method from a super class, always
	// annotate it "@Override" as here, overriding Object#equals(Object)
	@Override // implementation
	public boolean equals(Object x) {
		//if x is not duration return false
		if(!(x instanceof Duration))return false;
		else {
			//else, cast Duration onto x and check if
			//argument extent is equal to THIS
			Duration y = (Duration) x;
			return this.extent == y.extent;
		}
		
		 //   
	}
	
	@Override // implementation
	public int hashCode() {
		//returns a unique integer value with hashing algorithm
		return Objects.hash(extent);//  : Do something efficient.  Do NOT create a String.
		
	}
	
	// If you are overriding a method from an interface, then Java 5
	// says you CANNOT use Override, but anything later says you MAY.
	// Please always do unless you write a javadoc comment. 
	@Override // implementation
	public int compareTo(Duration other) {
		if (this.extent < other.extent) {
			return -1;
		}
		else if(this.extent > other.extent){
			return 1;
		}
		else {
			return 0;
		} //  : Remember what compareTo is supposed to return.
	}
	
	@Override // implementation
	public String toString() {
		//convert to seconds
		if (this.extent >= 1000.0 && this.extent < 60000.0 ) {
			return this.extent/1000.0 + " s.";
		}
		//convert to minutes
		else if (this.extent >= 1000.0 * 60 && this.extent < 1000L * 3600) {
			return this.extent/(1000.0 * 60) + " min.";
		}
		//convert to hours
		else if(this.extent >= 1000L * 3600.0 && this.extent < 1000L * 3600 * 24) {
			return this.extent/(1000L * 3600.0) + " hr.";
		}
		//convert to years
		else if (this.extent >= (long) (1000L * 3600 * 24 * 365.25)) {
			return this.extent/(float)(1000L * 3600.0 * 24 * 365.25) + " years";
		}
		//convert to days
		else if(this.extent >= 1000L * 3600.0 * 24 && this.extent < (long) (1000L * 3600 * 24 * 365.25f)) {
			return this.extent/(1000L * 3600.0 * 24) + " days";
		}
		//stays milliseconds
		else {
			return this.extent +".0" + " ms.";
		}
		
		 //  
	}
	
	
	// Methods that are not standard or private must have a documentation comment:
	
	/**
	 * Add two durations together to get a larger duration.
	 * @param d duration being added to this, must not be null
	 * @return new duration equal to the combined duration of this and the argument.
	 * @throws NullPointerException if d is null
	 */
	
	//Returns a new duration which adds the current extent and the argument's extent 
	// which is passed through as the parameter. Throws NullPointerException if d is null
	public Duration add(Duration d) throws NullPointerException {
		Duration f = new Duration(this.extent + d.extent);
		return f;
	}
	//Returns a new duration which subtracts the given duration(extent of time)
	//if the argument passed is bigger than THIS, throws ArithmeticException
	public Duration subtract(Duration d) throws ArithmeticException{
		Duration f = new Duration(this.extent - d.extent);
		if (d.extent > this.extent) {
			throw new ArithmeticException("argument bigger than THIS.");
		}
		else {
			return f;
		}
		
	}
	
	//returns a new Duration where the argument and current extend 
	//is multiplied together and rounded. Throws IllegalArgumentException if d is negative
	public Duration scale(double d) throws IllegalArgumentException{
		Duration f = new Duration(Math.round(d*this.extent));
		if (d < 0.0) {
			throw new IllegalArgumentException("Negative number not allowed.");
		}
		else {
			return f;
		}
	}
	
	//converts both parameter and current call into ms. doubles
	//if d is not 0, current extent and argument gets divided in current measurements
	public double divide(Duration d) {
		if (d != INSTANTANEOUS) {
			double y = (this.extent);
			double x = (d.extent);
			return y/x ;
		}
		else {
			double f = (this.extent/d.extent);
			return f;
		}
	}
	
	//  : three other public methods: subtract, scale & divide
	// Don't forget to write documentation comments.
}
