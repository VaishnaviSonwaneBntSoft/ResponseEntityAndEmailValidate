package com.springboot.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.entity.Student;

@Repository
public class StudentRepoImpl extends DBConfig implements StudentRepo{

    @Override
    public void insertStudent(Student s) {
      try{
        s.show();
        System.out.println("hey i am in method");
        stmt = con.prepareStatement("insert into studentrecords values(?,?,?,?)");
        stmt.setInt(1, s.getId());
        stmt.setString(2, s.getName());
        stmt.setInt(3, s.getRollno());
        stmt.setString(4, s.getEmail());
        int v = stmt.executeUpdate();
        System.out.println("From Repo"+v);
        if(v>0)
        {
            System.out.println("Data Added Succsfully");
            }else{
            System.out.println("Data NOT Added Succsfully");

        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
      
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<Student>();
        try{
            stmt = con.prepareStatement("select *from studentrecords");
            rs = stmt.executeQuery();
            while(rs.next())
            {
                Student s = new Student();
                s.setId(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setRollno(rs.getInt(3));
                s.setEmail(rs.getString(4));
                System.out.println(s);
                list.add(s);
            }

            return list.size()>0?list:null;
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Student updateStudent(Student s, int id) {
        try{
            stmt = con.prepareStatement("update studentrecords set name=? , rollno=? , email=? , id=? where id=?");
            stmt.setString(1, s.getName());
            stmt.setInt(2, s.getId());
            stmt.setString(3, s.getEmail());
            stmt.setInt(4, s.getId());
            stmt.setInt(5, id);
            return (stmt.executeUpdate()>0)?s:null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteStudent(int id) {
       try{
            stmt = con.prepareStatement("delete from studentrecords where id=?");
            stmt.setInt(1, id);
            if(stmt.executeUpdate()>0)
                System.out.println("Data Deleted of Id : "+id);
            else
                System.out.println("Data Not Deleted ");
       }
       catch(Exception ex)
       {
            ex.printStackTrace();
       }
    }

}
