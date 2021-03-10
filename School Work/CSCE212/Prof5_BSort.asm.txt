.data
FPNum:  	.word 0x0, 0xff800000, 0x7f800000, 0x00000004 	# Float-point numbers 0, -Infty and Infty					# bytes -> 4 per word, 40 words
FP_Array:	.space 160

string1:  .asciiz "Input a Float-Point #:(0 indicates the end)\n" 
string2:  .asciiz "The sorted array is:\n" 
string3:  .asciiz "\nThe value in $s1 is: \n"
string4:  .asciiz ", "

					# Project 5 - Bubble Sort
					# JACOB HUFFSTETLER (STUDENT ID: 0031-5461)
.text
main:	
		la $t0, FPNum
		lwc1 $f10, ($t0) 	# $f10=0.	
		#lwc1 $f8, 12($t0)	# 4.0
				
		addi $s1, $zero, 0	# FP number counts in array 
		
		#baseAddress of the FP_Array in $s5
		la	$s5, FP_Array
		
		# Input a number 
InputLoop:	addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string1 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 6      # code for reading FP number is 6 
   		syscall           	# call operating system  

		add.s $f1, $f0, $f10	# move input fp number to $f1
		
		c.eq.s 	$f1, $f10	# chech if value is 0 to stop main loop
 		bc1t 	BubbleSort
		
		# if not Zero, continue on:
		
		# Add number to Array:	# $s1 -> Array Counter
		swc1 	$f1, ($s5)	# User Input($f1) into Base Address($f5)-> FP_Array
		addi	$s5, $s5, 4	# increase base Address by 4
		addi	$s1, $s1, 1	# increment counter by 1	
		
		j	InputLoop
		
		# Bubble Sort
		
BubbleSort:	# Get to Starting Value of Array Numbers
		#addi	$s5, $s5, -4	# ??? -> remove last increment
		sll	$t2, $s1, 2	# counter *4 -> to further reset array
		sub	$s5, $s5, $t2	# reset to start of Array

		# Setup Loop Variables:
		add	$s3, $s1,$zero		# $s3 -> n= Array Size || $s1: Array counter
		addi	$s7, $s7, 0		# $s7 -> i=0
		
		
		addi	$s6, $s6, 1		# setup boolean
		#addi	$s2, $s2, 0		# NotSort = 1 = true
						# if NotSort = 0 = false
		
WhileLoop:	beqz 	$s6, Sorted		# while notSorted == true
	 	#j	NotSorted				
	 					
NotSorted:	#addi	$s6, $s6, -1		# NotSorted = false = 0
		li	$s6, 0
		
ForLoop:	addi	$t7, $s3, -1		# array size -1
		blt   	$s7, $t7, SwapLoop		
		j	WhileLoop	 						 						 						 					

SwapLoop:	sll	$t4, $s7, 2		# data[i] -> $f2
 		add	$t5, $s5, $t4		# $s5 -> Array base
 		lwc1	$f2, 0($t5)
 		
 		addi	$t6, $t5, 4		# data[i+1] -> $f3
 		lwc1	$f3, 0($t6)
 		
 		c.lt.s 	$f3, $f2		# if f3 < f2 -> set c1 to 0, true
 		bc1f 	NoSwap
 		
 		# Swapping: set $s6 back to 1 | boolean = true
 		
 		#swc1	$f3, 0($s5)
 		#swc1	$f2, 4($s5)
 		
 		move 	$a0, $s5		# Array -> $a0
 		move	$a1, $s7		# i -> $a1	||  $s7 -> i
 		addi	$t0, $s7, 1		# i +1 
 		move	$a2, $t0		# i +1 -> $a2
 		 	 
 		#j	Swap			# $t5 -> data[j] || $t6 -> data[j+1]
 		 				
		sll 	$t0, $a1, 2		# calculate address of element 1
		add	$t0, $a0, $t0		
		sll	$t1, $a2, 2		# calculate address of element 2
		add	$t1, $a0, $t1
		
		lw	$t2, ($t0)
		lw	$t3, ($t1)
		sw	$t2, ($t1)
		sw	$t3, ($t0)
		
		li	$s6, 1
		#addi	$s6, $s6, 1		# Reset boolean -> add 1 to $s6
						# We swapped, so need to go thru loop again
		addi	$s7, $s7, 1		# increment i + 1
		
		# Debugging Println
		add $a0, $s6, $zero
      		addi $v0, $zero, 1
      		syscall
			
		j	NotSorted
		
NoSwap:		addi	$s7, $s7, 1		# increment i + 1 -> for inner loop
		j	ForLoop
			
				
Sorted:		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string2 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
      		
      		# Get to Starting Value of Array Numbers
		#addi	$s5, $s5, -4	# remove last increment
		#sll	$t2, $s1, 2	# counter *4 -> to further reset array
		#sub	$s5, $s5, $t2	# reset to start of Array
      		
      		# Print Loop: Output of Elements in the Array
PrintCond:	bgt $s1, $zero, PrintLoop
		j Exit
		
PrintLoop:	la $t0, ($s5)
		l.s  $f4, ($t0)		# Address plus 4 -> start of Array values
		addi $v0, $zero, 2      # code for printing FP number is 2
		add.s $f12, $f4, $f10 
   		syscall 
      		
      		addi $s5, $s5, 4	# Move to next element in Array -> +4
      		addi $s1, $s1, -1	# decrease counter
      		
      		# Print Comma - seperate values
      		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string4 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
      		
      		
      		# print value in $s1
      		#addi $v0, $zero, 4      # code for printing string is 4 
      		#la $a0, string3 	# load address of string to be printed into $a0    
      		#syscall
      		#add $a0, $s1, $zero
      		#addi $v0, $zero, 1
      		#syscall
      		
      		j PrintCond
      		
  		# Exit Code:	  		
Exit:		addi $v0, $zero, 10
		syscall 
		
		
		# Printing An Element:
		#addi $v0, $zero, 2      # code for printing FP number is 2
		#add.s $f12, $f4,$f10	# Print out value in Array
   		#syscall           	# $f12 -> printing register
		
		
