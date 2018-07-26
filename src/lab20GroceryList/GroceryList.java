package lab20GroceryList;

//created by Brian Seyferth on 7/25/18

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GroceryList {

	public static void main(String[] args) {
		
		//Open the scanner.
		Scanner scnr = new Scanner(System.in);
		
		//Declare the variables.
		String restartApp = "Y";
		String userInput;
		ArrayList <String> itemOrdered = new ArrayList<>();
		ArrayList <Double> priceOfItem = new ArrayList<>();
		Map <String, Double> menu = new HashMap<>(); 
		String avg;
		String min;
		String max;
		
		//Input items and prices into the HashMap.
		menu.put ("baseball", 3.50);
		menu.put ("basketball", 19.99);
		menu.put ("football", 19.99);
		menu.put ("bowling ball", 49.99);
		menu.put ("soccer ball", 19.99);
		menu.put ("golf ball", 0.99);
		menu.put ("cricket ball", 4.99);
		menu.put ("lacrosse ball", 5.00);
		
		//Initial while loop sets up the restart program question.
		while (restartApp.equals("y") || restartApp.equals("Y")) {
			
			//These lines make sure the ArrayLists are clear for new entries
			//in case the user decides to rerun the program.
			itemOrdered.clear();
			priceOfItem.clear();
			
			//This will print out a welcome and the options for the grocery store.
			System.out.println("Welcome to Seyferth's Sports Store!");
			System.out.println("Item\t\t\tPrice");
			System.out.println("===============================");
			for (Map.Entry<String, Double> entry : menu.entrySet()) {
				System.out.println(entry.getKey() + "\t\t$" + String.format( "%.2f" , entry.getValue()));
			}
			
			//This loop starts collecting user inputs, and continues doing so until
			//the user has a blank entry.
			do {
				System.out.println("Enter an item to add to your list:");
				System.out.println("(Enter nothing to end list, \"menu\" to see the menu again, or \"list\" to see your current list)");
				userInput = scnr.nextLine();
				
				//This if statement builds a menu option in to redisplay the menu
				if ( userInput.equals("menu")) {
					System.out.println("Item\t\t\tPrice");
					System.out.println("===============================");
					for (Map.Entry<String, Double> entry : menu.entrySet()) {
						System.out.println(entry.getKey() + "\t\t$" + String.format( "%.2f" , entry.getValue()));
					}
					
				//This else if statement sets up a list option to show what you have input.	
				} else if (userInput.equals("list")) {
					System.out.println("Here's what you got:");
					System.out.println("Item\t\t\t\tPrice");
					System.out.println("=======================================");
					for(int i = 0 ; i < itemOrdered.size(); i++) {
						System.out.println((i+1) + ". " + itemOrdered.get(i) + "\t\t\t$" + String.format( "%.2f" , priceOfItem.get(i)));
					}
						//Methods to get avg, min, and max. Then they print the results.
						avg = getAverage(priceOfItem);
						min = getMin(priceOfItem, itemOrdered);
						max = getMax(priceOfItem, itemOrdered);
						System.out.println("You have " + priceOfItem.size() + " items on your list.");
						System.out.println("Average price per item is: $" + avg);
						System.out.println(min);
						System.out.println(max);
						
				} else if (!userInput.isEmpty() && menu.containsKey(userInput)) {
					System.out.println("Adding " + userInput + " to cart at $" + String.format( "%.2f", menu.get(userInput)));
					itemOrdered.add(userInput);
					priceOfItem.add(menu.get(userInput));
					//need to add the price to the price list
					
					
				} else if (!userInput.isEmpty() && !menu.containsKey(userInput)) {
					System.out.println("That item is not on the list.");
				}
			
			} while (!userInput.isEmpty());
			
			//This statement prints the final list
			System.out.println("Here's what you got:");
			System.out.println("Item\t\t\t\tPrice");
			System.out.println("=======================================");
			for(int i = 0 ; i < itemOrdered.size(); i++) {
			System.out.println((i+1) + ". " + itemOrdered.get(i) + "\t\t\t$" + String.format( "%.2f" , priceOfItem.get(i)));
			}
			//Methods to get avg, min, and max. Then they print the results.
			avg = getAverage(priceOfItem);
			min = getMin(priceOfItem, itemOrdered);
			max = getMax(priceOfItem, itemOrdered);
			System.out.println("You have " + priceOfItem.size() + " items on your list.");
			System.out.println("Average price per item is: $" + avg);
			System.out.println(min);
			System.out.println(max);
			
			//Option to run the question again.
			System.out.println("Would you like to run the program again? \"y\" to continue");
			restartApp = scnr.nextLine();
		}
		
		//Exit message for when you leave program
		System.out.println("Goodbye!");
		scnr.close();
	}

	//Average method calculates average.
	public static String getAverage(ArrayList <Double> list) {
		double sum = 0;
		String avg = "0";
		for (int i = 0; i < list.size(); i++) {
			sum = sum + list.get(i);
		}
		avg =String.format( "%.2f" , (sum / list.size()));
		return avg;
	}
	
	//Max method calculates max, and addresses issue if you print list 
	//or leave program with no items.
	public static String getMax(ArrayList <Double> list, ArrayList <String> label) {
		String max = "0";
		String maxSen = "";
		String y = "0";
		try {
			double x = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) > x) {
					x = list.get(i);
					y = label.get(i);
				}
			}	
			max =String.format( "%.2f" , x);
			maxSen = "The highest price item is " + y + "and the price is: $" + max;
		} catch (IndexOutOfBoundsException ex) {
		maxSen = "You have no items so there is no maximum.";	
		}
			return maxSen;
		}
		
	//Min method calculates min, and addresses issue if you print list 
	//or leave program with no items.
	public static String getMin(ArrayList <Double> list, ArrayList <String> label) {
		String min = "0";
		String minSen = "";
		String y = "0";
		try {
			double x = list.get(0);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) < x) {
					x = list.get(i);
					y = label.get(i);
				}
			}	
			min =String.format( "%.2f" , x);
			minSen = "The lowest price item is " + y + "and the price is: $" + min;
		} catch (IndexOutOfBoundsException ex) {
		minSen = "You have no items so there is no minimum.";	
		}
			return minSen;
		}
}
