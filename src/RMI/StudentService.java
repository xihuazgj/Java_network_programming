package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {
    public List<Student> getStudent(IDService ids) throws RemoteException;
}
