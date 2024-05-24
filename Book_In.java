 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class Book_In implements ActionListener 
        {
        
        
        	Statement st;
        	String title;
        	ResultSet set;
        	Order ord;
            JButton b1;
            JList list;
            MyTable tab;
            String arr2[][];
            String arr[];
            String cate;
            String sbox[]={"Educational","Horror","Suspense","Mystery","Teen Fiction","Romance","Fantasy","Fiction Magazines"};
            public void load()
            {  
                	try
               	{
               		while(true)
               			{ 
               				tab.delete(0);
               			}
               	}
               	catch(Exception ee){}	
            	arr=new String[3];
            	try
            	{   
            	    
            	    
            
            		set=st.executeQuery("Select * from Orders order by Name");
            		while(set.next())
            		{
            			arr[0]=set.getString("Name");
            			arr[1]=sbox[Integer.parseInt(set.getString("Category"))-1];
            			arr[2]=set.getString("Quantity");
            			
            			tab.insert(arr);
            		}
            	
            	}catch(Exception ee){ee.printStackTrace();}
            	
            
            }
            public void actionPerformed(ActionEvent e)
            {
            	 
            		   String ss=JOptionPane.showInputDialog("Please Enter Quantity:");
            		   String cate="";
            		   int sss=Integer.parseInt(ss);
            		   try
            		   	{
            		   	    set=st.executeQuery("Select * from Orders where Name='"+tab.getValue(tab.getTb().getSelectedRow(),0)+"'");
            		   	    set.next();
            		   	    int myquan=Integer.parseInt(set.getString("Quantity"));
            		   	    System.out.println(myquan+"");
            		   	    int totalquan=sss+myquan;
            		   	    cate=set.getString("Category");
            		   	    st.execute("UPDATE  Orders set Quantity='"+totalquan+"' where Name='"+tab.getValue(tab.getTb().getSelectedRow(),0)+"'");
            		   	    
            		   	    
            	  set=st.executeQuery("Select count(*) as cc from Book_in where Name='"+tab.getValue(tab.getTb().getSelectedRow(),0)+"' and Date_in=Date()");set.next();
   				    
   				    if(Integer.parseInt(set.getString("cc"))!=0)
   				    	{
   				    		set=st.executeQuery("Select * from Book_in where Name='"+tab.getValue(tab.getTb().getSelectedRow(),0)+"' and Date_in=Date()");set.next(); 		
   				    		int myquan2=Integer.parseInt(set.getString("Quantity"));
   				    	
   				    		
   				    		int totalquan2=sss+myquan2;
   				    		
   				    		
   				    	
   				    		
   				    		st.execute("UPDATE  Book_in set Quantity='"+totalquan2+"', Category='"+cate+"' where Name='"+tab.getValue(tab.getTb().getSelectedRow(),0)+"' and Date_in=Date()");
   				    	}
   				    else{
   				    	
   				    
   			     
   			      	 		 
   			      	  	
   			       	 		 st.execute("Insert Into Book_in(Name,Category,Quantity) values('"+tab.getValue(tab.getTb().getSelectedRow(),0)+"','"+cate+"','"+sss+"')");
   			
   			         	}
            		   	}catch(Exception ee){}
            		 load();
            
            
            }
        	
        	public Book_In(Statement st)
        	{
        		     	
        		 this.st=st;    	
        		 JPanel p1=new JPanel();       		 
	           	 JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,200);}}; 
	             p2.setLayout(new BorderLayout());
	           	 
	           	 
	           	 b1=new JButton("Add");
	           
	             String arr[]={"Item Name","Category","Quantity"};
                 tab=new MyTable(); tab.setData(arr2,arr);
	            
	            
	             b1.addActionListener(this);
	            
	             
	             
	       
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