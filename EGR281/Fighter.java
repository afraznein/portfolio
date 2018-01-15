/*
Name: Anthony Frazier
Date: June 29, 2015
Program Name: Fighter
Description: Lab Assignment 9: Classes

Create a class for a Fighter in a combat game. The Fighter class has the following instance fields:

name
health points -- a double representing health. 0 or below is dead.
strength points -- a double representing the strength of the Fighter
The Fighter class also has the following methods

public void setName(String n)
A mutator method for the name Note: Do not input the name in this method. That belongs in the main method.
public void setHealth(double h)
A mutator method for the health (Do not input the health here.)
public void setStrength(double s)
A mutator method for the strength (Do not input the strength here.)
public String getName()
An accessor method for the name
public double getHealth()
An accessor method for the health
public double getStrength()
An accessor method for the strength
public void attack(Fighter other)
Calculates a change in the health of the other  parameter by multiplying the strength of this Fighter by Math.random(), then changing the health of the foe by the change, then calling printChange, as described below.  Note that this method, like an equals method, has
a parameter that is also a Fighter object, so you can refer to other.health, etc., even though
those fields are private.
public boolean isDead()
Returns true if the Fighter's health is less than or equal to 0, else false
public void printChange(double c,Fighter other)
Prints a message displaying the name of the other Fighter, the amount of change in health c, and the resulting level of health  for other.

*/
import java.util.Scanner;
import java.text.*;
public class Fighter
{
	private String name;
	private double health, strength;
// Setters
	public void setName(String n)
	{
		this.name = n;
	}

	public void setHealth(double h)
	{
		this.health = h;
	}
	public void setStrength(double s)
	{
		this.strength = s;
	}
// Getters
	public String getName()
	{
		return name;
	}

	public double getHealth()
	{
		return health;
	}
	public double getStrength()
	{
		return strength;
	}
// Attack
	public void attack(Fighter other)
	{
		double hit;
		hit = this.strength * Math.random();
		other.health = other.health - hit;
		printChange(hit, other);
	}
// Dead Check Method
	public boolean isDead()
	{
		if (this.health <= 0)
			return true;
		else
			return false;
	}
// Print Attack Sheet
	public void printChange(double c,Fighter other)
	{	DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(this.name + " has hit " + other.name + " for " + df.format(c) + " points of damage. ");
		System.out.println(other.name + " has " + df.format(other.health) + " points of health left.");
	}
// Main Method
	public static void main(String[] args)
  	{
		Scanner input = new Scanner(System.in);
		Fighter f1 = new Fighter();
		Fighter f2 = new Fighter();
		System.out.println("This program declares two 'Fighter' classes that will attack each other in a turn based style until one has died.");

		System.out.println("Please name your first Fighter");			// Obtain first fighter attributes from user
		f1.setName( input.nextLine() );
		System.out.println("How much health does " + f1.getName() + " have?");
		f1.setHealth( input.nextDouble() );
		System.out.println("How much strength does " + f1.getName()+ " have?");
		f1.setStrength( input.nextDouble() );
			input.nextLine(); // Clear Buffer

		System.out.println("Please name your second Fighter");			// Obtain second fighter attributes from user
		f2.setName( input.nextLine() );
		System.out.println("How much health does " + f2.getName() + " have?");
		f2.setHealth( input.nextDouble() );
		System.out.println("How much strength does " + f2.getName() + " have?");
		f2.setStrength( input.nextDouble() );

// Now Fight (Until one is dead)
		while ( !f1.isDead() && !f2.isDead() )
		{
			if ( !f1.isDead() )
			{
			f1.attack(f2);
			f1.isDead();
			f2.isDead();
			}
			if ( !f2.isDead() )
			{
			f2.attack(f1);
			f1.isDead();
			f2.isDead();
			}
		}
	}

}