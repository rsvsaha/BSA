package BSA;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.lang.*;


public class Transfer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton button=new JButton("Transfer");
	
	private JLabel label1,label2,label3;
	private JTextField text1,text2;
	static Client Client_TRANSFER;
	static int opt=0;
	static ArrayList<Client> Client_list_Transfer=null;
 	public Transfer(int i)
	{	this.opt=i;
		Container con=getContentPane();
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		text1=new JTextField(10);
		text2=new JTextField(10);
		
		
		if(this.opt==1)
		{label1.setText("Account number:"+Account_Number.Client_VALUEABLE.getac_id()+" Name:"+Account_Number.Client_VALUEABLE.getac_Name());}
		label2.setText("Transfer to Account");
		label3.setText("Amount ");
		JPanel pan1=new JPanel();
		JPanel pan2=new JPanel();
		JPanel pan3=new JPanel();
		
		
		pan1.setLayout(new GridLayout(1,1));
		GridLayout layout=new GridLayout(2,2);
		layout.setHgap(10);
		layout.setVgap(10);
		pan2.setLayout(layout);
		pan3.setLayout(new GridLayout(1,1));
		pan1.add(label1);
		pan2.add(label2);
		pan2.add(text1);
		pan2.add(label3);
		pan2.add(text2);
		pan3.add(button);
		button.addActionListener((e)->
		{	
			if(isNumeric(text2.getText().trim()))
			{
				if(text1.getText().trim().equals(Account_Number.Client_VALUEABLE.getac_id()))	
				{
					JOptionPane.showMessageDialog(this, "TRYING TO TRANSFER TO SAME ACCOUNT!!","Error",JOptionPane.WARNING_MESSAGE);
					
				}
				else
				{
					if(text2.getText().trim().equals(""))
					{
						JOptionPane.showMessageDialog(this, "PLEASE ENTER AN AMOUNT","Error",JOptionPane.WARNING_MESSAGE);

						
					}
					else
					{
						int n=checkac_no(text1.getText().trim());
						
						if(Integer.parseInt(Account_Number.Client_VALUEABLE.getac_Amt())>Integer.parseInt(text2.getText().trim()))
						{
						if(n==1)
						{
								int a=JOptionPane.showConfirmDialog(this,"Transfer to: "+Client_TRANSFER.getac_Name(), "Transfer Confirmation",JOptionPane.YES_NO_OPTION);
								if(a==JOptionPane.YES_OPTION)
								{	pass.AMOUNT=Integer.parseInt(text2.getText().trim());
									new pass(5);
									this.dispose();
								
								}
								
						}
						else
						{
							JOptionPane.showMessageDialog(this, "ACCOUNT NUMBER NOT FOUND","Error",JOptionPane.WARNING_MESSAGE);
						}
						
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
		con.setLayout(new BorderLayout());
		con.add(pan1,BorderLayout.NORTH);
		
		
		con.add(pan2,BorderLayout.CENTER);
		con.add(pan3,BorderLayout.SOUTH);
		
		setSize(300,300);
		setLocation(600,300);
		setResizable(false);
		setVisible(true);
		setTitle("TRANSFER");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{		
			
			public void windowClosing(WindowEvent e)
			{	if(LogIn.Logged_As.equals("Accountant"))
				{Accountant A=new Accountant(BSA.User.UserName);}
				if(LogIn.Logged_As.equals("Customer"))
				{new Customer(Account_Number.Client_VALUEABLE.getac_Name());}
			
			}
		
		});
		
		
		
		
	}
	
 	boolean isNumeric(String s) {
 	    return java.util.regex.Pattern.matches("\\d+", s);
 	}

 	static int checkac_no(String ac_id) //This function checks the account numbers existence...Can be called from anywhere
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
					Client_TRANSFER=re;
					Client.Client_arraylist_index=list.indexOf(re);
					flag=1;
					break;
				}
			}
			return flag;
	 
	 }

	
	

}

