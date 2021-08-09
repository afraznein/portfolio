using System;
 
//Creating a Class
public class Animal
{
	//C# has public private, and public access types
	// { get; set; } creates default getters and setters
	public double height { get; set; }
	public double weight { get; set; }
	
	//Custom getters and setter 
	public string name;
	public string Name
	{
		get { return name; }
		set { name = value; }
	}

	//Default Constuctor
	public Animal()
    {
            this.height = 0;
            this.weight = 0;
            this.name = "No Name";
    }

    //Custom Constuctor
    public Animal(double height, double weight, String name)
    {
    	this.height = height;
    	this.weight = weight;
    	this.name = name;
    }

    //Function
    public string toString()
    {
    	return "Your Animal is " + height + " inches" + "\n" + 
    		   "Your Animal is " + weight + " lbs" + "\n" +
    		   "Your Animal's name is" + name;
    }
        

    static public void Main (string[] args)
    {
    	//The basics
        Console.WriteLine ("Hello World");

        Console.WriteLine ("What is your name?");
        String myName = Console.ReadLine();
        Console.WriteLine("Your name is " + myName);

        //C# has the standard data types, int, long, decimal, float, double, String, char, bool, etc.
        int myInt = int.MaxValue;
		Console.WriteLine("My int = " + myInt);

		//C# has dynamic data types, that are defined at run time
		dynamic myType = "Justin";
		myType = 1;
		Console.WriteLine("myType = " + myType);

		//C# also has variable data types that is defined when compiled
		var anotherType = 1;
		// ERROR : anotherType = "Justin";
		var otherType = "Justin";
		Console.WriteLine("anotherType = " + anotherType + "\n" + "otherType = " + otherType);

		//C# has the if, and switch statments just like other C languages
		//C# has for, foreach, while, and do while loops

		//C# doesnt care if the s in string is captial or not
		string myString = "C# is great!";
		foreach (char c in myString)
		{
			Console.Write(c);
		}
		Console.WriteLine();

		//Creating an Object
		Animal myDog = new Animal(5, 10, "Lucky");
		Console.WriteLine(myDog.toString());
    }
}