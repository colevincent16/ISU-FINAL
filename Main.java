// Name: Cole Vincent

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ArrayLists to store all lists necessary 
        ArrayList<String> serviceNames = new ArrayList<>();
        ArrayList<String> serviceCategories = new ArrayList<>();
        ArrayList<Double> serviceRates = new ArrayList<>();
        ArrayList<Integer> serviceAvailability = new ArrayList<>(); // way to get the fully booked option
        ArrayList<Integer> cartIndices = new ArrayList<>(); // stores indices of booked services

        while (true) {
            // Main menu loop to be looped through every stage of output. 
            System.out.println("\n   __Main Menu__");
            System.out.println("Input (1) To Add a Service");
            System.out.println("Input (2) To Book a Service");
            System.out.println("Input (3) To Leave/Checkout the Marketplace");
            System.out.println("Input (4) To Remove a Service from your Cart");
            System.out.print("Enter your choice: ");
            
            int choice = input.nextInt();
            input.nextLine(); 

            // Option 1 - Add a service
            if (choice == 1) {
                while (true) {
                    // Get details of service from user
                    System.out.print("Enter service name: ");
                    String name = input.nextLine();

                    System.out.print("Enter category: ");
                    String category = input.nextLine();

                    System.out.print("Enter hourly rate: ");
                    double rate = input.nextDouble();
                    input.nextLine();

                    // Stores the services information
                    serviceNames.add(name);
                    serviceCategories.add(category);
                    serviceRates.add(rate);
                    serviceAvailability.add(1); // makes the fully booked option marked a available rather then fully booked. 

                    System.out.println("Service   " + name + "   added successfully.");

                    // ask user if they want another service to be added. 
                    System.out.print("Add another service? (yes/no): ");
                    String again = input.nextLine();
                    if (!again.equalsIgnoreCase("yes")) {
                        break;
                    }
                }

            // Option 2 - Book a service
            } else if (choice == 2) {
                if (serviceNames.size() == 0) {
                    System.out.println("No services available yet.");
                } else {
                    // Create list of unique categories so they can input the same category multiple times, but won't output nultiple times. 
                    ArrayList<String> uniqueCategories = new ArrayList<>();
                    for (String category : serviceCategories) {
                        if (!uniqueCategories.contains(category)) {
                            uniqueCategories.add(category);
                        }
                    }

                    // outputs categories to user
                    System.out.println("Available Service Categories:");
                    for (int i = 0; i < uniqueCategories.size(); i++) {
                        System.out.println((i + 1) + ". " + uniqueCategories.get(i));
                    }

                    // gets user to input the category they want
                    System.out.print("\nChoose a category: ");
                    int catChoice = input.nextInt();
                    input.nextLine();

                    if (catChoice < 1 || catChoice > uniqueCategories.size()) {
                        System.out.println("Invalid category.");
                    } else {
                        // outputs services in the chosen category
                        String selectedCategory = uniqueCategories.get(catChoice - 1);
                        System.out.println("\nServices in category: " + selectedCategory);

                        ArrayList<Integer> matchingIndices = new ArrayList<>();
                        for (int i = 0; i < serviceNames.size(); i++) {
                            if (serviceCategories.get(i).equalsIgnoreCase(selectedCategory)) {
                                matchingIndices.add(i);
                                
                                String status = "";
                                if (serviceAvailability.get(i) <= 0) {
                                    status = "_Fully Booked_";
                                }

                                // Shows the service as well as its price to user. 
                                System.out.println((matchingIndices.size()) + ". " + serviceNames.get(i) +
                                        " | Rate: $" + serviceRates.get(i) + " " + status);
                            }
                        }

                        if (matchingIndices.size() == 0) {
                            System.out.println("No services found in this category.");
                        }

                        // asks user to input the service they wnat booked. 
                        System.out.print("\nEnter the number of the service to book (or 0 to cancel): ");
                        int bookChoice = input.nextInt();
                        input.nextLine();

                        if (bookChoice == 0) {
                            System.out.println("Booking cancelled.");
                        } else if (bookChoice >= 1 && bookChoice <= matchingIndices.size()) {
                            int serviceIndex = matchingIndices.get(bookChoice - 1);
                            if (serviceAvailability.get(serviceIndex) == 0) {
                                System.out.println("That service is fully booked. You cannot book that service again.");
                            } else {
                                cartIndices.add(serviceIndex); // Adds service to cart
                                serviceAvailability.set(serviceIndex, 0); // Marks as booked
                                System.out.println(serviceNames.get(serviceIndex) + " added to your cart.");
                            }
                        } else {
                            System.out.println("Invalid choice.");
                        }

                        // outputs cart to user after booking
                        System.out.println("\n __Your Cart__");
                        double total = 0;
                        for (int i = 0; i < cartIndices.size(); i++) {
                            int idx = cartIndices.get(i);
                            System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                            total += serviceRates.get(idx);
                        }
                        System.out.println("Total Cost: $" + total);
                    }
                }

            // Option 3 - Checkout and show invoice
            } else if (choice == 3) {
                System.out.println("\nThank you for using the service booking program. Here is your Invoice.");
                System.out.println("\n  __Your Invoice__");
                double total = 0;
                for (int i = 0; i < cartIndices.size(); i++) {
                    int idx = cartIndices.get(i);
                    System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                    total += serviceRates.get(idx);
                }
                System.out.println("Total Cost: $" + total);
                break; // leaves program when 3 is chosen. 

            // Option 4 - Remove a service from cart
            } else if (choice == 4) {
                System.out.println("\n  __Your Cart__");
                double total = 0;
                for (int i = 0; i < cartIndices.size(); i++) {
                    int idx = cartIndices.get(i);
                    System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                    total += serviceRates.get(idx);
                }
                System.out.println("Total Cost: $" + total);

                if (total == 0) {
                    System.out.println("There are no services to remove from your cart.");
                } else {
                    // Asks user which service they want to reove
                    System.out.print("\nWhat service number do you want to remove from your cart? ");
                    int removeIndex = input.nextInt();
                    input.nextLine();

                    if (removeIndex >= 1 && removeIndex <= cartIndices.size()) {
                        // Restore/changes availability once service is removed from from cart
                        int removedServiceIndex = cartIndices.get(removeIndex - 1);
                        serviceAvailability.set(removedServiceIndex, 1);

                        cartIndices.remove(removeIndex - 1); // Removes service chosen by user. 
                        System.out.println("Service removed from cart.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                }

            // done to tell the user through every stage if they inputed incorrect choice. 
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
