package BSA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.*;
import java.time.LocalDate;
import java.time.LocalTime;
public class Accountant extends JFrame {
	private String Username;
	private JButton button[]=new JButton[9];
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private LocalDate d=User.logindate;
	private LocalTime t=User.logintime;
	private JLabel login_info=new JLabel();
	private JLabel login_time=new JLabel();
	private JLabel login_date=new JLabel(); 
	public Accountant(String User)
	{
		setTitle("Accountant Login");
		this.Username=User;
		login_info.setText("Logged in as: "+BSA.User.UserName);
		login_date.setText("Log in Date:"+d);
		login_time.setText("Log in Time:"+t);
		for(int i=0;i<9;i++)
		{
			button[i]=new JButton();
			button[i].setBounds(5, 5, 10, 10);
		}
		button[0].setText("ADD ACCOUNT");
		button[0].addActionListener((e)->{
			BSA.User.add_edit_init(2);
			BSA.User.add_edit_visibility(true);
			dispose();
		});
		
		button[1].setText("EDIT ACCOUNT");
		button[1].addActionListener((e)->{
			new Account_Number(1);
			dispose();
		});
		button[2].setText("DELETE ACCOUNT");
		button[2].addActionListener((e)->{
			new Account_Number(2);
			dispose();
		});
		button[3].setText("SHOW ALL ACCOUNT");
		button[3].addActionListener((e)->{
		new Diplay_All_Accounts();
		dispose();
		});
		
		
		
		button[4].setText("SHOW ACCOUNT DETAILS");
		button[4].addActionListener((e)->{
			new Account_Number(3);
			dispose();
		});
		button[6].setText("WITHDRAW");
		button[6].addActionListener((e)->{
			new Account_Number(5);
			dispose();
		});
		button[5].setText("DEPOSIT");
		button[5].addActionListener((e)->{
			new Account_Number(4);
			dispose();
		});
		
		button[7].setText("TRANSFER");
		button[7].addActionListener((e)->{
			new Account_Number(6);
			dispose();
		});
		
		button[8].setText("LOG OUT");
		button[8].addActionListener((e)->{
			int a=JOptionPane.showConfirmDialog(this, "Sure You Want To Log Out?","Alert",JOptionPane.YES_NO_OPTION);
			if(a==JOptionPane.YES_OPTION)
			{new LogIn();
			dispose();}
			});
		panel2.setLayout(new GridLayout(1,1));
		panel3.setLayout(new GridLayout(2,1));
		GridLayout layout=new GridLayout(3,3);
		layout.setHgap(10);
		layout.setVgap(10);
		panel1.setLayout(layout);
		
		Container C=getContentPane();
		C.setLayout(new BorderLayout());
		
		for(int i=0;i<9;i++)
		{
			panel1.add(button[i]);
		}
	
		panel2.add(login_info);
		panel3.add(login_date);
		panel3.add(login_time);
		
		C.add(panel2,BorderLayout.NORTH);
		C.add(panel1,BorderLayout.CENTER);
		C.add(panel3,BorderLayout.SOUTH);
	
		setSize(700,700);
		setLocation(600,300);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
		addWindowListener(new WindowAdapter()
		{		
			
			public void windowClosing(WindowEvent e)
			{	new LogIn();
			
			}
	
	
		});	
	}

}
