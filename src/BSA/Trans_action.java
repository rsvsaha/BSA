package BSA;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Trans_action extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel id;
	public Trans_action(String ac_id)
	{
	String heading[]={"Date","Time","Trans.id","Deposit","Withdraw","Balance"};
	
	String data[][];
	ArrayList<Transdet> list2;
	try
	{	
		list2=ReaderWriter2.ReadfromFile();
		data=new String[list2.size()][6];
		
		int r=0;
		
		
		for(Transdet re:list2)
		{	
			//System.out.println(ac_id);
			//System.out.println(re.get_accid());
			//System.out.println();
			if(ac_id.equals(re.get_accid()))
			{	//System.out.println(re.get_accid());
				LocalDate de=re.get_date();
				DateTimeFormatter formatter_1=DateTimeFormatter.ofPattern("dd-MMM-yyyy");
				String v=(de).format(formatter_1);
				data[r][0]=v;
				
				LocalTime te=re.get_time();
				data[r][1]=te.toString();
				
				data[r][2]=re.get_trans_id();
				data[r][3]=re.get_deposit();
				data[r][4]=re.get_withdraw();
				data[r][5]=re.get_balance();
				r++;
			}
		}


		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		
		JLabel id=new JLabel();
		id.setText("Account number:"+ac_id+" Name:"+Account_Number.Client_VALUEABLE.getac_Name());
		//System.out.println(id.getText());
		JTable datatable=new JTable(data,heading);
		JScrollPane jsp=new JScrollPane(datatable);
		
		//con.add(new JLabel("TRANSACTION HISTORY"),BorderLayout.NORTH);
		con.add(id,BorderLayout.NORTH);
		con.add(jsp,BorderLayout.CENTER);
		
		
		setSize(900,300);
		setLocation(600,300);
		setTitle("TRANSACTION HISTORY");
		setVisible(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	addWindowListener(new WindowAdapter() //This does a function when the window is closed
			{		
				public void windowClosing(WindowEvent e)
				{
					if(LogIn.Logged_As.equals("Customer"))
					{new Customer(Account_Number.Client_VALUEABLE.getac_Name());}
						//Starts a new frame of the customer type on closing...or goes back to the beginning
				
					if(LogIn.Logged_As.equals("Accountant"))
					{new Accountant(BSA.User.UserName);}
				
				}
			
			});




}

}
	
		
