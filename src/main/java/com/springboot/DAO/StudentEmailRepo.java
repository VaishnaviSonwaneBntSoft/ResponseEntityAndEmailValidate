package com.springboot.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Email;

public interface StudentEmailRepo extends JpaRepository<Email , Integer>{

}
