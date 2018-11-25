package models;

/**
 * this the member class which handle one member only
 * @author manziwit
 * @version 2.0
 */public class Member {
	private int memberId;
	private String memberName;
	private String memberAddress;
	private double height;
	private double startingWeight;
	private String gender;
	
	/**
	 * constructor with parameters below
	 * @param memberId
	 * @param memberName
	 * @param memberAddress
	 * @param height
	 * @param startingWeight
	 * @param gender
	 */
	public Member(int memberId,String memberName,String memberAddress,double height,double startingWeight,String gender) {
		this.memberAddress = memberAddress;
	
	if((memberId>=100000) && (memberId<= 999999)) {
		this.memberId= memberId;
	}else {
			this.memberId = 100000;
		}
	
	if(memberName.length()<=30) {
		this.memberName = memberName;
	} else {
		this.memberName = memberName.substring(0,30);
	}
	
	if ((height>=1) && (height<=3)) {
		this.height = height;
	}
	
	if((startingWeight>=35) && (startingWeight<=250)) {
		this.startingWeight= startingWeight;
	}
	
	if (gender.toUpperCase().equals("M") ) { 
		this.gender = "M";
	}else if(gender.toUpperCase().equals("F") ) { 
		this.gender = "F";
	}else {
		this.gender = "Unspecified";
	}
	
	}
	
	/**
	 * method to calculate the BMI
	 * @return
	 */
	public double calculateBMI() {
		double BMI;
		BMI = startingWeight / (height * height);
		return toTwoDecimalPlaces(BMI);
	}
	
	/**
	 * method to convert meters to inches
	 * @return
	 */
	public double convertHeightMetresToInches() {
		double meterConverter = 39.37;
		double inches = height * meterConverter;
		return toTwoDecimalPlaces(inches);
	}
	
	/**
	 * method to convert weight from kg to pounds
	 * @return
	 */
	public double convertWeightKGtoPounds() {
		double poundConverter = 2.2;
		double weight = startingWeight * poundConverter;
		return toTwoDecimalPlaces(weight);
	}
	
	/**
	 * method to determine the BMI category of a member
	 * @return
	 */
	public String determineBMICategory() {
		double BMI = calculateBMI();
		if (BMI <15) {
			return "(VERY SEVERELY UNDERWEIGHT)";
		}else if((BMI>= 15) && (BMI<16)){
			return "(SEVERELY UNDERWEIGHT)";
		}else if((BMI >= 16) && (BMI<18.5)) {
			return "(UNDERWEIGHT)";
		}else if((BMI >= 18.5) && (BMI<25)) {
			return "(NORMAL)";
		}else if((BMI >= 25) && (BMI<30)) {
			return ("OVERWEIGHT");
		}else if((BMI >= 30) && (BMI<35)) {
			return "(MODERATELY OBESE)";
		}else if((BMI >= 35) && (BMI<40)) {
			return "(SEVERELY OBESE)";	
		}else {
			return "(VERY SEVERELY OBESE)";
		}
	}

	/**
	 * method for determining the ideal starting weight for males and females
	 * @return true or false
	 */
	public boolean isIdealBodyWeight() {
    	double idealWeight;
        if(convertHeightMetresToInches() <= 60){
        	if(gender == "M")
        		idealWeight = 50;
        	else
        		idealWeight = 45.5;
        }else{
        	if(gender == "M")
        		idealWeight = 50 + ((convertHeightMetresToInches()-60)*2.3);
        		else
        		idealWeight = 45.5 + ((convertHeightMetresToInches()-60)*2.3);
        }

        if(idealWeight >= (startingWeight-2) && idealWeight <= (startingWeight+2))
        		return true;
        else 
        		return false;
    }
	
	/**
	 * method to change the double number to the two decimal places
	 * @param num
	 * @return
	 */
	private double toTwoDecimalPlaces(double num) {
		return(int)(num * 100)/100.0;
	}
	
	//getter//
	/**
	 * getter for height
	 * @return
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * getter for the member address
	 * @return
	 */
	public String getMemberAddress() {
		return memberAddress;
	}
	
	/**
	 * getter for the member gender
	 * @return
	 */
	public String getMemberGender() {
		return gender;
	}
	
	/**
	 * getter for the member id
	 * @return
	 */
	public int getMemberId() {
		return memberId;
	}
	
	/**
	 * getter for the member name
	 * @return
	 */
	public String getMemberName() {
		 return memberName;
	}
	
	/**
	 * getter for starting weight for the member
	 * @return
	 */
	public double getStartingWeight() {
		return startingWeight;
	}

	//setters//
	/**
	 * setter for updating the gender of the member
	 * @param gender
	 */
	public void setGender(String gender) {
		if((gender == "M") || (gender== "m")) {
			this.gender = "M";
		}else if ((gender == "F") || (gender == "f")) {
			this.gender = "F";
	}
	}
	
	/**
	 * setter for updating the height of the member
	 * @param height
	 */
	public void setHeight(double height) {
		if(height>=1 && height<=3)
		this.height = height;
	}
	
	/**
	 * setter for updating the member's address
	 * @param memberAddress
	 */
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	
	/**
	 * setter for updating the memberId
	 * @param memberId
	 */
	public void setMemberId(int memberId) {
		if((memberId>=100000) && (memberId<= 999999)) {
			this.memberId= memberId;
		}
	}
	
	/**
	 * setter for updating the member name
	 * @param memberName
	 */
	public void setMemberName(String memberName) {
		if(memberName.length()<=30) {
			this.memberName = memberName;
		} else {
			this.memberName = memberName.substring(0,30);
		}
	}
	
	/**
	 * setter for updating the starting weight of the member
	 * @param startingWeight
	 */
	public void setStartingWeight(double startingWeight) {
		if((startingWeight>=35) && (startingWeight<=250)) {
			this.startingWeight= startingWeight;
		}
	}
	
	// toString method//s
	/**
	 * a toString method that displays the output in a human readable way
	 */
	public String toString() {
		return "Member Id : "  +  memberId + ", " +
				"Name : "  +  memberName + ", " +
				"Address : "  + memberAddress + ", " +
				"Height : "  +  height + " " +
				"Starting Weight : "  +  startingWeight + ", " +
				"Gender :"  +  gender + ", " + 
				"BMI of " + calculateBMI() + " " +  determineBMICategory();
	}

}
