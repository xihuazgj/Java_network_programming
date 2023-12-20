package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDService extends Remote {
    public int getID() throws RemoteException;
}
