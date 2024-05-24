 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class ItemList implements ActionListener 
        {
        
        
        	Statement st;
        	String title;
        	ResultSet set;
        	Order ord;
            JButton b1,b2;
            JList list;
            MyTable tab;
            String arr2[][];
            String arr[];
            String cate;
            public void load()
            {  arr=new String[2];
            	try
            	{   
            	    
            	    
            
            		set=st.executeQuery("Select * from Orders where category='"+cate+"'");
            		while(set.next())
            		{
            			arr[0]=set.getString("Name");
            			
            			tab.insert(arr);
            		}
            	
            	}catch(Exception ee){ee.printStackTrace();}
            	
            
            }
            public void actionPerformed(ActionEvent e)
            {
            	if(e.getSource()==b1)
            	{
            		 
            		    new ItemDetails(this,st,tab.getValue(tab.getTb().getSelectedRow(),0),ord);
            		 	//JOptionPane.showMessageDialog(aba,"New Purpose Added !");
            		 
            	}
            	if(e.getSource()==b2)
            	{
            	  
            	}
            }
        	
        	public ItemList(Order ord,String title,String cate)
        	{
        		 this.ord=ord; 
        		 this.cate=cate;	
        		 this.title=title;
        		 this.st=ord.getSt();      	
        		 JPanel p1=new JPanel();       		 
	           	 JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,200);}}; 
	             p2.setLayout(new BorderLayout());
	           	 
	           	 
	           	 b1=new JButton("Add");
	           	 b2=new JButton("Close");
	             String arr[]={"Item"};
                 tab=new MyTable(); tab.setData(arr2,arr);
	            
	            
	             b1.addActionListener(this);
	             b2.addActionListener(this);
	             
	             
	       
	             p2.add(new JScrollPane(tab.getTb()),BorderLayout.CENTER);
	             p1.add(b1);
	             	        		       		       
        		 Object[] message = new Object[2];          
                 message[0]=p2;
                 message[1]=p1;
                                         
             
                

                 String[] options = {""};
                load();
		    		int result = JOptionPane.showOptionDialog(
		    		ord,
		    		message,
		    		title,
		    		JOptionPane.DEFAULT_OPTION,
		    		JOptionPane.INFORMATION_MESSAGE,
		    		null,
		    		options,
		    		options[0]

						);
						
				 
               
           }
                
        }