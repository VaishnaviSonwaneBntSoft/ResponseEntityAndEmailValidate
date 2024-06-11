package com.springboot.entity;


import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import javax.persistence.Entity;
import lombok.Setter;


//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table("studentrecords")
public class Student {

    @Id
    private int id;
    private String name;
    private int rollno;
    private String email;


    public void show()
    {
        System.out.println("From Student : "+id+" "+name+" "+rollno+" "+email);
    }

    @Override
    public String toString() {
       
        return "Student [id=" + id + ", name=" + name + ", rollno=" + rollno + ", email=" + email + "]";
    }
}

