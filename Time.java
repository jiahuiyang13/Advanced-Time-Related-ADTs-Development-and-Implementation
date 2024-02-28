package edu.uwm.cs351;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

//Jiahui Yang

/**
 * A point in time.
 */
public class Time implements Comparable<Time> {
	//   data structure for Time (very simple)
	// The solution also has a private constructor,
	// which is very useful.
	private final long point;
	
	//long constructor
	private Time(long ms) {
		//point in time is a long millisecond
		point = ms;
	}
	/**
	 * 
	 * Create a time for now.
	 */
	public Time() {
		//made point in time the current time
		this.point = System.currentTimeMillis();
	
		//   (see homework)
	}
	
	/**
	 * Create a time according to the time parameter.
	 * @param c calendar object representing a time, must not be null
	 */
	public Time(Calendar c) throws NullPointerException{
		//if argument is null throws NPE
		if (c == null) {
			throw new NullPointerException("Calendar object is null.");
		}
		else {
			//else, current point in time is set to argument
			this.point = c.getTimeInMillis();
		}
		//  
	}
	
	// Override/implement methods standard for immutable classes.
	@Override // implementation
	public boolean equals(Object x){
		if(!(x instanceof Time))return false;
		else {
			Time y = (Time) x;
			return this.point == y.point;
		}
	}
	@Override // implementation
	public int hashCode() {
		return Objects.hash(point);//   Do something efficient.  Do NOT create a String.
		
	}
	@Override // implementation
	public int compareTo(Time other) {
		if (this.point < other.point) {
			return -1;
		}
		else if(this.point > other.point){
			return 1;
		}
		else {
			return 0;
		}
		
	}
	@Override // implementation
	public String toString() {
		//formatted with SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("G yyyy/MM/dd HH:mm:ss");
		//converted Time Zone to UTC
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		//used current point of time to apply to format
		String str = sdf.format(this.point);
		//return completed format with correct date and time
		return sdf.getTimeZone().getID() + " " + str ;
				
	}
	/**
	 * Return the difference between two time points.
	 * The order doesn't matter --- the difference is always a
	 * (positive) Duration.
	 * @param t time point to compute difference with
	 * @return duration between two time points.
	 */
	public Duration difference(Time t) {
		//duration variables that scale time points into appropriate MS.
		Duration x = Duration.MILLISECOND.scale(this.point);
		Duration y = Duration.MILLISECOND.scale(t.point);
		//if current point in time is bigger/equal, current will subtract from argument
		if(this.point >= t.point) {
			Duration z = x.subtract(y);
			return z;
		}
		//else argument would subtract from x
		else {
			return y.subtract(x);
		}
		 //   
	}

	/**
	 * Return the time point after a particular duration.
	 * If the point advances too far into the future,
	 * more than a hundred million years from now, this
	 * method may malfunction.
	 * @param d duration to advance, must not be null
	 * @return new time after given duration
	 */
	public Time add(Duration d) throws ArithmeticException{
		//if d is not 0 cast long onto d converted into MS.
		if (d != Duration.INSTANTANEOUS) {
			long timeAdd = (long) d.divide(Duration.MILLISECOND);
			//New time variable which is the argument casted into a long MS added onto current point of time
			Time afterAdd = new Time(timeAdd + this.point);
			return afterAdd;
		}
		else {
			//else return the point in time
			Time current = new Time(this.point);
			return current;
		}
		
			
		
		
		 //   
	}
	
	/**
	 * Return the time point before a particular duration.
	 * If a point regresses too far into the past,
	 * more than a hundred million years from now,
	 * this method may malfunction.
	 * @param d duration to regress, must not be null
	 * @return new time before this one by the given duration.
	 */
	public Time subtract(Duration d) {
		//if d is not 0, cast long onto d converted into MS.
		if (d != Duration.INSTANTANEOUS) {
			long timeAdd = (long) d.divide(Duration.MILLISECOND);
			//New time variable which is the current point in time subtracted by the argument casted into a long MS.
			Time afterAdd = new Time(this.point - timeAdd);
			return afterAdd;
		}
		else {
			//else returns point in time
			Time current = new Time(this.point);
			return current;
		}
		} //   
	
	
	/**
	 * Return the time as a (mutable) Calendar object.
	 * @return new Calendar object for time.
	 */
	public Calendar asCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(point);
		return cal;
	}
}
