/**
 * User: Class representing users.
 * 
 * @author Carson Komes, Caleb Smith
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class User {

	protected String name;
	protected int registrationNum;


	public void printInfo(){
		System.out.println(name + " " + registrationNum);
	}
	
	public String getName(){
		return name;
	}
	public int getNum(){
		return registrationNum;
	}
	
	/**
	 * Checks out a document for this user
	 * @param doc 
	 * @author Carson Komes
	 */
	public void createLoan(Document doc) {
		Loan i = new Loan(this, doc);
		doc.checkoutCopy();
		i.printInfo();
		LibDirectory.loans.add(i);
	}
	/**
	 * Returns a document this user has checked out
	 * @param l 
	 * @author Carson Komes
	 */
	public void returnLoan(Loan l){
		for(Document i : LibDirectory.documents){
			if(i instanceof Book){
				if(((Book) i).getISBN().equals(l.getIN())){
					i.returnCopy();
				}		
			}
			else if(i instanceof Journal){
				if(((Journal) i).getISSN().equals(l.getIN())){
					i.returnCopy();
				}
			}
			LibDirectory.loans.remove(l);
		}
	}
	
	/**
	 * User menu
	 * @param sc
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
			System.out.println("5. Logout");
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
			else if (menuChoice == 5) {
				System.out.println("Goodbye!");
			}
			else {
				System.out.println("Please enter a valid number.");
				menuChoice = 0;
			}
		
		}
		
	}
	
	/**
	 * menu for searching for documents
	 * @param sc
	 * @author Caleb Smith
	 */
	public static ArrayList<Document> searchDocument(Scanner sc){
		ArrayList<Document> matchingDocs = new ArrayList<Document>();
		System.out.println("1. Search by Title");
		System.out.println("2. Search by Author");
		System.out.println("3. Search by Publisher");
		System.out.println("4. Back to previous menu");
		while (!sc.hasNextInt()){
			System.out.println("Please choose a valid option.");
			sc.next();
		}
	
		int searchChoice = sc.nextInt();
		
		if (searchChoice == 1){
			System.out.println("Enter Document Title: ");
			Scanner scan = new Scanner(System.in);
			String title = scan.nextLine();
			matchingDocs.clear();
			matchingDocs.addAll(LibDirectory.searchTitle(title));
		}
		
		if (searchChoice == 2){
			System.out.println("Enter Document Author: ");
			Scanner scan = new Scanner(System.in);
			String author = scan.nextLine();
			matchingDocs.clear();
			matchingDocs.addAll(LibDirectory.searchAuthor(author));
		}
		
		if (searchChoice == 3){
			System.out.println("Enter Document Publisher: ");
			Scanner scan = new Scanner(System.in);
			String title = scan.nextLine();
			return LibDirectory.searchPublisher(title);
		}
		System.out.println("The following documents match your search: ");
		int docIndex = 0;
		for (Document i : matchingDocs) {
			docIndex++;
			System.out.println(docIndex + ". " +
					           i.getTitle() + " " +
					           i.getPublisher() + " " +
					           i.getPublicationDate());
		}
		
		return matchingDocs;
	}
	
	/**
	 * menu for borrowing documents
	 * @param sc
	 * @author Caleb Smith
	 */
	public void borrowDocument(Scanner sc){
		ArrayList<Document> matchingDocs = new ArrayList<Document>();
		System.out.println("You have chosen to borrow a document.");
		System.out.println("Please search for the document you would"
				           + " like to borrow.");
		matchingDocs.clear();
		matchingDocs.addAll(searchDocument(sc));
		
		System.out.println("Enter the number of the item you would " +
		                   "like to borrow or press 0 to cancel.");
		while (!sc.hasNextInt()){
			System.out.println("Please choose a valid option.");
			sc.next();
		}
		int docSelection = sc.nextInt();
		if (docSelection > 0) {
			createLoan(matchingDocs.get(docSelection-1));
		}
	}
	
	/**
	 * menu for displaying loans
	 * @param sc
	 * @author Caleb Smith
	 */
	public ArrayList<Loan> displayLoans(){
		ArrayList<Loan> borrowedDocs = new ArrayList<Loan>();
		borrowedDocs.clear();
		borrowedDocs.addAll(LibDirectory.searchLoans(registrationNum));
		System.out.println("You currently have the following documents borrowed: ");
		int docIndex = 1;
		for (Loan i : borrowedDocs){
			for (Document j : LibDirectory.searchIN(i.getIN())){
				System.out.println(docIndex + ". " + j.getTitle());
			}
			docIndex++;
			
		}
		return borrowedDocs;
	}
	
	/**
	 * menu for returning a document
	 * @param sc
	 * @author Caleb Smith
	 */
	public void returnDocument(Scanner sc){
		ArrayList<Loan> borrowedDocs = displayLoans();
		System.out.println("Enter the number of the document to return: ");
		while (!sc.hasNextInt()){
			System.out.println("Please choose a valid option.");
			sc.next();
		}
		int selectedLoan = sc.nextInt();
		returnLoan(borrowedDocs.get(selectedLoan-1));
		
	}

}