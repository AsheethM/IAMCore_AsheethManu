/**
 * 
 *
 */
package fr.epita.iam.launcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.services.JDBCIdentityDAO;

/**
 * @author tbrou
 *
 */
public class ConsoleLauncher {
	
	private static JDBCIdentityDAO dao;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, SQLException {
		System.out.println("Hello, welcome to - iAm - User Identity Management Application");
		Scanner scanner = new Scanner(System.in);
		dao = new JDBCIdentityDAO();
		
		
		
		//authentication
		System.out.println("Please enter your login: ");
		String login = scanner.nextLine();
		System.out.println("Please enter your password: ");
		String password = scanner.nextLine();
		
		if(!authenticate(login, password)){
			System.out.println("Invalid login or password. Please retry with the correct one.");
			scanner.close();
			return;
		}
		
		// menu
		String answer = menu(scanner);
		
		switch (answer) {
		case "a":
			// creation
			createIdentity(scanner);
			break;
		case "b":
			//modify
			modifyIdentity(scanner);
			break;
		case "c":
			//delete
			deleteIdentity(scanner);
			break;
		case "d":
			listIdentities();
			break;
		default:
			System.out.println("This option is not recognized ("+ answer + ")");
			break;
		}
		
		scanner.close();

	}
/**
 * Modifying the Identity
 * @param scanner
 */
	private static void modifyIdentity(Scanner scanner) {
		System.out.println("You've selected : Modify User Record");
		System.out.println("Please enter the current Email Address of the User: ");
		String email1 = scanner.nextLine();
		System.out.println("Please enter the current Name of the User: ");
		String displayname=scanner.nextLine();
		String id = null;
		try {
			id = dao.select(email1,displayname);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(id == null)
		{
			System.out.println("No such User exists. Please retry");
		scanner.close();
	}
		else
		{
		System.out.println("Please enter new Name for this User: ");
		String displayName=scanner.nextLine();
		System.out.println("Please enter new Email Address for this User: ");
		String email=scanner.nextLine();
		Identity newIdentity = new Identity(id, displayName,email);
		try {
			dao.modifyIdentity(newIdentity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You have succesfully updated this User :" + newIdentity);
		}
	}
	
	/**
	 * Deleting the Identity
	 * @param scanner
	 */
	
	private static void deleteIdentity(Scanner scanner) {
		System.out.println("You've selected : Delete User Record");
		System.out.println("Please enter the User's Email Address: ");
		String email1 = scanner.nextLine();
		System.out.println("Please enter the User's Name: ");
		String displayname=scanner.nextLine();
		String id = null;
		try {
			id = dao.select(email1,displayname);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(id == null)
		{
			System.out.println("No such User exists. Please retry");
		scanner.close();
	}
		else
		{
		Identity newIdentity = new Identity(id, displayname,email1);
		try {
			dao.deleteIdentity(newIdentity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You have succesfully deleted this User: " + newIdentity);
		}
	}


	/**
	 * Listing all the identities
	 * @throws SQLException 
	 * 
	 */
	private static void listIdentities() throws SQLException {
		System.out.println("This is the list of all the Users in the system");
		List<Identity> list = dao.readAll();
		int size = list.size();
		for(int i = 0; i < size; i++){
			System.out.println( i+ "." + list.get(i));
		}
		
	}

	/**
	 * Creating an Identity
	 * @param scanner
	 * @throws SQLException 
	 */
	private static void createIdentity(Scanner scanner) throws SQLException {
		System.out.println("You've selected : Create New User");
		System.out.println("Please enter the New User's Name: ");
		String displayName = scanner.nextLine();
		System.out.println("Please enter the New User's Email Address: ");
		String email = scanner.nextLine();
		Identity newIdentity = new Identity(null, displayName, email);
		dao.writeIdentity(newIdentity);
		System.out.println("You have succesfully created this User: " + newIdentity);
	}

	/**
	 * Switch Menu described below
	 * @param scanner
	 * @return
	 */
	private static String menu(Scanner scanner) {
		System.out.println("You're authenticated");
		System.out.println("Here are the actions you can perform :");
		System.out.println("a. Create New User");
		System.out.println("b. Modify User Record");
		System.out.println("c. Delete User Record");
		System.out.println("d. List all Users");
		System.out.println("your choice (a|b|c|d) ? : ");
		String answer = scanner.nextLine();
		return answer;
	}

	/**
	 * @param login
	 * @param password
	 */
	private static boolean authenticate(String login, String password) {

		// TODO replace this hardcoded check by the real authentication method
		return "asheeth".equals(login) && "asheeth".equals(password);
	}

}
