#ifndef QUICKSORT_H
#define QUICKSORT_H

#include <algorithm>

using std::swap;
/*
* Modified by : Anthony Frazier
* Modified date: 03/27/17
* Class: CSCE350
*/
//note returns INDEX of median
template<typename T> inline
int medianOf3(T A[], int l, int r){
	//this is overcommented... also, try and avoid using pointers
	T* a = A + l;//array name is just pointer to 1st (0 index) elem., + l shifts l*(T size)
	T* b = A + l + (r-l)/2;//middle item... int division rounds down
	T* c = A + r;

	//when a is a pointer, *a is the dereference operator (gives value a points to)
	T* m;
	if(*a < *b){
		if(*b < *c) m=b; 
		else if(*c < *a) m=a;
		else m=c;
	} else{ //b <=a
		if(*a < *c) m=a;
		else if(*c < *b) m=b;
		else m=c;
	}
	return m-A; //m-A is the number of elements from A[0]

}

//remember: l and r are INLCUSIVE (just like Lomuto)
template<typename T>
int hoarePartition(T A[], int l, int r){

	T m = medianOf3(A, l, r); // Returns index of item to use as pivot
	swap (A[l], A[m]); // Swap above found median and L
	T p = A[l]; // Assign pivot to left most item.
	
	int i = l;
	int j = r+1;

	do {
		do{
		i = i+1;
		} while(A[i] < p); // repeat until A[i] >= p
		do{
		j = j-1;	
		} while(A[j] > p); // repeat until A[j] <= p

		swap(A[i], A[j]); // perform inner swap of A[i] and A[j]
	} while (i < j);	  // repeat until i >= j
	swap(A[i], A[j]);	  // undo the last swap
	swap(A[l], A[j]);	  // perform final swap

	return j;
}

template<typename T>
void quicksort(T A[], int l, int r){

	if (l < r){
	T s = hoarePartition(A, l, r);
	quicksort(A, l, s-1);
	quicksort(A, s+1, r);
	}	
}


#endif
