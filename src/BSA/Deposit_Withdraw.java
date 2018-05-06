package BSA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Deposit_Withdraw extends JFrame {
	private JLabel lb1,lb2,lb3;
	private static JTextField txt1;
	private JPanel pan1,pan2,pan3;
	private JButton btn;
	static ArrayList<Client> list4;
	private int i=0;
	public Deposit_Withdraw(int opt)
	{
		this.i=opt;
		lb1=new JLabel();
		lb1.setText("Account number:"+Account_Number.Client_VALUEABLE.getac_id()+" Name:"+Account_Number.Client_VALUEABLE.getac_Name());
		lb2=new JLabel();
		lb2.setText("Current Balance:"+Account_Number.Client_VALUEABLE.getac_Amt());
		if(this.i==1)
		{lb3=new JLabel("Deposit Amount");}
		if(this.i==2)
		{lb3=new JLabel("Withdraw Amount");}
		txt1=new JTextField(10);

		btn=new JButton("OK");
		Container con=getContentPane();
		con.setLayout(new GridLayout(10,1));
		pan1=new JPanel(new GridLayout(2,1));
		pan1.add(lb1);
		pan1.add(lb2);
		con.add(pan1);

		pan2=new JPanel(new GridLayout(1,2));
		pan2.setSize(200, 100);
		pan2.add(lb3);
		pan2.add(txt1);
		con.add(pan2);
		
		pan3=new JPanel(new GridLayout(1,1));
		pan3.add(btn);
		con.add(pan3);
		
		btn.addActionListener((e)->{
			if(isNumeric(txt1.getText().trim()))
			{
				if(txt1.getText().trim().equals(""))
				{
					JOptionPane.showMessageDialog(this, "PLEASE ENTER AN AMOUNT","Error",JOptionPane.WARNING_MESSAGE);

				}
				else
				{
					if(this.i==1)
					{pass.AMOUNT=Integer.parseInt(txt1.getText().trim());
						new pass(3);
					this.dispose();
					}
					if(this.i==2)
					{
						
						
					if(Integer.parseInt(Account_Number.Client_VALUEABLE.getac_Amt())>Integer.parseInt(txt1.getText().trim()))	
					{
						pass.AMOUNT=Integer.parseInt(txt1.getText().trim());
						new pass(4);
						this.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(this, "INSUFFICIENT BALANCE","Error",JOptionPane.WARNING_MESSAGE);
						
					}
					
					
					
					}
					
					
					
				}
			

			}
		
			else
			{
				JOptionPane.showMessageDialog(this, "ENTER VALID AMOUNT!!","Error",JOptionPane.WARNING_MESSAGE);
				
			}
		
		
		
		});

	
		if(this.i==1)
		{setTitle("DEPOSIT");}
		if(this.i==2)
		{setTitle("WITHDRAW");}
		setSize(400,400);
		setLocation(600,300);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		addWindowListener(new WindowAdapter()
				{		
					public void windowClosing(WindowEvent e)
					{
						Accountant A=new Accountant(BSA.User.UserName);
					}
				
				});
	}

		
	boolean isNumeric(String s) {
 	    return java.util.regex.Pattern.matches("\\d+", s);
 	}
	
	public void resetFrame()
		{
			this.dispose();}
}
