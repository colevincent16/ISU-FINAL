# ISU-FINAL
local marketplace program. 

array lists and use in the program - 

serviceNames - string array list - stores all the names of all the services 
serviceCategories - string array list - stores the category for each service 
serviceRates - double array list - stores the rates for each service
serviceAvailability - integer arraylist  - tracks if a service is available (1), means available (0) means booked.
cartIndices - integer array list - stores indices of services added to the user's cart. 
uniqueCategories - string array list - temporarily stores the unique/ distinct categories for browsing. 
matchingIndicies - integer array list  - temporarily stores indices of services in the selected category from the user. 

important variables and use in the program - 

 choice - integer variable - used in the main menu loop to store the user's selected choice from the menu. 
 name - String variable - used to store the names of the services inputed from the user and eventually stored into the appropriate array list. 
 category - String variable - used to store the names of the categories input from the user and eventually stored into the appropriate array list. 
 rate - double variable - used to store the pay rate of the service input and contributes to the overall cost. 
 total - double variable - used to display the total cost of the services in the user's cart and their invoice. 
 idx - integer variable - used to retrieve the index when the program is iterating through the services or the user's cart. 
 catChoice - Integer variable - used to allow the user to make a selection of the categories and see the categories 
 bookChoice - Integer variable - used to allow the user to make a selection to book a service. 
 removeIndex - Integer variable - used to allow the user to make the selection to be able to remove a service from their cart. 
 status - String variable - used to temporarily hold a service as "fully booked" if the service has already been selected. 
 again - String variable - used in the choice 1 part of the program to ask the user whether they want to continue to add services. 
 selectedCategory - String variable - used to keep or hold the category that the user selected for browsing or looking into the service in a specific category. 
 - These varibales are important as they ensure that the arraylists can stay in sync, be a way or temporary storage,  and give a better, clearer user experience. 
 
features - 

Choice 1 - add a service 
user enters - 
- service name 
- category
- rate for service $$
- Information is added to the appropriate arraylist to be stored.
- Each service is marked as available at first.
- Important because the inputs build the entire data set, which the program uses for the booking and invoice. 

Choice 2 - book a service 
- displays all available categories
- lists services in the selected category
- Users can book available services
- Booked services are added to their cart and then marked as "fully booked."
- Important because it allows users to book a service and place an order, which is necessary for a marketplace.  

Choice 3 - checkout
- Shows final invoice with all booked services and also displays the total price.
- exits the program.
- Important as it allows people to check out or leave the program and check out the services in their cart. 
 
Choice 4 - View & manage the cart
- The cart displays all selected services and the total cost
- Displays the option to remove a service from the cart by selecting the one you want to remove.
- Services are removed from the cart if selected.
- Important as it allows people to remove a service, just like how you would in real life. 

logical structure - 
The whole program is structured around a while (true) loop, which is the main loop, which displays the menu with 4 options - 
- Add a service
- Book a service
- Checkout
- Remove service from cart
  Each option branches into its section of logical input prompts for the array list options.

  Everything is in place in their specific choice boxes in a way to also ensure that I wasn't forgetting anything. 




