package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(8000);
        StudentService service=new StudentServiceImp();
        Naming.rebind("rmi://localhost:8000/service",service);
    }
}
