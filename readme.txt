Shopping Cart Application

Instructions:

	1) To compile the application:
		- From the command line in project\src folder, enter the following:
			javac shoppingCart\system\CartSystem.java

	2) To run the application
		- From the command line in project folder, enter the following:
			java -cp .\src shoppingCart.system.CartSystem

	3) To create/restore initial database:
		- From the command line in project\src folder, enter the following:
			javac shoppingCart\system\CreateDatabase.java
		- From the command line in project folder, enter the following:
			java -cp .\src shoppingCart.system.CreateDatabase
			
	4) To run application from jar file:
		- From the command line in project folder, enter the following:
			java -jar bin\ShoppingCart.jar


Directory structure:

   project
      \
      src
          \
          shoppingCart.gui
          \
          shoppingCart.model
          \
          shoppingCart.system
          \
          shoppingCart.unitTests
      \
      database
      \
      docs
          \
          PDF files
          \
          UML diagrams
      \
      javadocs


Notes:

   1) database folder contains database files using Java Serialization

   2) PDF files folder contains PDF version of:
      - Application Requirements document
      - Source code
      - Screen mock-ups
      
   3) UML diagrams folder contains original Violet diagram files
