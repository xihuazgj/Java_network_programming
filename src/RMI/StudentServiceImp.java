package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImp extends UnicastRemoteObject implements StudentService {

    protected StudentServiceImp() throws RemoteException{
        super();
    }
    @Override
    public List<Student> getStudent(IDService ids) throws RemoteException {
        List<Student> students=new ArrayList<>();

        Student student1=new Student();
        student1.setId(ids.getID());
        student1.setName("小程");
        student1.setMajor("软件工程");

        Student student2=new Student();
        student2.setId(ids.getID());
        student2.setName("小王");
        student2.setMajor("网络与安全");

        students.add(student1);
        students.add(student2);

        return students;
    }
}
