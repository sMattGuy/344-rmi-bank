import java.rmi.server.UnicastRemoteObject; 
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Server extends BankRemoteObject{
	public static void main(String args[]){
		try{
			if(args.length != 2){
				System.out.println("Usage: Server hostname port");
				return;
			}
			
			String hostname = args[0];
			int port = Integer.parseInt(args[1]);
			
			BankRemoteObject bank = new BankRemoteObject();
			UserCommands stub = (UserCommands)UnicastRemoteObject.exportObject(bank, 0);
			
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind("UserCommands", stub);
			System.out.println("Ready. hostname:"+hostname+" port:"+port);
		}
		catch(Exception e){
			System.err.println("Server Exception: "+e.toString());
			e.printStackTrace();
		}
	}
}