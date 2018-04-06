/**
 * Console Main interface for the library system
 * 
 * @author Carson Komes, Caleb Smith
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		load();
		User currentUser = login(sc);
		currentUser.displayMenu(sc);
		save();
		sc.close();
	}
	
	public static User login(Scanner sc){
		User currentUser = null;
		currentUser = selectUser(currentUser, sc);
		System.out.println("Hello, " + currentUser.getName() + ".");
		return currentUser;
	}
	
	
	/**
	 *	load: Loads the data stored in the save files and loads it into the index
	 *  users.txt
	 *  books.txt
	 *  journals.txt
	 *  loans.txt
	 *  
	 *  @author Carson Komes
	 */
	public static void load(){
		try{
			readUserFile();
			readBookFile();
			readJournalFile();
			readLoanFile();
		}
		catch(FileNotFoundException e){
			System.out.println("Failed to Load: Please check if input files are avalible and properly formated.");
			System.exit(1);
		}

	}
	
	/**
	 *	save: Saves the data stored in the index to the save files
	 *  users.txt
	 *  books.txt
	 *  journals.txt
	 *  loans.txt
	 *  
	 *  @author Carson Komes
	 */	
	public static void save(){
		try{
			writeLoanFile();
			writeUserFile();
			writeBookFile();
			writeJournalFile();
		}
		catch(FileNotFoundException e){
			System.out.println("Failed to Save: Input files may have been overwriten..");
			System.exit(1);
		}
	}
	
	private static void readUserFile() throws FileNotFoundException{
		
		File users = new File("users.txt");
		Scanner userScanner = new Scanner(users);
		
		while(userScanner.hasNext()){
			String name = (userScanner.next() + " " + userScanner.next());
			int id = userScanner.nextInt();
			String status = userScanner.next();
			
			if(status.equals("student")){
				Student x = new Student(name,id);
				LibDirectory.addUser(x);
			}
			if(status.equals("faculty")){
				Faculty x = new Faculty(name,id);
				LibDirectory.addUser(x);
			}
			if(status.equals("librarian")){
				Librarian x = new Librarian(name,id);
				LibDirectory.addUser(x);
			}	
		}
		/*
		for(User i : Index.users){
			i.printInfo();
		}
		*/
		userScanner.close();
		
	}
	
	private static void readBookFile() throws FileNotFoundException{
		
		File books = new File("books.txt");
		Scanner bookScanner = new Scanner(books);
		
		while(bookScanner.hasNext()){
			String book = bookScanner.nextLine();
			String[] bookElms = book.split("\\:");
			Book x = new Book(bookElms);
			LibDirectory.addBook(x);
		}
		
		/*
		for(Document i : Index.documents){
			if(i instanceof Book){
				((Book) i).printInfo();
			}
		}
		*/
		bookScanner.close();
		
	}
	
	private static void readJournalFile() throws FileNotFoundException{
		
		File journals = new File("journals.txt");
		Scanner journalScanner = new Scanner(journals);
	
		while(journalScanner.hasNext()){
			String journal = journalScanner.nextLine();
			String[] journalElms = journal.split("\\:");
			Journal x = new Journal(journalElms);
			LibDirectory.addJournal(x);
		}
		
		/*
		for(Document i : Index.documents){
			if(i instanceof Journal){
				((Journal) i).printInfo();
				((Journal) i).printArticles();
			}
		}
		*/
		journalScanner.close();
		
	}
	
	private static void readLoanFile() throws FileNotFoundException{
		
		File loans = new File("loans.txt");
		Scanner loanScanner = new Scanner(loans);

		while(loanScanner.hasNext()){
			String loan = loanScanner.nextLine();
			String[] loanElms = loan.split("\\:");
			Loan x = new Loan(loanElms);
			LibDirectory.addLoan(x);
		}
		
		/*
		for(Loan i : Index.loans){
			i.printInfo();
		}
		*/
		loanScanner.close();
		
	}
	
	private static void writeJournalFile() throws FileNotFoundException{
		
		PrintWriter journalWriter = new PrintWriter("journals.txt");
		
		for(Document i: LibDirectory.documents){
			if(i instanceof Journal){
				journalWriter.print(i.getTitle() +":"+ i.getPublicationDate() +":"+ ((Journal) i).getISSN() +":"+ ((Journal) i).getVolume() +":"+ ((Journal) i).getNumber() +":"+i.getCopies()+":"+i.getCopiesAvalible() +":"+ i.getPublisher() +":");
				int k = 1;
				for(Article a: ((Journal) i).getArticles()){
					journalWriter.print(a.getTitle() + ",");
					int j = 1;
					for(String au: a.getAuthors()){
						journalWriter.print(au);
						if(j<a.getAuthors().length){
							journalWriter.print(",");
							j++;
						}
					}
					if(k < ((Journal) i).getArticles().size()){
						journalWriter.print("/");
						k++;
					}
				}
				journalWriter.println(":");
			}
		}
		
		journalWriter.close();
	}
	
	private static void writeBookFile() throws FileNotFoundException{
		
		PrintWriter bookWriter = new PrintWriter("books.txt");
		
		for(Document i: LibDirectory.documents){
			if(i instanceof Book){
				bookWriter.print(i.getTitle() + ":" + i.getPublisher() +":"+ i.getPublicationDate() +":"+((Book) i).getISBN() +":"+ i.getCopies() +":"+ i.getCopiesAvalible() +":");
				int l = 1;
				for(String a: ((Book) i).getAuthors()){
					bookWriter.print(a);
					if( l < ((Book) i).getAuthors().length){
						bookWriter.print(",");
						l++;
					}
				}
				bookWriter.println(":");
			}
		}
		
		bookWriter.close();
	}
	
	private static void writeUserFile() throws FileNotFoundException{
		
		PrintWriter userWriter = new PrintWriter("users.txt");
		
		for(User i: LibDirectory.users){
			userWriter.print(i.getName() + " " + i.getNum() + " ");
			if(i instanceof Student){
				userWriter.println("student");
			}
			if(i instanceof Faculty){
				userWriter.println("faculty");
			}
			if(i instanceof Librarian){
				userWriter.println("librarian");
			}
		}
		
		userWriter.close();
	}
	
	private static void writeLoanFile() throws FileNotFoundException{
		
		PrintWriter loanWriter = new PrintWriter("loans.txt");
		
		for(Loan i: LibDirectory.loans){
			loanWriter.println(i.getUserNum() + ":" + i.getIN() + ":" + i.getDueDate() + ":");
		}
		
		loanWriter.close();
	}
	
	/**
	 * Method for selecting a user
	 * 
	 * @author Carson Komes
	 * @param U, user object
	 * @param sc, scanner
	 * @return
	 */
	public static User selectUser(User U, Scanner sc){
		System.out.println("Enter user ID:");
		//for(User i: Index.users){
		//	i.printInfo();
		//}
		int un = 0;
		do {
			while (un == 0){
				try{
					un = sc.nextInt();
				}
				catch(InputMismatchException i){
					System.out.println("Invalid Input");
					System.out.println("Please enter a valid user ID");
					sc.next();
					//return U;	
				}
			}
		
			for(User i: LibDirectory.users){
				if(i.getNum() == un){
					return i;
				}
			}
			System.out.println("Invalid choice");
			un = 0;
			System.out.println("Please enter a valid user ID");
		}while (un==0);
			
				return U;
		
		}

}


