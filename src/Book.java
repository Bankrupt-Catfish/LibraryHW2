/**
 * Book: Subclass of Document representing a book
 * 
 * @author Carson Komes
 * 
 */
public class Book extends Document {

	private String ISBN;
	private String[] authors;
	
	/**
	 * Constructor for loading a book from the save files
	 * 
	 * @author Carson Komes
	 */
	public Book(String[] bookElms) {

		try{
			this.title = bookElms[0].trim();
			this.publisher = bookElms[1].trim();
			this.publicationDate = bookElms[2].trim();
			this.ISBN = bookElms[3].trim();
			this.copies = Integer.parseInt(bookElms[4].trim());
			this.copiesAvalible = Integer.parseInt(bookElms[5].trim());
			this.authors = bookElms[6].split(",");
		}
		catch(NumberFormatException|NullPointerException|ArrayIndexOutOfBoundsException e){
			System.out.println("ERROR: Files may be inproperly formated or missing information");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Constructor for creating a new book
	 * @param t - title
	 * @param p - publisher
	 * @param pd - publication date
	 * @param i - ISBN number
	 * @param c - copies
	 * @param a - authors (stored as single string separated by ','
	 * 
	 * @author Carson Komes
	 */
	public Book(String t, String p, String pd, String i, int c, String a){
		try{
			this.title = t.trim();
			this.publisher = p.trim();
			this.publicationDate = pd.trim();
			this.ISBN = i.trim();
			this.copies = c;
			this.copiesAvalible = c;
			this.authors = a.split(",");
		}
		catch(NumberFormatException|NullPointerException|ArrayIndexOutOfBoundsException e){
			System.out.println("ERROR: Files may be inproperly formated or missing information");
			e.printStackTrace();
			System.exit(1);
		}
	}
	/**
	 * Prints book info
	 * 
	 * @author Carson Komes
	 */
	public void printInfo(){
		System.out.println(title + " " + publisher + " " + publicationDate + " " + ISBN + " " + copies + " " + copiesAvalible);
		for(String i : authors){
			System.out.println(i.trim());
		}
	}
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

}