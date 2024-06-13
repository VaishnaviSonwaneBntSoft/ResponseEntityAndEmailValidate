package com.springboot.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.Student;

@Repository
public interface StudentJpaRepo  extends JpaRepository<Student , Integer>{
   boolean existsByEmail(String email);
}
