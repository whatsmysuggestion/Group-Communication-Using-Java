import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class server extends Declare
{

  


	public static void main(String args[])
	{	
		

		try{  Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  }
		catch(Exception ex){  System.out.println("Driver_Load :"+ex);  }


		Service.first();


		new Catch();
		Declare.group_pass="mec";
		new ConferenceSend();


		
		
		new MonitorMultiCast();	
	

		MainCreate create=new MainCreate();

		create.frm_Create();
		create.mdi();

		create.menu_Create();
		create.menu_Add();		
	
		create.label_Create();
		create.text_Create();
		create.radio_Create();
		create.button_Create();

		create.monitor();
		create.monitorUpdateCU();                   /*  Monitor	*/
		create.monitorUpdateGU();

		create.frm_Add();

		create.main_frm.show();

		

		new Listen();

	
	}
}






class Declare
{
	static JFrame main_frm;
	static ExInternalFrame add_user;
	
	static JDesktopPane desk;

	static Container container_main;
	static Container container_user;

	static ExLabel monitor,currentuser,groupuser;
	static JTable t_current_user,t_group_user;
              static JScrollPane scroll_c_u,scroll_g_u;
	static TableModel tm_current_user,tm_group_user;

	static JMenuBar menubar;
	static JMenu user;
		static JMenu  u_user;
			static JMenuItem add;
			static JMenuItem delete;
		static JMenuItem detail;	
	static JMenu server;
		static JMenuItem shutdown;
		static JMenuItem restart;
	
	static ExLabel userid,username,useractivate;
	static ExTextField user_id,user_name;

	static ExRadio yes,no;	
	static ButtonGroup bg;

	static ExButton add_b,update_b,close_b,temp;	

	static String u_action="YES";

	static String group_pass;

				/*  delete  */

	static   ExInternalFrame del_frm;
	static Container  del_frm_con;
	static ExLabel del_label;
	static ExTextField del_id;	
	static ExButton del_delete,del_close;
			
				/* detail */
	static ExInternalFrame detail_frm;
	static Container detail_frm_con;
	static ExLabel detail_label;
	static ExButton detail_close;
	static JTable detail_table;
	static TableModel tm_detail;
              static JScrollPane detail_scroll;
		
}



	/*	Create Components	*/

class MainCreate extends Declare
{

	public void frm_Create()
	{
		main_frm=new JFrame("server");    main_frm.addWindowListener(new WindowClose()); 
		main_frm.setResizable(false);

		main_frm.setBounds(200,100,400,450);
		container_main=main_frm.getContentPane();

		add_user=new ExInternalFrame(20,20,300,300,"Add User");
		container_user=add_user.getContentPane();

		del_frm=new ExInternalFrame(20,20,250,130,"User Delete");
		del_frm_con=del_frm.getContentPane();
		del_frm_con.setLayout(null);

		detail_frm=new ExInternalFrame(20,20,300,350,"All User Detail");
		detail_frm_con=detail_frm.getContentPane();
		detail_frm_con.setLayout(null);

		
	}

	public void mdi()
	{
		desk=new JDesktopPane();
		desk.add(add_user);
                             desk.add(del_frm);
		desk.add(detail_frm);
	

		container_main.add(desk);

					    
		        
	}

	public void frm_Add()
	{
		main_frm.setJMenuBar(menubar);

		container_user.add(userid);
		container_user.add(username);
		container_user.add(useractivate);

		container_user.add(user_id);
		container_user.add(user_name);

		container_user.add(yes);
		container_user.add(no);
		bg.add(yes);
		bg.add(no);

		container_user.add(add_b);
		container_user.add(update_b);
		container_user.add(close_b);
		container_user.add(temp);


		del_frm_con.add(del_label);
		del_frm_con.add(del_id);
		del_frm_con.add(del_delete);
		del_frm_con.add(del_close);

		detail_frm_con.add(detail_scroll);
		detail_frm_con.add(detail_label);
		detail_frm_con.add(detail_close);

		desk.add(monitor);
		desk.add(currentuser);
		desk.add(groupuser);
		desk.add(scroll_c_u);
		desk.add(scroll_g_u);

	}

	public void menu_Create()
	{
		menubar=new JMenuBar();

         	user =new JMenu("User");
	
	    u_user=new JMenu("User");				  
                    	add=new JMenuItem("Add");        add.addActionListener(new menuItemAction()); 
	    	 delete=new JMenuItem("Delete");	delete.addActionListener(new menuItemAction());
                   detail=new JMenuItem("Detail");	           detail.addActionListener(new menuItemAction());

	server=new JMenu("Server");
		
                    shutdown=new JMenuItem("Shutdown"); 		shutdown.addActionListener(new menuItemAction());
                    restart=new JMenuItem("Restart"); 		restart.addActionListener(new menuItemAction());
			
		
	}
	
	public void menu_Add()
	{
		menubar.add(user);
		                                    	
		     user.add(u_user);
                                               u_user.add(add);	
                                               u_user.add(delete); 	
		     user.add(detail); 

		menubar.add(server);

		    server.add(shutdown);	
		    server.add(restart);

			                
	}	
	
	public void label_Create()
	{
	
		userid=new ExLabel(60,43,50,20,"User ID  :");
		username=new ExLabel(38,80,80,20,"User Name  :");
		useractivate=new ExLabel(55,120,70,20,"Activate  :");

		monitor=new ExLabel(0,10,500,20,"--------------------------------------------Monitor--------------------------------------------");
		currentuser=new ExLabel(12,40,120,20,"Current User  :");
		groupuser=new ExLabel(232,40,120,20,"Group User  :");


		del_label=new ExLabel(30,20,80,20,"User Id  :");
		detail_label=new ExLabel(105,5,100,20,"Group User");
	}
	
	public void text_Create()
	{
		user_id=new ExTextField(120,43,100,20);
		user_name=new ExTextField(120,80,100,20);

		del_id=new ExTextField(90,20,120,20);
	}
	public void radio_Create()
	{

		yes=new ExRadio(120,130,100,20,"YES");
		yes.setSelected(true);
		no=new ExRadio(120,160,100,20,"NO");
		bg=new ButtonGroup();
		
	}
	public void button_Create()
	{
		add_b=new ExButton(25,220,80,20,"Add");         add_b.addActionListener(new UserAction());	
		update_b=new ExButton(105,220,80,20,"Update");  update_b.addActionListener(new UserAction());	
		close_b=new ExButton(185,220,80,20,"Close");    close_b.addActionListener(new UserAction());  
		temp=new ExButton(105,220,80,20,""); 
			temp.setVisible(false); 

		del_delete=new ExButton(50,60,70,20,"Delete");  del_delete.addActionListener(new DeleteAction());
		del_close=new ExButton(130,60,70,20,"Close");  del_close.addActionListener(new DeleteAction());

		detail_close=new ExButton(105,290,70,20,"Close");  detail_close.addActionListener(new menuItemAction());
	}

