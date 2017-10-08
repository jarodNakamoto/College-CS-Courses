.data
array: .space 80
prompt:.asciiz "Input Integer: "
small: .asciiz "Smallest value in the array: "
large: .asciiz "Largest value in the array: "
divis: .asciiz "Number of integers that are divisible by 4: "
newL:  .asciiz "\n"

.text
main: 	li $t0, 20
		la $t1,array
		
		#load array
loop:	la $a0,prompt
		li $v0,4 		#print prompt
		syscall
		li $v0,5 		#loads int to v0
		syscall
		sw $v0,0($t1)
		add $t0,$t0,-1
		add $t1,$t1,4
		bgtz $t0,loop
		
		#call smallestLargest
		la $a0,small
		li $v0,4
		syscall
		li $a0, 20
		la $a1,array
		jal smallestLargest
		move $a0,$v0 	#smallest in v0
		li $v0,1
		syscall
		la $a0,newL
		li $v0,4
		syscall
		la $a0,large
		li $v0,4
		syscall
		move $a0,$v1 	#largest in v1
		li $v0,1
		syscall
		la $a0,newL
		li $v0,4
		syscall
		
		#call divisible
		la $a0,divis
		li $v0,4
		syscall
		li $a0, 20
		la $a1,array
		jal divisible
		move $a0,$v1 	#count was in v1
		li $v0,1
		syscall
		la $a0,newL
		li $v0,4
		syscall
		
		li $v0, 10 		#stop
		syscall
		
smallestLargest: 		#returns smallest in v0 and largest in v1
loop2:
		b smallest
next:	add $a0,$a0,-1
		add $a1,$a1,4
		bgtz $a0,loop2
		
		jr $ra
		
smallest:
		lw $t0,($a1)  	#load int from array to t0
		bgt $t0,$v0,largest #skip if bigger than current
		move $v0,$t0 	#change v0 to new smallest 
		b largest
		
largest:
		lw $t0,($a1)  	#load int from array to t0
		blt $t0,$v1,next #skip if less than current
		move $v1,$t0  	#change v1 to new largest 
		b next
		
		
divisible:				#returns count in v1
		li $v1,0 		#count is 0 to start
loop3:
		lw $t0,($a1) 	#get int from array
		remu $t1,$t0,4 	#t1 = t0%4
		bgtz $t1,next2	#skip if not divisible by 4
		add $v1,$v1,1 	#increase count
		
next2:	add $a0,$a0,-1
		add $a1,$a1,4
		bgtz $a0,loop3
		
		jr $ra