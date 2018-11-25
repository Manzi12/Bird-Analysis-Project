package utils;

import java.util.Scanner;

public class ScannerInput {
	@SuppressWarnings("resource")
	public static int validNextInt(String prompt) {
		Scanner input = new Scanner(System.in);
		do {
			try {
				System.out.print(prompt);
				return input.nextInt();
			}
			catch (Exception e){
				input.nextLine(); //swallows the buffers
				System.err.println("\t Enter a number please.");
			}
		}
		 
		while(true);
	}



public static double validNextDouble(String prompt) {
	Scanner input = new Scanner(System.in);
	do {
		try {
			System.out.print(prompt);
			return input.nextDouble();
		}
		catch (Exception e){
			input.nextLine();
			System.err.println("\t Enter a decimal number please.");
			
		}
	}while(true);
	
   }
}
