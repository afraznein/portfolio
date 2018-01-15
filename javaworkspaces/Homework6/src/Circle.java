
public class Circle extends Shape{
	
	private double area = 0.0;
	private double radius;
	String str;
	
	public double getArea() {
		return area;
	}

	public void setArea(double area, double side) {		// Calculate and Set area of a circle
		area = (side*side) * Math.PI;
		this.area = area;
		this.radius = side;
	}
	
	public String toString()
	{
		str = "Circle|"+"Radius: "+radius+" Area: "+area;
		return str;
	}

}
