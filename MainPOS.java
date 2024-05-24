import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class MainPOS extends JFrame implements ActionListener{
	
    JButton b1,b2,b3,b4,b5,b6,b7,b8;
    Connection conn;
    Statement st;
    Order ord;
    ResultSet set;
   login log;
    public MainPOS()
    {   
    	
    	addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
        System.exit(0);}});
    	 try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Database.mdb;DriverID=22;READONLY=true;) ","",""); 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 st=conn.createStatement();
    				 				 
    				 
	      }catch(Exception e){e.printStackTrace();}
    	
    	
    	Container con=getContentPane();
        con.setLayout(new BorderLayout());
        ord=new Order(st);
        
    	JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(150,100);}};
    	JPanel p11=new JPanel(){public Dimension getPreferredSize(){return new Dimension(150,218);}};
    
    	p11.setLayout(new GridLayout(8,1));
		JPanel p2=new JPanel();
    	
    	b1=new JButton("Educational");b1.addActionListener(this);p11.add(b1);
    	b2=new JButton("Horror");b2.addActionListener(this);p11.add(b2);
    	b3=new JButton("Suspense");b3.addActionListener(this);p11.add(b3);
    	b4=new JButton("Mystery");b4.addActionListener(this);p11.add(b4);
    	b5=new JButton("Teen Fiction");b5.addActionListener(this);p11.add(b5);
    	b6=new JButton("Romance");b6.addActionListener(this);p11.add(b6);
    	b7=new JButton("Fantasy");b7.addActionListener(this);p11.add(b7);
    	b8=new JButton("Fiction Magazines");b8.addActionListener(this);p11.add(b8);
    	p1.add(p11);
    	
    	con.add(p1,BorderLayout.WEST);con.add(ord,BorderLayout.CENTER);
    	
    	show();
    	setSize(750,450);
    	setLocation(200,200);
    	new login(this,st);
    }
    public void actionPerformed(ActionEvent e)
    	{
    		 if(e.getSource()==b1)
   		 	{
   		 	   new ItemList(ord,"Educational","1");
   		 	}
   		 	 if(e.getSource()==b2)
   		 	{
   		 	   new ItemList(ord,"Horror","2");
   		 	}
   		 		 if(e.getSource()==b3)
   		 	{
   		 	   new ItemList(ord,"Suspense","3");
   		 	}
   		 		 if(e.getSource()==b4)
   		 	{
   		 	   new ItemList(ord,"Mystery","4");
   		 	}
   		 		 if(e.getSource()==b5)
   		 	{
   		 	   new ItemList(ord,"Teen Fiction","5");
   		 	}
   		 			 if(e.getSource()==b6)
   		 	{
   		 	   new ItemList(ord,"Romance","6");
   		 	}
   		 			 if(e.getSource()==b7)
   		 	{
   		 	   new ItemList(ord,"Fantasy","7");
   		 	}
   		 			 if(e.getSource()==b8)
   		 	{
   		 	   new ItemList(ord,"Fiction Magazines","8");
   		 	}
    	}
    public static void main(String args[])
    	{
    	new 	MainPOS();
    	}   
}