	public void monitor()
	{
		Object head[]={"User ID","User Name"};
		Object data[][]=new Object[1000][2]; 

		Object head1[]={"User ID","User Name"};
		Object data1[][]=new Object[1000][2]; 

		Object head3[]={"User ID","User Name","Status"};
		Object data3[][]=new Object[1000][3]; 		

		t_current_user=new JTable(data1,head1);
		scroll_c_u=new JScrollPane(t_current_user);
		scroll_c_u.setBounds(10,80,150,280);
		tm_current_user=t_current_user.getModel();

		t_group_user=new JTable(data,head);
		scroll_g_u=new JScrollPane(t_group_user);
		scroll_g_u.setBounds(230,80,150,280);
		tm_group_user=t_group_user.getModel();

		detail_table=new JTable(data3,head3);
		detail_scroll=new JScrollPane(detail_table);
		detail_scroll.setBounds(20,30,250,250 );
		tm_detail=detail_table.getModel();
		
	}

	public void monitorUpdateCU()
	{
		Monitor.groupUser();
	}

	public void monitorUpdateGU()
	{
		Monitor.currentUser();
	}

}



	/*	Extend Components	*/

class ExInternalFrame extends JInternalFrame
{
	ExInternalFrame(int x1,int y1,int x2,int y2,String cap)
	{
		super(cap,false,false,false,true);
		
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

class ExRadio extends JRadioButton
{
	ExRadio(int x1,int y1,int x2,int y2,String cap)
	{
			
			super(cap);
			this.setBounds(x1,y1,x2,y2);
			this.addActionListener(new RadioAction());
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
	/*-------	Extend Components	---------*/
	
class DeleteAction implements ActionListener
{
    String cap;
   int ok;

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		
	        if(cap.equals("Delete"))
	        {

		ok=JOptionPane.showConfirmDialog(Declare.del_frm, "Are you sure to Delete User", "Delete", JOptionPane.YES_NO_OPTION);
		
		    if(ok==JOptionPane.YES_OPTION)
		    {    	 

			if(!(Declare.del_id.getText().trim()).equals(""))
			{
				DoDataBase.userDelete(Declare.del_id.getText().trim());
		
				Monitor.groupUser();
				
			}
			else
			{
				JOptionPane.showMessageDialog(Declare.del_frm, "You should  Enter the User ID" , "Add User", JOptionPane.WARNING_MESSAGE ); 
			}
		   }
	         }

	         if(cap.equals("Close"))
	        {
		Declare.del_frm.setVisible(false);
		Declare.del_id.setText("");
	        }	
	}
}

	/*	User ADD 	*/

class UserAction extends Declare implements ActionListener
{

  String cap;
  String u_id,u_name;	

	
	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		cap.trim();

		if(cap.equals("Add"))
		{
			u_id=user_id.getText();u_id=u_id.trim();
			u_name=user_name.getText();u_name=u_name.trim(); 
			DoDataBase.addUser(u_id,u_name,u_action);

			Monitor.groupUser();
		}

		if(cap.equals("Update"))
		{
			u_id=user_id.getText();u_id=u_id.trim();
			u_name=user_name.getText();u_name=u_name.trim(); 
			DoDataBase.userUpdate(u_id,u_name,u_action);
	
			Monitor.groupUser();

		}

		if(cap.equals("Close"))
		{
			add_user.setVisible(false);
		}	
	}
	
}



	/*	Menu Item Listener 	*/

class menuItemAction extends Declare implements ActionListener
{
   String cap;
   int n;
  
	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		cap=cap.trim();	
		
		if(cap.equals("Add"))
		{	
			user_id.setText("");
			user_name.setText("");
			yes.setSelected(true);
			
			add_user.setVisible(true);
			
		}

		if(cap.equals("Delete"))
		{
			Declare.del_frm.setVisible(true);
		}
		
		if(cap.equals("Detail"))
		{
			Declare.detail_frm.setVisible(true);
			DoDataBase.allUser();	
		}
		
		if(cap.equals("Close"))
		{
			Declare.detail_frm.setVisible(false);
		}

		if(cap.equals("Shutdown"))
		{
			n=JOptionPane.showConfirmDialog(main_frm,"Are you sure to Shutdown The Server", "Server", JOptionPane.YES_NO_OPTION);
			
			if (n == JOptionPane.YES_OPTION) 
			{
			System.exit(1);
			}
		}
		if(cap.equals("Restart"))
		{

		         n=JOptionPane.showConfirmDialog(main_frm,"Are you sure to Restart The Server", "Server", JOptionPane.YES_NO_OPTION);
			
		         if (n == JOptionPane.YES_OPTION) 
		        {
			try
			{
				Runtime run=Runtime.getRuntime();
				Process pro=run.exec("java server");
				
				System.exit(1);
			}
			catch(Exception ex)
			{  System.out.println("process : "+ ex);  }
		        }	
		}
				
	
	}
}


	/*	For User Activate	*/

class RadioAction extends Declare implements ActionListener
{
    String cap;

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		u_action=cap.trim();        //assign check box value for user Activate
		
	}
}



/*    Add user's to DataBase */

class DoDataBase
{
    String u_id,u_name;
	public static void addUser(String u_id,String u_name,String u_act)
	{
		u_id=Declare.user_id.getText();  u_id=u_id.trim();		
		u_name=Declare.user_name.getText();  u_name=u_name.trim();
		
		if(u_id.equals("") || u_name.equals(""))
		{
			JOptionPane.showMessageDialog(Declare.add_user, "You should  Enter the User ID And User Name", "Add User", JOptionPane.WARNING_MESSAGE ); 
		}
		else
		{

		UserDataBase.connect();
		UserDataBase.check(u_id);
		UserDataBase.close();

			if(UserDataBase.bool)
			{
				
				UserDataBase.connect();
				UserDataBase.userAdd(u_id,u_name,u_act);
				UserDataBase.close();
	
				JOptionPane.showMessageDialog(Declare.add_user, "User Added Sucessfully", "Add User", JOptionPane.INFORMATION_MESSAGE ); 
			}
			else
			{ JOptionPane.showMessageDialog(Declare.add_user, "Already exist Your User Id ", "Add User", JOptionPane.WARNING_MESSAGE ); }
	               }	

	}

	public static void userUpdate(String u_id,String u_name,String u_act)
	{

		UserDataBase.connect();
		UserDataBase.check(u_id);
		UserDataBase.close();

		u_id=Declare.user_id.getText();  u_id=u_id.trim();
		if(u_id.equals(""))
		{
			JOptionPane.showMessageDialog(Declare.add_user, "You should  Enter the User ID", "Add User", JOptionPane.WARNING_MESSAGE ); 
		}
		else
		{

			if(!UserDataBase.bool)
			{

				UserDataBase.connect();
				UserDataBase.userUpdate(u_id,u_name,u_act);
				UserDataBase.close();

				JOptionPane.showMessageDialog(Declare.add_user, "Updated Sucessfully", "Add User", JOptionPane.INFORMATION_MESSAGE ); 
			}
			else
			{ JOptionPane.showMessageDialog(Declare.add_user, "This User Id not Exist", "Add User", JOptionPane.WARNING_MESSAGE ); }			
		}
	}
	
	public static void userDelete(String id)
	{
		UserDataBase.connect();
		UserDataBase.check(id);
		UserDataBase.close();

			if(!UserDataBase.bool)
			{
				UserDataBase.connect();
				UserDataBase.userDelete(id);
				UserDataBase.close();

				UserDataBase.connect();
				UserDataBase.passDelete(id);
				UserDataBase.close();
			
				JOptionPane.showMessageDialog(Declare.del_frm, "User Deleted from Group", "Add User", JOptionPane.INFORMATION_MESSAGE );
			}
			else
			{
				JOptionPane.showMessageDialog(Declare.del_frm, "This User Id not Exist", "Add User", JOptionPane.WARNING_MESSAGE );
			}		
	}

