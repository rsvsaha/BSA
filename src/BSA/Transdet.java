package BSA;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class Transdet implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accid;
	private LocalDate date;
	private LocalTime time;
	private String deposit;
	private String withdraw;
	private String trans_id;
	private String balance;
	Transdet(String accid,LocalDate date,LocalTime time,String trans_id,String deposit,String withdraw,String balance)
		{
		    this.accid=accid;
			this.date=date;
			this.time=time;
			this.trans_id=trans_id;
			this.deposit=deposit;
			this.withdraw=withdraw;
			this.balance=balance;	
		}
	
	String get_accid()
	{return accid;}
	
	LocalDate get_date()
	{return date;}

	LocalTime get_time()
	{return time;}
	
	String get_deposit()
	{return deposit;}

	String get_withdraw()
	{return withdraw;}
	
	String get_balance()
	{return balance;}
	
	
	String get_trans_id()
	{return trans_id;}

	


	
	
	
}

