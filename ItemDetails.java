 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class ItemDetails implements TextListener
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            TextField t1,t2,t3;
            String selected;
            String cate;
            JLabel lab1;
            JPanel p3;
            Order ord;
            
            public void textValueChanged(TextEvent e)
			{
				t3.setText(Integer.parseInt(t1.getText())*Integer.parseInt(t2.getText())+"");
			}  
            public void load()
            {  arr=new String[4];
               String img="";
            	try
            	{   
            	    
            	    
                    
            		set=st.executeQuery("Select * from Orders where name='"+selected+"'");
            		while(set.next())
            		{
            			t1.setText(set.getString("Price"));
            			int ss=Integer.parseInt(set.getString("Category"));
            		    if(ss==1)cate="Breakfast Meal";
            		    if(ss==2)cate="Lunch Meal";
            		    if(ss==3)cate="Dinner Meal";
            		    if(ss==4)cate="Desserts";
            		    if(ss==5)cate="Beverages";
            		    img=set.getString("Image");
            		    t3.setText(t1.getText());
            		
            		}
            	
            	}catch(Exception ee){ee.printStackTrace();}
            	try
            		{
            			 ImageIcon icon = new ImageIcon(img);
	                     lab1 = new JLabel( icon);
	                     p3.add(lab1);
            		}
                catch(Exception ee)
                	{
                	
                	}
            }
        
          
        	
        	public ItemDetails(ItemList itemlist,Statement st,String selected,Order ord)
        	{
        		 this.st=st;
        		 this.ord=ord;
        		 this.selected=selected;
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,100);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(3,2));
        		 p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(120,100);}};p3.setBackground(Color.GREEN);
        		 p3.setLayout(new BorderLayout());
        		 
        		 t1=new TextField(15);
        		 t2=new TextField("1",15);
        		 t3=new TextField(15);
        		 
        		 p2.add(new JLabel("Item Price"));p2.add(t1);t1.setEditable(false);
        		 p2.add(new JLabel("Quantity"));p2.add(t2);t2.addTextListener(this);
        		 p2.add(new JLabel("Total"));p2.add(t3);t3.setEditable(false);
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		 p1.add(p3,BorderLayout.WEST);
        		 
        		 Object[] message = new Object[1];          
                 message[0]=p1;
                                         
             
                

                 String[] options = {"OK","CANCEL"};
                 load();
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
						{   arr=new String[4];
							arr[0]=selected;
							arr[1]=cate;
							arr[2]=t2.getText();
							arr[3]=t3.getText();
							ord.setOrder(arr);
							ord.totalme();
							ord.setCash();
						}
						
				 
               
           }
                
        }