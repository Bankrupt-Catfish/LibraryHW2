/**
 * Index, Stores library data
 * 
 * @author Carson Komes, Caleb Smith
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class LibDirectory {

	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<Document> documents = new ArrayList<Document>();
	static ArrayList<Loan> loans = new ArrayList<Loan>();
	
	public static void addUser(User u){
		users.add(u);
	}
	public static void addBook(Book b){
		documents.add(b);
	}
	public static void addJournal(Journal j) {
		documents.add(j);
	}
	public static void addLoan(Loan l) {
		loans.add(l);
	}
	public static void removeUser(User u){
		users.remove(u);
	}
	public static void removeDocument(Document d){
		documents.remove(d);
	}

	
	/**
	 * searchTitle: Searches for a documents matching the title given
	 * @param t - title
	 * @return
	 * 
	 * @author Caleb Smith
	 */
	public static ArrayList<Document> searchTitle(String t) {
		ArrayList<Document> matchingDocs = new ArrayList<Document>();
		for (Document i : documents){
			if (i.getTitle().equals(t)){
				matchingDocs.add(i);
			}
		}
		return matchingDocs;
	}

	/**
	 * searchAuthor: Searches for a documents with authors matching the name given
	 * @param a - author
	 * @return
	 * 
	 * @author Caleb Smith
	 */
	public static ArrayList<Document> searchAuthor(String a) {
		System.out.println(a);
		ArrayList<Document> matchingDocs = new ArrayList<Document>();
		for (Document i : documents) {
			if (i instanceof Book) {
				String[] authors = ((Book) i).getAuthors();
				for (String j : authors) {
					if (j.equals(a)) {
						matchingDocs.add(i);
					}
				}
			}
			else if (i instanceof Journal) {
				ArrayList<Article> articles = new ArrayList<Article>();
				for (Article k : articles) {
					String[] authors = k.getAuthors();
					for (String l : authors) {
						if (l.equals(a)) {
							matchingDocs.add(i);
						}
					}
				}
			}
		}
		return matchingDocs;
	}
	
	/**
	 * searchIN: searches for documents with ISBN or ISSN numbers matching one given
	 * @param num 
	 * @return
	 * 
	 * @author Caleb Smith
	 */
	public static ArrayList<Document> searchIN(String num) {
		ArrayList<Document> matchingDocs = new ArrayList<Document>();
		for (Document i : documents){
			if (i instanceof Book){
				if (((Book) i).getISBN().equals(num)){
					matchingDocs.add(i);
				}
			}
			if (i instanceof Journal){
				if (((Journal) i).getISSN().equals(num)){
					matchingDocs.add(i);
				}
			}
		}
		return matchingDocs;
	}
	
	/**
	 * searchPublisher: searches for documents published by the name given
	 * @param p
	 * @return
	 * 
	 * @author Caleb Smith
	 */
	public static ArrayList<Document> searchPublisher(String p) {
		ArrayList<Document> matchingDocs = new ArrayList<Document>();
		for (Document i : documents){
			if (i.getPublisher().equals(p)){
				matchingDocs.add(i);
			}
		}
		return matchingDocs;
	}
	/**
	 * searchLoans: searches for loans made to the user given
	 * @param user
	 * @return
	 * 
	 * @author Caleb Smith
	 */
	public static ArrayList<Loan> searchLoans(int user) {
		ArrayList<Loan> userLoans = new ArrayList<Loan>();
		for (Loan i : loans){
			if (i.getUserNum() == user){
				userLoans.add(i);
			}
		}
		return userLoans;
	}
	
	public static void printLoans() {
		for (Loan i : loans) {
			i.printInfo();
		}
	}
	
	public static int getLoansLength() {
		return loans.size();
	}
	public static int getUserLength() {
		return users.size();
	}
}