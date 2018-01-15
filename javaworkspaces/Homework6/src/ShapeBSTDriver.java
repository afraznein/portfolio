/*
Name: Anthony Frazier
Date: March 25, 2016
Program Name: Homework6
Description: Homework6
Objective:	Objective:

Write a program which creates a binary search tree of different shapes from a file.

     The comparison is based on the shape’s area
     There are 3 shapes
     
  Rectangle
  Circle
  Right Triangle
  
  The file is tab delimited format goes as follows
  
  Rectangle \t side 1 \t side 2
  Circle \t radius
  Right Triangle \t side 1 \t side 2
  
  The binary search tree needs to have the following methods
  
  -insert: inserts a new shape into the tree
  -delete: deletes the shape instance.  Keep in mind that in order for a shape to be equal it must have the same same type and area, but the sides can be interchangeable.
  -print pre-order traversal: Print the data.  Next explore the left subtree.  Finally right explore subtree.
  -print in-order traversal:  Explore the left subtree. Print the data.  Finally explore the right subtree.
  -print post-order traversal:  Explore the left subtree. Then explore the right subtree.  Finally print out the data.
  -max area: return the maximum area in the tree.  Use the properties of the tree to make it efficient.
  -delete areas greater than value: For a given value all shapes with an area that’s strictly greater than those values should be deleted.
    Use the principle of a binary search tree to help make it efficient.
  Finally write a test file that demonstrates THE POWER OF TREES!!! SHAPES!!! By reading a shape file.
 
HINTS: 
Inheritance and polymorphism makes this problem not so difficult, so interfaces and base classes are a good idea.
Yes there will be many different class files.
Recursion is your friend.


*/
import java.util.*;
import java.io.*;
public class ShapeBSTDriver {

	public static void main(String[] args)
	{
		ShapeBST newShapeTree = new ShapeBST();
		Shape newShape = new Shape();
		
		
		String str = "";
		String str1 = "";
		String str2 = "";
		System.out.println("Welcome to the shape tree tester!");
		System.out.println("Reading data from shapeFile.txt and inserting into a new tree.\n");
		try
		{
		Scanner read = new Scanner(new File("src/shapeFile.txt"));
		read.useDelimiter("\t|\n");
		while ( read.hasNext() )
		{
			str = read.next();
			if( str.contains("Rectangle"))
			{
				//read.reset();
				//str = read.next();
				str1 = read.next();
				str2 = read.next();
				double side1 = Double.parseDouble(str1);
				double side2 = Double.parseDouble(str2);
				Rectangle newRect = new Rectangle();
				newRect.setArea(0.0, side1, side2);
				newShape = newRect;
				System.out.println("Type: "+str+" Side1: "+str1+" Side2: "+str2+" Area:"+newShape.getArea());
				
				newShapeTree.insert(newShape);
				
			}
			if( str.contains("Circle"))
			{
				//read.reset();
				//str = read.next();
				str1 = read.next();
				double side1 = Double.parseDouble(str1);
				Circle newCircle = new Circle();
				newCircle.setArea(0.0, side1);
				newShape = newCircle;
				System.out.println("Type: "+str+" Radius: "+str1+" Area:"+newShape.getArea());
				newShapeTree.insert(newShape);
				
			}
			if( str.contains("Right Triangle"))
			{
				//read.reset();
				//str = read.next();
				str1 = read.next();
				str2 = read.next();
				double side1 = Double.parseDouble(str1);
				double side2 = Double.parseDouble(str2);
				RightTriangle newTriangle = new RightTriangle();
				newTriangle.setArea(0.0, side1, side2);
				newShape = newTriangle;
				System.out.println("Type: "+str+" Side1: "+str1+" Side2: "+str2+" Area:"+newShape.getArea());
				newShapeTree.insert(newShape);
				
			}
			if (str.equals("Rombus"))			// Temporary work around
			{
				System.out.println("Not a properly formatted line");
			}
			//if (!str.contains("Rectangle") || !str.contains("Circle") || !str.contains("Right Triangle"))
			//{
			//	System.out.println("Not a properly formatted line");
			//}
			System.out.println("");
		}
		read.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println();
			System.out.println("Printing pre-order");
			System.out.println();
			newShapeTree.printPreOrder();
			System.out.println();
			System.out.println("Printing in-order");
			System.out.println();
			newShapeTree.printInOrder();
			System.out.println();
			System.out.println("Printing post-order");
			System.out.println();
			newShapeTree.printPostOrder();
			System.out.println();
			
			newShape = newShapeTree.maxArea();
			double maxArea = newShape.getArea();
			System.out.println("The max area is: "+maxArea);
			
			
			System.out.println("Enter a double value. All nodes with an area exceeding this value will be deleted.");
			Scanner input = new Scanner(System.in);
			double value = input.nextDouble();
			System.out.println("Deleting all nodes with an area greater than "+value);
			newShapeTree.deleteAreasGreaterThanValue(value);
			input.close();
			System.out.println();
			System.out.println("Printing in-order");
			System.out.println();
			newShapeTree.printInOrder();
			
			newShape = newShapeTree.maxArea();
			maxArea = newShape.getArea();
			System.out.println("The max area is now: "+maxArea);
		}
	}
}
