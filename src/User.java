public class User{
	private int ID;
	private String username;
	private String password;
	private double balance;
	
	public User(int ID, String username, String password, double balance){
		this.ID = ID;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	public double getBalance(){
		return this.balance;
	}
	public String getUsername(){
		return this.username;
	}
	public int getID(){
		return this.ID;
	}
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}
	public void deposit(double amount){
		this.balance += amount;
	}
	public void withdraw(double amount){
		this.balance -= amount;
	}
}