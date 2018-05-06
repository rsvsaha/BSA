package BSA;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class ReaderWriter {

	public static ArrayList<Client> ReadfromFile()
	{
		ArrayList<Client> list;
		try
		{
			FileInputStream fin=new FileInputStream("Record.txt");
			ObjectInputStream oin=new ObjectInputStream(fin);
			list=(ArrayList<Client>)oin.readObject();
			
			oin.close();
			fin.close();
			
		}
		catch (Exception e)
		{
			list=new ArrayList<Client>();
		}
	
		return list;
	}	
	public static void WitetoFile(ArrayList<Client> wrlist)
	{
		try
		{
			FileOutputStream fout=new FileOutputStream("Record.txt");
			ObjectOutputStream oout=new ObjectOutputStream(fout);
			oout.writeObject(wrlist);
			oout.close();
			fout.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
	}
		
}
	
	
	
