package BSA;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class ReaderWriter2 {

	public static ArrayList<Transdet> ReadfromFile()
	{
		ArrayList<Transdet> list;
		try
		{
			FileInputStream fin=new FileInputStream("Record2.txt");
			ObjectInputStream oin=new ObjectInputStream(fin);
			list=(ArrayList<Transdet>)oin.readObject();
			
			oin.close();
			fin.close();}
		catch (Exception e)
		{
			list=new ArrayList<Transdet>();
		}
	
		return list;
	}	
	public static void WitetoFile(ArrayList<Transdet> wrlist)
	{
		try
		{
			FileOutputStream fout=new FileOutputStream("Record2.txt");
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
	
		
