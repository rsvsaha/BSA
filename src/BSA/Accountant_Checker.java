package BSA;
import java.lang.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Accountant_Checker {
	public static boolean accountant_checker(String username,String pass)
	{
		
		ArrayList<String> list;
		boolean flag=false;
		String temp=username+"$"+pass;
		try
		{
			
			FileInputStream fin=new FileInputStream("USER_RECORD.txt");
			ObjectInputStream oin=new ObjectInputStream(fin);
			list=(ArrayList<String>)oin.readObject();
			
			oin.close();
			fin.close();
			for(String re:list)
			{
				if(re.equals(temp))
				{
					User.UserName=username;
					User.Password=pass;
					flag=true;
					break;
				}
				
			}	
		
		
		
		}
		catch (Exception e)
		{
			list=new ArrayList<String>();
		}
		
		return flag;
	}
}
