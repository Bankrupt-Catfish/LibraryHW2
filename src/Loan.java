/**
 * Loan: Class that represents a loan of a Document to a User
 * 
 * @author Carson Komes
 * 
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Loan {

	private String dueDate;
	private int userNumber;
	public String IN;
	
	/**
	 * Constructor for creating a loan from the save file
	 * 
	 * @param loanElms (loan data as it is stored in the save file)
	 * 
	 * @author Carson Komes
	 */
	public Loan(String[] loanElms){
		
		try{
			userNumber = Integer.parseInt(loanElms[0].trim());
			IN = loanElms[1];
			dueDate = loanElms[2];
		}
		catch(NumberFormatException|NullPointerException|ArrayIndexOutOfBoundsException e){
			System.out.println("ERROR: Files may be inproperly formated or missing information");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Constructor for creating a new loan from user input
	 * 
	 * @param loanElms (loan data as it is stored in a file)
	 * 
	 * @author Carson Komes
	 */
	public Loan(User u, Document d){
		userNumber = u.registrationNum;
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = new GregorianCalendar();
		
		if(u instanceof Student){
			if(d instanceof Journal){
				calendar.add(Calendar.DATE, 10);
			}
			else{
				calendar.add(Calendar.DATE, 30);
			}
		}
		else{
			if(d instanceof Journal){
				calendar.add(Calendar.DATE, 10);
			}
			else{
				calendar.add(Calendar.DATE, 60);
			}
		}
		
		dueDate = format.format(calendar.getTime());
		
		if(d instanceof Book){
			IN = ((Book) d).getISBN();
		}
		if(d instanceof Journal){
			IN = ((Journal) d).getISSN();
		}
	}

	public String getDueDate() {
		return this.dueDate;
	}

	public void printInfo() {
		System.out.println(userNumber + " " + IN + " " + dueDate);		
	}

	public int getUserNum() {
		return userNumber;
	}

	public String getIN() {
		return IN;
	}

}