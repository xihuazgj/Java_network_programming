package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IDServiceImp extends  UnicastRemoteObject implements IDService{
    public static int id=0;
    protected IDServiceImp() throws RemoteException{
        super();
    }

    @Override
    public int getID() throws RemoteException {
        return ++id;
    }
}
