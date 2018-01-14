.data
OddArray:  .space 100
EvenArray: .space 100

string1:  .asciiz "Input a Number:\n" 
string2:  .asciiz "\n Odd #'s:" 
string3:  .asciiz "\n Even #'s:" 
string4:  .asciiz ", "

.text
main:	
		addi $s2, $zero, 99999	# Indicator for stop input new number
		addi $s3, $zero, 0	# Initialize # of input odd numbers
		addi $s4, $zero, 0	# Initialize # of input even numbers
		la $s5, OddArray	# BaseAddress of Odd Array
		la $s6, EvenArray	# BaseAddress of Even Array
		
		# Read input number until 99999 is input. 
		# 99999 is the indicator of the end and it will not be counted as an input number.
		
InputLoop:	addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string1 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 5      # code for reading integer is 5 
   		syscall           	# call operating system
   		add $s1, $v0, $zero  	# input into $s1
   		
   		beq $s1, $s2, ExitLoop  # No more input if 99999 is input
   			
   		
   		andi $t1, $s1, 1	# $t1 will be 1 if odd, or 0 if even
   		
   		beq $t1, $zero, Even    # $t1 is 1 if odd, 0 if even, if 0, Jump to Even, Else, Jump to Odd
   		j Odd
   		
 Even:		sll $t2, $s4, 2		# even counter $s4 * 4, store in $t2
 		add $t3, $t2, $s6	# add $t2 to base even address $s6, store new address in $t3
 		sw $s1, 0($t3)		# store inputed value $s1 in $t3	
 		addi $s4, $s4, 1	# increment even counter $s4 by 1
 		
 		j InputLoop
 
 Odd: 		sll $t2, $s3, 2		# odd counter $s3 * 4, store in $t2
 		add $t3, $t2, $s5	# add $t2 to base odd address, $s5, store new address in $t3
 		sw $s1, 0($t3)		# store inputed value $s1 in $t3
 		addi $s3, $s3, 1	# increment odd counter $s3 by 1
 		  		
		j InputLoop

ExitLoop:
		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string2 	# load address of string to be printed into $a0    
      		syscall  
      		
      		# You write the code here to print out the odd numbers in the OddArray. This is basically a for loop over $s3 elements in OddArray.
 		# The printed numbers are separted by ', ' as in string4 defined above.
 		addi $t4, $zero, 0	# initialize a counter value * int i in for loop
 		
 Check:		beq $t4, $s3, EvenPrintStrt	# if counter $t4 = odd counter $s3, Done with Odd array, jump to EvenPrintStart
 		addi $t5, $t4, 0		# assign the counter $t4 into a new temp storage $t5, so we dont reset the counter
		sll $t5, $t5, 2			# shift $t5 over 4 by multiplying by 4
 		add $t3, $t5, $s5		# add $tf to the base address for odd array, $s5
 		
 		lw $t6, 0($t3)		 # loads Odd[i] to $t6
 		
 		add $a0, $t6, $zero	
		addi $v0,$zero,1	# prints integer
		syscall
 		
 		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string4 	# load address of string to be printed into $a0    
      		syscall 
      		 
      		addi $t4, $t4, 1	# increment our counter $t4 by 1
 		j Check
 		     		
 EvenPrintStrt: addi $t4, $zero, 0	# Once done with all odd array, reset counter $t4 to 0
 		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string3 	# load address of string to be printed into $a0    
      		syscall  
 			
 EvenPrint:	beq $t4, $s4, End
 		addi $t5, $t4, 0
		sll $t5, $t5, 2	
 		add $t3, $t5, $s6
 		
 		lw $t6, 0($t3)		 # loads Even[i] to $t6
 		
 		add $a0, $t6, $zero	
		addi $v0,$zero,1	# prints integer
		syscall
 		
 		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string4 	# load address of string to be printed into $a0    
      		syscall  
      		addi $t4, $t4, 1
 		j EvenPrint
 		     		
End:		# exit	
		addi $v0, $zero, 10
		syscall 

		

