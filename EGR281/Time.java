import java.text.DecimalFormat;


public class Time {
private int hours, minutes;
private String amPm;
private static final DecimalFormat DF = new DecimalFormat("00");
//constructors
public Time(int h, int m, String ap){
	this.setHours(h);
	this.setMinutes(m);
	this.setAmPm(ap);
}
public Time(int h, String ap){
	this.set(h, 0, ap);
}
public Time(){
	this.set(12, 0, "am");
}

// mutator methods
public void setHours(int h) {
	if (h<1 || h>12) {
		this.hours = 12;
	}
	else 
		this.hours = h;
}
public void setMinutes(int m){
	if (m<0 || m>59){
		this.minutes=0;
	}
	else 
		this.minutes=m;
}
public void setAmPm(String s){
	s = s.toLowerCase();
	if (!s.equals("am") && !s.equals("pm")){
		this.amPm = "am";
	}
	else 
		this.amPm = s;
}
public void set(int h, int m, String ap){
	this.setHours(h);
	this.setMinutes(m);
	this.setAmPm(ap);
}
public void set(int h, String ap){
	this.set(h, 0, ap);
}
public void set(){
	this.set(12, 0, "am");
}
// accessor methods
public int getHours() {
	return this.hours;
}
public int getMinutes() {
	return this.minutes;
}
public String getAmPm() {
	return this.amPm;
}

public String toString(){
	return DF.format(this.hours) + ":" + DF.format(this.minutes) + this.amPm;
}
public boolean equals(Time other){
	return this.hours==other.hours 
			&& this.minutes==other.minutes 
			&& this.amPm.equals(other.amPm);
}
}
