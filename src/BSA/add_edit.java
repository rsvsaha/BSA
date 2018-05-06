package BSA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;
public class add_edit extends JFrame{
	private int i=0;//this is for choosing add or edit
	private String ac_id;
	private static JTextField text[]=new JTextField[3];
	private static JComboBox DOB[]=new JComboBox[3];
	private static JTextField Address=new JTextField(100);
	private JPanel DoB=new JPanel(new GridLayout(1,3));	
	private JLabel label[]=new JLabel[6];
	private JButton add_edit_button=new JButton();
	private JButton reset=new JButton("RESET");
	
	Client client;//DONOT DELETE.....Used for creating account 
	static ArrayList<Client> Client_list;//This list is for adding and editing the records from the file
	static ArrayList<Transdet> Transdet_list;
	
	
	void DOB_assign()//This function creates the Combo Box with Dates,Months And Years
	{
		String d[]=new String[32];
		d[0]="Select Date";
		for(int i=1;i<32;i++)
		{d[i]=String.valueOf(i);}
		String m[]= {"Select Month","JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
		String y[]=new String[28];
		y[0]="Select Year";
		for(int i=1;i<y.length;i++)
		{y[i]=String.valueOf(1990+(i-1));}
		
		DOB[0]=new JComboBox(d);
		DOB[1]=new JComboBox(m);
		DOB[2]=new JComboBox(y);
		for(int i=0;i<3;i++)
		{DoB.add(DOB[i]);}
	
	
	}
	
	
	public add_edit(int j)//parameterized constructor (parameter for choosing adding or editing) 
		{
			
			this.i=j;
		if(this.i==2)//this starts the frame in adding new account mode
		{
			add_edit_ac();
			this.i=0;
		}
		if(this.i==1)//this starts the frame in editing  account mode
		{
			add_edit_ac();
			edit_field_populate();
			this.i=0;
		}

		}
	
	
	
	void create_clientobj()   //Creates The Client Object For Adding Account
	{
		String tName,tPh_no,tdob,tAddress,tAmt;
		String tac_id=ac_id;
		tName=text[0].getText().trim();
		tPh_no=text[1].getText().trim();
		tdob=DOB[0].getSelectedItem()+"/"+DOB[1].getSelectedItem()+"/"+DOB[2].getSelectedItem();
		tAddress=Address.getText().trim();
		tAmt=text[2].getText().trim();
		client=new Client(tac_id,tName,tPh_no,tdob,tAddress,tAmt);
		String trans_id=trans_id_gen("DEP");
		Transdet trans_dep= new Transdet(ac_id,LocalDate.now(),LocalTime.now(),trans_id,tAmt,null,tAmt);
		Transdet_list=ReaderWriter2.ReadfromFile();
		Transdet_list.add(trans_dep);
		ReaderWriter2.WitetoFile(Transdet_list);
	}
	
	void Add_acc() //Adds An Account to the DataBase
	{
		create_clientobj();
		Client_list=ReaderWriter.ReadfromFile();
		Client_list.add(client);
		ReaderWriter.WitetoFile(Client_list);
		JOptionPane.showMessageDialog(this,"Account Added Successfully");
		Client.write_acc_gen(ac_id);
		resetFrame();
	
	}
	
	void edit_field_populate() //Adds Data of the Existing Account to the respective fields
	{
		this.text[0].setText(Account_Number.Client_VALUEABLE.getac_Name());
		this.text[1].setText(Account_Number.Client_VALUEABLE.getac_Ph_no());
		this.Address.setText(Account_Number.Client_VALUEABLE.getac_Address());
		String c=Account_Number.Client_VALUEABLE.getac_DOB();
		String dob_edit[]=new String [3];
		dob_edit=c.split("/");
		for(int i=0;i<3;i++)
		{
			DOB[i].setSelectedItem(dob_edit[i]);
		}
		
	}
	
	public  static void Edit_acc() //Edits the Account Details (Except Amount) Edits the file
	{	
		Client_list=ReaderWriter.ReadfromFile();
		Client_list.get(User.index_array_list).setac_Name(text[0].getText());	
		Client_list.get(User.index_array_list).setac_Ph_no(text[1].getText());
		Client_list.get(User.index_array_list).setac_Address(Address.getText());
		Client_list.get(User.index_array_list).setac_DOB(DOB[0].getSelectedItem()+"/"+DOB[1].getSelectedItem()+"/"+DOB[2].getSelectedItem());
		ReaderWriter.WitetoFile(Client_list);
		User.index_array_list=0;
	}
	
	public static void Change_acc_pass(String new_pass)
	{
		Client_list=ReaderWriter.ReadfromFile();
		//System.out.println(Client_list.get(User.index_array_list).getac_Password());
		Client_list.get(User.index_array_list).setac_Password(new_pass);
		ReaderWriter.WitetoFile(Client_list);
		Client_list=ReaderWriter.ReadfromFile();
		//System.out.println(Client_list.get(User.index_array_list).getac_Password());
		Account_Number.Client_VALUEABLE.setac_Password(new_pass);
		//System.out.println(Account_Number.Client_VALUEABLE.getac_Password());
		User.index_array_list=0;
	}
	
	
	
	
	
	
	
	public static void Deposit(int Amt)
	{
		
		Client_list=ReaderWriter.ReadfromFile();
		int balance=Integer.parseInt(Client_list.get(User.index_array_list).getac_Amt());
		int dep=balance+Amt;
		Client_list.get(User.index_array_list).setac_Amt(String.valueOf(dep));
		ReaderWriter.WitetoFile(Client_list);
		String trans_id=trans_id_gen("DEP");
		Transdet trans_dep= new Transdet(Client_list.get(User.index_array_list).getac_id(),LocalDate.now(),LocalTime.now(),trans_id,String.valueOf(Amt),null,String.valueOf(dep));
		Transdet_list=ReaderWriter2.ReadfromFile();
		Transdet_list.add(trans_dep);
		ReaderWriter2.WitetoFile(Transdet_list);
		User.index_array_list=0;
	}
	
	
	
	public static void Transfer(int Amt)
	{
		
		Client_list=ReaderWriter.ReadfromFile();
		int from_balance=Integer.parseInt(Client_list.get(User.index_array_list).getac_Amt());
		int to_balance=Integer.parseInt(Client_list.get(Client.Client_arraylist_index).getac_Amt());
		from_balance=from_balance-Amt;
		to_balance=to_balance+Amt;
		Client_list.get(User.index_array_list).setac_Amt(String.valueOf(from_balance));
		Client_list.get(Client.Client_arraylist_index).setac_Amt(String.valueOf(to_balance));
		ReaderWriter.WitetoFile(Client_list);
		String trans_id=trans_id_gen(Client_list.get(Client.Client_arraylist_index).getac_id());
		Transdet trans_dep= new Transdet(Client_list.get(User.index_array_list).getac_id(),LocalDate.now(),LocalTime.now(),trans_id,null,String.valueOf(Amt),String.valueOf(from_balance));
		Transdet_list=ReaderWriter2.ReadfromFile();
		Transdet_list.add(trans_dep);
		ReaderWriter2.WitetoFile(Transdet_list);
		trans_id=trans_id_gen(Client_list.get(User.index_array_list).getac_id());
		trans_dep= new Transdet(Client_list.get(Client.Client_arraylist_index).getac_id(),LocalDate.now(),LocalTime.now(),trans_id,String.valueOf(Amt),null,String.valueOf(to_balance));
		Transdet_list=ReaderWriter2.ReadfromFile();
		Transdet_list.add(trans_dep);
		ReaderWriter2.WitetoFile(Transdet_list);
		User.index_array_list=0;
		Client.Client_arraylist_index=0;
		
	}
	
	
	
	
	
	
	public static void Withdraw(int Amt)
	{
		
		Client_list=ReaderWriter.ReadfromFile();
		int balance=Integer.parseInt(Client_list.get(User.index_array_list).getac_Amt());
		int dep=balance-Amt;
		Client_list.get(User.index_array_list).setac_Amt(String.valueOf(dep));
		ReaderWriter.WitetoFile(Client_list);
		String trans_id=trans_id_gen("WITH");
		Transdet trans_dep= new Transdet(Client_list.get(User.index_array_list).getac_id(),LocalDate.now(),LocalTime.now(),trans_id,null,String.valueOf(Amt),String.valueOf(dep));
		Transdet_list=ReaderWriter2.ReadfromFile();
		Transdet_list.add(trans_dep);
		ReaderWriter2.WitetoFile(Transdet_list);
		User.index_array_list=0;
	}

	
	
	
	
	static String trans_id_gen(String id)
	{
		String id_gen;
		LocalDate date=LocalDate.now();
		LocalTime time=LocalTime.now();
		id_gen=id+"/"+String.valueOf(date)+String.valueOf(time);
		return id_gen;
	}
	
	
	
	public void resetFrame() //resets the frame
	{
		this.dispose();
		BSA.User.add_edit_init(2);
		BSA.User.add_edit_visibility(true);
	}
	
	
	public static void Delete_acc() //Deletes the Account Based on the account number
	{
		Client_list=ReaderWriter.ReadfromFile();
		Client_list.remove(User.index_array_list);
		ReaderWriter.WitetoFile(Client_list);
		
	}
	
	
	
	boolean EmptyFieldTest()
	{
		if(text[0].getText().equals("") || text[1].getText().equals("") || DOB[0].getSelectedItem().equals("Select Date") || DOB[1].getSelectedItem().equals("Select Month") || DOB[2].getSelectedItem().equals("Select Year") || Address.getText().equals(""))
		{
			return false;
		}
		
		else 
			return true;
		
		
	}
	
	boolean DOB_Validator()
	{
		if(DOB[0].getSelectedItem().equals("31"))
		{
			if(DOB[1].getSelectedItem().equals("JANUARY") || DOB[1].getSelectedItem().equals("MARCH") || DOB[1].getSelectedItem().equals("MAY") || DOB[1].getSelectedItem().equals("JULY") || DOB[1].getSelectedItem().equals("AUGUST") || DOB[1].getSelectedItem().equals("OCTOBER") || DOB[1].getSelectedItem().equals("DECEMBER") )
			{
				return true;
			}
			
			else
			{
				return false;
			}
		}
		else if(DOB[0].getSelectedItem().equals("30") || DOB[0].getSelectedItem().equals("31"))
		 {
			 if(DOB[1].getSelectedItem().equals("FEBRUARY"))
			 {
				 return false;
			 }
		 
			 else
			 {
				 return true;
			 }
		 
		 }
		else if(DOB[0].getSelectedItem().equals("29"))
		{
			if(DOB[1].getSelectedItem().equals("FEBRUARAY"))
			{
				String j=(String)DOB[2].getSelectedItem();
				int i=Integer.parseInt(j);
				if((i%4)==0)
				{
					return true;
				}
				else
				{
					return false;
				}
			
			
			}
			else
			{
				return true;
			}
			
		}
		else
		{
			return true;
		}
		
		
		
}
	

	void add_edit_ac() //This function Creates the Fields in the Frame The parameter i=2 is for adding account and The Parameter i=1 for editing
	{
		DOB_assign(); //Creates the DOB Combo Box
		
		
		for (int i=0;i<6;i++)//for loops are for initializing (allocating memory) or adding the components defined as arrays 
		{label[i]=new JLabel();}
		
		
		if(this.i==2)//Add the generated account number to the window (for new account)
		ac_id=Client.acc_id_gen();
		label[0].setText("Account No.:"+ac_id);
		
		if(this.i==1)//Adds the clients account number to the window(account to be edited)
		label[0].setText("Account No.:"+Account_Number.Client_VALUEABLE.getac_id());	
		
		
		
		
		label[1].setText("Name");
		label[2].setText("Phone");
		label[3].setText("Date of Birth");
		label[4].setText("Address");
		label[5].setText("Amount");
		
		for(int i=0;i<3;i++)
		{text[i]=new JTextField();}
		
		
		JPanel button=new JPanel(new GridLayout(1,2)); //Panel for holding the buttons
		button.setSize(200, 100);
		
		if(i==2) //Adding Account this will have the reset button
		{
		add_edit_button.setText("ADD");
		 button.add(reset);
		 reset.addActionListener((e)->{resetFrame();});
		 add_edit_button.addActionListener((e)->{
			 if(EmptyFieldTest())
			 {
				 boolean b=DOB_Validator();
				 if(b==true)
				 {Add_acc();}
				 else
				 {
					 JOptionPane.showMessageDialog(this, "NOT A VALID DATE OF BIRTH!!","Alert",JOptionPane.WARNING_MESSAGE);

				 }
			 
			 }
			 
			 else
			 {
				 JOptionPane.showMessageDialog(this, "Enter All Fields","Alert",JOptionPane.WARNING_MESSAGE);
			 }
		 
		 
		 });
		 	
		}
		
		
		if(i==1)//Editing Account this won't have the reset button
		{
			add_edit_button.setText("EDIT");
			add_edit_button.addActionListener((e)->
			{
				
				if(EmptyFieldTest())
				{
					boolean b=DOB_Validator();
					if(b==true)
					{
						Edit_acc();
						new pass(1);
						BSA.User.add_edit_visibility(false);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "NOT A VALID DATE OF BIRTH!!","Alert",JOptionPane.WARNING_MESSAGE);

					}
				
				
				
				}
				
				else
				{
					JOptionPane.showMessageDialog(this, "Enter All Fields","Alert",JOptionPane.WARNING_MESSAGE);

				}
			
			
			
			
			});
		}
		button.add(add_edit_button);
		
		
		
		Container con=getContentPane(); //Container for adding everything to the frame
		con.setLayout(new BorderLayout());
		
		
		JPanel AccountNumber=new JPanel(new GridLayout(1,1)); //Panel for Adding Account Number to the window
		AccountNumber.setSize(500, 100);
		AccountNumber.add(label[0]);
		
		
		
		
		JPanel Data=new JPanel(new GridLayout(10,1)); //Panel for Adding Credentials(Name,Ph_no...etc) fields
		Data.setSize(500,300);
		
		Data.add(label[1]);
		Data.add(text[0]);
		Data.add(label[2]);
		Data.add(text[1]);
		Data.add(label[3]);
		Data.add(DoB);
		Data.add(label[4]);
		Data.add(Address);
		Address.setText(null);
		
		
		if(i==2)//Adds The Amount Field for Adding Account.....Not in Edit Account
		{	
		Data.add(label[5]);
		Data.add(text[2]);
		}
		
		con.add(AccountNumber,BorderLayout.NORTH); //Adding All the Panels to the Container
		con.add(Data,BorderLayout.CENTER);
		con.add(button,BorderLayout.SOUTH);
		
		
		if(i==2)//Setting the title for Adding Account
		setTitle("ADD ACCOUNT");
		
		if(i==1)//Setting the title for Editing Account
		setTitle("EDIT ACCOUNT");
			
		setSize(800,800);
		setLocation(600,200);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Disposes the Frame on closing the window
		
		
		addWindowListener(new WindowAdapter() //This does a function when the window is closed
				{		
					public void windowClosing(WindowEvent e)
					{
					Accountant A=new Accountant(BSA.User.UserName); //Starts a new frame of the accountant type on closing...or goes back to the beginning
					}
				
				});
	}

	
	
	
	
	
	
	
	
}


