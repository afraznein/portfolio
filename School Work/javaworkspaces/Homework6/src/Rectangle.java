
public class Rectangle extends Shape{
	private double area = 0.0;
	private double length = 0.0;
	private double width = 0.0;
	String str = "";
	
	public double getArea() {
		return area;
	}

	public void setArea(double area, double side, double side2) {		// Calculate and Set area of a circle
		area = (side*side2);
		this.area = area;
		this.length = side;
		this.width = side2;
	}
	public String toString()
	{
		str = "Rectangle|"+" Side1: "+length+" Side2: "+width+" Area: "+area;
		return str;
		}
}
