/**
 * Subclass of user representing a Faculty Member
 * 
 * @author Carson Komes
 * 
 */
import java.util.Scanner;

public class Faculty extends User {

	private int maxItems = 12;
	
	/**
	 * Creates new faculty object
	 * @param name: string representing name
	 * @param id: identification number
	 */
	public Faculty(String name, int id){
		this.name = name;
		this.registrationNum = id;
	}
	
	/**
	 * prints information about this faculty member
	 */
	public void printInfo(){
		System.out.println(name + " " + registrationNum + " Faculty");
	}

}