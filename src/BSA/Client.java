package BSA;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client implements Serializable {
private String Name,Ph_no,DOB,Address;
private String Amt;
private String ac_id;
private String password;
public static int Client_arraylist_index=0;
Client(String ac_id,String Name,String Ph_no,String DOB,String Address,String Amt)
{
	this.ac_id=ac_id;
	this.Name=Name;
	this.Ph_no=Ph_no;
	this.DOB=DOB;
	this.Address=Address;
	this.Amt=Amt;
	this.password=Name+ac_id;
}
String getac_id()
{return this.ac_id;}

String getac_Name()
{return this.Name;}

String getac_Ph_no()
{return this.Ph_no;}

String getac_DOB()
{return this.DOB;}

String getac_Address()
{return this.Address;}

String getac_Amt()
{return this.Amt;}

String getac_Password()
{return this.password;}
void setac_Password(String Password)
{this.password=Password;}
void setac_Name(String Name)
{this.Name=Name;}

void setac_Ph_no(String Ph_no)
{this.Ph_no=Ph_no;}

void setac_DOB(String DOB)
{this.DOB=DOB;}

void setac_Address(String Address)
{this.Address=Address;}

void setac_Amt(String Amt)
{this.Amt=Amt;}
public static String read_acc_gen()//used for generating account number
{
	String id;
	try
	{
		FileInputStream fin=new FileInputStream("Account_gen.txt");
		ObjectInputStream oin=new ObjectInputStream(fin);
		id=(String)oin.readObject();
		
		oin.close();
		fin.close();
		
	}
	catch (Exception e)
	{
		id=new String();
	}
	return id;
	
	
	
}
public static void write_acc_gen(String temp)//Used for generating account number
{	
	String new_id=String.valueOf(Integer.parseInt(temp)+1);
	
	try
	{
		FileOutputStream fout=new FileOutputStream("Account_gen.txt");
		ObjectOutputStream oout=new ObjectOutputStream(fout);
		oout.writeObject(new_id);
		oout.close();
		fout.close();
	}
	catch (Exception e)
	{
		System.out.println(e);
	}
}


public static String acc_id_gen()//Used for generating account number
{
	String id;
	id=read_acc_gen();
	return id;
	
}









}
