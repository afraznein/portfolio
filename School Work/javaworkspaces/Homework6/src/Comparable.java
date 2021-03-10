
public class Comparable
{
	private static double area = 0.0;

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		Comparable.area = area;
	}
	
	int compareTo(Object o)
	{
		Shape aShape = (Shape) o;
		return (int) ( this.getArea() - aShape.getArea() );
	}
}
