/**
 * Student: Subclass of User representing Students
 * 
 * @author Carson Komes
 * 
 */
import java.util.Scanner;

public class Student extends User{

	private int maxItems = 6;
	
	public Student(String name, int id){
		this.name = name;
		this.registrationNum = id;
	}
	
	public void printInfo(){
		System.out.println(name + " " + registrationNum + " Student");
	}

}