// Name: Cole Vincent

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
// lists to store service inormation and cart indices
        ArrayList<String> serviceNames = new ArrayList<>();
        ArrayList<String> serviceCategories = new ArrayList<>();
        ArrayList<Double> serviceRates = new ArrayList<>();
        ArrayList<Integer> serviceAvailability = new ArrayList<>();
        ArrayList<Integer> cartIndices = new ArrayList<>();   
// main menu loop for whole program
        while (true) {
            System.out.println("\n   __Main Menu__");
            System.out.println("Input (1) to Add a Service");
            System.out.println("Input (2) to Book a Service");
            System.out.println("Input (3) to Checkout");
            System.out.println("Input (4) to Remove a Service from Cart");
            System.out.println("Input (5) to Update a Service in Cart");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); 

            // Option 1 - Add Service
            if (choice == 1) {
                // asks user for service details as (1) was inputted. 
                while (true) {
                    System.out.print("Enter service name: ");
                    String name = input.nextLine();

                    System.out.print("Enter category: ");
                    String category = input.nextLine();

                    System.out.print("Enter hourly rate: ");
                    double rate = input.nextDouble();
                    input.nextLine();
// adds service information to the lists 
                    serviceNames.add(name);
                    serviceCategories.add(category);
                    serviceRates.add(rate);
                    serviceAvailability.add(1);

                    System.out.println("Service '" + name + "' added successfully.");
//  asks user if they want add another service. 
                    System.out.print("Add another service? (yes/no): ");
                    String again = input.nextLine();
                    if (!again.equalsIgnoreCase("yes")) 
                        break;
                }

            // Option 2 - Book Service
            } else if (choice == 2) {
                if (serviceNames.size() == 0) {
                    System.out.println("No services available.");
                } else {
                    // gets unique categories to let user choose. 
                    ArrayList<String> uniqueCategories = new ArrayList<>();
                    for (String category : serviceCategories) {
                        if (!uniqueCategories.contains(category)) {
                            uniqueCategories.add(category);
                        }
                    }
// shows category options to user. 
                    System.out.println("Available Categories:");
                    for (int i = 0; i < uniqueCategories.size(); i++) {
                        System.out.println((i + 1) + ". " + uniqueCategories.get(i));
                    }

                    System.out.print("Choose a category (input the corresponding number): ");
                    int catChoice = input.nextInt();
                    input.nextLine();

                    if (catChoice < 1 || catChoice > uniqueCategories.size()) {
                        System.out.println("Invalid category.");
                    } else {
                        // shows services in the chosen category. 
                        String selectedCategory = uniqueCategories.get(catChoice - 1);
                        ArrayList<Integer> matchingIndices = new ArrayList<>();
                        System.out.println("Services in " + selectedCategory + ":");

                        for (int i = 0; i < serviceNames.size(); i++) {
                            if (serviceCategories.get(i).equalsIgnoreCase(selectedCategory)) {
                                matchingIndices.add(i);
                                String status;
                                if (serviceAvailability.get(i) <= 0) {
                                 status = "(Fully Booked)";
                                } else {
                                 status = "";
                                }
                                System.out.println(matchingIndices.size() + ". " + serviceNames.get(i) + " - $" + serviceRates.get(i) + "/hr " + status);
                            }
                        }

                        if (matchingIndices.size() == 0) {
                            System.out.println("No services found in this category.");
                        } else {
                            //  user selects the service to book. 
                            System.out.print("Enter the number of the service to book (input (0) to cancel): ");
                            int bookChoice = input.nextInt();
                            input.nextLine();

                            if (bookChoice == 0) {
                                System.out.println("Booking cancelled");
                            } else if (bookChoice >= 1 && bookChoice <= matchingIndices.size()) {
                                int serviceIndex = matchingIndices.get(bookChoice - 1);
                                if (serviceAvailability.get(serviceIndex) == 0) {
                                    System.out.println("Service is fully booked.");
                                } else {
                                    // adds service to cart and marks as booked. 
                                     cartIndices.add(serviceIndex);
                                    serviceAvailability.set(serviceIndex, 0);
                                    System.out.println(serviceNames.get(serviceIndex) + " added to cart.");
                                }
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        }
                    }
                }
            // Option 3 - Checkout
            } else if (choice == 3) {
                System.out.println("\nThank you for using the service booking program. Here is your Invoice.");
                System.out.println("\n__Your Invoice__");
                double total = 0;
                for (int i = 0; i < cartIndices.size(); i++) {
                    int idx = cartIndices.get(i);
                    System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                    total += serviceRates.get(idx);
                }
                // displays total cost and exits program. 
                System.out.println("Total Cost: $" + total);
                break;
                

            // Option 4 - Remove from Cart 
            } else if (choice == 4) {
                System.out.println("\n__Your Cart__");
                double total = 0;
                for (int i = 0; i < cartIndices.size(); i++) {
                    int idx = cartIndices.get(i);
                    System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                    total += serviceRates.get(idx);
                }
                System.out.println("Total Cost: $" + total);

                if (cartIndices.size() == 0) {
                    System.out.println("Your cart is empty.");
                } else {
                    System.out.print("Enter the number of the service to remove: ");
                    int removeIndex = input.nextInt();
                    input.nextLine();

                    if (removeIndex >= 1 && removeIndex <= cartIndices.size()) {
                        int removedServiceIndex = cartIndices.get(removeIndex - 1);
                        // removes from cart and makes service available again
                        cartIndices.remove(removeIndex - 1);  
                        serviceAvailability.set(removedServiceIndex, 1); 
                        System.out.println("Service removed from cart and is available again.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
                }

            // Option 5 - Update Service in Cart
            } else if (choice == 5) {
                if (cartIndices.size() == 0) {
                    System.out.println("\nYour cart is empty.");
                } else {
                    System.out.println("\n__Your Cart__");
                    for (int i = 0; i < cartIndices.size(); i++) {
                        int idx = cartIndices.get(i);
                        System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                    }

                    System.out.print("Enter the number of the service to update: ");
                    int updateChoice = input.nextInt();
                    input.nextLine();

                    if (updateChoice >= 1 && updateChoice <= cartIndices.size()) {
                        int oldServiceIndex = cartIndices.get(updateChoice - 1);
                        serviceAvailability.set(oldServiceIndex, 1); 
// Mark old one available again ^^^
                        System.out.println("\n__Available Services__");
                        ArrayList<Integer> availableIndices = new ArrayList<>();
                        for (int i = 0; i < serviceNames.size(); i++) {
                            String status1 = "";
                        if (serviceAvailability.get(i) == 0) {
                         status1 = "(Fully Booked)";
                        } else { 
                         if (serviceAvailability.get(i) == 1) {
                                availableIndices.add(i);
                                System.out.println(availableIndices.size() + ". " + serviceNames.get(i) + " - $" + serviceRates.get(i) + "/hr " + status1);
                            }
                        }
                        }
                        if (availableIndices.size() == 0) {
                            System.out.println("No services available to replace with.");
                            serviceAvailability.set(oldServiceIndex, 0); // put old one as booked
                        } else {
                            System.out.print("Enter the number of the new service: ");
                            int newServiceChoice = input.nextInt();
                            input.nextLine();

                            if (newServiceChoice >= 1 && newServiceChoice <= availableIndices.size()) {
                                int newServiceIndex = availableIndices.get(newServiceChoice - 1);
                                // updates cart and marks new one as booked. 
                                cartIndices.set(updateChoice - 1, newServiceIndex);
                                serviceAvailability.set(newServiceIndex, 0); 
                                System.out.println("Cart updated successfully.");
                            } else {
                                System.out.println("Invalid choice. Keeping original.");
                                serviceAvailability.set(oldServiceIndex, 0); // go back to original booking
                            }
                        }
                    } else {
                        System.out.println("Invalid selection.");
                    }

                    System.out.println("\n__Updated Cart__");
                    double total = 0;
                    for (int i = 0; i < cartIndices.size(); i++) {
                        int idx = cartIndices.get(i);
                        System.out.println((i + 1) + ". " + serviceNames.get(idx) + " - $" + serviceRates.get(idx) + "/hr");
                        total += serviceRates.get(idx);
                    }
                    System.out.println("Total Cost: $" + total);
                }

            // Invalid input choice for menu
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        input.close();
    }
}