	public static void allUser()
	{
		UserDataBase.connect();
		UserDataBase.allUser();
		UserDataBase.close();
	}
}

class UserDataBase 
{

  static Connection con;
  static Statement st;	
  static ResultSet rs;
  static String str,user_id;
  static Object u_id,u_name,u_status;
  static boolean bool;
  static int i;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("DataBase_Connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("DataBase_Close :"+ex); }
	}

	public static void userAdd(String u_id,String u_name,String u_act)
	{
		try
		{

			str="insert into user values ('"+u_id+"','"+u_name+"','"+u_act+"')";
			st=con.createStatement();
			st.executeUpdate(str);
			st.close();


			str="insert into password values ('"+u_id+"','"+u_id+"')";
			st=con.createStatement();
			st.executeUpdate(str);
			st.close();
		}
		catch(Exception ex){}

	}

	public static void check(String id)
	{
		str="select * from user";
		bool=true;

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			while(rs.next())
			{
				user_id=rs.getString(1);

				  if(user_id.equals(id))
				  {
					bool=false;
					break;
				  }
				  else
				  {	bool=true;	}
			}	
		}
		catch(Exception ex){  System.out.println("User Check  : " + ex); }
	 }

	public static void userUpdate(String u_id,String u_name,String u_act)
	{
		str="update user set user_activate='"+u_act+"' where user_id='"+u_id+"'";
		
		try
		{
			st=con.createStatement();
			st.execute(str);

		}
		catch(Exception ex)
		{ System.out.println("User_Update :"+ex); }
	}

	public static void userDelete(String id)
	{
		str="delete from user where user_id='"+id+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	

		}

		catch(Exception ex)
		{
			System.out.println("User Delete : "+ex);
		}
	
	}
	public static void passDelete(String id)
	{
		str="delete from password where user_id='"+id+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	

		}

		catch(Exception ex)
		{
			System.out.println("Pass Delete : "+ex);
		}
	
	}


	public static void allUser()
	{
		str="select * from user";
		i=0;

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			while(rs.next())
			{
				u_id=rs.getString(1);
				u_name=rs.getString(2);
				u_status=rs.getString(3);

				Declare.tm_detail.setValueAt(u_id,i,0);	
				Declare.tm_detail.setValueAt(u_name,i,1);
				Declare.tm_detail.setValueAt(u_status,i,2);

				i++;
			}

		new TableClear_1(Declare.tm_detail,i);

		}
		catch(Exception ex)
		{ System.out.println("All_User :"+ex); }

	}


}

	/*    --------Add user's to DataBase--------- */


	/*	MONITOR	*/

class Monitor
{
	public static void groupUser()
	{
		
		MonitorDataBase.connect();
		MonitorDataBase.groupUser();
		MonitorDataBase.close();
	}
	
	public static void currentUser()
	{
		
		MonitorDataBase.connect();
		MonitorDataBase.currentUser();
		MonitorDataBase.close();
	}
}

class MonitorDataBase
{
   static Connection con;
   static Statement st;	
   static ResultSet rs;
   static String str,user_id,user_name;

	public static void connect()
	{	
		try
		{
			con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");		
		}
		catch(Exception ex)
		{  System.out.println("Monitor  Connect"+ex); }

	}
	public static void close()
	{
		try
		{
			con.close();
		}
		catch(Exception ex)
		{  System.out.println("Monitor  Close"+ex); }
	}

	public static void groupUser()
	{

	 int i=0;
		str="select * from user where user_activate='YES'";
		
		
		
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			while(rs.next())
			{
				user_id=rs.getString(1);
				user_name=rs.getString(2);
				Declare.tm_group_user.setValueAt(user_id,i,0);
				Declare.tm_group_user.setValueAt(user_name,i,1);
				
				i++;

				MonitorMultiCast.send("mc@201#"+user_id+"/"+user_name);
			}

			MonitorMultiCast.send("mc@202#");
			Declare.groupuser.setText("Group User  :  " +i);

			new TableClear(Declare.tm_group_user,i);
		}
		catch(Exception ex)
		{  System.out.println("Monitor group USER  : " + ex);  }
			
	}

	public static void currentUser()
	{

	 int i=0;
		str="select * from current where status='connected'";
		
			
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			while(rs.next())
			{
				user_id=rs.getString(1);
				user_name=rs.getString(2);
				Declare.tm_current_user.setValueAt(user_id,i,0);
				Declare.tm_current_user.setValueAt(user_name,i,1);
				i++;

				MonitorMultiCast.send("mc@101#"+user_id+"/"+user_name);

							
			}

			new TableClear(Declare.tm_current_user,i);
			MonitorMultiCast.send("mc@102#");
			Declare.currentuser.setText("Current User  :  "+i);
		}
		catch(Exception ex)
		{  System.out.println("Monitor Current USER  : " + ex);  }
			
	}
}

class TableClear_1 extends Thread
{
  int i;
 TableModel tablemodel;

	TableClear_1(TableModel tm,int j) 
	{
	   i=j;
	  tablemodel=tm;
	  start();
	}

	public  void run()
	{
		while(i<1000)
		{
			tablemodel.setValueAt(null,i,0);
			tablemodel.setValueAt(null,i,1);
			tablemodel.setValueAt(null,i,2);			
			i++;
		}
	}
}

class TableClear extends Thread
{
  int i;
 TableModel tablemodel;

	TableClear(TableModel tm,int j) 
	{
	   i=j;
	  tablemodel=tm;
	  start();
	}

	public  void run()
	{
		while(i<1000)
		{
			tablemodel.setValueAt(null,i,0);
			tablemodel.setValueAt(null,i,1);
						
			i++;
		}
	}
}

class MonitorMultiCast
{
   static InetAddress addr;
   static MulticastSocket ms;
   static DatagramPacket dp;
   static byte b[];
	MonitorMultiCast()
	{
		try
		{
			addr=InetAddress.getByName("224.2.2.2");
			ms=new MulticastSocket();
		}
		catch(Exception ex)
		{
			System.out.println("Multucast Monitor : "  + ex );
		}
	}

	public static void send(String data)
	{
		b=data.getBytes();
		dp=new DatagramPacket(b,b.length,addr,2222);
		try
		{
		ms.send(dp);	
		}
		catch(Exception ex)
		{  System.out.println("MultiCast Send  : " + ex);  }
	}
	
}



	/*   Listen for NEW USER      */
   
class Listen extends Thread
{

   ServerSocket ser;
   Socket server;
   InetAddress addr;
   
   PrintStream ps;

   InputStream in;
   BufferedReader br;

   String ser_password,user_id,user_password;

   String result,port_ser,port_client,addr_client;
   boolean flag;

   int length;			



	Listen()
	{	
		try
		{
		addr=InetAddress.getLocalHost();
		}
		catch(Exception ex)
		{
		System.out.println("GET LOCALHOST  :"+ex);
		}

		start();
	}

