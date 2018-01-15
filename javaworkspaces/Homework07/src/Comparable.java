
public class Comparable
{
	private static double weight = 0.0;

	public double getWeight() {
		return weight;
	}

	public void setArea(double weight) {
		Comparable.weight = weight;
	}
	
	int compareTo(Object o)
	{
		Sheep aSheep = (Sheep) o;
		return (int) ( this.getWeight() - aSheep.getWeight() );
	}
}
