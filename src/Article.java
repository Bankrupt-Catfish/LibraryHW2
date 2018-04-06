/**
 * Class representing articles
 * 
 * @author Carson Komes
 * 
 */
import java.lang.reflect.Array;
import java.util.Arrays;

public class Article {

	private String title;
	private String[] authors;
	
	/**
	 * Constructor for article from file
	 * @param i string containing article information, format: (title,author,author,...)
	 * @author Carson Komes
	 */
	public Article(String i) {
		try{
			String tmp[] = i.split(",");
			title = tmp[0].trim();
			setAuthors(Arrays.copyOfRange(tmp, 1, tmp.length));
		}
		catch(NumberFormatException|NullPointerException|ArrayIndexOutOfBoundsException e){
			System.out.println("ERROR: Files may be inproperly formated or missing information");
			e.printStackTrace();
			System.exit(1);
		}
	}
	/**
	 * Constructor for new article
	 * @param t is title, a is authors
	 * @author Carson Komes
	 */
	public Article(String t, String[] a){
		title = t;
		authors = a;
		
	}
	public void printArticle() {
		System.out.print(title + ",");
		int j = 1;
		for(String i: authors){
			System.out.print(i);
			if( j < authors.length){
				System.out.print(",");
				j++;
			}
		}
		System.out.println();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	


}