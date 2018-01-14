
public class Car {
	
	private String make;
	private String model;
	private int year;

	
	public Car()
	{
		make = model = "None yet";
		year = -1;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		
		if (year >= 0)
		{
			this.year = year;	
		}
		
	}
	
	public String toString()
	{
		return this.make + " " + this.model + " " + this.year;
	}
	
	public boolean equals (Car aCar)
	{
		return this.model.equals(aCar.getModel()) 
				&& this.make.equals(aCar.getMake()) 
				&& this.year == aCar.getYear();
	}
}
