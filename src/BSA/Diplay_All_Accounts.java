package BSA;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Diplay_All_Accounts extends JFrame {
	public Diplay_All_Accounts()
	{
		
		super("All Account Details");
		String heading[]={"Acc_id","Name","Address","phone no","DOB","Balance"};
		String data[][];
		ArrayList<Client> list;
		try{
			list=ReaderWriter.ReadfromFile();
			data=new String[list.size()][6];
			int r=0;
			for(Client re:list)
			{
				data[r][0]=re.getac_id();
				data[r][1]=re.getac_Name();
				data[r][2]=re.getac_Address();
				data[r][3]=re.getac_Ph_no();
				data[r][4]=re.getac_DOB();
				data[r][5]=re.getac_Amt();
				r++;
				
			}
			Container c=getContentPane();
			c.setLayout(new BorderLayout());
			JTable datatable=new JTable(data,heading);
			
			JScrollPane jsp=new JScrollPane(datatable);
			c.add(new JLabel("All Account details"),BorderLayout.NORTH);
			c.add(jsp,BorderLayout.CENTER);
			setSize(850,800);
			setLocation(600,300);
			setVisible(true);
			setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		addWindowListener(new WindowAdapter() //This does a function when the window is closed
				{		
					public void windowClosing(WindowEvent e)
					{
					Accountant A=new Accountant("User"); //Starts a new frame of the accountant type on closing...or goes back to the beginning
					}
				
				});
	
	
	
	
	}
	





}
