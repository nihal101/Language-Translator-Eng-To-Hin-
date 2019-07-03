/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDatabase {
    Connection connection;
    static String databaseURL="jdbc:mysql://localhost:3306/mydictionary";
    static String user="root";
    static String password="root";
    String query;
    char fisrtCharacter;
    Statement statement;
    ResultSet[] rs;
    MyDatabase() throws SQLException 
    {
        connection=DriverManager.getConnection(databaseURL,user,password);    
    }
    
 
  boolean insert(String englishWord) throws SQLException
  {
      int result;
      fisrtCharacter=englishWord.charAt(0);
      query=String.format("insert into "+fisrtCharacter+" values ('%s')",englishWord);
      System.out.println(query);
      statement=connection.createStatement();
      result=statement.executeUpdate(query);
      if(result==0)
      {
          return false;
      }else
      {
          return true;
      }
  }
    
  ResultSet[] select(String string) throws SQLException
  {
     
      statement=connection.createStatement();
      if(string.equalsIgnoreCase("All"))
      {
          rs=new ResultSet[26];
          for(int i=0;i<26;i++)
          {
              query="select * from "+(char)(65+i);
              ResultSet rs1=statement.executeQuery(query);
              rs[i]=statement.executeQuery(query);
          }
      }else
      {
         query="select * from  "+string.charAt(0)+" where EnglishWord like '"+string+"%'";
          rs=new ResultSet[1];
          rs[0]=statement.executeQuery(query);
      }
      return rs;
  }
    
}
