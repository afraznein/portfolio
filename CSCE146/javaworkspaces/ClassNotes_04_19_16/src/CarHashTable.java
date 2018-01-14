import java.util.*;
public class CarHashTable {
	private ArrayList<Car>[] table;
	public CarHashTable()
	{
		table = new ArrayList[100];
		for (int i = 0; i < table.length; i++)
		{
			table[i] = new ArrayList<Car>();
		}
	}
	// Hash Fucntion
	
	private int calculateIndex(Car aCar)
	{
		int index = 0;
		String makeAndModel =  aCar.getMake() + aCar.getModel();
		for (int i = 0; i < makeAndModel.length(); i++)
		{
			index += makeAndModel.charAt(i);
		}
		index %= 100;
		return index;
	}
	
	public void add(Car aCar)
	{
		int index = calculateIndex(aCar);
		table[index].add(aCar);
	}
	
	public void remove(Car aCar)
	{
		int index = calculateIndex(aCar);
		if (table[index].contains(aCar))
		{
		table[index].remove(aCar);
		}
		else
		{
			System.out.println("Car not found");
		}
	}
	
	public void lookUp(Car aCar)
	{
		int index = calculateIndex(aCar);
		if (table[index].contains(aCar))
		{
		System.out.println(aCar.toString());
		}
		else
		{
			System.out.println("Car not found");
		}
	}
}
