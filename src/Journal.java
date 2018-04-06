/**
 * Journal: Document subclass representing journals, contains a list of Articles
 * 
 * @author Carson Komes
 * 
 */
import java.util.ArrayList;

public class Journal extends Document {

	private int volume;
	private int number;
	private String ISSN;
	ArrayList<Article> articles = new ArrayList<Article>();

	/**
	 * Constructor for creating a journal from the saved file
	 * 
	 * @param journalElms (journal data as it is stored in the save file)
	 * 
	 * @author Carson Komes
	 */
	public Journal(String[] journalElms) {
		try{
			this.title = journalElms[0].trim();
			this.publicationDate = journalElms[1].trim();
			this.ISSN = journalElms[2].trim();
			this.volume = Integer.parseInt(journalElms[3].trim());
			this.number = Integer.parseInt(journalElms[4].trim());
			this.copies = Integer.parseInt(journalElms[5].trim());
			this.copiesAvalible = Integer.parseInt(journalElms[6].trim());
			this.publisher = journalElms[7];
		
			String tmp[] = journalElms[8].split("/"); 
			for(String i : tmp){
				articles.add(new Article(i));
			}
		}
		catch(NumberFormatException|NullPointerException|ArrayIndexOutOfBoundsException e){
			System.out.println("ERROR: Files may be inproperly formated or missing information");
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	/**
	 * Constructor for creating a new journal
	 * @param t - title
	 * @param p - publisher
	 * @param pd - publication date
	 * @param i - ISSN number
	 * @param v - volume
	 * @param n - number
	 * @param c - copies 
	 * 
	 * @author Carson Komes
	 */
	public Journal(String t, String p, String pd, String i,  int v, int n, int c) {
		try{
			this.title = t.trim();
			this.publisher = p;
			this.publicationDate = pd.trim();
			this.ISSN = i.trim();
			this.volume = v;
			this.number = n;
			this.copies = c;
			this.copiesAvalible = c;
			
		}
		catch(NumberFormatException|NullPointerException|ArrayIndexOutOfBoundsException e){
			System.out.println("ERROR: Files may be inproperly formated or missing information");
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	/**
	 * Prints the information of the Articles in this journal
	 */
	public void printArticles(){
		for(Article i : articles){
			i.printArticle();
		}
	}
	
	/**
	 * Prints the information of this Journal
	 */
	public void printInfo(){
		System.out.println(title + " " + publisher + " " + publicationDate + " " + volume + "" + number + "" + ISSN + " " + copies + " " + copiesAvalible);
	}
	
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getISSN(){
		return ISSN;
	}
	public void addArticle(Article x){
		articles.add(x);
	}
	public ArrayList<Article> getArticles(){
		return articles;
	}

}