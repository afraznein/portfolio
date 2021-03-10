// Modified with permission by Anthony Frazier
// CSCE350 Spring 17
// Project One: Anagram Checker
// Objective: Based on two input strings (a) and (b), determine if these two strings are anagrams
//  of each other. Utilizing the helper function char2int, convert char values in the strings to
//  ascii values, and sum those values. If the sums of the arrays are equal, the strings are anagrams.
public class AnagramChecker {
	
	private static int char2int(char c){
		return (int)c-(int)'a';
	}
	
	//assume input strings are LOWER CASE
	//fine to add any additional helper functions (do in this file)
	//your solution must run in O(n+m) time (50% of points)
	public static boolean areAnagrams(String a, String b){
		int A[] = new int[a.length()];
		int B[] = new int[b.length()];
		int sum1 = 0;
		int sum2 = 0;
		for (int i=0; i < a.length(); i++)
		{
			A[i] = char2int(a.charAt(i));
			sum1 = sum1 + A[i];
		}
		for (int i=0; i < b.length(); i++)
		{
			B[i] = char2int(b.charAt(i));
			sum2 = sum2 + B[i];
		}
		// Must include the a and b length checks, because otherwise theoretically a string with
		// 'higher' values in the alphabet x, y, z etc could return false positives adding up to 
		// the correct number. Sums must be exactly equal while length also is equal.
		if (sum1 == sum2 && a.length() == b.length())
			return true; // they are anagrams based on ASCII value sums
		else
			return false;
		
	}


}


