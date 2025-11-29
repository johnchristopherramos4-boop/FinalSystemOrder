package com.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main{
   public static void main(String args[]){
      
      Scanner scanner = new Scanner(System.in);
      
      String[] items = {"McShare Bundle for 3 Person", "McShare Bundle for 4 Person", "McSpaghetti Platter - Classic Meal", "20 Piece - Chicken McNuggets", "Snack Burger McShare Meal - Popular", "BFF Fries N' McFloat Combo", "Double Big Mac Meal w/ Drinks & Fries", "The BCB Meal Box (Beef Chicken Beef)", "Mega Meal – Chicken McDo w/ Fries & McFlurry", "1-pc. Chicken McDo Happy Meal", "4-pc. Chicken McNuggets Happy Meal", "McSpaghetti Happy Meal with Fries and juice", "Burger McDo Happy Meal with Fries and Juice"};
      double[] prices = {612.00, 842.00, 259.00, 350.00, 380.00, 282.00, 331.00, 264.00, 223.00, 185.00, 147.00, 140.00, 129.00};
      
      List<String> orders = new ArrayList<>();
      List<Double> orderPrices = new ArrayList<>();
      
      boolean headerShown = false;
      int choice;
      double payment = 0;
      double change = 0;
      
      do{
         if(!headerShown) {
         printHeader();
         headerShown = true;
         }
         
         printChoices();
         System.out.print("Enter your choice: ");
         choice = validateInput(scanner, 1, 6);
         
         switch(choice){
            case 1 -> showMenu(scanner);
            case 2 -> placeAnOrder(scanner, choice, orderPrices, orders, items, prices);
            case 3 -> viewCurrentOrder(orders, orderPrices);
            case 4 -> removeAnOrder(scanner, orders, orderPrices, choice);
            case 5 -> checkoutOrder(scanner, payment, change, orders, orderPrices);
         }
      }while(choice != 6);
      System.out.println("✅ Thank you for visiting!!");
   }
   public static void printUpperBorder(){
      System.out.println("┌------------------------------------------------------------------┐");
   }
   public static void printMiddleBorder(){
      System.out.println("|------------------------------------------------------------------|");
   }
   public static void printLowerBorder(){
      System.out.println("└------------------------------------------------------------------┘\n");
   }
   public static void printHeader(){
      System.out.println("\n--------------------------------");
      System.out.println("      JOLLIMCDO PHILIPPINES     ");
      System.out.println("--------------------------------\n");
   }
   public static void printMenu(){
      System.out.println("┌------------------------------------------------------------------┐");
      System.out.println("|                            MCDO MENU                             |");
      System.out.println("└------------------------------------------------------------------┘\n");
      
      System.out.println("Special Meals:");
      System.out.println("┌------------------------------------------------------------------┐");
      System.out.printf("| %-4s | %-46s | %-7s  |\n", " No.", "                    Item ", " Price");
      System.out.println("|------------------------------------------------------------------|");
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[01]", " McShare Bundle for 3 Person", 612.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[02]", " McShare Bundle for 4 Person", 842.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[03]", " McSpaghetti Platter - Classic Meal", 259.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[04]", " 20 Piece - Chicken McNuggets", 350.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[05]", " Snack Burger McShare Meal - Popular", 380.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[06]", " BFF Fries N' McFloat Combo", 282.00);
      System.out.println("└------------------------------------------------------------------┘\n");
      
      System.out.println("McDelivery Meals:");
      System.out.println("┌------------------------------------------------------------------┐");
      System.out.printf("| %-4s | %-46s | %-7s  |\n", " No.", "                    Item ", " Price");
      System.out.println("|------------------------------------------------------------------|");
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[07]", " Double Big Mac Meal w/ Drinks & Fries", 331.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[08]", " The BCB Meal Box (Beef Chicken Beef)", 264.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[09]", " Mega Meal – Chicken McDo w/ Fries & McFlurry", 223.00);
      System.out.println("└------------------------------------------------------------------┘\n");
      
      System.out.println("Happy Meals:");
      System.out.println("┌------------------------------------------------------------------┐");
      System.out.printf("| %-4s | %-46s | %-7s  |\n", " No.", "                    Item ", " Price");
      System.out.println("|------------------------------------------------------------------|");
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[10]", " 1-pc. Chicken McDo Happy Meal", 185.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[11]", " 4-pc. Chicken McNuggets Happy Meal", 147.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[12]", " McSpaghetti Happy Meal with Fries and juice", 140.00);
      System.out.printf("| %-4s | %-46s | P%.2f  |\n", "[13]", " Burger McDo Happy Meal with Fries and Juice", 129.00);
      System.out.println("└------------------------------------------------------------------┘\n");
   }
   public static void showMenu(Scanner scanner){
      System.out.println("\nShowing menu...");
      printMenu();
      char back;
   
      do{
         System.out.print("Back (Y): ");
         back = scanner.next().charAt(0);
      }while(Character.toUpperCase(back) != 'Y');
   }
   public static void printChoices(){
      System.out.println("\n[1] Show Menu");
      System.out.println("[2] Place an Order");
      System.out.println("[3] View Current Order");
      System.out.println("[4] Remove Item");
      System.out.println("[5] Check Out");
      System.out.println("[6] Exit\n");
   }
   public static void placeOrder(int choice, String[] items, double[] prices, List<String> orders, List<Double> orderPrices){
    String order = items[choice - 1];
    double price = prices[choice - 1];

    orders.add(order);
    orderPrices.add(price);

    System.out.printf("✅ You ordered: %s (₱%,.2f)\n", order, price);
   }
   public static void placeAnOrder(Scanner scanner, int choice, List<Double> orderPrices, List<String> orders, String[] items, double[] prices){
      System.out.println("\nPlacing an order...");
      boolean menuShown = false; 
      char again;
      
      do{
         if(!menuShown) {
         printMenu();
         menuShown = true;
         }
         
         System.out.print("Enter the number of your order: ");
         choice = validateInput(scanner, 1, items.length);
         
         placeOrder(choice, items, prices, orders, orderPrices);
         
         System.out.print("\nWant to add more? (Y/N): ");
         again = scanner.next().charAt(0);
      }while(again == 'Y' || again == 'y');
   }
   public static void viewCurrentOrder(List<String> orders, List<Double> orderPrices){
      double total = 0;
    
      System.out.println("\nHere's your Current Order: ");
               
      printUpperBorder();
      System.out.printf("| %s | %-46s | %-7s  |\n", " No.", "                    Item ", "  Price");
      
      printMiddleBorder();
      for(int i = 0; i < orders.size(); i++){
         System.out.printf("| [%02d] |  %-45s |  ₱%,.2f |\n", (i + 1), orders.get(i), orderPrices.get(i));
         total += orderPrices.get(i);
      }
      printLowerBorder();
      
      printUpperBorder();
      System.out.printf("|                        TOTAL: ₱%,.2f                          |%n", total);
      printLowerBorder();
      
      total = 0;
   }
   public static void removeAnOrder(Scanner scanner, List<String> orders, List<Double> orderPrices, int choice){
      char again;
      double total = 0;
      
      do{
         printUpperBorder();
         System.out.printf("| %s | %-46s | %-7s  |\n", " No.", "                    Item ", "  Price");
         
         printMiddleBorder();
         for(int i = 0; i < orders.size(); i++){
            System.out.printf("| [%02d] |  %-45s |  ₱%,.2f |\n", (i + 1), orders.get(i), orderPrices.get(i));
            total += orderPrices.get(i);
         }
         printLowerBorder();
         
         printUpperBorder();
         System.out.printf("|                        TOTAL: ₱%,.2f                          |%n", total);
         printLowerBorder();
         
         total = 0;
         
         System.out.print("Enter the number you want to remove: ");
         choice = validateInput(scanner, 1, orders.size());
         
         orders.remove(choice - 1);
         
         printUpperBorder();
         System.out.printf("| %s | %-46s | %-7s  |\n", " No.", "                    Item ", "  Price");
         
         printMiddleBorder();
         for(int i = 0; i < orders.size(); i++){
            System.out.printf("| [%02d] |  %-45s |  ₱%,.2f |\n", (i + 1), orders.get(i), orderPrices.get(i));
            total += orderPrices.get(i);
         }
         printLowerBorder();
         
         printUpperBorder();
         System.out.printf("|                        TOTAL: ₱%,.2f                          |%n", total);
         printLowerBorder();
         
         total = 0;
         
         System.out.print("\nWant to remove more? (Y/N): ");
         again = scanner.next().charAt(0);
      }while(again == 'Y' || again == 'y');
   }
   public static void checkoutOrder(Scanner scanner, double payment, double change, List<String> orders, List<Double> orderPrices){
	    double total = 0;

	    if(orders.isEmpty()){
	        System.out.println("‼️  You don't have orders yet");
	        return;
	    }

	    // CALL SENIOR ID VERIFICATION
	    boolean isSenior = Id(scanner);
	    double discount = 0;

	    printUpperBorder();
	    System.out.printf("|                            CHECKOUT                              |%n");
	    printLowerBorder();

	    printUpperBorder();
	    System.out.printf("| %s | %-46s | %-7s  |\n", " No.", "                    Item ", "  Price");

	    printMiddleBorder();
	    for(int i = 0; i < orders.size(); i++){
	        System.out.printf("| [%02d] |  %-45s |  ₱%,.2f |\n", (i + 1), orders.get(i), orderPrices.get(i));
	        total += orderPrices.get(i);
	    }
	    printLowerBorder();

	    // APPLY DISCOUNT
	    if(isSenior){
	        discount = total * 0.20;      // 20% senior discount
	        total -= discount;

	        System.out.printf("Senior Discount (20%%): -₱%,.2f%n", discount);
	    }
	    
	    printUpperBorder();
	    System.out.printf("|                        TOTAL: ₱%,.2f                          |%n", total);
	    printLowerBorder();
	    
	    // PAYMENT
	    while(true){
	        System.out.print("Enter your payment: ");
	        if(scanner.hasNextDouble()){
	            payment = scanner.nextDouble();
	            if(payment >= total) break;
	            else{
	                System.out.println("‼️  Insufficient Payment. Please Try Again");
	            }
	        }else{
	            System.out.print("‼️  Invalid Input. Please enter a number: ");
	            scanner.next();
	        }
	    }

	    change = payment - total;
	    System.out.printf("\nTotal Cost: ₱%,.2f\n", total);
	    System.out.printf("Your Payment: ₱%,.2f\n", payment);
	    System.out.printf("Your Change: ₱%,.2f\n", change);

	    System.out.println("\n✅ Thank you for ordering at McDo! Come back again!");
   }
   public static int validateInput(Scanner scanner, int min, int max){
      while(true){
         int choice;
         if(scanner.hasNextInt()) {
            choice = scanner.nextInt();
               if(choice >= min && choice <= max){
                  return choice;
               }else{
                  System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
               }
         }else{
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
         }
      }
   }
   public static boolean Id(Scanner sc) {

		    class Person {
		        private String name;
		        private int age;

		        public Person(String name, int age) {
		            this.name = name;
		            this.age = age;
		        }

		        public String getName() {
		            return name;
		        }

		        public int getAge() {
		            return age;
		        }
		    }

		   
		    HashMap<Integer, Person> IdPersons = new HashMap<>();

		    IdPersons.put(9812312, new Person("Juan Dela Cruz", 65));
		    IdPersons.put(1234512, new Person("Maria Clara", 70));
		    IdPersons.put(6789012, new Person("Jose Rizal", 68));

		    String answer;

		    // loop until Y or N
		    while (true) {
		        System.out.print("Do you have Senior Citizen Card? (Y or N): ");
		        answer = sc.next();

		        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
		            break;
		        }
		        System.out.println("Invalid input. Please answer Y or N only.");
		    }

		    if (answer.equalsIgnoreCase("N")) {
		        System.out.println("No ID. No Discount.");
		        return false;
		    }

		    // ============================
		    //   3 ATTEMPTS FOR ID
		    // ============================
		    int attempts = 0;

		    while (attempts < 6) {

		        System.out.print("Provide your ID number for verification: ");
		        String idInput = sc.next();

		        // must be digits only
		        if (!idInput.matches("\\d+")) {
		            System.out.println("ID must not contain characters. Try again.");
		            attempts++;
		            continue;
		        }

		        int IdNumber = Integer.parseInt(idInput);

		        System.out.print("Verifying");
		        for (int i = 0; i < 5; i++) {
		            try {
		                Thread.sleep(400);
		                System.out.print(".");
		            } catch (InterruptedException e) {}
		        }
		        System.out.println();

		        // check if ID exists
		        if (IdPersons.containsKey(IdNumber)) {
		            Person person = IdPersons.get(IdNumber);

		            System.out.println("\nVerified!");
		            System.out.println("Name: " + person.getName());
		            System.out.println("Age: " + person.getAge());

		            return true;
		        } else if (attempts == 4){
		        	System.out.println("Invalid again!!, You have only last attempt" );
		        } 
		        else if (attempts < 4) {
		        	  System.out.println("");
		                System.out.println("┌------------------------------------------------------------------┐");
		                System.out.println("|                         INVALID!!                              |");
		                System.out.println("|              You have only " + (Math.abs(attempts - 5)) + " Attempts                         |");
		                System.out.println("└------------------------------------------------------------------┘\n");
		            } 
		        else {
		            System.out.println("Invalid ID Number! Try again.");
		          
		           
		        } attempts++;
		    }

		    // IF NAKA 3 WRONG ATTEMPTS
		    System.out.println("\n❌ You exceeded the limit of attempts!");
		    System.out.println(" ");
		    System.out.println("Cancel Discount");
		    System.out.println(" ");
		    return false;
   }
   public static String printReceipt(List<Double> orderPrices, List<String> orders, double change, double payment){
      double total = 0;
      StringBuilder receipt = new StringBuilder();
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
      
      receipt.append("==== McOrder RECEIPT ====\n");
      receipt.append("Date & Time: " + dtf.format(now) + "\n");
      receipt.append("Cashier: System Auto\n");
      receipt.append("--------------------------\n\n");
   
      receipt.append("Items Ordered:\n");
      receipt.append("--------------------------\n");
      for (int i = 0; i < orders.size(); i++) {
         receipt.append(String.format("[%02d] %-45s ₱%,.2f%n", (i + 1), orders.get(i), orderPrices.get(i)));
         total += orderPrices.get(i);
      }
   
      receipt.append("\n--------------------------\n");
      receipt.append(String.format("TOTAL:   ₱%,.2f%n", total));
      receipt.append(String.format("PAYMENT: ₱%,.2f%n", payment));
      receipt.append(String.format("CHANGE:  ₱%,.2f%n", change));
      receipt.append("--------------------------\n");
       
      return receipt.toString();
   }
   }