package BSA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class pass extends JFrame{ //This is confirming the Accountant's password for doing the job (adds security) 
	private JPasswordField password=new JPasswordField();
	private JLabel Pass=new JLabel("Password");
	private JLabel authentic=new JLabel(" ");
	private JButton but=new JButton("OK");
	private JPanel pan=new JPanel(new GridLayout(1,2));
	public static int option=0;//Option tells to edit,delete,withdraw,deposit and transfer
	public static int AMOUNT=0;
	public pass(int i)
	{	pass.option=i;
		setLayout(new GridLayout(3,1));
		setSize(500,200);
		setTitle("PASSWORD CONFIRMATION");
		pan.add(Pass);
		pan.add(password);
		add(pan);
		add(authentic);
		add(but);
		setVisible(true);
		setResizable(false);
		setLocation(600,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		but.addActionListener((e)->{
			
			try {
			if(LogIn.Logged_As.equals("Accountant"))
			{
				logged_as_Accountant();}
			if(LogIn.Logged_As.equals("Customer"))
			{
				logged_as_Customer();}
			}
			catch(Exception AE)
			{
				AE.printStackTrace();
			}
		
		});
		addWindowListener(new WindowAdapter()
		{		
			public void windowClosing(WindowEvent e)
			{	if(LogIn.Logged_As.equals("Accountant"))
				{
				if(pass.option==1)
				{BSA.User.add_edit_visibility(true);
				pass.option=0;}
				if(pass.option==2)
				{new Accountant(BSA.User.UserName);}
				if(pass.option==3)
				{new Accountant(BSA.User.UserName);}
				if(pass.option==4)
				{new Accountant(BSA.User.UserName);}
				if(pass.option==5)
				{new Accountant(BSA.User.UserName);}
				
				}
				if(LogIn.Logged_As.equals("Customer"))
				{new Customer(Account_Number.Client_VALUEABLE.getac_Name());}
			}
		
		});
	
	}	
	
	void logged_as_Accountant()
	{
		String pass=password.getText().trim();
		if(pass.equals(BSA.User.Password))
		{
			if(this.option==1)//Edit
			{
			JOptionPane.showMessageDialog(this,"DONE");
			this.dispose();
			Accountant A=new Accountant(BSA.User.UserName);
			add_edit.Edit_acc();
			BSA.User.a_e.dispose();
			this.option=0;
			}
			if(this.option==2)//Delete
			{
				JOptionPane.showMessageDialog(this,"DONE");
				this.dispose();
				new Accountant(BSA.User.UserName);
				add_edit.Delete_acc();
				this.option=0;
			}
			if(this.option==3)//Deposit
			{
				JOptionPane.showMessageDialog(this,"DONE");
				new Accountant(BSA.User.UserName);
				add_edit.Deposit(AMOUNT);
				this.dispose();
			}
			if(this.option==4)//Withdraw
			{
				JOptionPane.showMessageDialog(this,"DONE");
				new Accountant(BSA.User.UserName);
				add_edit.Withdraw(AMOUNT);
				this.dispose();
			}
			if(this.option==5)//Transfer
			{
				JOptionPane.showMessageDialog(this,"DONE");
				new Accountant(BSA.User.UserName);
				add_edit.Transfer(AMOUNT);
				this.dispose();
			}
		
		AMOUNT=0;
		}
		else
		{
			JOptionPane.showMessageDialog(this, "WRONG PASSWORD!!","Alert",JOptionPane.ERROR_MESSAGE);
			password.setText(null);
		}
	}
	
	void logged_as_Customer()
	{
		String pass=password.getText().trim();
		//System.out.println(Account_Number.Client_VALUEABLE.getac_Password());
		if(pass.equals(Account_Number.Client_VALUEABLE.getac_Password()))
		{
			if(this.option==5)//Transfer
			{
				JOptionPane.showMessageDialog(this,"DONE");
				new Customer(Account_Number.Client_VALUEABLE.getac_Name());
				add_edit.Transfer(AMOUNT);
				this.dispose();
			}
		
		AMOUNT=0;
		}
		else
		{
			JOptionPane.showMessageDialog(this, "WRONG PASSWORD!!","Alert",JOptionPane.ERROR_MESSAGE);
			password.setText(null);
		}
			
			
		
	}
	
}
