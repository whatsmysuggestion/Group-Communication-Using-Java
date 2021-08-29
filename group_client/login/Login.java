import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;



public class Login
{
	public static void main(String args[])
	{

		 log_window ani=new log_window();

		Create_login ob=new Create_login();

		ob.frm_Create();
		
		ob.label_Create();
		ob.text_Create();
		ob.button_Create();
		
		ob.frm_Add();

		ob.login_frm.show();
	}
}

class Create_login extends Declare_login
{
	public void frm_Create()
	{
		login_frm=new ExFrame(200,100,300,300,"client");
		login_frm.setResizable(false);

			login_frm.addWindowListener(new Log_WindowClose()); 		

		con=login_frm.getContentPane();
		con.setLayout(null);
	}

	public void label_Create()
	{
		server=new ExLabel(30,30,100,20,"Server Address  :");		
		ser_pass=new ExLabel(60,70,80,20,"Password  :");
		user=new ExLabel(75,130,80,20,"User ID  :");
		user_pass=new ExLabel(60,170,80,20,"Password  :");
		
	}

	public void text_Create()
	{
		text_server=new ExTextField(140,30,100,20);
		text_ser_pass=new JPasswordField();
			text_ser_pass.setBounds(140,70,100,20);
			text_ser_pass.setEchoChar('*');
			
		text_user=new ExTextField(140,130,100,20);
		text_user_pass=new JPasswordField();
			text_user_pass.setBounds(140,170,100,20);
			text_user_pass.setEchoChar('*');
	}

	public void button_Create()
	{
		ok=new ExButton(60,220,80,20,"Ok");	
			ok.addActionListener(new loginAction());
		
		cancel=new ExButton(150,220,80,20,"Cancel");
			cancel.addActionListener(new loginAction());
	}

	public void frm_Add()
	{
		con.add(server);
		con.add(ser_pass);
		con.add(user);
		con.add(user_pass);

		con.add(text_server);
		con.add(text_ser_pass);
		con.add(text_user);
		con.add(text_user_pass);

		con.add(ok);
		con.add(cancel);
	}
}

class Declare_login
{
	static ExFrame login_frm;
	static Container con;

	static ExLabel server,ser_pass,user,user_pass;

	static ExTextField text_server,text_user;
	static JPasswordField text_ser_pass,text_user_pass;
	static ExButton ok,cancel;
}

class ExFrame extends JFrame
{
	ExFrame(int x1,int y1,int x2,int y2,String cap)
	{
                
       	        super("Login");
		this.setBounds(x1,y1,x2,y2);
		
	}
}

class ExButton extends JButton
{
	ExButton(int x1,int y1,int x2,int y2,String cap)
	{

			super(cap);
			this.setBounds(x1,y1,x2,y2);	
	}	

}

class ExLabel extends JLabel
{
	ExLabel(int x1,int y1,int x2,int y2,String cap)
	{
		super(cap);
		this.setBounds(x1,y1,x2,y2);
	}
}

class ExTextField extends JTextField
{
	ExTextField(int x1,int y1,int x2,int y2)
	{
		this.setBounds(x1,y1,x2,y2);
	}
}


class loginAction extends Declare_login implements ActionListener
{
String str;
int n;
	public void actionPerformed(ActionEvent ae)
	{

	 String ser_addr,ser_password,user_id,user_password;

		str=ae.getActionCommand();
		
		if(str.equals("Ok"))
		{
		ok.setEnabled(false);
		cancel.setEnabled(false);

		ser_addr=text_server.getText(); ser_addr=ser_addr.trim();
		ser_password=text_ser_pass.getText(); ser_password=ser_password.trim();
		user_id=text_user.getText(); user_id=user_id.trim();
		user_password=text_user_pass.getText(); user_password=user_password.trim();

		First_login first=new First_login();
		first.connect(ser_addr,ser_password,user_id,user_password);

		System.out.println(ser_addr+ser_password+user_id+user_password);

		
		}

		if(str.equals("Cancel"))
		{
			n=JOptionPane.showConfirmDialog(login_frm,"Are you sure to Terminate Application ", "Log on", JOptionPane.YES_NO_OPTION);
				
			if(n==JOptionPane.YES_OPTION)
			System.exit(1);
		}
	}
}  



