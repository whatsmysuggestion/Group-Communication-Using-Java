import javax.swing.*;
import java.awt.*;
class info
{
	public static void main(String a[])
	{
		JWindow win=new JWindow();
		Container con=win.getContentPane();	

		ImageIcon icon=new ImageIcon(".//image//group_5.gif");
		JLabel la=new JLabel(icon);

		con.add(la);
		win.pack();
		win.setLocation(200,220);
		
		win.setVisible(true);

		try{ Thread.sleep(7000);  }
		catch(Exception ex){ }
		
		win.dispose();
		System.exit(1);

	}
}	