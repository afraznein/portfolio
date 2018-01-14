import java.util.*;
public class PhobiaDrivers {

	public static void main(String[] args) {
		
		PhobiaHashTable hash = new PhobiaHashTable();
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the fear hash table!");
		System.out.println("Enter:");
		System.out.println("Input any number to continue. 5 to quit.");
		
		int usr = input.nextInt();
		while ( usr != 5)
		{
			System.out.println("Welcome to the fear hash table!");
			System.out.println("Enter:");
			System.out.println("1.	To add a fear.");
			System.out.println("2.	To remove a fear.");
			System.out.println("3.	To lookup a fear.");
			System.out.println("4.	To print all fears.");
			System.out.println("5.	Quit.");
			
			usr = input.nextInt();
			if (usr == 5)
			{
				return;
			}
			
			if (usr == 1)
			{
				System.out.println("Enter the name of your fear:");
				String nameStr = input.next();
				System.out.println("Enter the description of your fear:");
				String str = input.next();
				String str2 = input.nextLine();
				str2 = str + str2;
				Phobia aFear = new Phobia();
				aFear.setName(nameStr);
				aFear.setDescription(str2);
				hash.add(aFear);
			}
			
			if (usr == 2)
			{

				System.out.println("Enter the name of your fear:");
				String nameStr = input.next();
				System.out.println("Enter the description of your fear:");
				String str = input.next();
				String str2 = input.nextLine();
				str2 = str + str2;
				Phobia aFear = new Phobia();
				aFear.setName(nameStr);
				aFear.setDescription(str2);
				hash.remove(aFear);	
			}
			
			if (usr == 3)
			{

				System.out.println("Enter the name of your fear:");
				String nameStr = input.next();
				System.out.println("Enter the description of your fear:");
				String str = input.next();
				String str2 = input.nextLine();
				str2 = str + str2;
				Phobia aFear = new Phobia();
				aFear.setName(nameStr);
				aFear.setDescription(str2);
				hash.lookUp(aFear);	
			}
			
			if (usr == 4)
			{
				hash.printHashTable();
			}
			
		}		// End While
	}
}
