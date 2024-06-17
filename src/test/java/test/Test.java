/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dane.dao.ItemDAO;

/**
 *
 * @author cyclingbd007
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        
        ItemDAO dao = new ItemDAO();
        System.out.println(dao.getItem(1));
    	
//    	Connection conn = DriverManager.getConnection("jdbc:sqlserver://DANE-PC;instanceName=SQLEXPRESS;databaseName=OBSTest;integratedSecurity=true;");
//    	System.out.println("Driver version: " + conn.getMetaData().getDriverVersion()); 
    }
}
