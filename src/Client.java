import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client{
	
	public static void main(String args[]){
		try{
			if(args.length != 2){
				System.out.println("Usage: Client hostname port");
				return;
			}
			
			String hostname = args[0];
			int port = Integer.parseInt(args[1]);
			
			Registry registry = LocateRegistry.getRegistry(hostname, port);
			UserCommands stub = (UserCommands) registry.lookup("UserCommands");
			
			Scanner in = new Scanner(System.in);
			while(true){
				String option = "";
				System.out.println("Welcome to RMI Bank. Please log in or create an account:\n1. log in\n2. Create Account\n3. Exit");
				option = in.nextLine();
				if(option.equals("1")){
					String username;
					String password;
					System.out.println("===== LOGIN =====\nPlease enter username");
					username = in.nextLine();
					System.out.println("Please enter password");
					password = in.nextLine();
					int result = stub.login(username,password);
					if(result == -1){
						System.out.println("Error: Invalid username or password");
					}
					else{
						int index = result;
						while(true){
							//menu options
							String menuOption;
							System.out.println("===== MENU =====\nPlease select an option\n1. View Account\n2. Deposit\n3. Withdraw\n4. Logout");
							menuOption = in.nextLine();
							if(menuOption.equals("1")){
								System.out.println(stub.viewAccountDetails(index));
							}
							else if(menuOption.equals("2")){
								double amount = 0;
								System.out.println("Enter a deposit amount");
								amount = Double.parseDouble(in.nextLine());
								if(stub.deposit(index, amount)){
									System.out.println("Deposit Successful!");
								}
								else{
									System.out.println("Error: Deposit amount invalid");
								}
							}
							else if(menuOption.equals("3")){
								double amount = 0;
								System.out.println("Enter a withdrawal amount");
								amount = Double.parseDouble(in.nextLine());
								if(stub.withdraw(index, amount)){
									System.out.println("withdrawal Successful!");
								}
								else{
									System.out.println("Error: withdrawal amount invalid");
								}
							}
							else if(menuOption.equals("4")){
								System.out.println("Logging out... Goodbye!");
								break;
							}
							else{
								System.out.println(menuOption);
								System.out.println("Error: Invalid option (use 1,2,3 or 4)");
							}
						}
					}
				}
				else if(option.equals("2")){
					String username;
					String password;
					double depositAmount;
					
					System.out.println("===== CREATE =====\nPlease enter a username:");
					username = in.nextLine();
					System.out.println("Please enter a password");
					password = in.nextLine();
					System.out.println("Please enter deposit amount");
					depositAmount = Double.parseDouble(in.nextLine());
					
					System.out.println(stub.createNewAccount(username, password, depositAmount));
				}
				else if(option.equals("3")){
					System.out.println("Thank you for using RMI bank!");
					break;
				}
				else{
					System.out.println(option);
					System.out.println("Error: Invalid option (use 1,2 or 3)");
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}