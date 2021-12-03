import java.util.Vector;

public class BankRemoteObject implements UserCommands {
	private Vector<User> users;
	
	public BankRemoteObject(){
		this.users = new Vector<User>();
		users.addElement(new User(0, "moe", "moe123", 5000.0));
		users.addElement(new User(1, "larry", "larry123", 5000.0));
		users.addElement(new User(2, "curley", "curley123", 5000.0));
	}
	
	public int login(String username, String password){
		System.out.println("Client logging in");
		for(int i=0;i<users.size();i++){
			if(users.elementAt(i).getUsername().equals(username)){
				//same username, check password
				if(users.elementAt(i).checkPassword(password)){
					System.out.println("Client logged in");
					return i;
				}
				else{
					System.out.println("Client log in failed");
					return -1;
				}
			}
		}
		System.out.println("Client log in failed");
		return -1;
	}
	
	public String createNewAccount(String username, String password, double balance){
		System.out.println("Client creating account");
		if(balance <= 0.0){
			//invalid amount
			System.out.println("Client account creation failed");
			return "Error: Must deposit a positive non-zero amount";
		}
		for(int i=0;i<users.size();i++){
			if(users.elementAt(i).getUsername().equals(username)){
				//same username error
				System.out.println("Client account creation failed");
				return "Error: Username not available";
			}
		}
		users.addElement(new User(users.size(), username, password, balance));
		System.out.println("Client account creation successful");
		return "Account Created!";
	}
	
	public String viewAccountDetails(int index){
		System.out.println("Client viewing account details");
		return "Account ID: "+users.elementAt(index).getID()+"\nBalance: "+users.elementAt(index).getBalance();
	}
	
	public boolean deposit(int index, double amount){
		System.out.println("Client depositing "+amount);
		if(amount <= 0){
			System.out.println("Client deposit failed");
			return false;
		}
		users.elementAt(index).deposit(amount);
		System.out.println(viewAccountDetails(index));
		return true;
	}
	
	public boolean withdraw(int index, double amount){
		System.out.println("Client withdrawing "+amount);
		if(amount <= 0){
			System.out.println("Client withdrawal failed");
			return false;
		}
		if(users.elementAt(index).getBalance() - amount < 0){
			System.out.println("Client withdrawal failed");
			return false;
		}
		users.elementAt(index).withdraw(amount);
		System.out.println(viewAccountDetails(index));
		return true;
	}
}