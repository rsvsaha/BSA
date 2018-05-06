package BSA;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Passchange extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
JButton OK;
JPanel panel;
JPasswordField pass1,pass2,pass3;
JLabel label1,label2,label3;

public Passchange()

	{
		label1 = new JLabel();
		label1.setText("Old Password");
		pass1 = new JPasswordField(15);
	
		label2 = new JLabel();
		label2.setText("New Password");
		pass2 = new JPasswordField(15);
	
		label3 = new JLabel();
		label3.setText("Confirm Password");
		pass3 = new JPasswordField(15);
	
		OK=new JButton("OK");
		OK.addActionListener((e)->{
			if((pass3.getText().equals("")))
			{
				JOptionPane.showConfirmDialog(this, "Empty Password Field!!s","Alert",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				if(pass3.getText().equals(pass2.getText()))
				{
					if(pass1.getText().equals(Account_Number.Client_VALUEABLE.getac_Password()))
						{
						
						add_edit.Change_acc_pass(pass3.getText());
						//System.out.println(pass3.getText());
						JOptionPane.showMessageDialog(this,"Password changed successfully");
						this.dispose();
						new Customer(Account_Number.Client_VALUEABLE.getac_Name());
						}

					else
						{
						JOptionPane.showConfirmDialog(this, "Old password doesn't exist!!","Alert",JOptionPane.WARNING_MESSAGE);
						this.dispose();
						new Customer(Account_Number.Client_VALUEABLE.getac_Name());
						}
				
						
				}
				
				else
				{
					JOptionPane.showConfirmDialog(this, "Password does not match","Alert",JOptionPane.WARNING_MESSAGE);
					
				}

				
				
				
			}
			
		
		
		});
	
			panel = new JPanel(new GridLayout(12,12));
			panel.add(label1);
			panel.add(pass1);
			panel.add(label2);
			panel.add(pass2);
			panel.add(label3);
			panel.add(pass3);
			panel.add(OK);
			add(BorderLayout.CENTER,panel);
			setVisible(true);
			setSize(400,400);
			setLocation(600,300);
			setTitle("CHANGE PASSWORD");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



			addWindowListener(new WindowAdapter() //This does a function when the window is closed
					{		
						public void windowClosing(WindowEvent e)
							{
							new Customer(Account_Number.Client_VALUEABLE.getac_Name());//Starts a new frame of the customer type on closing...or goes back to the beginning
							}
		
					});
	}
}






