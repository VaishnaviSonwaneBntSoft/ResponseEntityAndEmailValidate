package com.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "studentrecords")
public class Student {

    @Id
    private int id;
    private String name;
    private int rollno;
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void show()
    {
        System.out.println("From Student : "+id+" "+name+" "+rollno+" "+email);
    }

    @Override
    public String toString() {
       
        return "Student [id=" + id + ", name=" + name + ", rollno=" + rollno + ", email=" + email + "]";
    }
}

