import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserCommands extends Remote{
	String createNewAccount(String username, String password, double balance) throws RemoteException;
	
	int login(String username, String password) throws RemoteException;
	
	String viewAccountDetails(int index) throws RemoteException;
	
	boolean deposit(int index, double amount) throws RemoteException;
	
	boolean withdraw(int index, double amount) throws RemoteException;
}