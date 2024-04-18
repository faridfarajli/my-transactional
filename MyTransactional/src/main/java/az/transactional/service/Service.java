package az.transactional.service;

import az.transactional.db.DBAccess;
import az.transactional.entity.Student;
import az.transactional.transactional.MyTransactional;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {
    private final DBAccess dbAccess;



    public List<Student> getAll(){
//        List<Student> students = dbAccess.getResultList("select * from student");
//        return students;

        return dbAccess.getResultList("select * from student");

    }
    public void insertStudent(Student s){
        dbAccess.executeUpdate("insert into student(name,rollNo,semester) values(?,?,?)", Arrays.asList(s.getName(),s.getRollNo(),s.getSemester()));
    }
    public void updateStudent(Student s){
        dbAccess.executeUpdate("update student set name=?, rollNo=?, semester=? where id=",Arrays.asList(s.getName(),s.getRollNo(),s.getSemester(),s.getId()+""));

    }

    @MyTransactional
    public void insertStudents(){
        insertStudent(new Student("Farid","1","3th"));
        insertStudent(new Student("Murad","2","4th"));
        insertStudent(new Student("Omer","3","1th"));
    }

    @MyTransactional
    public void insertStudentsExp(){
        insertStudent(new Student("Emil","4","2th"));
        Student s= getAll().get(0);
        s.setName("Feda");
        updateStudent(s);
    }


}
