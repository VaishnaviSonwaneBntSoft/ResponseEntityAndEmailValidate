package com.springboot.DAO;
import java.sql.*;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;

    public DBConfig()
    {
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
        }
        catch(Exception ex)
        {
            System.out.println("Exception in DBCofing : "+ex);
        }

    }
}
