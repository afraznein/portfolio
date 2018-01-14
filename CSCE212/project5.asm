# CSCE 212
# Anthony Frazier
# 00187888
# April 22, 2016
.data
FPNum:  .word 0x0
baseadd: .space 200

string1:  .asciiz "Input a Float-Point #:(0.0 indicates the end)\n" 
string2:  .asciiz "\nBubble Sorted Array: "
string3:  .asciiz ", "
.text
main:	
		la $t0, FPNum
		lwc1 $f10, ($t0) 	# $f10=0.0	
   		la $s5, baseadd		# baseadd of the array to $s5
				
		addi $s1, $zero, 0	# FP number counts in array 

		# Input a number 
InputLoop:	addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string1 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 6      # code for reading FP number is 6 
   		syscall           	# call operating system  

		add.s $f1, $f0, $f10	# move input fp number to $f1
 		c.eq.s $f1, $f10	# if input is 0, then jump to SortStart
 		bc1t SortStart
 		sll $t0, $s1, 2		# Index of next open spot in array * 4, store in t0
 		add $t0, $s5, $t0	# Add above value to base add of array
 		swc1 $f1, 0($t0)	# Store input value into array
 		addi $s1, $s1, 1	# increment counter s1 by 1
 		j InputLoop		# input is nonzero, jump back to input loop
 		
 		
 		
 		#Sort
 		
SortStart:	addi $s2, $zero, 0	# Impelement counter s2 (start at index 0)
 		addi $s3, $s2, 1	# Implement counter s3 (s2+1)
 					#addi $s6, $zero, 0	# Implement loop counter s6
 		add $s7, $zero, $s1	# Implement # of elements in array to value s7
 		
Sort:		beq $s2, $s7, SortAgn	# If first element s2 = length of array, jump to SortAgn?)
 		sll $t2, $s2, 2		# S2 * 4, store in t2 (i)
 		sll $t3, $s3, 2		# s3 * 4, store in t3 (i+1)
 		add $t2, $t2, $s5	# t2 + base address = new address of A[t2]
 		add $t3, $t3, $s5	# t3 + base address = new address of A[t3]
 		lwc1 $f2, 0($t2)	# Load fp number from t2 into f2	
 		lwc1 $f3, 0($t3)	# Load fp number from t3 into f3
 		
 		c.lt.s $f3, $f2		# If A[i+1] < A[i] we need to swap
 		bc1t LessThan		# Jump to less than if above is true
 		addi $s2, $s2, 1	# Increment s2 by 1
 		addi $s3, $s3, 1	# Increment s3 by 1		
 		j Sort
 		
 LessThan:	add.s $f5, $f10, $f10 	# Impelement temp fp num f5
 		add.s $f5, $f5, $f2	# Store f2 temp into f5
 		add.s $f2, $f10, $f3	# Store f3 into f2
 		add.s $f3, $f10, $f5	# store temp f5 into f3
 		#Store the values back into the array
 		
 		sll $t2, $s2, 2		# 4i
 		sll $t3, $s3, 2		# 4(i+1)
 		add $t2, $s5, $t2 	# add 4i to base add
 		add $t3, $s5, $t3	# add 4(i+1) to base add
 		swc1 $f2, 0($t2)	# store f2 back into array
 		swc1 $f3, 0($t3)	# store f3 back into array
 		
 		addi $s2, $s2, 1	# Increment s2 by 1
 		addi $s3, $s3, 1	# Increment s3 by 1		
 		j Sort
 		
SortAgn:	beq $s7, $zero, Exit	# If # of unsorted elements in array = 0, then jump to exit, else
 		subi $s7, $s7, 1	# Last number in array is max, S7 = s7 - 1. Now only need to check length-1
 		addi $s2, $zero, 0	# Reset i = 0
 		addi $s3, $s2, 1	# Reset i+1 = 1
 		j Sort		
					
Exit:		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string2 	# load address of string to be printed into $a0    
      		syscall         	# call operating system
      		
      		addi $t7, $zero, 1	# implement print counter t7
      		addi $s1, $s1, 1	# For some reason my loop is moving memory up by one space
Print:      	beq $t7, $s1, Done	# if t7 = s1 (# of elements in array) then jump to done
		addi $v0, $zero, 2      # code for printing FP number is 2
		sll $t9, $t7, 2		# t7 * 4, store in t9
		add $t9, $t9, $s5	# store baseadd+t9 into t9
		lwc1 $f12, 0($t9)
		#add.s $f12, $f12, $f10
   		syscall           	# call operating system 
   		
   		addi $t7, $t7, 1	# Increment t7 by 1
   		
   		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string3 	# load address of string to be printed into $a0    
      		syscall         	# call operating system
      		
   		j Print 

 Done:  		
		addi $v0, $zero, 10	# End Program
		syscall 
