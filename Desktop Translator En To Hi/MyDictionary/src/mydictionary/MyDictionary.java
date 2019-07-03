
package mydictionary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class MyDictionary implements ActionListener {

    int height,width;
    JFrame frame;
    JTextField englishWord;
    JLabel englishWordLabel,hindiWord,hindiWordLabel;
    JButton show,button,get;
    Translator translator;
    MyDatabase mydatabase;
    static String output,input;
    int showclick=0;
    
    void findDesktopSize()
    {
        Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
        height=(int)dimension.getHeight();
        width=(int)dimension.getWidth();
    }
    
    void createFrame()
    {
        frame=new JFrame("MyDictionary");
        frame.setSize(width/2, height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
 
        
    }
    
    void addItemToFrame()
    {
        
        englishWordLabel=new JLabel("ENLISH WORD");
        englishWordLabel.setBounds(100, 80, 100, 30);
        englishWordLabel.setBackground(Color.DARK_GRAY);
        englishWordLabel.setForeground(Color.white);
        
        englishWord=new JTextField();
        englishWord.setBounds(300, 80, 200, 30);
        englishWord.setBackground(Color.DARK_GRAY);
        englishWord.setForeground(Color.WHITE);
        englishWord.setHorizontalAlignment(JTextField.CENTER);
        
        hindiWordLabel=new JLabel("IN HINDI");
        hindiWordLabel.setBounds(100, 160, 100, 30);
        hindiWordLabel.setBackground(Color.DARK_GRAY);
        hindiWordLabel.setForeground(Color.WHITE);
        
        show=new JButton("SHOW");
        show.setBounds(500, 160, 100, 30);
        show.setBackground(Color.DARK_GRAY);
        show.setForeground(Color.BLUE);
        show.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
           
        hindiWord=new JLabel("");
        hindiWord.setBounds(250,130,200,100);
        hindiWord.setBackground(Color.DARK_GRAY);
        hindiWord.setForeground(Color.WHITE);
        hindiWord.setFont(new Font("TimesRoman",Font.PLAIN,20));
        hindiWord.setVisible(false);
        
        button=new JButton("ADD");
        button.setBounds(200,250,60,30);
        button.setBackground(Color.GREEN);
        button.setForeground(Color.BLACK);
       
        get=new JButton("GET");
        get.setBounds(400, 250, 60, 30);
        get.setBackground(Color.GREEN);
        get.setForeground(Color.BLACK);
        
        show.addActionListener(this);
        button.addActionListener(this);
        get.addActionListener(this);
        
        frame.add(englishWordLabel);
        frame.add(englishWord);
        frame.add(hindiWordLabel);
        frame.add(show);
        frame.add(hindiWord);
        frame.add(button);
        frame.add(get);
        
    }
    MyDictionary()
    {
        findDesktopSize();
        createFrame();
        addItemToFrame();
        frame.setVisible(true);
        translator=new Translator();
        
        try
        {
            mydatabase=new MyDatabase();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Connection Not Establish With Database");
        }
    }
    
    public static void main(String[] args) {
       MyDictionary obj=new MyDictionary();
       
     
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==show)
        {
          try
          {
               input=englishWord.getText().toString();
               output=translator.EnglishToHindi(input);
               hindiWord.setText(output);
               hindiWord.setVisible(true);
               showclick=1;
          }catch(Exception e)
          {
              JOptionPane.showMessageDialog(null, "Please Check Yout Internet Connection");
          }
        }else if(ae.getSource()==button)
        {
            try {
                
                     input=englishWord.getText().toString();
                     mydatabase.insert(input);
                     JOptionPane.showMessageDialog(null, "Insert Successfully");
              
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Some SQL Error");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Some Error");
            }
        }else if(ae.getSource()==get)
        {
            try {
                new Choose();
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Some SQL Error");
            }
        }
    }
}