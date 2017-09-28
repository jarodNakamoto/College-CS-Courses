        .data
array:  .space 80

prompt: .asciiz "Input Integer: "
prompt2:.asciiz "Input Number Of Integers Per Line:"
newLine:.asciiz "\n"
aspace:	.asciiz " "



        .text
		.globl main
main:							#setup 
     li $t0, 20					#counter var to 20
	 la $a1, array              #load array address into $a1
	 
    #loading loop	 
loopl:
	 la $a0, prompt			    #load prompt into $a0
	 li $v0, 4                  #print ascii
	 syscall
	 li $v0,5					#load int
	 syscall
	 sw $v0, 0($a1)				#save from v0 to array
	 add $t0,$t0,-1             #decrement counter var
	 add $a1,$a1,4              #increment to next space in array
	 bgtz $t0,loopl				#if $t0 > 0, loop back to loopl

	 #printing setup by line loop

	 li $t0,20					#load counter var to 20
	 la $a1,array				#load array address to a1

	 #printing by line loop
looppl:	 
	 lw $a0,0($a1)				#load int from a1 to a0(from right to left)
	 li $v0,1					#print int
	 syscall
	 la $a0,newLine             #load newLine
	 li $v0,4					#print ascii
	 syscall
	 add $t0,$t0,-1				#decrement counter var
	 add $a1,$a1,4				#move to next spot in array
	 bgtz $t0,looppl			#loop back to looppl
	 
	 #add a newLine
	 la $a0,newLine             #load newLine
	 li $v0,4					#print ascii
	 syscall
	 
	 #printing setup by space loop

	 li $t0,20					#load counter var to 20
	 la $a1,array				#load array address to a1

	 #printing by space loop
loopsc:	 
	 lw $a0,0($a1)				#load int from a1 to a0(from right to left)
	 li $v0,1					#print int
	 syscall
	 la $a0,aspace              #load space
	 li $v0,4					#print ascii
	 syscall
	 add $t0,$t0,-1				#decrement counter var
	 add $a1,$a1,4				#move to next spot in array
	 bgtz $t0,loopsc			#loop back to loopsc
	 
	 #add a newLine
	 la $a0,newLine             #load newLine
	 li $v0,4					#print ascii
	 syscall
	 
	 #get an int and then ask again if less than 20
rqstInt:
     li $t0, 20					#counter var to 20
	 li $t1, 0                  #number of ints printed counter
	 la $a1, array              #load array address into $a1
	 
	 la $a0, prompt			    #load prompt into $a0
	 li $v0, 4                  #print ascii
	 syscall
	 li $v0,5					#load int
	 syscall   
	 move $t2,$v0				#save user input from v0 to holder var

	 
	 #inner loop that prints with spaces
	 #print ints with spaces
printSpcLoop:
	 lw $a0,0($a1)				#load int from a1 to a0(from right to left)
	 li $v0,1					#print int
	 syscall
	 la $a0,aspace              #load space
	 li $v0,4					#print ascii
	 syscall
	 add $t0,$t0,-1				#decrement counter var
	 add $a1,$a1,4				#move to next spot in array
	 add $t1,$t1,1				#increment number of ints printed counter
	 
	 blez $t0,newLineLoop		#if done with printing go to newLineLoop
	 blt $t1,$t2,printSpcLoop   #if $t1 counter var is less than user input
                                #don't make a new line	 
	 
	 	 
	 #print out ints and newline after done with printing with spaces
newLineLoop:	 
	 li $t1,0					#reset number of ints printed counter 
	 
	 #add a newLine
	 la $a0,newLine             #load newLine
	 li $v0,4					#print ascii
	 syscall
	 bgtz $t0,printSpcLoop		#loop back to spaceLoop if still need to print things