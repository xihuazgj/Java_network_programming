package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Client {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        StudentService service=(StudentService) Naming.lookup("rmi://localhost:8000/service");
        IDService idService=new IDServiceImp();
        List<Student> list=service.getStudent(idService);
        for (Student student:list) {
            System.out.println(student);
        }
    }
}
