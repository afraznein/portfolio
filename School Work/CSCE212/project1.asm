# Name: Anthony Frazier
# Date: February, 2016
# Program Name: Project1
# Description: Edit Project1 to achieve the assigned outputs

.data
baseadd:  .word 34, 5, 11, -12, 60, -2, 14, 71, 13, -27

string1:  .asciiz "Index i [0~9]:\n" 
string2:  .asciiz "Index j [0~9]:\n" 
string3:  .asciiz "\n A[i]=" 
string4:  .asciiz "\n A[j]=" 
string5:  .asciiz "\n A[i]+A[j]=" 
string6:  .asciiz "\n A[i]-A[j]=" 


.text
main:	
		# Read input i to $s1
		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string1 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 5      # code for reading integer is 5 
   		syscall           	# call operating system
   		add $s1, $v0, $zero  	# i in $s1
 
 		# Read input j to $s2
		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string2 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
   		addi $v0, $zero, 5      # code for reading integer is 5 
   		syscall           	# call operating system
   		add $s2, $v0, $zero  	# i in $s1#  Write code here to read input j to $s2
		
   		#baseadd of the array to $s5
   		la $s5, baseadd    	
  		
   		add $t0, $s1, $s1	# Write code here to load A[i] to $s3
   		add $t0, $t0, $t0
   		add $t1, $t0, $s5	# After summing up (4 x i ), add that amount to base address so we can load the correct portion of array
   		lw $s3, 0($t1) 
   		# Print A[i] from $s3	
   		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string3 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 								   											   											
		add $a0, $s3, $zero	
		addi $v0,$zero,1	# prints integer
		syscall

		
		add $t0, $s2, $s2	# Write code here to load and print A[j]
   		add $t0, $t0, $t0
   		add $t1, $t0, $s5	# After summing up (4 x i ), add that amount to base address so we can load the correct portion of array
   		lw $s4, 0($t1) 
   		# Print A[j] from $s4	
   		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string4 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 								   											   											
		add $a0, $s4, $zero	
		addi $v0,$zero,1	# prints integer
		syscall	  
						
		# Write code here to compute and print A[i]+A[j]
		# $s3 is A[i] $s4 is A[j]
		add $t0, $s3, $s4	# add A[i] and A[j]
   		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string5 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 								   											   											
		add $a0, $t0, $zero	
		addi $v0,$zero,1	# prints integer
		syscall	  
			  

 		# Write code here to compute and print A[i]-A[j]
 		# $s3 is A[i] $s4 is A[j]
		sub $t0, $s3, $s4	# sub A[i] and A[j]
   		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string6 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 								   											   											
		add $a0, $t0, $zero	
		addi $v0,$zero,1	# prints integer
		syscall	  	  
			
		# exit	
		addi $v0, $zero, 10
		syscall 

		

