package BSA;
import javax.swing.*;
import BSA.ReaderWriter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
public class Account_Number extends JFrame{
	private int i=0;//(Choice of Operation) i=1 for EDIT,2 for REMOVE,3 for SPECIFIC ACCOUNT DETAILS,4 for DEPOSIT,5 for WITHDRAWL,6 for TRANSFER 
	
	
	private JPanel pan=new JPanel(new GridLayout(1,2));
	private JPanel pan1=new JPanel(new GridLayout(1,1));
	private JPanel pan2=new JPanel(new GridLayout(1,2));
	private JButton Button=new JButton("OK");
	private JLabel Acc_no=new JLabel("ACCOUNT NUMBER");
	private JTextField field=new JTextField(10);
	private static JLabel Name=new JLabel(); //These 2 are for showing the Name and Phone Number while deleting (EXTRA CAUTIOUS)
	private static JLabel Ph_no=new JLabel();
	
	
	public static Client Client_VALUEABLE=null;//DONOT DELETE ANY INSTANCES OF THIS VARIABLE....Stores the Searched account details....used for editing and displaying 
	 
	public Account_Number(int opt)//Parameterized Constructor for choosing type of operation 
	{
		Name.setText(null);
		Ph_no.setText(null);
		pan.add(Acc_no);
		pan.add(field);
		pan1.add(Button);
		pan2.add(Name);
		pan2.add(Ph_no);
		setLayout(new GridLayout(3,1));
		add(pan);
		add(pan2);
		add(pan1);
		this.i=opt;
		Button.addActionListener((e)->{doaction();});
		
		setTitle("ACCOUNT NUMBER");
		setVisible(true);
		setSize(400,200);
		setLocation(600,300);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter()//If Closed Begins from the start
				{		
					public void windowClosing(WindowEvent e)
					{
						Accountant A=new Accountant(BSA.User.UserName);
					}
				
				});
	}
 public static int checkac_no(String ac_id) //This function checks the account numbers existence...Can be called from anywhere
 {
	 int flag=0;
		String tac_id=ac_id;
		String data;
		ArrayList<Client> list;
		list=ReaderWriter.ReadfromFile();
		for(Client re:list)
		{
			data=re.getac_id();
			if((tac_id).equals(data))
			{
				Client_VALUEABLE=re;
				Name.setText("NAME:"+re.getac_Name());
				Ph_no.setText("PHONE:"+re.getac_Ph_no());
				User.index_array_list=list.indexOf(re);
				flag=1;
				break;
			}
		}
		return flag;
 
 }
 
 
 
 void resetFrame() //resets the frame in case of a wrong account number or warning message
	{
		this.dispose();
		new Account_Number(this.i);
	}
 	
 void doaction() 
	{


try{
	switch(i)//i denotes the case REMEMBER adding break is necessary
	{
		case 1://Editing Account 
			int j=checkac_no(field.getText().trim());
			if(j==1)
			{User.add_edit_init(1);
			User.add_edit_visibility(true);
			this.dispose();
			this.i=0;
			Name.setText(null);
			Ph_no.setText(null);
			}
			else
				{JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
				resetFrame();
				}
			break;
			
		case 2://Deleting Account
			int k=checkac_no(field.getText().trim());
			if(k==1)
			{
				int a=JOptionPane.showConfirmDialog(this,"Sure You Want to Delete This Account?", "Delete Account Confirmatio",JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.YES_OPTION)
				{new pass(2);
				this.dispose();
				this.i=0;}
				else
				{this.dispose();
				 new Accountant("user");
				}
			
			}
			else
			{JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
			resetFrame();
			}
		
			break;
	
		case 3://Viewing Specific Account
			int l=checkac_no(field.getText().trim());
			if(l==1)
			{
				new Trans_action(field.getText().trim());
				this.i=0;
				this.dispose();
			}
			else
			{JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
			resetFrame();
			}
			break;
		case 4://Deposit
			int m=checkac_no(field.getText().trim());
			if(m==1)
			{	new Deposit_Withdraw(1);
				this.dispose();
			this.i=0;}
			else
			{JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
			resetFrame();
			}
			break;
		case 5://WithDraw
			int n=checkac_no(field.getText().trim());
			if(n==1)
			{	new Deposit_Withdraw(2);
			this.dispose();	
			this.i=0;}
			else
			{JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
			resetFrame();
			}
			break;
		case 6://Transfer
			int o=checkac_no(field.getText().trim());
			if(o==1)
			{ new Transfer(1);
			this.dispose();
			this.i=0;}
			else
			{JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
			resetFrame();
			}
			break;
	}//for switch
   }//for try		
	catch(Exception e){resetFrame();}
}//for Do Action	
}

