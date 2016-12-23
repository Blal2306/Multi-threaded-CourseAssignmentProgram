CS542 Design Patterns
Fall 2016
PROJECT 2 README FILE

PURPOSE:

	To create a multi-threaded application that assigns courses
	to students while keeping the preference score minimum.
	
PERCENT COMPLETE:

	100 %

To CLEAN:

	ant -buildfile src/build.xml clean

TO COMPILE: 

	ant -buildfile src/build.xml all

TO RUN: 

	ant -buildfile src/build.xml run -Darg0=firstarg -Darg1=2nd argument
	-Darg2=3rd argument -Darg3=4 argument

	First Argument = input file from which to read the course requests
	Second Argument = output file where the final output will be written
	Third Argument = Number of threads to use (Must be from 1 to 3)
	Fourth Argument = Debug value (Must be from 0 to 4)
		0 = Will print the average preference score
		1 = Will print the final output on the console
		2 = Will print the thread # and the entry being added to results
		3 = Prints every time the run method is called for a thread
		4 = Prints every time a constructor is called

CHOICE OF DATA STRUCTURE:
	
	The data structure used is a treeMap because the OS can schedule the threads
	to run in any order. However, to keep the order of course assignment
	same as the input file, the assigned courses are inserted in the treeMap.
	
TO MAKE A TARBALL:

	ant -buildfile src/build.xml tarzip
	
TO UNTAR:
	
	tar -xvf blal_zafar_assign2.tar.gz 