	public void run()
	{

	

		while(true)
		{
		B:	try
			{
			ser=new ServerSocket(1500);
			server=ser.accept();

			PrintStream ps=new PrintStream(server.getOutputStream(),true);
			
			in=server.getInputStream();
			br=new BufferedReader(new InputStreamReader(in));


			ser_password=br.readLine(); ser_password=ser_password.trim();
			result=Verify.serverPassword(ser_password,Declare.group_pass);

/* server password */	ps.println(result);//System.out.println(result);

				if(result.equals("no"))
				{
				server.close();
				ser.close();
				break B;
				}

			user_id=br.readLine();  user_id=user_id.trim();
			flag=Verify.user(user_id);
/* verify User */	result=flag?"yes":"no";

  			ps.println(result);System.out.println(result);
		
				if(result.equals("no"))
				{
				server.close();
				ser.close();
				break B;
				}
	
			
			user_password=br.readLine();  user_password=user_password.trim();
			result=Verify.userPassword(user_id,user_password);
			
/* User Password */	ps.println(result);System.out.println(result);

				if(result.equals("no"))
				{
				server.close();
				ser.close();
				break B;
				}

			
			result=Verify.port();System.out.println(result);

				if(result.equals("not"))
				{
/* Port manage */		ps.println(result);
				server.close();
				ser.close();
				break B;
				}

			length=result.lastIndexOf("/");
			port_ser=result.substring(0,length);
			port_client=result.substring(length+1);
			

			ps.println(port_client);
			ps.println(port_ser);
/* client addr */	ps.println(VerifyDataBase.user_name);
			addr_client=br.readLine();  addr_client=addr_client.trim();

		        VerifyDataBase.connect();
			VerifyDataBase.portUpdate(port_ser); 
			VerifyDataBase.close();    

			VerifyDataBase.connect();
			VerifyDataBase.currentUserUpdate(user_id,VerifyDataBase.user_name,addr_client,port_client,port_ser,"connected");
			VerifyDataBase.close(); 

						
			server.close();
			ser.close();

			new UDPReceive(port_ser,port_client,addr_client);
			System.out.println(port_ser+":"+port_client);
		
			new SendUserInfo();

			}
			catch(Exception ex)
			{
			System.out.println("EX_Listen : "+ex);
			}
		}
               

	}
  
}
	
	/*	Verify New User		*/

class Verify
{

	public static String serverPassword(String pass,String server_pass)
	{
		if(server_pass.equals(pass))
		{
			return "yes";
		}
		else
		{
			return "no";
		}
	}
	
	public static boolean user(String user_id)
	{
	boolean flag;

		VerifyDataBase.connect();
		flag=VerifyDataBase.userTake(user_id);
		VerifyDataBase.close();

		return (flag);
	
	}
	
	public static String userPassword(String u_id,String u_pass)
	{
	String user_pass;

		VerifyDataBase.connect();
		user_pass=VerifyDataBase.userPassword(u_id); user_pass=user_pass.trim();
		VerifyDataBase.close();


			if(user_pass.equals(u_pass))
				return("yes");
			else
				return("no");
	}

	public static String port()
	{
	String port;

		VerifyDataBase.connect();
		port=VerifyDataBase.takePort();
		VerifyDataBase.close();

		return(port);
	}

	
}


	
	
class VerifyDataBase
{

   static Connection con;
   static Statement st;	
   static ResultSet rs;
   static String str,user_name;	
	
		


	public static void connect()
	{	
		try
		{
			con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");		
		}
		catch(Exception ex)
		{  System.out.println("Db_Connect"+ex); }

	}
	public static void close()
	{
		try
		{
			con.close();
		}
		catch(Exception ex)
		{  System.out.println("Db_Close"+ex); }
	}

	public static boolean userTake(String user_id)
	{
	  String u_id,u_name,u_act;
  	  boolean flag=false;
  	
	  str="select * from user where user_activate='YES'";	

		try
		{
			
			st=con.createStatement();
			rs=st.executeQuery(str);

			while(rs.next())
			{
			
				u_id=rs.getString(1);u_id=u_id.trim();
				u_name=rs.getString(2);
				u_act=rs.getString(3);
				
					if(user_id.equals(u_id))
					{
					flag=true;
					user_name=u_name;
					break;
					}		
												
				//System.out.println(u_id+u_name+u_act);
			
			}
			
			
		}
		catch(Exception ex)
		{ System.out.println("Db_User Take "+ex); }
	

		 return flag; 
	}

	public static String userPassword(String u_id)
	{
	 String user_id,user_pass=null;
	 str="select * from password where user_id='"+u_id+"'"; 
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			
			rs.next();
			
			user_id=rs.getString(1);
			user_pass=rs.getString(2);

			//System.out.println(user_id+user_pass);

			
		}
		catch(Exception ex)
		{ System.out.println("Db_User_Password :"+ex); }
	
	return (user_pass);
		
	}

	public static String takePort()

	{
	String server,client,port=null;
	str="select * from port where status='notused'";
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			rs.next();

			server=rs.getString(1);
			client=rs.getString(2);

			port=server+"/"+client;
			port=port.trim();


				if(port.equals("3500/5500"))
				     port="not";
			

		}
		catch(Exception ex)
		{ System.out.println("Port_Take :"+ex); }

	return(port);
	}

	public static void portUpdate(String port_ser)
	{
	str="update port set status='used' where server='"+port_ser+"'";
		
		try
		{
			st=con.createStatement();
			st.execute(str);

		}
		catch(Exception ex)
		{ System.out.println("Port_Update :"+ex); }
	}

	public static void currentUserUpdate(String user_id,String u_name,String addr_client,String port_client,String port_ser,String status)
	{
		str="insert into current values ('"+user_id+"','"+u_name+"','"+addr_client+"','"+port_client+"','"+port_ser+"','"+status+"')";
		
		try
		{
			st=con.createStatement();
			st.executeUpdate(str);

		}
		catch(Exception ex)
		{ System.out.println("Current_User_Update :"+ex); }
	}


		/* First */

	public static void first_currentDelete()
	{
	 str="delete from current";
		
		try{
			st=con.createStatement();
			st.executeUpdate(str);
		}
		catch(Exception ex)
		{
			System.out.println("hai  :"+ex);
		}
	}

	public static void first_port()
	{
	 str="update port set status='notused'";
		
		try{
			st=con.createStatement();
			st.execute(str);
		}
		catch(Exception ex)
		{
			System.out.println("hai  :"+ex);
		}
	}

	public static void first_conference()
	{
	 str="update conference set status='notused'";
		
		try{
			st=con.createStatement();
			st.execute(str);
		}
		catch(Exception ex)
		{
			System.out.println("hai  :"+ex);
		}
	}

	public static void first_voice()
	{
	 str="update voice set status='notused'";
		
		try{
			st=con.createStatement();
			st.execute(str);
		}
		catch(Exception ex)
		{
			System.out.println("hai  :"+ex);
		}
	}

}


	/*	------Verify New User-----		*/


	/*	Declare UDP variable	*/

class DeclareUDP
{
	DatagramSocket ds;
	DatagramPacket dp; 
	InetAddress addr;		
	String str,service_id,core;
	int port;
   	byte b[]; 	
}

	/*      UDP Receive	*/

