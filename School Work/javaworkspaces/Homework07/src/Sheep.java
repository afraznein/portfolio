
public class Sheep{
	private String name;
	private String str;
	private double weight;
	
	public void Sheep()
	{
		this.name = "";
		this.weight = 0.0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String toString()
	{
		str = "Name:"+name+"\nWeight:"+weight;
		return str;
	}

}
