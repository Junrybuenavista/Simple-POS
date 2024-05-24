 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class Reports implements ActionListener 
        {
        
        
        	Statement st;
        	String title;
        	ResultSet set;
        	JComboBox comboM,comboD,comboY;
        	Order ord;
            JButton b1;
            JList list;
            MyTable tab;
            String arr2[][];
            String arr[];
            String mydate;
            public void load()
            {  arr=new String[4];
               try
               	{
               		while(true)
               			{ 
               				tab.delete(0);
               			}
               	}
               	catch(Exception ee){}
            	try
            	{   
            	    
            	    
            	    
                    //set=st.executeQuery("Select * from Report where Date_Purchase = #10/01/2014# ");
            		  set=st.executeQuery("Select * from Report where Date_Purchase = #"+(comboM.getSelectedIndex()+1)+"/"+(comboD.getSelectedIndex()+1)+"/"+comboY.getItemAt(comboY.getSelectedIndex())+"#");
            		while(set.next())
            		{
            			arr[0]=set.getString("Name");
            			arr[1]=set.getString("Quantity");
            			arr[2]=set.getString("Price");
            			arr[3]=set.getString("Total_Price") ;
            			
            			tab.insert(arr);
            		}
            	
            	}catch(Exception ee){ee.printStackTrace();}
            	
            
            }
            public void actionPerformed(ActionEvent e)
            {
                 load();
            }
        	
        	public Reports(Order ord,String title,String mydate)
        	{
        		 this.ord=ord; 
        		 this.mydate=mydate;	
        		 this.title=title;
        		 this.st=ord.getSt();      	
        		 JPanel p1=new JPanel();       		 
	           	 JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(600,400);}}; 
	             p2.setLayout(new BorderLayout());
	           	 
	           	 setCombo();
	           	 b1=new JButton("Search");
	           	
	             String arr[]={"Item Name","Total Quantity","Price","Total Price"};
                 tab=new MyTable(); tab.setData(arr2,arr);
	            
	            
	             b1.addActionListener(this);
	       
	             
	             
	       
	             p2.add(new JScrollPane(tab.getTb()),BorderLayout.CENTER);
	             p1.add(comboM);p1.add(comboD);p1.add(comboY);p1.add(b1);
	             	        		       		       
        		 Object[] message = new Object[2];          
                 message[0]=p2;
                 message[1]=p1;
                                         
             
                

                 String[] options = {""};
               
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
           
         public void setCombo()
         	{
         		String s1[]={"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
         	    String s2[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
         	    String s3[]={"2014","2015","2016","2017","2018"};	
         		comboM=new JComboBox(s1);comboD=new JComboBox(s2);comboY=new JComboBox(s3);
         	}  
        	public String getMonth(int i)
    		{
    			String ret="";
    			if(i==1)ret="January";
    			if(i==2)ret="February";
    			if(i==3)ret="March";
    			if(i==4)ret="April";
    			if(i==5)ret="May";
    			if(i==6)ret="June";
    			if(i==7)ret="July";
    			if(i==8)ret="August";
    			if(i==9)ret="September";
    			if(i==10)ret="October";
    			if(i==11)ret="November";
    			if(i==12)ret="December";
    			return ret;
   		 } 		      
        }