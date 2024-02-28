package edu.uwm.cs351;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.TimeZone;

/**
 * A time period within historical time.
 */
public class Period {
	//Choose fields
	//variables
	private final Time start;
	private final Time stop;
	private Duration length;

	
	/**
	 * Construct a period given the start time and length.
	 * @param s start time, must not be null
	 * @param l length, must not be null
	 */
	public Period(Time s, Duration l) throws NullPointerException{
		//  
		//if start time and length are not null, start time and l duration are added together
		if (s != null && l != null) {
			this.start = s;
			this.length = l;
			stop = start.add(l);
		}
		else {
			throw new NullPointerException();
		}
		
		
	}
	
	/**
	 * Construct a period given the start and end time
	 * @param from start time, must not be null
	 * @param to end time, must not be null or before the start time
	 */
	public Period(Time from, Time to) throws NullPointerException{
		//  
		//if end time and start time are not null and end time is not before start time
		//start time and stop time give a period
		if (from != null && to != null || from.compareTo(to) == 1) {
			this.start = from;
			this.stop = to;
		}
		else {
			throw new NullPointerException();
		}
		
		
	}
	
	/**
	 * Construct a period given the length and end time.
	 * @param len length of the period, must not be null
	 * @param end end time of the period.
	 */
	public Period(Duration len, Time end) throws NullPointerException{
		//  
		//if length is not null, end time and length are subtracted to get the start time
		if (len != null) {
			this.length = len;
			this.stop = end;
			start = end.subtract(this.length);
		}
		else {
			throw new NullPointerException();
		}
		
	}
	
	/**
	 * Return the start time of the period.
	 * @return beginning time
	 */
	public Time getStart() {
		return this.start; //  
	}
	
	/**
	 * Return the stop time of the period.
	 * @return end time
	 */
	public Time getStop() {
		return this.stop; //  
	}
	
	/**
	 * Return the length of the period.
	 * @return the amount of time in this period
	 */
	public Duration getLength() {
		//length is the duration between start and end
		this.length = start.difference(stop);
		return this.length; //  
	}
	
	@Override // implementation
	public boolean equals(Object x) {
		if(!(x instanceof Period))return false;
		else {
			Period y = (Period) x;
			//tests if start times and end times are equal between argument and THIS
			return this.start.equals(y.start) && this.stop.equals(y.stop);
		} //  
	}
	
	@Override // implementation
	public int hashCode() {
		return Objects.hash(this.start, this.stop); //  
	}
	
	@Override // implementation
	public String toString() {
		return "[" + this.start + "; " + getLength() + "]" ; //  
	}
	
	/**
	 * Return whether this period overlaps with the parameter.
	 * If one appointment starts where the other ends, they do not overlap.
	 * @param p period to compare to, must not be null
	 * @return whether this period overlaps the parameter
	 */
	public boolean overlap(Period p) throws NullPointerException{
		//if period is not null
		if (p != null) {
			//if THIS start point is the same or before arguments stop
			//and arguments start point is the same or after THIS stop point, doesn't overlap
			
			//if THIS start point is same or after arguments stop point
			//and arguments start point is same or after THIS stop point, doesn't overlap
			if (getStart().compareTo(p.getStop()) <= 0 && p.getStart().compareTo(getStop()) >= 0||
				getStart().compareTo(p.getStop()) >=0 && p.getStart().compareTo(getStop()) <= 0	){
				return false;
			}
			//OVERLAPS if THIS stop point is after argument stop point
			//OVERLAPS if THIS stop point is after argument stop point and THIS stop point is before argument start point
			else if (getStop().compareTo(p.getStart()) > 0||
					getStop().compareTo(p.getStart()) > 0 && getStop().compareTo(p.getStart()) < 0){
				return true;
			}
			//if not any cases above, return false
			else{
			return false;
			}
		}
	
		else {
			throw new NullPointerException();
		}
		
		 //  
	}
	}
