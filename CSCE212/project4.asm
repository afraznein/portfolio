.data
FPNum:  .word 0x0, 0xff800000, 0x7f800000 # Float-point numbers 0, -Infty and Infty

string1:  .asciiz "Input a Float-Point #:(0 indicates the end)\n" 
string2:  .asciiz "\n MAX:" 
string3:  .asciiz "\n MIN:"
string4:  .asciiz "\n SUM:"

.text
main:	
		la $t0, FPNum
		lwc1 $f10, ($t0) 	# $f10=0.0	
		lwc1 $f4, ($t0)		# SUM =0
		lwc1 $f5, 4($t0)	# MAX=-InftY
		lwc1 $f6, 8($t0)	# MIN=Infty
				
		addi $s1, $zero, 0	# FP number counts in array 
		
		sub.s $f9, $f9, $f9    # f9 - f9 = 0; set to f9
		# Input a number 
InputLoop:	addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string1 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 6      # code for reading FP number is 6 
   		syscall           	# call operating system  

		add.s $f1, $f0, $f10	# move input fp number to $f1
 		
 		
 		c.eq.s $f1, $f9		# if input is 0, then jump to exit
 		bc1t Exit
 		
 		add.s $f4, $f4, $f1	# Update sum = sum + input
 		
 		c.lt.s $f1, $f6		# If the input is less than the current min, jump to LessThan
 		bc1t LessThan
 		
 		add.s $f5, $f1, $f9 	# Else, set Max to input
 		j InputLoop
 		
 LessThan:	add.s $f6, $f1, $f9		
 		
 		# 2) Write MIPS code here to update SUM ($f4), MAX ($f5) and MIN ($f6)
 		
 			  		  		
	 	addi $s1, $s1, 1
   		j InputLoop

		# Print out the values of MAX, MIN, and SUM			
Exit:		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string2 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 2      # code for printing FP number is 2
		add.s $f12, $f5, $f10 
   		syscall           	# call operating system  
   		
 		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string3 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 2      # code for printing FP number is 2
		add.s $f12, $f6, $f10 
   		syscall           	# call operating system  
 
   		addi $v0, $zero, 4      # code for printing string is 4 
      		la $a0, string4 	# load address of string to be printed into $a0    
      		syscall         	# call operating system 
		addi $v0, $zero, 2      # code for printing FP number is 2
		add.s $f12, $f4, $f10 
   		syscall           	# call operating system  
  			  		
		addi $v0, $zero, 10
		syscall 
