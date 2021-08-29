import javax.swing.*;

class Log
{
	public static void main(String str[]) throws Exception
	{
		JFrame frm=new JFrame();
		frm.setVisible(false);

		Runtime run=Runtime.getRuntime();
		Process pro=run.exec("java Login");

		System.exit(1);
	}
}