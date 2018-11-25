package controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Member;

/**
 * gym class deals with member in the gym
 * @author manziwit
 * @version 2.0
 *
 */
public class Gym {
	 private String gymName;
	 private String managerName;
	 private String phoneNumber;
	 private ArrayList<Member> members;
	
	 /**
	  * constructor for the gym
	  * @param gymName stores the gym name
	  * @param managerName stores the manager name
	  */
	 public Gym(String gymName, String managerName) {
		 	members = new ArrayList<Member>();
	        if(gymName.length() <= 50)
				this.gymName = gymName;
			else
				this.gymName = gymName.substring(0, 30);
	        this.managerName = managerName;
	    }
	 
	 /**
	  * constructor for the gym details
	  * @param gymName
	  * @param managerName
	  * @param phoneNumber
	  */
	 public Gym(String gymName, String managerName, String phoneNumber) {
	        members = new ArrayList<Member>();
	        if(gymName.length() <= 30){
				this.gymName = gymName;
	        }else{
				this.gymName = gymName.substring(0, 30);
	        }
	    
	        this.managerName = managerName;
	        if(isNumeric(phoneNumber)){
		  		this.phoneNumber = phoneNumber;
	        }else{
		  		this.phoneNumber = "unknown";
	        } 
	 }
	 
	 public static boolean isNumeric(String str) {
		   try {  
		     double l = Double.parseDouble(str);  
		   }  
		   catch(NumberFormatException nfe) {
		     return false;  
		   }  
		   return true;  
		 }
		   
	    
	  /**
	   * ArrayList method to add member
	   * @param member
	   */
	 public void add (Member member) {
	        members.add (member); 
	    }
	 
	  /**
	   * 
	   * @param it passes in the category as an argument
	   * @return
	   */
	  public String listBySpecificBMICategory(String category) {
		  	if(members.size() == 0)
		  		return "There are no members in the gym";
		  	String listOfMembers = "";
	        int index = 0;
	        for (Member member: members) {
	        		if(member.determineBMICategory().contains(category)) {
	        			listOfMembers = listOfMembers + index + ": " + member.toString() + "\n";
	        			index++;
	        			}
	        }
	        if(listOfMembers == "")
	        		return "No members in this BMI category";
	        else
	        		return listOfMembers;
	    }
	  
	  
	  public String listMemberDetailsImperialAndMetric() {
		  	String str = "";
		  
		  	if(members.size() == 0)
		  		return "There are no members in the gym";
	        
	        for (Member member : members) {  
	                str += member.getMemberName() + ":\t" + member.getStartingWeight() + " kg (" + member.convertWeightKGtoPounds() + " lbs) \t" 
	                							+ member.getHeight() + " metres (" + member.convertHeightMetresToInches() + " inches)." + "\n";   
	        }
	        return str;
	    }
	  
	  /**
	   * this methods prints out the list of members
	   * @return it returns the list of member and the index
	   */
	  public String listMembers() {
	        String listOfMembers = "";
	        int index = 0;
	        for (Member member: members) {
	            listOfMembers = listOfMembers + index + ": " + member.toString() + "\n";
	            index++;
	        }
	        if (listOfMembers.equals("")) {
	            return "There are no members in the gym";
	        }
	        else {
	            return listOfMembers;
	        }
	    }
	  
	  public String listMembersWithIdealWeight() {
		  	String str = "";
		  
		  	if(members.size() == 0)
		  		return "There are no members in the gym";
	        
	        for (Member member : members) {
	           if (member.isIdealBodyWeight())            
	                str += listMembers();   
	        }

	        if (str.equals("")) {
	            return "There are no members in the gym with an ideal weight.";
	        }
	        else 
	            return str;
	    }
	  
	  
	  /**
	   * this method will print out the total number of the members in the collection
	   * @return return integer of the total number of members in the collection
	   */
	  public int numberOfMembers() {
	        return members.size();
	    }
	  
	  /**
	   * this method removes a list of member according to the index selected
	   */
	  public void remove(int index) {
		  if(!(index > (numberOfMembers()-1) || index < 0))
		  members.remove(index);
	  	}
	
	 
	 /**
	  * getter for the gym name   
	  * @return the gym name
	  */
	 public String getGymName() {
	        return gymName;
	    }
	   /**
	    * getter for the gym manager
	    * @return the string manager name
	    */
	  public String getManagerName() {
	        return managerName;
	    }
	   
	  /**
	   * getter for the phone number
	   * @return the numeric phone number in form of string
	   */
	  public String getPhoneNumber() {
	        return phoneNumber;
	    }
	  /**
	   * setter for the gym name,it updates the gym name
	   * @param passes the gymName as an argument 
	   */
	  public void setGymName(String gymName) {
		  if(gymName.length() <= 30)
				this.gymName = gymName;
			else
				this.gymName = gymName.substring(0, 30);
	    }
	    /**
	     * setter for the manager name,it updates the manager name
	     * @param passes the managerName as an argument 
	     */
	  public void setManagerName(String managerName) {
	        this.managerName = managerName;
	    }
	    
	  
	  /**
	   * setter for the phone number,updates the numeric phone number
	   * @param passes the phoneNumber as an argument
	   */
	  public void setPhoneNumber(String phoneNumber) {
		  if(isNumeric(phoneNumber))
		  		this.phoneNumber = phoneNumber;
		  	else
		  		this.phoneNumber = phoneNumber.replaceAll("\\D+","");
	    }
	  
	  /**
	   * this is the toString which prints out the human readable form of the list
	   */
	  public String toString() {
	        return "Gym Name: " + gymName
	             + ", Manager: " + managerName
	             + ", Phone Number: " + phoneNumber
	             + ". \n\nList of members in the gym:\n"
	             + listMembers();
	    }
	  
	 
	  
	  @SuppressWarnings("unchecked")
	    public void load()
	    		throws Exception{
	        XStream xstream = new XStream(new DomDriver());
	        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("members.xml"));
	        members = (ArrayList<Member>) is.readObject();
	        is.close();
	    }
	    
	    public void save() 
	    		throws Exception{
	        XStream xstream = new XStream(new DomDriver());
	        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("members.xml"));
	        out.writeObject(members);
	        out.close();    
	    }

}