class First_login
{
  Socket client;
  InetAddress addr,client_addr;

  PrintStream ps;
		
  InputStream in;
  BufferedReader br;

  String result,port_ser,port_cli,user_name,str_addr;;

  boolean flag;


 	
  	public void connect(String ser_addr,String ser_password,String user_id,String user_password)
	{

		

	B:	try
		{	client_addr=InetAddress.getLocalHost();		
			
			addr=InetAddress.getByName(ser_addr);
			client=new Socket(addr,1500);

			ps=new PrintStream(client.getOutputStream());

			in=client.getInputStream();
			br=new BufferedReader(new InputStreamReader(in));
			
			ps.println(ser_password); result=br.readLine(); System.out.println(result);
				if(result.equals("no"))
				{
				Bussy_login.warning("Group Password is Wrong");
				
				Declare_login.ok.setEnabled(true);
				Declare_login.cancel.setEnabled(true);

				break B;
				}
				
			ps.println(user_id); result=br.readLine(); System.out.println(result);
				if(result.equals("no"))
				{
				
				Bussy_login.warning("Verify User Id");
				
				Declare_login.ok.setEnabled(true);
				Declare_login.cancel.setEnabled(true);

				break B;
				}
				
			ps.println(user_password); result=br.readLine(); System.out.println(result);
				if(result.equals("no"))
				{
				
				Bussy_login.warning("Verify User Password");

				Declare_login.ok.setEnabled(true);
				Declare_login.cancel.setEnabled(true);

				break B;
				}

			port_cli=br.readLine(); port_cli=port_cli.trim();  System.out.println(port_cli);
				if(port_cli.equals("not"))
				{
				Bussy_login.warning("Currently you could not Login into Server");

				Declare_login.ok.setEnabled(true);
				Declare_login.cancel.setEnabled(true);

				break B;
				}

			port_ser=br.readLine(); port_ser=port_ser.trim();  System.out.println(port_ser);
			
			user_name=br.readLine(); user_name=user_name.trim();  System.out.println(user_name);
				
			ps.println(client_addr);
			str_addr=client_addr.toString();
			str_addr=str_addr.substring(0,str_addr.indexOf("/"));
		
			Runtime r=Runtime.getRuntime();
			
			Process pro=r.exec("java client "+ser_addr+" "+port_ser+" "+port_cli+" "+user_id+" "+user_name+" "+str_addr);

			System.out.println("java client "+ser_addr+" "+port_ser+" "+port_cli+" "+user_id+" "+user_name+" "+str_addr);

			client.close();

			System.exit(1);
	
			
		}
		catch(Exception ex)
		{
		Bussy_login.warning("Can not find Server");
		
		Declare_login.ok.setEnabled(true);
		Declare_login.cancel.setEnabled(true);

		
		System.out.println("EX_TCP_Connect : "+ ex);
		}

		try
		{
			client.close();
		}
		catch(Exception ex)
		{  System.out.println("Connection_Close :"+ex);  }

	
	}
}
 
class Bussy_login
{
	public static void warning(String mesg)
	{
		JOptionPane.showMessageDialog(Declare_login.login_frm, mesg, "From Server", JOptionPane.WARNING_MESSAGE ); 
	}
}

class log_window
{
	log_window()
	{
		JWindow win=new JWindow();
		Container con=win.getContentPane();	

		ImageIcon icon=new ImageIcon(".//image//group_4.gif");
		JLabel la=new JLabel(icon);

		con.add(la);
		win.pack();
		win.setLocation(250,220);
		win.setVisible(true);

		try{ Thread.sleep(13000);  }
		catch(Exception ex){ }
		
		win.dispose();
		
	}
}

class Log_WindowClose extends WindowAdapter
{
	public void  windowClosing(WindowEvent e) 
 	{
		System.exit(1);
	}
}
