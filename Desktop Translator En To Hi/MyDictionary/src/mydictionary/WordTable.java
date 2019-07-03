/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class WordTable  {
   int height,width;
   JFrame frame;
   JTable table;
   JScrollPane paneTable;
   void findDesktopSize()
   {
       Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
       width=(int)dimension.getWidth();
       height=(int)dimension.getHeight();
   }
   
   void createFrame()
   {
       frame=new JFrame("MyDictionary");
       frame.setSize(width, height);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
  
   void addTable(String[][] data,String[] column)
   {
      
       table=new JTable(data,column);
       table.setBounds(30, 40, 200, 300);
       
       paneTable=new JScrollPane(table);
    
       paneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       paneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
       frame.add(paneTable);
   }
   
  
   
   WordTable(String[][] data,String[] column) throws SQLException
   {
       findDesktopSize();
       createFrame();
       addTable(data,column);
       
       frame.setVisible(true);
      
   }
   
  
 
   

   

}