class UDPReceive extends DeclareUDP implements Runnable
{
  Thread thread;		
  int port_s,flag; 
  String port_c,addr_c;	
  boolean sema,chat_sema,change_sema,file_sema,voice_sema;
  
  String to_id,req,res;

 int f1,no;
 String user_id;	
			/* coference      */


	UDPReceive(String port_ser,String port_client,String addr_client)
	{
		port_s=Integer.parseInt(port_ser);
		port_c=port_client;
		addr_client=addr_client.substring(0,addr_client.indexOf("/"));
		addr_c=addr_client;

		thread =new Thread(this);
		thread.start();

		
	}
	public void run()
	{
		
		try
		{
	
		ds=new DatagramSocket(port_s);
		

			while(true)
			{	
				b=new byte[1024];
				dp=new DatagramPacket(b,b.length);
				ds.receive(dp);
				System.out.println("fG");
				str=new String(b);
				str=str.trim();
				
			
				flag=str.indexOf("#");
				service_id=str.substring(0,flag+1);

				core=str.substring(flag+1);
				//System.out.println(service_id+" "+core);

/* mail service */
					if(service_id.equals("mail@01_101#"))
					{
						sema=Catch.setsemaphore();
						if(sema)
						   {
							Catch.var=1;
							UDPSend.send(addr_c,port_c,"mail@01_201#"); /* good */
						   }
						else
						   {
							UDPSend.send(addr_c,port_c,"mail@01_401#"); /* bad */
						   }	
							
					}

					if(service_id.equals("mail@02_101#"))
					{
						sema=Catch.getsemaphore();
						if(sema)
						   {
							Catch.var1=1;
							UDPSend.send(addr_c,port_c,"mail@02_201#");  /* good */
							Service.mailGet(addr_c,port_c,core);
							UDPSend.send(addr_c,port_c,"mail@02_501#");  /* finish service */					
							Catch.var1=0;
						   }
						else
						   {
							UDPSend.send(addr_c,port_c,"mail@02_401#");  /* bad  */
						   }	
					}	

					if(service_id.equals("mail@03_101#"))
					{
						sema=Catch.mesgsemaphore();
						  if(sema)
						     {	
							Catch.var2=1;
							UDPSend.send(addr_c,port_c,"mail@03_201#");
							Service.mailMesg(addr_c,port_c,core);
							
						     }
						  else
						     {
							UDPSend.send(addr_c,port_c,"mail@03_401#");
						     } 		
					}
					
					if(service_id.equals("mail@04_101#"))
					{
						sema=Catch.deletesemaphore();
						  if(sema)
						    {
							Catch.var3=1;	
							UDPSend.send(addr_c,port_c,"mail@04_201#");
							Service.mailDelete(addr_c,port_c,core);
						    }
						else
						    {	
							UDPSend.send(addr_c,port_c,"mail@04_401#");
						    }
					}	
						
				
					if(service_id.equals("mail@01#"))
					{
										
						Catch.store(core);		/* store mail	*/
/* -----mail service---- */			}

/*     chat service   */

					if(service_id.equals("chat@01_101#"))
					{
				
						chat_sema=Catch.chatconnectSemaphore();
						if(chat_sema)
						{	
						Catch.var4=1;
						req=service_id+core;

						to_id=req.substring(req.lastIndexOf("/")+1);
						System.out.println(to_id);
						Service.chatConnect(to_id,req);
						}
						else
						{
							UDPSend.send(addr_c,port_c,"chat@01_401#");
						}
					}

					if(service_id.equals("chat@01_201#"))
					{
						res=service_id+ChatServiceDataBase.user_ip+"/"+ChatServiceDataBase.user_port+"/"+ChatServiceDataBase.user_name+core;
						UDPSend.send(ChatServiceDataBase.addr,ChatServiceDataBase.portt,res);
						
						Catch.var4=0;
					}

/*  Conference   */

					if(service_id.equals("conf@01_101#"))	
					{	
						chat_sema=Catch.conferenceSema();
						if(chat_sema)
						{	
						Catch.var5=1;

							if(Service.conferencePort_1())
							      {  UDPSend.send(addr_c,port_c,"conf@01_201#");  }
							else
							      { 
							           UDPSend.send(addr_c,port_c,"conf_full@01#");
							          Catch.var5=0;
							      } 
								
						}
						else
						{
						UDPSend.send(addr_c,port_c,"conf@01_401#");
						}	
					}
					if(service_id.equals("conf@01#"))	
					{
						no=Integer.parseInt(core.substring(0,core.indexOf("/")));
						core=core.substring(core.indexOf("/")+1);
					
						System.out.println("aaaaaa"+no);
						
						Service.conferencePort();     							

						while(no>1)
						{
							f1=core.indexOf("/");
							user_id=core.substring(0,f1);
							System.out.println(user_id);
							core=core.substring(f1+1);
							no--;
							
							Service.conferenceConnect(user_id);
						}
						user_id=core.substring(0);
						System.out.println(user_id);
						Service.conferenceConnect(user_id);
						UDPSend.send(addr_c,port_c,"conf@101#"+ConferenceServiceDataBase.conf_port);

						new ConferevceReceive(ConferenceServiceDataBase.conf_port);
						
						Service.conferencePortUpdate();

						Service.calledPerson(port_c);
						ConferenceServiceDataBase.no=ConferenceServiceDataBase.no+1;
						ConferenceSend.send(ConferenceServiceDataBase.conf_port,"conf@02#"+ConferenceServiceDataBase.no+ConferenceServiceDataBase.whole+"/"+ConferenceServiceDataBase.called);

						ConferenceServiceDataBase.no=0;
						ConferenceServiceDataBase.whole="";

						Catch.var5=0;
												
					}

					if(service_id.equals("conf_sema@01#"))
					{         Catch.var5=0;    }
/*   Change Password     */

					if(service_id.equals("pass@01_101#"))	
					{
						change_sema=Catch.changeSema();
						if(change_sema)
						{
							Catch.var6=1;
							System.out.println("gggggggg");	

							UDPSend.send(addr_c,port_c,"pass@01_201#");

						}
						else
						{
							UDPSend.send(addr_c,port_c,"pass@01_401#");
						}
					}

					if(service_id.equals("pass@01#"))	
					{
						Service.changePassword(addr_c,port_c,core);
					}

/*     File Transfer         */
					if(service_id.equals("file@01_101#"))	
					{
						file_sema=Catch.fileSema();
						if(file_sema)
						{
							Catch.var7=1;
							UDPSend.send(addr_c,port_c,"file@01_201#");
							Service.response(addr_c,port_c,core);
						}
						else
						{
							UDPSend.send(addr_c,port_c,"file@01_401#");	
						}
					}

					if(service_id.equals("file@close#"))	
					{
						Catch.var7=0;
						
					}
/* voice chat   */
					if(service_id.equals("voice@01_101#"))	
					{
						voice_sema=Catch.voiceSema();
						if(voice_sema)
						{
							Catch.var8=1;
							UDPSend.send(addr_c,port_c,"voice@01_201#");
							Service.voicePort(addr_c,port_c,core);
						}
						else
						{
							UDPSend.send(addr_c,port_c,"voice@01_401#");	
						}
						
					}	

					if(service_id.equals("voice_close@01_101#"))
					{
						Service.voicePortUpdate_2(core);
					}			
			
/*	Close	*/

					if(service_id.equals("close@01#"))	
					{
						Service.udpPortUpdate(port_c);
						Service.currentUserUpdate(port_c);
						UDPSend.send(addr_c,port_c,"close@01#");

						new SendUserInfo();
					
							try { ds.close();  }
							catch(Exception ex) {  System.out.println("Socket Close  :  "+ex);  } 	
						break;
					}

				
			}
		
		}
		catch(Exception ex)
		{
		System.out.println("UDP_Receive : "+ ex);
		}
		
	}
}	

	/*	UDP SEND	*/

