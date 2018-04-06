import java.util.ArrayList;

/**
 * Document: represents a document stored in the library
 * 
 * @author Carson Komes
 * 
 */

public class Document {

	protected String title;
	protected int copies;
	protected int copiesAvalible;
	protected String publicationDate;
	protected String publisher;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public int getCopiesAvalible() {
		return copiesAvalible;
	}
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * updates amount of copies available when checking out a document
	 * @return
	 */
	public boolean checkoutCopy(){
		if(copiesAvalible > 0){
			copiesAvalible--;
			return true;
		}
		return false;
			
	}
	/**
	 * updates amount of copies available when returning a document
	 */
	public void returnCopy(){
		copiesAvalible++;
	}


}