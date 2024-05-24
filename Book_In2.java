 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
 
 
public class Book_In2 implements ActionListener
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            JTextField t1,t2,t3;
            JComboBox box;
            String selected;
            String cate;
            JLabel lab1;
            JPanel p3;
            Order ord;
            
           
           public void actionPerformed(ActionEvent e)
           	{  
           		Vector vec =new Vector();
           		try
           		{
           		
           		    set=st.executeQuery("Select * from Orders where Name='"+t1.getText()+"'");set.next(); 
           		}catch(Exception ee){}
           	}
          
        	
        	public Book_In2(Statement st)
        	{
        		 this.st=st;
        		 this.ord=ord;
        		 this.selected=selected;
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,100);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(4,2));
        		
        		 
        		 t1=new JTextField(15);
        		 t2=new JTextField(15);
        		 t3=new JTextField(15);
        		 String sbox[]={"Educational","Horror","Suspense","Mystery","Teen Fiction","Romance","Fantasy","Fiction Magazines"};
        	
        		 box=new JComboBox(sbox);
        		 box.addActionListener(this);
        		 p2.add(new JLabel("Item Name"));p2.add(t1);
        		 p2.add(new JLabel("Category"));p2.add(box);
        		 p2.add(new JLabel("Price"));p2.add(t3);
        		 p2.add(new JLabel("Quantity"));p2.add(t2);
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		
        		 
        		 Object[] message = new Object[1];          
                 message[0]=p1;
                                         
             
                

                 String[] options = {"OK","CANCEL"};
          
		    		int result = JOptionPane.showOptionDialog(
		    		ord,
		    		message,
		    		"",
		    		JOptionPane.DEFAULT_OPTION,
		    		JOptionPane.INFORMATION_MESSAGE,
		    		null,
		    		options,
		    		options[0]

						);
					if(result==0)
						{  
							
							
   				    		 try
   				    		 	{
   				    		 		st.execute("Insert Into Orders(Name,Category,price,Quantity,image) values('"+t1.getText()+"','"+(box.getSelectedIndex()+1)+"','"+t3.getText()+"','"+t2.getText()+"','image\\noimage.jpg')");
   				    		 		st.execute("Insert Into Book_in(Name,Category,Quantity) values('"+t1.getText()+"','"+(box.getSelectedIndex()+1)+"','"+t2.getText()+"')");
   				    		 		JOptionPane.showMessageDialog(null,"Data Save !");
   				    		 	}
   				    		 catch(Exception eee){}	
   				    
   				    	}
   				  
   				
   			  
   			  	
   		 
					
			
						
				 
               
           }
                
        }