class UDPSend
{
   static DatagramSocket ds;
   static DatagramPacket dp; 
   static InetAddress addr;		
   static String str;	
   static byte b[];
   static int port;

	public static void send(String des_addr,String pport,String mesg)
	{
		b=mesg.getBytes();
		port=Integer.parseInt(pport);
		try
		{
		addr=InetAddress.getByName(des_addr);
		dp=new DatagramPacket(b,b.length,addr,port);
		ds=new DatagramSocket();
		ds.send(dp);
		System.out.println(addr);
		}
		catch(Exception ex)
		{
		System.out.println("UDP_SEND : "+ex);
		}
	}

	
}


/* mail store process */

class Catch
{
	static int var=0,flag=0,var1=0,var2=0,var3=0,var4=0,var5=0,var6=0,var7=0,var8=0;

	static String mail_str[]; 
	
	Catch()
	{
		mail_str=new String[4];
	}
	
	public static boolean setsemaphore()
	{
		if(var==0)
			return true;
		else
			return false;

	}
	public static boolean getsemaphore()
	{
		if(var1==0)
			return true;
		else
			return false;
	}
	public static boolean mesgsemaphore()
	{
		if(var2==0)
			return true;
		else
			return false;
	}
	public static boolean deletesemaphore()
	{
		if(var3==0)
			return true;
		else
			return false;
	}
	public static boolean chatconnectSemaphore()
	{
		if(var4==0)
			return true;
		else
			return false;
	}

	public static boolean conferenceSema()
	{
		if(var5==0)
			return true;
		else
			return false;
	}
	public static boolean changeSema()
	{
		if(var6==0)
			return true;
		else
			return false;
	}
	public static boolean fileSema()
	{
		if(var7==0)
			return true;
		else
			return false;
	}
	public static boolean voiceSema()
	{
		if(var8==0)
			return true;
		else
			return false;
	}

	public static void store(String s)
	{

		System.out.println("hai");
		mail_str[flag]=s;
		flag++;

		if(flag==4)
		{
			flag=0;
			//var=0;
			//System.out.println(mail_str[0]+mail_str[1]+mail_str[2]+mail_str[3]);
		        Service.mailStore(mail_str[0],mail_str[1],mail_str[2],mail_str[3]);
		}	
	}	

	
}

class Service 
{

	public static void first()
	{	VerifyDataBase.connect();
		VerifyDataBase.first_currentDelete();
		VerifyDataBase.close();

		VerifyDataBase.connect();
		VerifyDataBase.first_port();
		VerifyDataBase.close();

		VerifyDataBase.connect();
		VerifyDataBase.first_conference();
		VerifyDataBase.close();

		VerifyDataBase.connect();
		VerifyDataBase.first_voice();
		VerifyDataBase.close();

	}   


	public static void mailStore(String to_id,String from,String sub,String mesg)
	{
		ServiceDataBase.connect();
		ServiceDataBase.mailStore(to_id,from,sub,mesg);
		ServiceDataBase.close();
	}	

	public static void mailGet(String addr_c,String port_c,String core)
	{
		ServiceDataBase.connect();
		ServiceDataBase.mailGet(addr_c,port_c,core);
		ServiceDataBase.close();
	}
	public static void mailMesg(String addr_c,String port_c,String core)
	{
		ServiceDataBase.connect();
		ServiceDataBase.mailMesg(addr_c,port_c,core);
		ServiceDataBase.close();
	}
	public static void mailDelete(String addr_c,String port_c,String core)
	{
		ServiceDataBase.connect();
		ServiceDataBase.mailDelete(addr_c,port_c,core);
		ServiceDataBase.close();
	}

	public static void chatConnect(String to_id,String req)
	{
		ChatServiceDataBase.connect();
		ChatServiceDataBase.chatConnect(to_id,req);
		ChatServiceDataBase.close();
	}

	
	public static void conferencePort()
	{
		ConferenceServiceDataBase.connect();
		ConferenceServiceDataBase.conferencePort();
		ConferenceServiceDataBase.close();
	}

	public static boolean conferencePort_1()
	{
		ConferenceServiceDataBase.connect();
		boolean bool=ConferenceServiceDataBase.conferencePort_1();
		ConferenceServiceDataBase.close();
		
		return bool;
	}


	public static void conferencePortUpdate()
	{
		ConferenceServiceDataBase.connect();
		ConferenceServiceDataBase.conferencePortUpdate();
		ConferenceServiceDataBase.close();
              }


	public static void conferenceConnect(String user_id)
	{
		ConferenceServiceDataBase.connect();
		ConferenceServiceDataBase.conferenceConnect(user_id);
		ConferenceServiceDataBase.close();
	}
	

	public static void calledPerson(String port_c)
	{
		ConferenceServiceDataBase.connect();
		ConferenceServiceDataBase.calledPerson(port_c);
		ConferenceServiceDataBase.close();
	}

	public static void changePassword(String addr_c,String port_c,String core)
	{
		ServiceDataBase.connect();
		ServiceDataBase.changePassword(addr_c,port_c,core);
		ServiceDataBase.close();
	}
	public static void updatePassword(String addr_c,String port_c)
	{
		ServiceDataBase.connect();
		ServiceDataBase.updatePassword(addr_c,port_c);
		ServiceDataBase.close();
	}
	
	public static void response(String addr_c,String port_c,String core)
	{
		FileService.connect();
		FileService.response(addr_c,port_c,core);
		FileService.close();
	}
	public static void voicePort(String addr_c,String port_c,String core)
	{
		VoiceServiceDataBase.connect();
		VoiceServiceDataBase.portGet(addr_c,port_c,core);
		
	}
	
	public static void voicePortUpdate_1(String port)
	{
		VoiceServiceDataBase.connect();
		VoiceServiceDataBase.voicePortUpdate_1(port);
		VoiceServiceDataBase.close();
	}
	public static void  voicePortUpdate_2(String port)
	{
		VoiceServiceDataBase.connect();
		VoiceServiceDataBase.voicePortUpdate_2(port);
		VoiceServiceDataBase.close();
	}
	public static void udpPortUpdate(String port_c)
	{
		PortClose.connect();
		PortClose.udpPortUpdate(port_c);
		PortClose.close();
	}
	public static void currentUserUpdate(String port_c)
	{
		PortClose.connect();
		PortClose.currentUserUpdate(port_c);
		PortClose.close();
	}

	public static void multicastPortUpdate(String pp)
	{
		PortClose.connect();
		PortClose.multicastPortUpdate(pp);
		PortClose.close();
	}
}

