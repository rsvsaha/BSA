package BSA;
import java.time.LocalDate;
import java.time.LocalTime;
public class User {
	
	public static Accountant A;
	public static add_edit a_e;
	public static int index_array_list;//DO NOT DELETE used for modifying account the list
	public static LocalDate logindate=LocalDate.now();
	public static LocalTime logintime=LocalTime.now();
	public static String Password=null;//this will store the password by which the person is entering
	public static String UserName=null;
	public static void add_edit_init(int j)
	{
		a_e=new add_edit(j);
	}

	public static void add_edit_visibility(boolean b)
	{
		a_e.setVisible(b);
	}


}
