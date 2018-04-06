Console.java contains the main method. 
When the program starts you will be prompted to provide a user id, each user has a unique id which can be seen in the users.txt file. Functionality of the program will differ depending on the type of user selected, Librarians will have additional functionality.



Files should be stored in the the same directory as this README and be in the following format:

users.txt:
	FirstName LastName Id Rank \n
	Firstname2 LastName2 Id2 Rank2 \n
	...
	
books.txt:
	Title: Publisher: DD/MM/YYYY: ISBN: #Copies: #CopiesAvalible: Author1,Author2,...,AuthorN: \n
	Title: Publisher: DD/MM/YYYY: ISBN: #Copies: #CopiesAvalible: Author1,Author2,...,AuthorN: \n
	...

journals.txt:
	Title: DD/MM/YYYY: ISSN: Volume: Number: #Copies: #CopiesAvalible: Article1,Author1-1,...,Author1-N/ Article2,Author2-1,...,Author2-N \n
	...

loans.txt
	id:ISBN/ISSN: DD/MM/YYYY: \n
	...