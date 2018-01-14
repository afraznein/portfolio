.data
OddArray:  .space 100
EvenArray: .space 100

string1:  .asciiz "Input a Number:\n" 
string2:  .asciiz "\n Odd #'s:" 
string3:  .asciiz "\n Even #'s:" 
string4:  .asciiz ", "

.text
main:	
Loop:		lw $t0, 0($s0)
		addi $t1, $t1, -5
		srl $t1, $t1, 2
		beq $t1, $s5, Exit
		addi $s0, $s0, 4
		j Loop
		
		add $s0, $s0, $s0
					
 		     		
Exit:		# exit	
		addi $v0, $zero, 10
		syscall 

		

