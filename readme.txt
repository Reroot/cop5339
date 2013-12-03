Shopping Cart Application

Instructions:

	1) To compile the application:
		- From the command line in project\src folder, enter the following:
			javac shoppingCart\system\CartSystem.java

	2) To run the application
		- From the command line in project folder, enter the following:
			java -cp .\src shoppingCart.system.CartSystem
			(or run ShoppingCart.bat to from project folder to run jar file)
		- To login as the seller, use the username "Newman" and password "newman"
		- To login as a customer, use username "Seth" and password "seth"

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
      bin
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
   
   4) When the seller updates a product, if the new quantity is greater
      than the old quantity, costs will increase. Costs will not change
      if the new quantity is lower, the same, or the product is deleted.
      This is the intended behavior.