class FileService
{
  static Connection con;
  static Statement st;	
  static ResultSet rs;
  static String str,to_addr,to_port;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("File_Database_Connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("File_DataBase_Close :"+ex); }
	}

	public static void response(String addr_c,String port_c,String core)
	{
		  str="select * from current where user_id='"+core+"' and status='connected'";

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			rs.next();

			to_addr=rs.getString(3);
			to_addr=to_addr.substring(to_addr.indexOf("/")+1);to_addr=to_addr.trim();
			to_port=rs.getString(4);to_port=to_port.trim();
			System.out.println("ooooooooo"+to_addr+":"+to_port);

			UDPSend.send(addr_c,port_c,"file@01#"+to_addr+"/"+to_port);
			
		}
		catch(Exception ex)
		{ System.out.println("File Transfer Response :"+ex); }			

	}
}

class ServiceDataBase
{
  static Connection con;
  static Statement st;	
  static ResultSet rs;
  static String str,date,chat_str;
  static java.util.Calendar ca;
  static java.util.Date da;

  static String change_str;	
  static String old_pass,new_pass,change_id;	
  static  int ch_1,ch_2;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("DataBase_Connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("DataBase_Close :"+ex); }
	}
	public static void mailStore(String to_id,String from,String sub,String mesg)
	{

		ca=Calendar.getInstance();
		da=ca.getTime();
		date=ca.get(Calendar.DAY_OF_MONTH)+"-"+ca.get(Calendar.MONTH)+1+"-"+ca.get(Calendar.YEAR);

		str="insert into mail values('"+to_id+"','"+from+"','"+date+"','"+sub+"','"+mesg+"')";

		try
		{
			st=con.createStatement();
			st.executeUpdate(str);

		}
		catch(Exception ex)
		{ System.out.println("Store Mail :"+ex); }
		
		Catch.var=0;
			
	}

	public static void mailGet(String addr_c,String port_c,String core)
	{

	  String from,date,sub,go;

	  str="select * from mail where user_id='"+core+"'";

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			
			while(rs.next())
			{	
			from=rs.getString(2);
			date=rs.getString(3);
			sub=rs.getString(4);
			//System.out.println(from+"/"+date+"/"+sub);
			go="mail@02#"+from+"/"+date+"/"+sub;

			UDPSend.send(addr_c,port_c,go);
			}

		}
		catch(Exception ex)
		{ System.out.println("Port_Update :"+ex); }

	}
	public static void mailMesg(String addr_c,String port_c,String core)
	{
	   int row;
	   String mesg=null,index,user_id;	
		
	   index=core.substring(core.indexOf("/")+1);
	   user_id=core.substring(0,core.indexOf("/"));	
	   System.out.println(user_id+"?????  :"+index);
	   row=Integer.parseInt(index);
	   row=row+1;			
		   	
	   str="select * from mail where user_id='"+user_id+"'";    		
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			
			while(row>=1)
			{ 
				rs.next();
				mesg=rs.getString(5);
				row--;
			}
			System.out.println(mesg);
			
			UDPSend.send(addr_c,port_c,"mail@03#"+mesg);	
			Catch.var2=0;		
		}
		catch(Exception ex)
		{  System.out.println("mailMesg : "+ex); }  
		
	}

	public static void mailDelete(String addr_c,String port_c,String core)
	{
	   int flag1;
	   String user_id,sub;	
	  
	   flag1=core.indexOf("/");
	 

	   user_id=core.substring(0,flag1);
	   sub=core.substring(flag1+1);

		//System.out.println(user_id+"[[[[[[[[[[]"+sub);


		str="delete from mail where user_id='"+user_id+"' and sub='"+sub+"'";
		
		try
		{
			st=con.createStatement();
			st.execute(str);		
		}
		catch(Exception ex)
		{
			System.out.println("Mail Delete : "+ex);
		}
	
	  Catch.var3=0;		
	}

	public static void changePassword(String addr_c,String port_c,String core)
	{
		ch_1=core.indexOf("/");
		ch_2=core.indexOf("/",ch_1+1);
						
		change_id=core.substring(0,ch_1);
		old_pass=core.substring(ch_1+1,ch_2);
		new_pass=core.substring(ch_2+1);
		System.out.println(change_id+":"+old_pass+":"+new_pass);

		change_str="select * from password where user_id='"+change_id+"'";


		try
		{
			st=con.createStatement();
			rs=st.executeQuery(change_str);

			rs.next();

			String pass=rs.getString(2);  pass=pass.trim();
				
			if(old_pass.equals(pass))
 			    {
				   System.out.println("lllllll"+pass);
				   Service.updatePassword(addr_c,port_c);
				   
                                                }     
				
			else
			      UDPSend.send(addr_c,port_c,"pass@04#");

		}
		catch(Exception ex)
		{
			System.out.println("Password Change Verify : "+ex);
		}

		Catch.var6=0;
	}

	public static void updatePassword(String addr_c,String port_c)
	{
		change_str="update password set password='"+new_pass+"' where user_id='"+change_id+"'";

		try
		{
			st=con.createStatement();
			st.execute(change_str);

			UDPSend.send(addr_c,port_c,"pass@02#");
		}
		catch(Exception ex)
		{	System.out.println("Password Change :"+ex);   
		}		
	
	}
}

class PortClose
{
	static Connection con;
	static Statement st;	
	static ResultSet rs;
	static String str;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("PortClose_connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("PortClose_close :"+ex); }
	}
	public static void udpPortUpdate(String port_c)
	{
		str="update port set status='notused' where client='"+port_c+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	
		}
		catch(Exception ex){ System.out.println("PortClose_Update :"+ex); }
	}
	public static void currentUserUpdate(String port_c)
	{
		str="update current set status='disconnected' where port='"+port_c+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	
		}
		catch(Exception ex){ System.out.println("PortClose_Update :"+ex); }
	}

	
	public static void multicastPortUpdate(String pp)
	{
		str="update conference set status='notused' where port='"+pp+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	
		}
		catch(Exception ex){ System.out.println("Conference PortClose_Update :"+ex); }
	}

	
}

class ChatServiceDataBase
{
  static Connection con;
  static Statement st;	
  static ResultSet rs;
  static String str,user_name,user_ip,user_port;
  
  static String addr,portt;
  
  
  static InetAddress to_addr;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("Chat_DataBase_Connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("Chat_DataBase_Close :"+ex); }
	}

	public static void chatConnect(String to_id,String req)
	{

	int port;
        int f1,f2,f3; 


		f1=req.indexOf("#");  
		f2=req.indexOf("/");
		f3=req.indexOf("/",f2+1);			
                 
		addr=req.substring(f1+1,f2);
		portt=req.substring(f2+1,f3); 
		
		System.out.println(addr+" llllllll"+portt);
		str="select * from current where user_id='"+to_id+"'";

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			
			rs.next();
			
			user_name=rs.getString(2);user_name=user_name.trim();
			user_ip=rs.getString(3);user_ip=user_ip.trim();
			user_ip=user_ip.substring(0,user_ip.indexOf("/"));
			user_port=rs.getString(4);user_port=user_port.trim();

			System.out.println(user_name+","+user_ip+","+user_port);

			to_addr=InetAddress.getByName(user_ip);
			port=Integer.parseInt(user_port);
			UDPSend.send(user_ip,user_port,req);

		}
		catch(Exception ex)
		{
			System.out.println("Chat Connect_DB :"+ex);
		}
	}
}

