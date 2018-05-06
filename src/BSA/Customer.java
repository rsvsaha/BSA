package BSA;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;

public class Customer extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Username;
	private JButton button[]=new JButton[4];
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private LocalDate d=User.logindate;
	private LocalTime t=User.logintime;
	private JLabel Acc_Id=new JLabel();
	private JLabel login_time=new JLabel();
	private JLabel login_date=new JLabel(); 
	public Customer(String User)
	{
		setTitle("Customer Login");
		this.Username=User;
		Acc_Id.setText("Logged in as: "+Username);
		login_date.setText("Log in Date:"+d);
		login_time.setText("Log in Time:"+t);
		for(int i=0;i<4;i++)
		{
			button[i]=new JButton();
		}
		button[0].setText("TRANSACTION HISTORY");
		button[0].addActionListener((e)->{
			String b=LogIn.a;
			new Trans_action(b);
			this.dispose();
	});
		button[1].setText("TRANSFER");
		button[1].addActionListener((e)->{
			new Transfer(2);
			this.dispose();
		});
		button[2].setText("LOG OUT");
		button[2].addActionListener((e)->{
			int a=JOptionPane.showConfirmDialog(this, "Sure You Want To Log Out?","Alert",JOptionPane.YES_NO_OPTION);
			if(a==JOptionPane.YES_OPTION)
			{new LogIn();
			dispose();}

		});
		button[3].setText("CHANGE PASSWORD");
		button[3].addActionListener((e)->{
			new Passchange();
			this.dispose();
		});
		panel2.setLayout(new GridLayout(1,1));
		panel3.setLayout(new GridLayout(2,1));
		GridLayout layout=new GridLayout(4,1);
		layout.setHgap(10);
		layout.setVgap(10);
		panel1.setLayout(layout);
		Container C=getContentPane();
		C.setLayout(new BorderLayout());
		
		for(int i=0;i<4;i++)
		{
			panel1.add(button[i]);
		}
	
		panel2.add(Acc_Id);
		panel3.add(login_date);
		panel3.add(login_time);
		
		C.add(panel2,BorderLayout.NORTH);
		C.add(panel1,BorderLayout.CENTER);
		C.add(panel3,BorderLayout.SOUTH);
	
		setSize(400,400);
		setResizable(false);
		setVisible(true);
		setLocation(600,300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
		{		
			
			public void windowClosing(WindowEvent e)
			{	
				
				new LogIn();
							
			}
	
	
		});
	
	
	}
}

