package BSA;
import javax.swing.*;
import java.awt.*;

public class LogIn extends JFrame{
	public static String Logged_As,a;
	JButton acc,user;
	JPanel panel;
	JLabel label1,label2;
	JTextField text1,text2;
	LogIn()
	{
				
				
				label1 = new JLabel();
				label1.setText("Username/Account No.");
				text1 = new JTextField(15);
				
				
				label2=new JLabel();
				label2.setText("Password");
				text2=new JPasswordField(15);
				
				acc=new JButton("Login as Accountant");
				acc.addActionListener((e)->{
					
				if(Accountant_Checker.accountant_checker(text1.getText().trim(),text2.getText().trim()))	
				{
				Logged_As="Accountant";	
				User.A=new Accountant(BSA.User.UserName);
				this.dispose();}
				else
				{
					JOptionPane.showConfirmDialog(this, "NOT A VALID USERID OR PASSWORD!!!!", "Alert",JOptionPane.WARNING_MESSAGE);
				}
				
				});
				user=new JButton("Login as Customer");
				user.addActionListener((e)->{
				int j=0;	
				j=Account_Number.checkac_no(text1.getText().trim());
				a=text1.getText().trim();
				if(j==1)	
				{	
					
					Logged_As="Customer";
					//System.out.println(text2.getText());
					//System.out.println(".....");
					//System.out.println(Account_Number.Client_VALUEABLE.getac_Password());
					if(text2.getText().trim().equals(Account_Number.Client_VALUEABLE.getac_Password()))
					{	new Customer(Account_Number.Client_VALUEABLE.getac_Name());
					this.dispose();}
					
					else
					{
						JOptionPane.showConfirmDialog(this, "NOT A VALID ACCOUNT OR PASSWORD!!!!", "Alert",JOptionPane.WARNING_MESSAGE);
					}
				
				
				
				}
				
				else
				{
					JOptionPane.showConfirmDialog(this, "NOT A VALID ACCOUNT OR PASSWORD!!!!", "Alert",JOptionPane.WARNING_MESSAGE);
				}
				
				
				
				});
				
				panel = new JPanel(new GridLayout(12,12));
				panel.add(label1);
				panel.add(text1);
				panel.add(label2);
				panel.add(text2);
				panel.add(acc);
				panel.add(user);
				add(BorderLayout.CENTER,panel);
				setTitle("Login form");
				setVisible(true);
				setSize(400,400);
				setLocation(600,300);
				setResizable(false);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				}
	public static void main(String args[])
	{
		new LogIn();
	} 
		

}