class VoiceServiceDataBase
{
   static Connection con;
   static Statement st;	
   static ResultSet rs;
   static String str,from_client,to_client;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("VoiceServiceDataBase_Connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("VoiceServiceDataBase_Connect :"+ex);  }
	}
	
	public static void portGet(String addr_c,String port_c,String core)
	{
		str="select * from voice where status='notused'";
		
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.next();

			from_client=rs.getString(1);
			to_client=rs.getString(2);

			System.out.println(from_client+":"+to_client);

				if(from_client.equals("4000"))
				{
					UDPSend.send(addr_c,port_c,"voice_full@01#");
				}
								
				else
				{
					UDPSend.send(addr_c,port_c,"voice@01#"+from_client+"/"+to_client+"/"+core);
					con.close();
					Service.voicePortUpdate_1(from_client);		
				}

		
			Catch.var8=0;
		}
		catch(Exception ex){ System.out.println("VoiceServiceDataBase_portGet :"+ex);  }
		
	}
	
	public static void voicePortUpdate_1(String port)
	{
		str="update voice set status='used' where from_client='"+port+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	
		}
		catch(Exception ex){ System.out.println("VoiceServiceDataBase_portUpdate_1 :"+ex);  }
	}
	
	public static void voicePortUpdate_2(String port)
	{
		str="update voice set status='notused' where to_client='"+port+"'";

		try
		{
			st=con.createStatement();
			st.execute(str);	
		}
		catch(Exception ex){ System.out.println("VoiceServiceDataBase_portUpdate_1 :"+ex);  }
	}

}

class ConferenceServiceDataBase
{
  static Connection con;
  static Statement st;	
  static ResultSet rs;
  static String str,user_port,user_ip,user_id,user_name;
    
  static String conf_port;

 static String whole="";
 static int no=0;

static String called_id,called_name,called;

	public static void connect()
	{
		try{  con=DriverManager.getConnection("jdbc:odbc:group","scott","tiger");	}
		catch(Exception ex){  System.out.println("Conference_DataBase_Connect :"+ex); }
	}
	public static void close()
	{
		try{ con.close(); }
		catch(Exception ex){ System.out.println("Conference_DataBase_Connect :"+ex);  }
	}
	
	public static void conferencePort()
	{
	
		str="select * from conference where status='notused'";

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.next();

			conf_port=rs.getString(1);

		}
		catch(Exception ex)
		{	System.out.println("Conference_DataBase_Port :"+ex);   
		}	
					
	}

	public static boolean conferencePort_1()
	{
	String c_port=null;
	
		str="select * from conference where status='notused'";

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.next();

			c_port=rs.getString(1); c_port=c_port.trim();
			
		}
		catch(Exception ex)
		{	System.out.println("Conference_DataBase_Port :"+ex);   
		}	
					
		if(c_port.equals("5000"))
		              return false;
		else
			return true;
	}


	public static void calledPerson(String port_c)
	{
		str="select * from current where port='"+port_c+"' and status='connected'";
		
		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.next();

			called_id=rs.getString(1);
			called_name=rs.getString(2);
			
			called=called_id+":"+called_name;

			System.out.println(called+"uuuuuuuu");
		}
		catch(Exception ex)
		{	System.out.println("Conference_DataBase_calledPerson :"+ex);   
		}	
		
	}
	public static void conferencePortUpdate()
	{
	
		str="update conference set status='used' where port='"+conf_port+"'";
			
		try
		{
			st=con.createStatement();
			st.execute(str);
		
		}
		catch(Exception ex)
		{	System.out.println("Conference_DataBase_Port :"+ex);   
		}	
					
	}

	public static void conferenceConnect(String user_id)
	{
		str="select * from current where user_id='"+user_id+"' and status='connected'";

		try
		{
			st=con.createStatement();
			rs=st.executeQuery(str);

			rs.next();			
			
			user_id=rs.getString(1);
			user_name=rs.getString(2);
 			user_ip=rs.getString(3);
			user_ip=user_ip.trim();
			user_ip=user_ip.substring(0,user_ip.indexOf("/"));
			user_port=rs.getString(4);
		
			System.out.println(user_ip+":"+user_port);
			UDPSend.send(user_ip,user_port,"conf@101#"+conf_port);


			whole=whole+"/"+user_id+":"+user_name;
			no++;
		}
		catch(Exception ex)
		{	System.out.println("Conference_DataBase_Connect :"+ex);   
		}
	}
}

	/*  Conference Users  Send	*/

class  ConferenceSend
{
   static MulticastSocket ms;
   static InetAddress addr;
   static DatagramPacket dp;
   static String str;
   static byte  b[];
   static int port;
	

	ConferenceSend()
	{
		try
		{		
		addr=InetAddress.getByName("225.2.2.2");
		ms=new MulticastSocket();
		}
		catch(Exception ex)
		{
		System.out.println("ConferenceSend Socket Create : " + ex);
		}
	}
	public static void send(String p,String mesg)
	{
		try
		{
		port=Integer.parseInt(p);
		b=mesg.getBytes();
		dp=new DatagramPacket(b,b.length,addr,port);
		ms.send(dp);
		}
		catch(Exception ex)
		{
		System.out.println("ConferenceSend Send Data  : " + ex );
		}
	}		
}


class ConferevceReceive extends Thread
{
   MulticastSocket ms;
  InetAddress addr;
  DatagramPacket dp;	
  String str;
  byte b[]	;  

 String pport,service_id;
  int port,flag=0;

	ConferevceReceive(String p)
	{
		pport=p;
		port=Integer.parseInt(p);
		start();
	}

	public void run()
	{
		try
		{
		addr=InetAddress.getByName("225.2.2.2");
		ms=new MulticastSocket(port);
		ms.joinGroup(addr);
		}

		catch(Exception ex)
		{  System.out.println("ConferevceReceive JoinGroup  : " + ex );  }
		

			while(true)
			{
				try
				{
				b=new byte[1024];
				dp=new DatagramPacket(b,b.length);	
				
				ms.receive(dp);
				str=new String (b);
				str=str.trim();
				service_id=str.substring(0,str.indexOf("#")+1);

					if(service_id.equals("close@02#"))
					   flag++;

					if(flag==2)
					 {
					     ConferenceSend.send(pport,"conf_close@01#"+"Service Closed by Other Person");
					     Service.multicastPortUpdate(pport);	 
					     ms.close();
					     break;		
					}
				}
				catch(Exception ex)
				{  System.out.println("ConferevceReceive  Receive  : "+ ex);  }
			}	
	}
}

class SendUserInfo extends Thread
{
	SendUserInfo()
	{
		start();
	}
	
	public void run()
	{
		try
		{
			Thread.sleep(5000);
		}
		catch(Exception ex)
		{}
		Monitor.groupUser();
		Monitor.currentUser();
	}
}

class WindowClose extends WindowAdapter
{
     JFrame frm;
   	      	
	public void  windowClosing(WindowEvent e) 
 	{
		frm=(JFrame)e.getSource();
				
		if(frm.equals(Declare.main_frm))
		{
			System.exit(1);
		}
	}
}

