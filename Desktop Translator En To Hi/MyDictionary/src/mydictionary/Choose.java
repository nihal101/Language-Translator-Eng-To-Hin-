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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author NIHAL SINGH
 */
public class Choose implements ActionListener{
    int height,width;
    JFrame frame;
    JTextField search;
    JButton get;
    MyDatabase mydatabase;
    ArrayList dataEnglish,dataHindi;
    ResultSet[] rs;
    String[][] data;
    String[] column;
    
    void findDesktopSize()
    {
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        height=(int)dimension.getHeight();
        width=(int)dimension.getWidth();
    }
    
     void createFrame()
    {
        frame=new JFrame("MyDictionary");
        frame.setSize(width/3, height/3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
 
        
    }
     
      void addSearch()
   {
       search=new JTextField();
       search.setBounds(100, 40, 200, 30);
       search.setBackground(Color.BLACK);
       search.setForeground(Color.WHITE);
       search.setHorizontalAlignment(JTextField.CENTER); 
       get=new JButton("GET");
       get.setBounds(160, 100, 100, 30);
       get.setBackground(Color.GREEN);
       
       get.addActionListener(this);
       
       frame.add(search);
       frame.add(get);
   }
      
   Choose() throws SQLException
   {
       findDesktopSize();
       createFrame();
       addSearch();
       mydatabase=new MyDatabase();
       frame.setVisible(true);
       
   }
   
   
   
    void addItemToArrayList() throws SQLException, Exception
   {
       dataEnglish=new ArrayList();
       dataHindi=new ArrayList();
       String str;
       Translator translator=new Translator();
       for(int i=0;i<rs.length;i++)
       {
           while(rs[i].next())
           {
               str=rs[i].getString(1);
               System.out.println(str);
              dataEnglish.add(str);
              dataHindi.add(translator.EnglishToHindi(str));
           }
       }
   }
   
    void addItemToString()
   {
       data=new String[dataEnglish.size()][2];
       for(int i=0;i<data.length;i++)
       {
           data[i][0]=(String)dataEnglish.get(i);
           data[i][1]=(String)dataHindi.get(i);
       }
       column=new String[2];
       column[0]="English Word";
       column[1]="Hindi Word";
       
   }
     

    @Override
    public void actionPerformed(ActionEvent ae) {
        
         if(ae.getSource()==get)
        {
            String str=search.getText().toString();
            if(str.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Empty Field");
            }else
            {

                try
                {
                 rs=mydatabase.select(search.getText().toString());
                 addItemToArrayList();
                 addItemToString();
                 new WordTable(data,column);
                }catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(null,"SQL Error");
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Some Error");
                }
            }
    }
    }
}
