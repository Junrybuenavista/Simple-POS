import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;

public class Order extends JPanel implements ActionListener,TextListener
{  
  
    MyTable tab;
    String arr2[][]=null; 
    
    TextField t1,t2,t3,t4,t5;
    JButton b1,b3;
    Statement st;
    ResultSet set;
    DecimalFormat form;
    public void setCash(){t2.setText("");t3.setText("");}
    public void textValueChanged(TextEvent e)
			{
				t3.setText(form.format(Double.parseDouble(t2.getText())-Double.parseDouble(t4.getText())));
			} 
    public Order(Statement st)
    {    
        form=new DecimalFormat(".00");
    	this.st=st;
    	GridLayout grid=new GridLayout(5,2);
    	grid.setVgap(6);
    	setLayout(new BorderLayout());
    	JPanel ptab=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,220);}};
    	ptab.setLayout(new BorderLayout());
        JPanel p11=new JPanel(){public Dimension getPreferredSize(){return new Dimension(150,150);}};
        JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(350,120);}};
        JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(350,40);}};
        
        p1.setLayout(grid);
        String arr[]={"Item","Category","Quantity","Price"};
                       
        tab=new MyTable();
        tab.setData(arr2,arr);
        b1=new JButton("        CASH-IN           ");b1.addActionListener(this);
       
        b3=new JButton("INVENTORY AND REPORTS");b3.addActionListener(this);
        t1=new TextField(15);
        t2=new TextField(15);t2.addTextListener(this);
        t3=new TextField(15);
        t4=new TextField(15);
        t5=new TextField(15);
        
        p1.add(new JLabel("NET PRICE"));p1.add(t5);t5.setEditable(false);
        p1.add(new JLabel("TOTAL VAT"));p1.add(t1);t1.setEditable(false);
        p1.add(new JLabel("TOTAL PRICE"));p1.add(t4);t4.setEditable(false);
        
        p1.add(new JLabel("CASH"));p1.add(t2);
        p1.add(new JLabel("CHANGE"));p1.add(t3);t3.setEditable(false);
        p11.add(p1);
        
        p3.add(b1);p3.add(b3);
        ptab.add(new JScrollPane(tab.getTb()));
        add(ptab,BorderLayout.NORTH);
        add(p11,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);
        
        
           	
    }
    public void actionPerformed(ActionEvent e)
   	{  
   		if(e.getSource()==b1)
   			{  
   				
   				record();
   				try
               	{
               		while(true)
               			{ 
               				tab.delete(0);
               			}
               	}
               	catch(Exception ee){}
               	t1.setText("");
               	t2.setText("");
               	t3.setText("");
               	t4.setText("");
               	t5.setText("");
               	JOptionPane.showMessageDialog(this,"Data Save !");
   			}
   		
   			if(e.getSource()==b3)
   			{
   				new Inventory(this,"Inventory","");
   			}	
    		
   	}
   	public void totalme()
   		{
   	double total=0;
   	int ctr=0;		
   		try{
   			
   			while(true)
   			{
   				total+=Double.parseDouble(tab.getValue(ctr,3));
   				ctr++;	
   			}
   		   }catch(Exception ee)
   		   	{
   		   	}
   		   	
   	      t4.setText(form.format(total)+"");
   	      t1.setText(form.format(total*0.12)+"");
   	      t5.setText(form.format(total-Double.parseDouble(t1.getText()))+"");
   	      	   	
   		}
   	public void record()
   		{
   	     	
   		 try{
   			
   			for(int ii=0;ii<tab.getcount();ii++)
   			{
   			        
   			        
   			        
   			   
   				    set=st.executeQuery("Select count(*) as cc from Report where Name='"+tab.getValue(ii,0)+"' and Date_Purchase=Date()");set.next();
   				    
   				    if(Integer.parseInt(set.getString("cc"))!=0)
   				    	{
   				    		set=st.executeQuery("Select * from Report where Name='"+tab.getValue(ii,0)+"' and Date_Purchase=Date()");set.next();
   				    		int myprice=Integer.parseInt(set.getString("Price"));
   				    		int myquan=Integer.parseInt(set.getString("Quantity"));
   				    		System.out.println(myprice);
   				    		System.out.println(myquan);
   				    		
   				    		int totalquan=Integer.parseInt(tab.getValue(ii,2))+myquan;
   				    		int totalprice=totalquan*myprice;
   				    		
   				    		System.out.println(totalprice);
   				    		System.out.println(totalquan);
   				    		
   				    		
   				    		st.execute("UPDATE  Report set Quantity='"+totalquan+"', Total_Price='"+totalprice+"' where Name='"+tab.getValue(ii,0)+"' and Date_Purchase=Date()");
   				    		
   				    	    set=st.executeQuery("Select * from orders where Name='"+tab.getValue(ii,0)+"'");set.next();
   				    	    int updatedquan=Integer.parseInt(set.getString("Quantity"))-Integer.parseInt(tab.getValue(ii,2));
   				    	    
   				    	    st.execute("UPDATE  orders set Quantity='"+updatedquan+"' where Name='"+tab.getValue(ii,0)+"'");
   				    	}
   				    else{
   				    	
   				    
   			     
   			      	 		 
   			      	  	
   			       	 	    st.execute("Insert Into Report(Name,Quantity,price,Total_Price) values('"+tab.getValue(ii,0)+"','"+tab.getValue(ii,2)+"','"+(Integer.parseInt(tab.getValue(ii,3))/Integer.parseInt(tab.getValue(ii,2)))+"','"+tab.getValue(ii,3)+"')");
   			       	 	    set=st.executeQuery("Select * from orders where Name='"+tab.getValue(ii,0)+"'");set.next();
   				    	     int updatedquan=Integer.parseInt(set.getString("Quantity"))-Integer.parseInt(tab.getValue(ii,2));
   				    	    
   				    	    st.execute("UPDATE  orders set Quantity='"+updatedquan+"' where Name='"+tab.getValue(ii,0)+"'");
   			
   			         	}
   				
   			  
   			   }	
   		 	
   		    }catch(Exception ee){ee.printStackTrace();}
   		   
   		   	
   	        	      	   	
   		}
   			
   	public void setOrder(String ss[])
   		{
   		  
   		   tab.insert(ss);
   			
   		}
   	public Statement getSt(){return st;}	   
}