/**
 * Librarian: Subclass of User representing a Librarian, has more functionality than other Users
 * 
 * @author Carson Komes, Caleb Smith
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Librarian extends User {

	private int maxItems = 12;
	
	public Librarian(String name, int id){
		this.name = name;
		this.registrationNum = id;
	}
	
	public void printInfo(){
		System.out.println(name + " " + registrationNum + " Librarian");
	}
	
	/**
	 * displayMenu: User interface for a librarian
	 * 
	 * @author Caleb Smith
	 */
	public void displayMenu(Scanner sc){
		int menuChoice = 0;
		while (menuChoice == 0) {
			
			System.out.println("Please choose an option:");
			System.out.println("1. Borrow a document");
			System.out.println("2. Return a loaned document");
			System.out.println("3. Search for a document");
			System.out.println("4. See my current loans");
			System.out.println("5. See all current loans");
			System.out.println("6. Add a user");
			System.out.println("7. Add a document");
			System.out.println("8. Logout");
			while (!sc.hasNextInt()){
				System.out.println("Please choose a valid option.");
				sc.next();
			}
			menuChoice = sc.nextInt();
		
			if (menuChoice == 1){
				borrowDocument(sc);
				menuChoice = 0;
			}			
			else if (menuChoice == 2) {
				System.out.println("You have chosen to return a document.");
				System.out.println("Select a document to return: ");
				returnDocument(sc);
				menuChoice = 0;
			}
			else if (menuChoice == 3){
				searchDocument(sc);
				menuChoice = 0;
			}
			else if (menuChoice == 4){
				displayLoans();
				menuChoice = 0;
			}
			else if (menuChoice == 5){
				LibDirectory.printLoans();
				menuChoice = 0;
			}
			/*
			 * adding a user
			 */
			else if (menuChoice == 6){
				Scanner scan = new Scanner(System.in);
				System.out.println("Enter user's first and last name: ");
				String userName = scan.nextLine();
				int userNumber = LibDirectory.getUserLength()+1;
				int userType = 0;
				while (userType == 0){
					System.out.println("Enter user type: ");
					System.out.println("1. Student");
					System.out.println("2. Faculty");
					System.out.println("3. Librarian");
					userType = sc.nextInt();
					if (userType == 1){
						Student newUser = new Student(userName, userNumber);
						LibDirectory.addUser(newUser);
					}
					else if (userType == 2){
						Faculty newUser = new Faculty(userName, userNumber);
						LibDirectory.addUser(newUser);
					}
					else if (userType == 3){
						Librarian newUser = new Librarian(userName, userNumber);
						LibDirectory.addUser(newUser);
					}
					else {
						userType = 0;
					}
				}
			}
			/*
			 * adding a new document
			 */
			else if (menuChoice == 7){
				int docType = 0;
				while (docType == 0){
					System.out.println("Enter document type: ");
					System.out.println("1. Book");
					System.out.println("2. Journal");
					docType = sc.nextInt();
					if (docType == 1) {
						Scanner scan = new Scanner(System.in);
						System.out.println("Enter title: ");
						String t = scan.nextLine();
						System.out.println("Enter publisher: ");
						String p = scan.nextLine();
						System.out.println("Enter publication date: ");
						String d = scan.nextLine();
						System.out.println("Enter ISBN: ");
						String i = scan.nextLine();
						System.out.println("Enter number of copies: ");
						int c = scan.nextInt();
						System.out.println("Enter author. If more than one author, "
								           + "separate authors by comma: ");
						String a = scan.nextLine();
						Book newBook = new Book(t, p, d, i, c, a);	
					}
					/*
					 * adds new journal with no articles.
					 */
					else if (docType == 2) {
						Scanner scan = new Scanner(System.in);
						System.out.println("Enter title: ");
						String t = scan.nextLine();
						System.out.println("Enter publisher: ");
						String p = scan.nextLine();
						System.out.println("Enter publication date: ");
						String d = scan.nextLine();
						System.out.println("Enter ISSN: ");
						String i = scan.nextLine();
						System.out.println("Enter journal volume: ");
						int v = scan.nextInt();
						System.out.println("Enter journal number: ");
						int n = scan.nextInt();
						System.out.println("Enter number of copies: ");
						int c = scan.nextInt();
						Journal newJournal = new Journal(t, p, d, i, v, n, c);
					}
					else {
						docType = 0;
						System.out.println("You have entered an invalid number.");
					}
				}
			}
			/*
			 * logout
			 */
			else if (menuChoice == 8) {
				System.out.println("Goodbye!");
			}
			/*
			 * invalid choice
			 */
			else {
				System.out.println("Please enter a valid number.");
				menuChoice = 0;
			}
		
		}
		
	}
	

}