package controllers;


import java.util.Scanner;

import models.Member;

/**
 * this the menu controller class also known as the dirver 
 * @author manziwit
 *
 */
public class MenuController {
	private Scanner input;
	private Gym gym;
	
	
	/**
	 * this is the constructor of the menu controller which has been created in the main 
	 * it runs from here
	 */
	public MenuController() {
		input = new Scanner(System.in);
	
	System.out.println("Please enter the Gym.....");
	System.out.print("\t" + "Name : ");
	String gymName = input.nextLine();
	
	System.out.print("\t" + "Manager Name : ");
	String gymManager = input.nextLine();
	
	System.out.print("\t" + "Phone number : ");
	String phoneNumber = input.next();
	gym = new Gym(gymName, gymManager ,phoneNumber);
	
	runMenu();
	}
	
	/**
	 * this is the main method which runs the app
	 * @param args
	 */
	public static void main(String[] args) {
		MenuController app = new MenuController();
	}
	
	
	/**
	 * gymMenu method displays the menu that the user has to choose from.
	 * @return it return the index or number of the menu selected by the user
	 */
	
	private int gymMenu() {
		System.out.println("Gym Menu");
		System.out.println("----------");
		System.out.println(" 1)  Add a Member");
		System.out.println(" 2)  List all Member");
		System.out.println(" 3)  Remove a Member (by index)");
		System.out.println(" 4)  Number of Members in the gym");
		System.out.println("-------------");
		System.out.println(" 5)  List gym details ");
		System.out.println(" 6)  list Members with ideal starting weight");
		System.out.println(" 7)  list Members with specific BMI category");
		System.out.println(" 8)  list all members stats imperatically and metrically");
		System.out.println("-------------");
		System.out.println(" 9)  Save to XML");
		System.out.println(" 10)  load from XNL");
		System.out.println(" 0)  Exit");
		int option = validNextInt("==>>");
		return option;
		
	}

	
	/**
	 * runMenu method - this is the method that control the loop.
	 */
	
	private void runMenu() {
		int option = gymMenu();
		while(option !=0) {
			switch (option) {
			case 1: 	addMember();
							break;
			case 2:		System.out.println(gym.listMembers());
							break;
			case 3:		System.out.println("Enter member index to delete");
						int index = input.nextInt();
						if(index > (gym.numberOfMembers()-1) || index < 0)
							System.out.println("There is no member to this index");
						else {
							System.out.println("Member deleted.");
							gym.remove(index);
						}
						break;
			case 4:  	System.out.println("Number of members:" + gym.numberOfMembers());
							break;
			case 5:		System.out.println(gym.toString());
							break;
			case 6:		System.out.println(gym.listMembersWithIdealWeight());
							break;
			case 7:		input.nextLine();
						System.out.println("please enter the category to search by:");
			            String category = input.nextLine();
						System.out.println(gym.listBySpecificBMICategory(category.toUpperCase()));
							break;
			case 8:		System.out.println(gym.listMemberDetailsImperialAndMetric());
							break;
			case 9:		try {
							gym.save();
						} catch (Exception e) {
							System.out.println("Error writing to file: " + e);							
						}
							System.out.println("Saving Member details....");
							System.out.println("Save Complete");
						break;
			case 10:	try {
							gym.load();
						} catch (Exception e) {
							System.out.println("Error Reading from file: " + e);
						}
							System.out.println("Loading Member details....");
							System.out.println("Load Complete");
			
			
			}
			System.out.println("\n Press any key to continue ...");
			option = gymMenu();
			}
	}
	/**
	 * method for adding a member to the gym
	 * returns type is void because it just dislays the details typed
	 */
	public void addMember() {
    	System.out.print("Please enter the following member details...\n");
    	System.out.print("\tId (between 100001 and 999999): ");
    	int id = validNextInt(" ");
    	System.out.print("\tName (max 30 chars): ");
    	String name = input.nextLine();
    	name = input.nextLine();
    	System.out.print("\tAddress: ");
    	String address = input.nextLine();
    	System.out.print("\tHeight (between 1 and 3 metres): ");
    	double height = validNextDouble("");
    	System.out.print("\tStarting Weight (between 35kg and 250kg): ");
    	double weight = validNextDouble("");
    	System.out.print("\tGender (M/F): ");
    	String gender = input.nextLine();
    	gender = input.nextLine();
    	gym.add(new Member(id, name, address, height, weight, gender.toUpperCase() ));
	}
	
	
	/**
	 * the methods below are for the exception and error handling on the integer and double
	 * @param prompt
	 * @return return a string to the user telling him what to type in if the he initially typed in 
	 * a wrong type of data 
	 */
	private int validNextInt(String prompt) {
		//@SuppressWarnings("resource")
		//Scanner input = new Scanner (System.in);
		do {
			try {
				System.out.print(prompt);
				return input.nextInt();
			}
			catch (Exception e) { 
				input.nextLine(); //swallows the buffer contents
				System.err.print("\tEnter a number please.");
			}
		}  while (true);

	}

	private double validNextDouble(String prompt) {
		//@SuppressWarnings("resource")
		//Scanner input = new Scanner (System.in);
		do {
			try {
				System.out.print(prompt);
				return input.nextDouble();
			}
			catch (Exception e) { 
				input.nextLine(); //swallows the buffer contents
				System.err.print("\tEnter a decimal number please.");
			}
		}  while (true);

	}
}
   
	
	

