# UAB EE333 - Engineering Programming with Objects
## Simulating an XY-Plotter with Filter Implementations in Java

This Java programette was submitted as P7 for Dr. David Greene's class at UAB, Spring 2018. 

#### Abstract from design document
>A software design problem was provided to the developer to demonstrate the complete process of an object-oriented software solution design. An X-Y Plotter system was to be designed and implemented that reads numerical data from Comma Separated Value (CSV) files and transforms the data using modular filter components into a graphical representation in the form of a Portable Network Graphics (PNG) file. The project was defined in full, examining constraints, goals, standards, and potential features. The chosen design was explained, including the use of Unified Modeling Language (UML) diagrams, and design alternatives were discussed. The process of finding supplemental information to assist implementation as well as debugging procedure was examined. The results of the design and implementation of the system were presented and discussed.

#### Project description
The project consisted of producing an object-oriented design and implementation of a program in the Java programming language that simulates an X-Y plotter. The requested program takes input in the form of a comma-separated table (.csv file format) where each column in the file is a data stream that can be modifed by filters the user chooses. Filters can be layered to take input from other filters, as well as output to the input of other filters. After filtering, two data streams are left, one mapping to the X-axis of the plot and the other mapping to the Y-axis of the plot. A Portable Network Graphics (.png) file is produced by the program displaying the plot resulting from the filtered input data.


Please refer to the [design document in the repo](cmcgarty-p7.pdf) for more detailed information, including sections describing the project definition, its constraints, goals, features, and applicable standards, sections on the design process including UML and state diagrams and different design decisions (with consideration of other options), and details on how the program works.
