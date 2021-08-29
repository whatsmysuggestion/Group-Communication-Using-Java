import javax.swing.*;

class ser
{
	public static void main(String args[])
	{
		JFrame frm=new JFrame();
		frm.setVisible(false);

		try
		{
			Runtime run=Runtime.getRuntime();
			Process pro=run.exec("java server");
		}
		catch(Exception ex)
		{}

		System.exit(1);
		
	}
}