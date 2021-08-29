import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.sound.sampled.*;

public class client
{


static String server_addr,server_port,client_port,user_id,user_name,user_addr;



static String whole;
	public static void main(String args[])
	{
	

	server_addr=args[0];
	server_port=args[1];
	client_port=args[2];
	user_id=args[3];
	user_name=args[4];
	user_addr=args[5];


			new CatchMail();            /* take */
			new MultiCastReceive("224.2.2.2","2222"); 
			

			new ConferenceSend();

		

		MainCreate ob=new MainCreate();

		ob.frm_Create();

		ob.menu_Create();
		ob.menu_Add();
		
		ob.label_Create();
		ob.text_Create();
		ob.button_Create();
		ob.mailTableCreate();

		ob.monitor();

		ob.mdi();
		ob.frm_Add();

		ob.main_frm.show();

		ob.confCreate();

		server_addr=args[0];
		server_port=args[1];
		client_port=args[2];
		user_id=args[3];
		whole=user_addr+"/"+client_port+"/"+user_id+"/"+user_name;

		new UDPReceive(client_port);
		
		new UDPSend(server_addr,server_port);

		//UDPSend.send("haao");

		
	}
}

	/*	Component Declare	*/

class Declare
{
	static JFrame main_frm;
	static ExInternalFrame mail_frm,mail_rec,mail_mesg;	
	static JDesktopPane desk;				/* Mail Declare  */		
	static Container container_main,container_mail,container_rec,container_mesg;


	static ExLabel monitor,currentuser,groupuser;
	static JTable t_current_user,t_group_user;		/* Monitor Declare  */
              static JScrollPane scroll_c_u,scroll_g_u;
	static TableModel tm_current_user,tm_group_user;


	static JFrame conf_main_frm;
	static Container conf_con;			/*	CONFERENCE frame	*/
	static JDesktopPane conf_desk;

	static JMenuBar menubar;
	static JMenu user;
		static JMenuItem change;
		static JMenuItem logout;			/* Menu Create */
	static JMenu service;
		static JMenu mail;
		    static JMenuItem send_mi;
		    static JMenuItem inbox;
		static JMenuItem chat;	
	              static JMenu filetransfer;
			static JMenuItem m_file_send;
			static JMenuItem m_file_received; 
			

	static ExLabel to_user_id,sub,mesg;
	static ExTextField text_to_user_id,text_sub;  /* mail send form */
	static ExTextArea text_mesg;
	static ExButton send,close,temp;
	static JScrollPane scrol_text_area;	

	static ExLabel la_from,la_sub,la_mesg;
	static ExLabel l_la_from,l_la_sub,l_la_mesg;   /* message show form */
	static ExTextArea show_mesg;
	static JScrollPane mesg_scrol;
	static ExButton show_close;

	static JTable table_mail_rec;
	static TableModel model;
	static JScrollPane scrol;
	static ExButton mail_view,mail_delete,mail_close;       /* mail's show form */
	static ExTextField mail_rec_text;


	static ExInternalFrame chang_pass_frm;
	static Container con_chang_pass_frm;	/* change Password	*/
	static ExLabel la_old_pass,la_new_pass;
	static JPasswordField  text_old_pass,text_new_pass;
	static ExButton b_update,b_cancel;

						/*   File transfer	*/
	static ExInternalFrame file_frm;
  	static  Container file_frm_con;
	static ExLabel la_toid,la_file;
    	static ExTextField text_toid,text_file;	
	static ExButton browse,file_send,file_close;
	static JFileChooser fc;

					           /*  file transfer animate  */
	static ExInternalFrame file_frm_ani;
	static Container file_frm_ani_con;
	static JProgressBar progress;
	static ExLabel  file_display;
					 /*  file rec transfer animate  */	
	
	static ExInternalFrame rec_file_frm_ani;
	static Container rec_file_frm_ani_con;
	static JProgressBar rec_progress;
	static ExLabel  rec_file_display,rec_from;

}
		/* chat send Declare */
class ChatDeclare
{
	static JFrame chat_main_frm;
	static Container chat_con;
	static JDesktopPane chat_desk;

	static ExButton b_chat;
	static ExTextField t_chat;
              static ExButton b_conference;

	static ExInternalFrame i_frm[];
	static Container i_chat_con[];
	static ExButton  i_send[],bu_voice[],bu_mute[];
	static ExLabel   i_l_mesg[],i_l_received[],la_voice[],la_income[];
	static ExTextField i_t_mesg[];
	static ExTextArea i_ta_mesg[];
	static JScrollPane i_scrol[];

	static String to_addr[],to_port[],to_app[];
	
	static int frm_count=0;
	static int flag[],flag_back[];

	static String voice_from_port[];
	static String voice_to_port[];

	static Thread  from_thread[];
              static DatagramSocket sound_ds[];

	static int state[],state_flag[];
	static String name[];
}


class ChatCreate extends ChatDeclare
{
	public static void mainfrmCreate()
	{
		i_frm=new ExInternalFrame[100];
		i_chat_con=new Container[100];
		i_l_mesg=new ExLabel[100];
		i_l_received =new ExLabel[100];
		i_t_mesg=new ExTextField[100];
		i_send=new ExButton[100];
		i_ta_mesg=new ExTextArea[100];
		i_scrol=new JScrollPane[100];

		la_voice=new ExLabel[100];
		la_income=new ExLabel[100];
		bu_voice=new ExButton[100];
		bu_mute=new ExButton[100];
		to_addr=new String[100];
		to_port=new String[100];
		to_app=new String[100];

		flag=new int[100];
		flag_back=new int[100];
		voice_from_port=new String[100];		
		voice_to_port=new String[100];

		from_thread=new Thread[100];
		sound_ds=new DatagramSocket[100]; 

		state=new int[100];
		state_flag=new int[100];

		name=new String[100];

		chat_main_frm=new JFrame("Chat  :  "+client.user_name);
		chat_main_frm.setResizable(false);  chat_main_frm.addWindowListener(new WindowClose()); 
		
		chat_main_frm.setBounds(20,20,500,500);
		chat_con=chat_main_frm.getContentPane();

		chat_desk=new JDesktopPane();
		

			t_chat=new ExTextField(205,20,100,20);
			b_chat=new ExButton(305,20,70,20,"Chat"); b_chat.addActionListener(new ChatAction());
			b_conference=new ExButton(105, 20,100,20,"Conference"); b_conference.addActionListener(new ChatAction());


			chat_desk.add(t_chat);		
			chat_desk.add(b_chat);
			chat_desk.add(b_conference);					


		chat_con.add(chat_desk);
		
		      /*  conference	*/		
		
		chat_main_frm.setVisible(true);
	}

	public static void chatfrm(int i)
	{
		i_frm[i]=new ExInternalFrame(70,125,300,230,"wait...");

		i_frm[i].addInternalFrameListener(new FrameStateChange(i,false));
		state_flag[i]=1;
			try
			{
			i_frm[i].setMaximum(true);
			}
			catch(Exception ex){}	

		i_frm[i].setVisible(true);
		
		i_frm[i].setClosable(true);
		i_chat_con[i]=i_frm[i].getContentPane();
		i_chat_con[i].setLayout(null);

		labelCreate(i);
		textCreate(i);
		buttonCreate(i);
		
		add(i);
	
		chat_desk.add(i_frm[i]);
		i_frm[i].moveToFront();
	}
	
	public static void buttonCreate(int i)
	{
		i_send[i]=new ExButton(211,24,70,20,"Send");  i_send[i].addActionListener(new ChatAction(i));
		bu_voice[i]=new ExButton(78,168,85,20,"Initiate");bu_voice[i].addActionListener(new ChatAction(i));
		bu_mute[i]=new ExButton(210,168,75,20,"Mute");bu_mute[i].addActionListener(new ChatAction(i));
		
	}
	
	public static void textCreate(int i)
	{
		i_t_mesg[i]=new ExTextField(10,24,200,20);  i_t_mesg[i].addActionListener(new ChatAction_1(i));

		i_ta_mesg[i]=new ExTextArea(10,65,270,95);
			i_ta_mesg[i].setForeground(Color.WHITE);
			i_ta_mesg[i].setBackground(Color.GRAY);
			i_ta_mesg[i].setFocusable(false);
		i_scrol[i]=new JScrollPane(i_ta_mesg[i]);
		i_scrol[i].setBounds(10,65,270,95);
	}

	public static void labelCreate(int i)
	{
		i_l_mesg[i]=new ExLabel(5,5,100,18,"Type Hear :");
		i_l_received[i]=new ExLabel(8,46,100,18,"Received :");
		la_voice[i]=new ExLabel(2,168,80,20,"Voice Chat  :  ");
		la_income[i]=new ExLabel(160,168,80,20," Voice  :  ");
	}

	public static void add(int i)
	{
		i_chat_con[i].add(i_l_mesg[i]);
		i_chat_con[i].add(i_t_mesg[i]);
		i_chat_con[i].add(i_send[i]);
		i_chat_con[i].add(i_l_received[i]);
		i_chat_con[i].add(i_scrol[i]);
		i_chat_con[i].add(la_voice[i]);
		i_chat_con[i].add(bu_voice[i]);
		i_chat_con[i].add(la_income[i]);
		i_chat_con[i].add(bu_mute[i]);
		
	}

	

}

		/* chat Receive declare */

class ChatRecDeclare
{
	static JFrame chat_main_frm;
	static Container chat_con;
	static JDesktopPane chat_desk;

	

	static ExInternalFrame i_frm[];
	static Container i_chat_con[];
	static ExButton  i_send[],bu_voice[],bu_mute[];
	static ExLabel   i_l_mesg[],i_l_received[],la_voice[],la_income[];
	static ExTextField i_t_mesg[];
	static ExTextArea i_ta_mesg[];
	static JScrollPane i_scrol[];
	
	static int frm_count=0;

	static int frm_flag=0;

	static String to_addr[];
	static String to_port[];
	static String to_app[];

	static String voice_rec_port[];
	static String voice_sen_port[];

	static int flag[],flag_back[];

	static Thread sound_record[],end_thread[];
	static DatagramSocket sound_ds[];

	static int state[],state_flag[];
	static String name[];
}
class ChatRecCreate extends ChatRecDeclare
{
	public static void mainfrmCreate(int i,String to_add,String to_por,String to_ap,String to_name)
	{
	   if(frm_flag==0)
                  {
		frm_flag=1;	
		
		i_frm=new ExInternalFrame[100];
		i_chat_con=new Container[100];
		i_l_mesg=new ExLabel[100];
		i_l_received =new ExLabel[100];
		i_t_mesg=new ExTextField[100];
		i_send=new ExButton[100];
		i_ta_mesg=new ExTextArea[100];
		i_scrol=new JScrollPane[100];

		to_addr=new String[100];
		to_port=new String[100];
		to_app=new String[100];

		voice_rec_port=new String[100];
		voice_sen_port=new String[100];

		la_voice=new ExLabel[100];   
		la_income=new ExLabel[100];
		bu_voice=new ExButton[100];
		bu_mute=new ExButton[100];

		flag=new int[100];
		flag_back=new int[100];


		sound_record=new Thread[100];
		end_thread=new Thread[100];
		sound_ds=new DatagramSocket[100];

		state=new int[100];
		state_flag=new int[100];
		name=new String[100];

		chat_main_frm=new JFrame("Receive Chat  :  "+client.user_name);
		chat_main_frm.setResizable(false);   chat_main_frm.addWindowListener(new WindowClose()); 
		chat_main_frm.setBounds(20,20,500,500);
		chat_con=chat_main_frm.getContentPane();

		chat_desk=new JDesktopPane();
		
		
		chat_con.add(chat_desk);
		
		chat_main_frm.setVisible(true);

		chatfrm(i,to_add,to_por,to_ap,to_name); 
	     }
	     else
	     {
		chatfrm(i,to_add,to_por,to_ap,to_name);	
	     }		 		
	}

	public static void chatfrm(int i,String to_addr,String to_port,String to_app,String to_name)
	{
		i_frm[i]=new ExInternalFrame(70,125,300,230,"chat");

		i_frm[i].addInternalFrameListener(new FrameStateChange(i,true));
		state_flag[i]=1;

			try
			{
			i_frm[i].setMaximum(true);
			}
			catch(Exception ex){}

		i_frm[i].setVisible(true);
		
		i_frm[i].setClosable(true);
		i_chat_con[i]=i_frm[i].getContentPane();
		i_chat_con[i].setLayout(null);

		labelCreate(i);
		textCreate(i);
		buttonCreate(i);
		
                assign(i,to_addr,to_port,to_app,to_name);  
		
		add(i);
	
		chat_desk.add(i_frm[i]);

		i_frm[i].moveToFront();
	}
	
	public static void buttonCreate(int i)
	{
		i_send[i]=new ExButton(211,24,70,20,"Send");  i_send[i].addActionListener(new RecChatAction(i));
		bu_voice[i]=new ExButton(78,168,85,20,"Enable");bu_voice[i].addActionListener(new SoundRecAction(i));
		bu_voice[i].setEnabled(false);
		bu_mute[i]=new ExButton(210,168,75,20,"Mute");  bu_mute[i].addActionListener(new SoundRecAction(i));
		bu_mute[i].setEnabled(false);
		
	}
	
	public static void textCreate(int i)
	{
		i_t_mesg[i]=new ExTextField(10,24,200,20);  i_t_mesg[i].addActionListener(new ChatAction_1(i));

		i_ta_mesg[i]=new ExTextArea(10,65,270,95);
			i_ta_mesg[i].setForeground(Color.WHITE);
			i_ta_mesg[i].setBackground(Color.darkGray);
			i_ta_mesg[i].setFocusable(false);
		i_scrol[i]=new JScrollPane(i_ta_mesg[i]);
		i_scrol[i].setBounds(10,65,270,95);
	}

	public static void labelCreate(int i)
	{
		i_l_mesg[i]=new ExLabel(5,5,100,18,"Type Hear :");
		i_l_received[i]=new ExLabel(8,46,100,18,"Received :");

		la_voice[i]=new ExLabel(2,168,80,20,"Voice Chat  :  ");
		la_income[i]=new ExLabel(160,168,80,20," Voice  :  ");
	}

	public static void assign(int i,String to_addre,String to_porta,String to_appip,String to_name)
	{
		to_addr[i]=to_addre;
		to_port[i]=to_porta;
		to_app[i]=to_appip;
		i_frm[i].setTitle(to_name);
		name[i]=to_name;
		
	}

	public static void add(int i)
	{
		i_chat_con[i].add(i_l_mesg[i]);
		i_chat_con[i].add(i_t_mesg[i]);
		i_chat_con[i].add(i_send[i]);
		i_chat_con[i].add(i_l_received[i]);
		i_chat_con[i].add(i_scrol[i]);
		
		i_chat_con[i].add(la_voice[i]);
		i_chat_con[i].add(bu_voice[i]);
		i_chat_con[i].add(la_income[i]);
		i_chat_con[i].add(bu_mute[i]);
	}

}

		/*	Conference Declare	*/

class ConferenceDeclare
{
	static ExInternalFrame adduser;

	static Container adduser_con;

	static ExLabel current_user,conf_user;

	static JList l_first,l_second;
	static JScrollPane scroll_first,scroll_second;

	static ExButton set,remove,ok,close;

	static  DefaultListModel  lm_first,lm_second;	

	static Object select;

}

class ConferenceCreate extends ConferenceDeclare
{

	public static void mainCreate()
	{

	   Object user_id,user_name;
 	   int i=0;	

		adduser=new ExInternalFrame(50,50,350,290,"Conference  :  "+client.user_name);
		adduser_con=adduser.getContentPane();
		adduser_con.setLayout(null);

		 lm_first=new DefaultListModel();
		 lm_second=new DefaultListModel();lm_second.clear(); 

		current_user=new ExLabel(20,8,100,20,"Current User");
		conf_user=new ExLabel(200,8,100,20,"Conference User");

		l_first = new JList(lm_first);    l_first.addListSelectionListener(new  ActionSelect());

		l_second = new JList(lm_second);
		

		scroll_first= new JScrollPane(l_first );
		scroll_first.setBounds(20,37,120,180);
		scroll_second= new JScrollPane(l_second );
		scroll_second.setBounds(200,37,120,180);

		set=new ExButton(143,90,50,20,">");              set.addActionListener(new SelectUserAction()); 
		remove=new ExButton(143,130,50,20,"<");     remove.addActionListener(new SelectUserAction());

		ok=new ExButton(85,225,80,20,"Intimate");             ok.addActionListener(new SelectUserAction(adduser)); 
		close=new ExButton(165,225,80,20,"Close");   close.addActionListener(new conferenceAction(adduser));

		adduser_con.add(current_user);
		adduser_con.add(conf_user);
		adduser_con.add(scroll_first);
		adduser_con.add(scroll_second);
		adduser_con.add(set);
		adduser_con.add(remove);
		adduser_con.add(ok);
		adduser_con.add(close);

		ChatDeclare.chat_desk.add(adduser);		
		adduser.setVisible(true);
		
		try
		{
			user_id=Declare.tm_current_user.getValueAt(0,0);
			while(!user_id.equals("null"))
			{
			    user_id=Declare.tm_current_user.getValueAt(i,0);	
			    user_name=Declare.tm_current_user.getValueAt(i,1);
			    
			    lm_first.addElement(user_id+":"+user_name);
			    i++;	
		                 user_id=Declare.tm_current_user.getValueAt(i,0);
			
			}	
		} 
		catch(Exception ex)
		{ } 
	}
}

class conferenceAction implements ActionListener
{
   ExInternalFrame frm;
	conferenceAction(ExInternalFrame t_frm)
	{
		frm=t_frm;
	}
	public void actionPerformed(ActionEvent ae)
	{
		frm.dispose();
		UDPSend.send("conf_sema@01#");
	}
}


class ActionSelect extends ConferenceDeclare implements ListSelectionListener
{  int i=0;
	public void valueChanged(ListSelectionEvent e) 
	{
		if(i==0)
		{i=1;
		}
		else
                              {   i=0;	}	
	}
 }

class SelectUserAction extends ConferenceDeclare implements ActionListener
{
    String cap,temp;
    String  users="",temp_test;
    int total,k=0;

    ExInternalFrame frm;
 
	SelectUserAction()
	{}
	SelectUserAction(ExInternalFrame t_frm)
	{
		frm=t_frm;
	}  

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		

		if(cap.equals(">"))
		{
			select=l_first.getSelectedValue();
			if(!select.equals(null))
			{
			lm_second.addElement(select);
			lm_first.removeElementAt(l_first.getSelectedIndex()) ;
			}
		}     
		if(cap.equals("<"))
		{
			select=l_second.getSelectedValue();
			if(!select.equals(null))
			{
			lm_first.addElement(select);
			lm_second.removeElementAt(l_second.getSelectedIndex()) ;
			}
		}    
		if(cap.equals("Ok"))
		{    	   
			total=lm_second.capacity();
		   try
		   {
			temp_test=lm_second.elementAt(0).toString();
			
			for(int i=0;i<total;i++)
			{
				try
				{
				temp=(lm_second.elementAt(i)).toString();
				users=users+"/"+temp.substring(0,temp.indexOf(":"));
				k++;
				}
				catch(Exception ex)
				{}
			}
		users=k+users;
		

		UDPSend.send("conf@01#"+users);

		users="";k=0;	

		frm.dispose();	
		    }
		    catch(Exception Ex)
		    { JOptionPane.showMessageDialog(frm, "Select Atleast one User", "From server", JOptionPane.WARNING_MESSAGE );  }				
 	       }

	       if(cap.equals("Intimate"))
	       {
		try
		{
		temp_test=lm_second.elementAt(0).toString();

		UDPSend.send("conf@01_101#");
		ok.setText("Ok");
		ok.setEnabled(false);
                            close.setEnabled(false);
		
		set.setEnabled(false);
		remove.setEnabled(false);
		}
		catch(Exception ex)
		{ JOptionPane.showMessageDialog(frm, "Select Atleast one User", "From server", JOptionPane.WARNING_MESSAGE ); }
		
	        }    	   

	}
	
	
}


	/*       ----------------- Conference-------------------  */

	/* Do  Form's	*/

class MainCreate extends Declare
{

	public void frm_Create()
	{
		main_frm=new JFrame("client  :  "+client.user_name);  main_frm.addWindowListener(new WindowClose()); 
			main_frm.setResizable(false);

		main_frm.setBounds(200,100,400,450);
		container_main=main_frm.getContentPane();

		mail_frm=new ExInternalFrame(10,10,300,300,"Mail");
		mail_rec=new ExInternalFrame(10,10,300,300,"Receive");
		mail_mesg=new ExInternalFrame(10,10,250,200,"Show");
		file_frm_ani=new ExInternalFrame(75,75,250,100,"progress");
		rec_file_frm_ani=new ExInternalFrame(75,75,250,120,"progress");

		chang_pass_frm=new ExInternalFrame(10,10,300,180,"Change Password  :  "+client.user_name);
	
		file_frm=new ExInternalFrame(50,50,300,130,"File");	

		container_mail=mail_frm.getContentPane();
		container_rec=mail_rec.getContentPane();
		con_chang_pass_frm =chang_pass_frm.getContentPane();	
		container_mesg=mail_mesg.getContentPane();
	              file_frm_con=file_frm.getContentPane();
		file_frm_ani_con=file_frm_ani.getContentPane();
		rec_file_frm_ani_con=rec_file_frm_ani.getContentPane();



		container_rec.setLayout(null);
		container_mesg.setLayout(null);
		con_chang_pass_frm.setLayout(null);
		file_frm_con.setLayout(null);
		file_frm_ani_con.setLayout(null);
		rec_file_frm_ani_con.setLayout(null);

	}

	public void mdi()
	{
		desk=new JDesktopPane();
			
		desk.add(chang_pass_frm);
		
		desk.add(mail_frm);
		desk.add(mail_rec);
		desk.add(mail_mesg);

		desk.add(monitor);
		desk.add(currentuser);
		desk.add(groupuser);
		desk.add(scroll_c_u);
		desk.add(scroll_g_u);

		desk.add(file_frm);
		desk.add(file_frm_ani);
		desk.add(rec_file_frm_ani);
	
		
		container_main.add(desk);
	}
	public void frm_Add()
	{
		main_frm.setJMenuBar(menubar);

		container_mail.add(to_user_id);
		container_mail.add(sub);
		container_mail.add(mesg);

		container_mail.add(text_to_user_id);
		container_mail.add(text_sub);
		container_mail.add(scrol_text_area);

		container_mail.add(send);
		container_mail.add(close);
		container_mail.add(temp);temp.setVisible(false);

		container_rec.add(mail_rec_text);
		container_rec.add(mail_delete);		
		container_rec.add(mail_view);
		container_rec.add(mail_close);

		container_mesg.add(la_from);
		container_mesg.add(la_sub);
		container_mesg.add(la_mesg);
		container_mesg.add(mesg_scrol);
		container_mesg.add(l_la_from);
		container_mesg.add(l_la_sub);
		container_mesg.add(show_close);

		con_chang_pass_frm.add(la_old_pass);
		con_chang_pass_frm.add(text_old_pass);
		con_chang_pass_frm.add(la_new_pass);
		con_chang_pass_frm.add(text_new_pass);
		con_chang_pass_frm.add(b_update);
		con_chang_pass_frm.add(b_cancel);

		file_frm_con.add(la_toid);
		file_frm_con.add(la_file);
		file_frm_con.add(text_toid);
		file_frm_con.add(text_file);
		file_frm_con.add(browse);
		file_frm_con.add(file_send);
		file_frm_con.add(file_close);

		file_frm_ani_con.add(file_display);

		rec_file_frm_ani_con.add(rec_file_display);
		rec_file_frm_ani_con.add(rec_from);

	}

	public void menu_Create()
	{
		menubar=new JMenuBar();

         	user =new JMenu("User");
		  
                    change=new JMenuItem("Change Password"); 	change.addActionListener(new  ChangePasswordAction()); 
                    logout=new JMenuItem("Logout");	                logout.addActionListener(new mailAction());

		service=new JMenu("Service");
		
                    mail=new JMenu("Mail"); 
			    send_mi = new JMenuItem("Send");send_mi.addActionListener(new mailAction());
			    inbox= new JMenuItem("Inbox");  inbox.addActionListener(new mailAction());
		    chat=new JMenuItem("Chat");             chat.addActionListener(new OpenChat()); 
		    filetransfer=new JMenu("File");   
			   m_file_send=new JMenuItem("send");  m_file_send.addActionListener(new FileAction());
			   m_file_received=new JMenuItem("Received");       m_file_received.addActionListener(new mailAction());
			
	}

	public void menu_Add()
	{
		menubar.add(user);

		     user.add(change);
		     user.add(logout); 

		menubar.add(service);

		    service.add(mail);	
			mail.add(send_mi);
			mail.add(inbox);
		    service.add(chat);
		    service.add(filetransfer);
			filetransfer.add(m_file_send);
			filetransfer.add(m_file_received);
			
	}

	public void label_Create()
	{
		to_user_id=new ExLabel(20,20,100,20,"To User ID  :");
		sub=new ExLabel(35,50,100,20,"Subject  :");		/* mail send */
		mesg=new ExLabel(10,80,100,20,"Message  :");

		la_from=new ExLabel(5,5,50,18,"From  :");
		la_sub=new ExLabel(12,23,40,20,"Sub  :");		/* mail show */
		la_mesg=new ExLabel(5,43,80,20,"Message");

		l_la_from=new ExLabel(50,5,90,20,"null");
		l_la_sub=new ExLabel(50,23,90,20,"null");

		monitor=new ExLabel(0,10,500,20,"--------------------------------------------Monitor--------------------------------------------");
		currentuser=new ExLabel(12,40,120,20,"Current User  :");
		groupuser=new ExLabel(232,40,120,20,"Group User  :");

		la_old_pass=new ExLabel(25,20,100,20,"Old Password  :");
		la_new_pass=new ExLabel(20,60,100,20,"New Password  :");
								/*	File transfer	*/
		la_toid=new ExLabel(10,10,50,18,"To ID  :");
		la_file=new ExLabel(17,35,50,18,"File  :");

		file_display=new ExLabel(20,8,200,20,"File  :");
		rec_file_display=new ExLabel(20,8,200,20,"File  :");
		
		rec_from=new ExLabel(20,60,200,20,"From  :");
		
	}

	public void text_Create()
	{
		text_to_user_id=new ExTextField(100,20,100,20);	
		text_sub=new ExTextField(100,50,100,20);

		text_mesg=new ExTextArea(10,110,260,120);
		scrol_text_area=new JScrollPane(text_mesg);		/* mail send */
		scrol_text_area.setBounds(10,110,260,120);		

		show_mesg=new ExTextArea(5,65,230,95);
								         /* mail show */
		mesg_scrol=new JScrollPane(show_mesg);
		mesg_scrol.setBounds(5,65,230,95);

		mail_rec_text=new ExTextField(95,210,103,23);
		mail_rec_text.setEditable(false);

								/* pass Change	*/
		text_old_pass=new JPasswordField();
			text_old_pass.setBounds(130,20,140,20);
			text_old_pass.setEchoChar('*');
						
		text_new_pass=new JPasswordField();
			text_new_pass.setBounds(130,60,140,20);
			text_new_pass.setEchoChar('*');
			
							/*  File Transfer	*/
		text_toid=new  ExTextField(60,10,100,20);			
		text_file=new  ExTextField(60,35,220,20);
			text_file.setEnabled(false);			
		
		
	}
	public void button_Create()
	{
		send=new ExButton(70,240,70,20,"Go");     send.addActionListener(new mailAction());  
		close=new ExButton(150,240,70,20,"Close");close.addActionListener(new mailAction());
		temp=new ExButton(230,240,70,20,"");

		mail_delete=new ExButton(5,210,90,23,"Delete"); mail_delete.addActionListener(new mailAction());
		mail_view=new ExButton(197,210,90,23,"View");  mail_view.addActionListener(new mailAction());
		mail_close=new ExButton(95,235,103,23,"Close"); mail_close.addActionListener(new CloseAction());

		show_close=new ExButton(170,8,70,50,"Close");   show_close.addActionListener(new CloseAction());


		b_update=new ExButton(40,110,100,20,"Update");  b_update.addActionListener(new  ChangePasswordAction()); 
		b_cancel=new ExButton(150,110,100,20,"Cancel");  b_cancel.addActionListener(new  ChangePasswordAction()); 


		browse=new ExButton(180,10,100,20,"Browse");    browse.addActionListener(new FileAction());
		file_send=new ExButton(60,70,70,20,"Send");       file_send.addActionListener(new FileAction());
		file_close=new ExButton(160,70,70,20,"Close");  file_close.addActionListener(new CloseAction());
		fc = new JFileChooser();



	}
	public  void mailTableCreate()
	{
		Object ob[][]=new Object[1000][3];
		table_mail_rec=new JTable(ob,CatchMail.table_head);  table_mail_rec.addMouseListener(new MailRecAction());
		//table_mail_rec.setPreferredScrollableViewportSize(new Dimension(70,500));
		scrol=new JScrollPane(table_mail_rec);
		scrol.setBounds(10,10,270,170);
		container_rec.add(scrol);//,BorderLayout.NORTH);

		table_mail_rec.addMouseListener(new MailRecAction());

		model=table_mail_rec.getModel();
	}
		
	public static void tableClear()
	{
		for(int i=0;i<1000;i++)
		  for(int j=0;j<=2;j++)
		          model.setValueAt(null,i,j); 
	}	

	public void monitor()
	{
		Object head[]={"User ID","User Name"};
		Object data[][]=new Object[1000][2]; 

		Object head1[]={"User ID","User Name"};
		Object data1[][]=new Object[1000][2]; 


		t_current_user=new JTable(data1,head1);
		scroll_c_u=new JScrollPane(t_current_user);
		scroll_c_u.setBounds(10,80,150,280);
		tm_current_user=t_current_user.getModel();

		t_group_user=new JTable(data,head);
		scroll_g_u=new JScrollPane(t_group_user);
		scroll_g_u.setBounds(230,80,150,280);
		tm_group_user=t_group_user.getModel();
		
	}

	public void confCreate()
	{
		conf_main_frm=new JFrame("Conference");
		conf_main_frm.setBounds(20,20,500,500);

		conf_con=conf_main_frm.getContentPane();
		//conf_con.setLayout(null);
		
		conf_desk=new JDesktopPane();

		conf_con.add(conf_desk);
		
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

class ExTextArea extends JTextArea
{
	ExTextArea(int x1,int y1,int x2,int y2)
	{
		this.setBounds(x1,y1,x2,y2);
	}
}

/*	File Transfer Action	*/

class FileAction extends Declare implements ActionListener
{
    static String cap,file_name,pre,suf,temp;
    static File select_file,op_file;
    static FileInputStream fips;
    static FileOutputStream  fops;
    static int ret,bc,bc_1;
    static byte b[],b_1[],q,w;
   
	
	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		
		if(cap.equals("send"))
		           {	
	  
			text_toid.setText("");
			text_file.setText("");
			file_send.setEnabled(false);
			file_frm.setVisible(true);

			m_file_send.setEnabled(false);
			  

		           }	

		if(cap.equals("Send"))
		          {	

			UserCheck.userCheck(tm_current_user,text_toid.getText().trim());	
		        	 if(UserCheck.bool)
			   {
				UDPSend.send("file@01_101#"+text_toid.getText().trim());
				file_send.setEnabled(false);
				browse.setEnabled(false);	
			   }
			 else
			  { 
				Bussy.userNot(file_frm);	
			  }	
				

		          }	
		
		if(cap.equals("Browse"))
		{
  			    b=new byte[100];
   
		   try
		   {	
			ret = fc.showOpenDialog(file_frm);
			
			if(ret== JFileChooser.APPROVE_OPTION)
			{
			select_file = fc.getSelectedFile();
			fips=new FileInputStream(select_file);
			text_file.setText(select_file.getAbsolutePath());
			file_name=select_file.getName();
		
			pre=file_name.substring(0,file_name.indexOf("."));
			suf=file_name.substring(file_name.indexOf("."));

			
			file_send.setEnabled(true);
			}
		    }	
		    catch(Exception ex)
  	     	    {     System.out.println("File Select   :  " +ex);   }
			
		}	

	}	
	
		  
	/* ------------ receive Section -------------*/

	public static void fileCreate(String preffix,String suffix)
	{
	
	  
		try
		{
			//op_file=File.createTempFile(preffix,suffix,new File("c:/group_client/login/client/received"));
			op_file=File.createTempFile(preffix,suffix,new File("./received"));
			fops=new FileOutputStream(op_file);
		}
		catch(Exception ex)
		{  System.out.println("File Create  :  " + ex);  }
	}

}


/*	Actio for mail	*/

class mailAction  extends Declare implements ActionListener
{
  String cap;
  int in=0; 
  int n; 
  Runtime run=null;
  Process pro=null;
	

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		
		
		if(cap.equals("Send"))
			mail_frm.setVisible(true);

		if(cap.equals("Close"))
		      {    	
			mail_frm.setVisible(false);
			text_to_user_id.setText("");
			text_sub.setText("");
			text_mesg.setText("");
			
		      }	

		if(cap.equals("Go"))
			{
			MailStore.to_id=text_to_user_id.getText();MailStore.to_id=MailStore.to_id.trim();

			UserCheck.userCheck(tm_group_user,MailStore.to_id);	
			
		              	     if(UserCheck.bool)
			    {	

			      	if((text_sub.getText().trim()).equals(""))
			      	{
					JOptionPane.showMessageDialog(mail_frm, "  Please Enter Subject in MailBox", "Client", JOptionPane.WARNING_MESSAGE); 
			      	}
			      
			     	 else	 	
			      	{	
					MailStore.to_id=text_to_user_id.getText();MailStore.to_id=MailStore.to_id.trim();
					MailStore.sub=text_sub.getText();MailStore.sub=MailStore.sub.trim();
					MailStore.msg=text_mesg.getText();MailStore.msg=MailStore.msg.trim();
					
					
					UDPSend.send("mail@01_101#"+client.user_id);
			      	}
			      }	
		 	      else
			      {
				Bussy.userNot(Declare.mail_frm);
			      }						
			}

		if(cap.equals("Inbox"))
			{
				mail_rec.setVisible(true);
				UDPSend.send("mail@02_101#"+client.user_id);
				mail_rec.setTitle("wait...");

				mail_delete.setEnabled(false);
				mail_view.setEnabled(false);
				mail_close.setEnabled(false);

			}

		if(cap.equals("View"))
			{
				   if(Declare.mail_rec_text.getText().equals(""))
				   {
					JOptionPane.showMessageDialog(mail_rec, "Please Select Subject Form Table", "Client", JOptionPane.WARNING_MESSAGE); 
			     	   }
			     	  else
			     	  {			
					show_mesg.setText("null");
					l_la_from.setText("null");
					l_la_sub.setText("null");
					mail_mesg.setTitle("wait...");
					

					mail_mesg.setVisible(true);
					UDPSend.send("mail@03_101#"+client.user_id+"/"+MailRecAction.i);
				  }
			}
		if(cap.equals("Delete"))
		  {
						
			     if(Declare.mail_rec_text.getText().equals(""))
			     {
				JOptionPane.showMessageDialog(mail_rec, "Please Select Subject Form Table", "Client", JOptionPane.WARNING_MESSAGE); 
			     }
			     else
			     {				
				

				n=JOptionPane.showConfirmDialog(mail_rec,"Are you sure to delete Message", "From Server", JOptionPane.YES_NO_OPTION);

			       	if (n == JOptionPane.YES_OPTION) 
				{
					UDPSend.send("mail@04_101#"+client.user_id+"/"+MailRecAction.sub);
					MainCreate.tableClear();
					UDPSend.send("mail@02_101#"+client.user_id);

					mail_rec.setTitle("wait...");
					mail_delete.setEnabled(false);
					mail_view.setEnabled(false);
					mail_close.setEnabled(false);
				}
			       }   
                                            
		   }
		   if(cap.equals("Logout"))
		  {
			n=JOptionPane.showConfirmDialog(main_frm,"Are you sure Log off", "Client", JOptionPane.YES_NO_OPTION);

			if (n == JOptionPane.YES_OPTION) 
			{
			UDPSend.send("close@01#");
			main_frm.dispose();
			
			new ani_window();	
			System.exit(1);
			}
		   }

		  if(cap.equals("Received"))
		  {
			try
			{
				run=Runtime.getRuntime();
				pro=run.exec("explorer .\\received");
			}
			catch(Exception ex)
			{}
		  }

			
	}

}

class MailStore
{
	static String to_id,sub,msg;

	public static void leave()
	{
		UDPSend.send("mail@01#"+to_id);
		UDPSend.send("mail@01#"+client.user_name);
		UDPSend.send("mail@01#"+sub);			
		UDPSend.send("mail@01#"+msg);

		JOptionPane.showMessageDialog(Declare.mail_frm, "          Message   Delivered   Successfully", "From Server", JOptionPane.PLAIN_MESSAGE  ); 
	}
	public static void bussy()
	{
		
		JOptionPane.showMessageDialog(Declare.mail_frm, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 
	}
}

		/*  Bussy Tone	*/

class Bussy
{

	public static void inboxBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(Declare.mail_rec, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 
		Declare.mail_rec.setTitle("Try Again");
	}
	
	public static void  userNot(ExInternalFrame frm)
	{
		new Beep();
		JOptionPane.showMessageDialog(frm, "User Not Available", "From Server", JOptionPane.WARNING_MESSAGE); 
	}
	public static void  userNot_1(JFrame frm)
	{
		new Beep();
		JOptionPane.showMessageDialog(frm, "User Not Available", "From Server", JOptionPane.WARNING_MESSAGE); 
	}
	
	public static void viewBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(Declare.mail_mesg, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 
		Declare.mail_mesg.setTitle("Try Again");
	}
	public static void deleteBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(Declare.mail_rec, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 	
		Declare.mail_rec.setTitle("Try Again");
	}

	public static void chatConnectBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 			
		ChatDeclare.b_chat.setEnabled(true);
		ChatDeclare.i_frm[ChatDeclare.frm_count].dispose();
	}

	public static void conferenceConnectBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 			
		ConferenceDeclare.adduser.dispose();
	}
	public static void conferenceConnected()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "After completing your Conference Chating \n  you must close  Conference Chat Window", "From Server", JOptionPane.WARNING_MESSAGE ); 			
	}

	public static void conferenceConnect_Port()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Currently The Service is Not Avilable", "From Server", JOptionPane.WARNING_MESSAGE ); 			
	}

	public static void fileConnectBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(Declare.file_frm, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 
		Declare.browse.setEnabled(true);
		Declare.text_file.setText("");
	}
	public static void passwordBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(Declare.chang_pass_frm, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 


		Declare.b_update.setEnabled(true);
		Declare.b_cancel.setEnabled(true);

		Declare.chang_pass_frm.setTitle("Change Password  :  "+client.user_name);
	}
	public static void passwordVerify()
	{
		new Beep();
		JOptionPane.showMessageDialog(Declare.chang_pass_frm, "      Verify    OLD    Password", "From Server", JOptionPane.WARNING_MESSAGE ); 

		Declare.b_update.setEnabled(true);
		Declare.b_cancel.setEnabled(true);
		
		Declare.chang_pass_frm.setTitle("Change Password  :  "+client.user_name);
	}
	public static void passwordChanged()
	{
		new Beep();

		JOptionPane.showMessageDialog(Declare.chang_pass_frm, "         Password   Changed   Successfully", "From Server", JOptionPane.PLAIN_MESSAGE ); 

		Declare.b_update.setEnabled(true);
		Declare.b_cancel.setEnabled(true);
		
		
		Declare.chang_pass_frm.setTitle("Change Password  :  "+client.user_name);
	}

	public static void voiceFull()
	{
	new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Currently the service is not Available Tyr Later", "From Server", JOptionPane.WARNING_MESSAGE ); 			
	}

	public static void voiceBussy()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Server Is BUSSY  Try Again...", "From Server", JOptionPane.WARNING_MESSAGE ); 			
	}

	public static void serverShutdown()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Server Is Now Shutdown", "From Server", JOptionPane.INFORMATION_MESSAGE ); 			
	}

	public static void serverRestart()
	{
		new Beep();
		JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "Server Is Now Restart", "From Server", JOptionPane.INFORMATION_MESSAGE ); 			
	}

}

	
	/*	Declare UDP Variables	*/

class DeclareUDP
{
	DatagramSocket ds;
	DatagramPacket dp; 
	InetAddress addr;		
	String str;
	int port;
   	byte b[]; 	
}


	/*      UDP Receive	*/

class UDPReceive extends DeclareUDP implements Runnable
{
  Thread thread;		
  int port,flag;
  String service_id,core;
  int f1,f2,f3,f4,f5;
  int ff1,ff2,ff3;
  int cm1,app_id;
  int c_cm1,c_app_id;

  int v_flag_1,v_flag_2,id;
  String voice_from,voice_to;

  int v_rec_flag1,v_rec_flag2,rec_id,v_close_id;
  String v_rec_port,v_sen_port;   

  String tto_addr,tto_port,tto_name,tto_app;
  String to_addr,to_port,to_id,to_name,to_app; 

  String rec_app_id,rec_mesg;
String c_rec_app_id,c_rec_mesg;

String conf_port;

String old_pass,new_pass;
String file_to_addr,file_to_port;
String preffix,suffix;

Thread thread_file_data,thread_file_receive;

String color_text_from,color_text_to;

	UDPReceive(String port_client)
	{
		port=Integer.parseInt(port_client);
		thread =new Thread(this);
		thread.start();
	}
	public void run()
	{
		
		try
		{
	
		ds=new DatagramSocket(port);
		

			while(true)
			{	
				b=new byte[1024];
				dp=new DatagramPacket(b,b.length);
				ds.receive(dp);
				str=new String(b);
				str=str.trim();

/*  mail Service */				if(str.equals("mail@01_201#"))
						MailStore.leave();

					if(str.equals("mail@01_401#"))
						MailStore.bussy();

					if(str.equals("mail@02_201#"))
						{}
					if(str.equals("mail@02_401#"))
						Bussy.inboxBussy();

					if(str.equals("mail@02_501#"))	
					      {
						CatchMail.i=0;	
						Declare.mail_rec.setTitle("Received");

						Declare.mail_delete.setEnabled(true);
						Declare.mail_view.setEnabled(true);
						Declare.mail_close.setEnabled(true);
					      }  

					if(str.equals("mail@03_201#"))
						{}
					if(str.equals("mail@03_401#"))
						Bussy.viewBussy();

					if(str.equals("mail@04_201#"))
						{}
					if(str.equals("mail@04_401#"))
						Bussy.deleteBussy();
	
				
				flag=str.indexOf("#");
				service_id=str.substring(0,flag+1);		
				core=str.substring(flag+1);

					if(service_id.equals("mail@02#"))
					   {
						
						CatchMail.store(core);
					   }
					if(service_id.equals("mail@03#"))
					   {
						
						Declare.show_mesg.setText(core);
						new Beep();
						Declare.l_la_from.setText(MailRecAction.from);
						Declare.l_la_sub.setText(MailRecAction.sub);
						Declare.mail_mesg.moveToFront();
			
						Declare.mail_mesg.setTitle("Show");								
	                 			   }	
/*------mail service-----*/		

/*  	chat service	*/
					if(service_id.equals("chat@01_401#"))
					   {
						
						Bussy.chatConnectBussy();
					   }

					if(service_id.equals("chat@01_101#"))
					   {
						if(Semaphore.resSemaphore())
						{
						Semaphore.var1=1;
												
						f1=core.indexOf("/");
						f2=core.indexOf("/",f1+1);
						f3=core.indexOf("/",f2+1);
						f4=core.indexOf("/",f3+1);
						f5=core.indexOf("/",f4+1);

						to_addr=core.substring(0,f1);
						to_port=core.substring(f1+1,f2);
						to_id=core.substring(f2+1,f3);
						to_name=core.substring(f3+1,f4);
						to_app=core.substring(f4+1,f5);
						

						new Beep();
						ChatRecCreate.mainfrmCreate(ChatRecDeclare.frm_count,to_addr,to_port,to_app,to_name);							
						UDPSend.send("chat@01_201#"+"/"+ChatRecDeclare.frm_count);
						
						ChatRecDeclare.frm_count++;

						Semaphore.var1=0;
						}
						
						
					   }	

					   if(service_id.equals("chat@01_201#"))
					   {
						new Beep();
						
						ff1=core.indexOf("/");
						ff2=core.indexOf("/",ff1+1);
						ff3=core.indexOf("/",ff2+1);

						tto_addr=core.substring(0,ff1);
						tto_port=core.substring(ff1+1,ff2);
						tto_name=core.substring(ff2+1,ff3);													
						tto_app=core.substring(ff3+1);

						
						
						ChatDeclare.to_addr[ChatDeclare.frm_count]=tto_addr;
						
						ChatDeclare.to_port[ChatDeclare.frm_count]=tto_port;	
						
						ChatDeclare.to_app[ChatDeclare.frm_count]=tto_app;
						
						ChatDeclare.i_frm[ChatDeclare.frm_count].setTitle(tto_name);
						ChatDeclare.name[ChatDeclare.frm_count]=tto_name;
						

						ChatDeclare.frm_count++;
						
						ChatDeclare.b_chat.setEnabled(true);

					   }	

					  
					   if(service_id.equals("chat@01#"))
					   {
						cm1=core.indexOf("/");
						rec_app_id=core.substring(0,cm1);
						app_id=Integer.parseInt(rec_app_id);		
						rec_mesg=core.substring(cm1+1);	
						ChatRecDeclare.i_ta_mesg[app_id].append(ChatRecDeclare.name[app_id]+" : "+rec_mesg+"\n");
						
					
						color_text_to=ChatRecDeclare.i_ta_mesg[app_id].getText();
						ChatRecDeclare.i_ta_mesg[app_id].setSelectionEnd(color_text_to.length());
						

						ChatRecDeclare.state[app_id]=1;
						if(ChatRecDeclare.state_flag[app_id]==1)
						{
						new FrameState(ChatRecDeclare.i_frm[app_id],app_id,true);		
						ChatRecDeclare.state_flag[app_id]=0;
						}
					   }	
						
					    if(service_id.equals("chat@02#"))
					   {
						c_cm1=core.indexOf("/");
						c_rec_app_id=core.substring(0,c_cm1);
						c_app_id=Integer.parseInt(c_rec_app_id);		
						c_rec_mesg=core.substring(c_cm1+1);	
						ChatDeclare.i_ta_mesg[c_app_id].append(ChatDeclare.name[c_app_id]+" : "+c_rec_mesg+"\n");
						

						color_text_from=ChatDeclare.i_ta_mesg[c_app_id].getText();
						ChatDeclare.i_ta_mesg[c_app_id].setSelectionEnd(color_text_from.length());			

						ChatDeclare.state[c_app_id]=1;
						if(ChatDeclare.state_flag[app_id]==1)
						{
						new FrameState(ChatDeclare.i_frm[c_app_id],c_app_id,false);
						ChatDeclare.state_flag[app_id]=0;
						}
					   }	

/*	Conference        */
					 if(service_id.equals("conf@01_201#"))
					   {

						
						Bussy.conferenceConnected();
						ConferenceDeclare.ok.setEnabled(true);
						 ConferenceDeclare.close.setEnabled(true);
						//ConferenceCreate.mainCreate();  

							
						/*str=1+"/"+"5";
						UDPSend.send("conf@01#"+str);*/
					   }	
						
					if(service_id.equals("conf@01_401#"))
					   {
						 Bussy.conferenceConnectBussy();
					   }	

					if(service_id.equals("conf@101#"))
					   {
						
						Declare.conf_main_frm.setVisible(true);
						new ConferevceReceive(core);

					   }


					if(service_id.equals("conf_full@01#"))
					  {
						 Bussy.conferenceConnect_Port();
					   }	
					 
/*    Change Password   */

					if(service_id.equals("pass@01_201#"))
					    {
						

						old_pass=Declare.text_old_pass.getText();  old_pass=old_pass.trim();
						new_pass=Declare.text_new_pass.getText();  new_pass=new_pass.trim();

						

						UDPSend.send("pass@01#"+client.user_id+"/"+old_pass+"/"+new_pass);						
												
					    }	

					if(service_id.equals("pass@01_401#"))
						Bussy.passwordBussy();

					if(service_id.equals("pass@04#"))
						Bussy.passwordVerify();

					if(service_id.equals("pass@02#"))
						Bussy.passwordChanged();

/*      File Transfer       */
					if(service_id.equals("file@01_201#"))
					              {
							
						}

					if(service_id.equals("file@01_401#"))
					              {  Bussy.fileConnectBussy();  }

					if(service_id.equals("file@01#"))
					  	{
						   
						file_to_addr=core.substring(0,core.indexOf("/"));
						file_to_port=core.substring(core.indexOf("/")+1);
						
						
						UDPSpecialSend.send(file_to_addr,file_to_port,"file@02_101#"+FileAction.pre+"/"+FileAction.suf);	

						new UDPFileSend(file_to_addr);	
																	
						}

					if(service_id.equals("file@02_101#"))
						{
						
						preffix=core.substring(0,core.indexOf("/"));
						suffix=core.substring(core.indexOf("/")+1);
						
						FileAction.fileCreate(preffix,suffix);

						new UDPFileReceice();	
																					
						}

					
					if(service_id.equals("file@02_901#"))
						{
							
							
						}
/*  voice chat	*/
					if(service_id.equals("voice@01_201#"))
					{
						
					}
					if(service_id.equals("voice@01_401#"))
					{
						Bussy.voiceBussy();
					}
					if(service_id.equals("voice@01#"))
					{
						v_flag_1=core.indexOf("/");
						v_flag_2=core.indexOf("/",v_flag_1+1);

						voice_from=core.substring(0,v_flag_1);
						voice_to=core.substring(v_flag_1+1,v_flag_2);
						id=Integer.parseInt(core.substring(v_flag_2+1));

						ChatDeclare.voice_from_port[id]=voice_from;
						ChatDeclare.voice_to_port[id]=voice_to;
						ChatDeclare.bu_voice[id].setText("Enable");

						UDPSpecialSend.send(ChatDeclare.to_addr[id],ChatDeclare.to_port[id],"voice@02_101#"+ChatDeclare.to_app[id]+"/"+voice_from+"/"+voice_to);

						ChatDeclare.flag_back[id]=0;
						new RecSoundReceive(ChatDeclare.voice_from_port[id],id,false); 
						
					}

					if(service_id.equals("voice@02_101#"))
					{
						
						v_rec_flag1=core.indexOf("/");
						v_rec_flag2=core.indexOf("/",v_rec_flag1+1);
						rec_id=Integer.parseInt(core.substring(0,v_rec_flag1));
						v_sen_port=core.substring(v_rec_flag1+1,v_rec_flag2);
						v_rec_port=core.substring(v_rec_flag2+1);

						ChatRecDeclare.voice_sen_port[rec_id]=v_sen_port;
						ChatRecDeclare.voice_rec_port[rec_id]=v_rec_port;

						

						 ChatRecDeclare.flag[rec_id]=0;

						ChatRecDeclare.bu_voice[rec_id].setEnabled(true);
						ChatRecDeclare.bu_mute[rec_id].setEnabled(true);
						
						ChatRecDeclare.bu_voice[rec_id].setText("Enable");
						ChatRecDeclare.bu_mute[rec_id].setText("Mute");
						
						new RecSoundReceive(ChatRecDeclare.voice_rec_port[rec_id],rec_id,true); 

					
					}
					if(service_id.equals("voice@901#"))
					{
						v_close_id=Integer.parseInt(core);
						ChatRecDeclare.flag[v_close_id]=1;

						ChatRecDeclare.sound_record[v_close_id].yield();
						ChatRecDeclare.flag_back[v_close_id]=0;

						ChatRecDeclare.bu_voice[v_close_id].setEnabled(false);
						ChatRecDeclare.bu_mute[v_close_id].setEnabled(false);

						 ChatRecDeclare.sound_ds[v_close_id].close();
						 ChatRecDeclare.end_thread[v_close_id].yield();
					}
					if(service_id.equals("voice_full@01#"))
					{
					
						Bussy.voiceFull();
					}
					
/*  Service Close	*/					
					if(service_id.equals("close@01#"))	
					{
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

class Semaphore
{
   static int var1=0;
	
	public static boolean resSemaphore()
	{
		if(var1==0)
			return true;
		else
			return false;
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

	UDPSend(String ser_addr,String ser_port)
	{
		try
		{
		port=Integer.parseInt(ser_port);
		addr=InetAddress.getByName(ser_addr);

		ds=new DatagramSocket();
		}
		catch(Exception ex)
		{
		System.out.println("UDP SEND Socket_Create :" +ex);
		}
	}

	public static void send(String mesg)
	{
		b=mesg.getBytes();
		
		try
		{
		dp=new DatagramPacket(b,b.length,addr,port);
		ds.send(dp);
		
		}
		catch(Exception ex)
		{
		System.out.println("UDP_SEND : "+ex);
		}
	}

}

class UDPSpecialSend
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
		
		}
		catch(Exception ex)
		{
		System.out.println("UDP_SEND : "+ex);
		}
	}

	
}



class CatchMail
{
   static Object table_data[][],table_head[];
   static String from,date,sub;
   static int flag1,flag2,i=0,j=0;
   static String str[][];
		CatchMail()
		{

			str=new String[100][3];
			table_data=new Object[100][3];
			table_head=new Object[3];
			table_head[0]="From";
			table_head[1]="Date";
			table_head[2]="Sub";

				
		}

		public static void store(String core)
		{

			flag1=core.indexOf("/",1);
			flag2=core.indexOf("/",flag1+1);
			
			from=core.substring(0,flag1);
			date=core.substring(flag1+1,flag2);
			sub=core.substring(flag2+1);

			Declare.model.setValueAt(from,i,j);j++;
			Declare.model.setValueAt(date,i,j);j++;
			Declare.model.setValueAt(sub,i,j);j=0;i++;
		
			

			

		}

		
}


class MailRecAction implements MouseListener
{
  static int i;
  static String sub,from;
  
	public void mouseClicked(MouseEvent e) 
	{
		try
		{
		i=Declare.table_mail_rec.getSelectedRow();
		from=(Declare.model.getValueAt(i,0)).toString();
		sub=(Declare.model.getValueAt(i,2)).toString();
		
		Declare.mail_rec_text.setText(sub);

		}
		catch(Exception ex)
		{
		Declare.mail_rec_text.setText("");
		}
						
	}
 	public void mouseEntered(MouseEvent e)  {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e)  {}
	public void mouseReleased(MouseEvent e)  {}
 

}
	/* mail close */

class CloseAction implements ActionListener
{
  JButton b;

	public void actionPerformed(ActionEvent ae)
	{
		b=(JButton)ae.getSource();
		
		if(b.equals(Declare.mail_close))
               		Declare.mail_rec.setVisible(false);

		if(b.equals(Declare.show_close))
			Declare.mail_mesg.setVisible(false);

		if(b.equals(Declare.file_close))
			{
				Declare.file_frm.setVisible(false);
				Declare.m_file_send.setEnabled(true);
			}

	}
}


	/* chat operation */

class OpenChat implements ActionListener
{
  String cap;	

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		
		if(cap.equals("Chat"))
			{
				ChatCreate.mainfrmCreate();
				Declare.chat.setEnabled(false);
			}
	}
}

class ChatAction implements ActionListener
{
  ExButton but;	
  int i=0;	
  String to_id,mesg,cap;
  Thread thread_rec;
   
  String color_text;	

	ChatAction()
	{}
	
	ChatAction(int t)
	{
		i=t;
	}
	public void actionPerformed(ActionEvent ae)
	{
		
		but=(ExButton)ae.getSource();
		
		if(but.equals(ChatDeclare.b_chat))
		{

		        UserCheck.userCheck(Declare.tm_current_user,ChatDeclare.t_chat.getText().trim());	
		        if(UserCheck.bool)
		        {
	
			ChatCreate.chatfrm(ChatDeclare.frm_count);   /* chat form */

			to_id=ChatDeclare.t_chat.getText();		
			
			to_id=to_id.trim();
			
			ChatDeclare.b_chat.setEnabled(false);
			UDPSend.send("chat@01_101#"+client.whole+"/"+ChatDeclare.frm_count+"/"+to_id);
		         }
		         else
		         {
			Bussy.userNot_1(ChatDeclare.chat_main_frm);		
		         }	
					
		}
		if(but.equals(ChatDeclare.i_send[i]))
		{
			
			mesg="chat@01#"+ChatDeclare.to_app[i]+"/"+ChatDeclare.i_t_mesg[i].getText();
			UDPSpecialSend.send(ChatDeclare.to_addr[i],ChatDeclare.to_port[i],mesg);           /* chat send_form send */
			ChatDeclare.i_ta_mesg[i].append(client.user_name+" : "+ChatDeclare.i_t_mesg[i].getText()+"\n");

			color_text=ChatDeclare.i_ta_mesg[i].getText();
			ChatDeclare.i_ta_mesg[i].setSelectionEnd(color_text.length());
						
			ChatDeclare.i_t_mesg[i].setText("");


			ChatDeclare.state[i]=0;
			ChatDeclare.state_flag[i]=1;
		}
		if(but.equals(ChatDeclare.bu_voice[i]))
		{
			cap=ChatDeclare.bu_voice[i].getText();
			if(cap.equals("Enable"))
			{
				JOptionPane.showMessageDialog(ChatDeclare.chat_main_frm, "You must Disable this option , After speech", "From Server", JOptionPane.WARNING_MESSAGE );

				ChatDeclare.bu_voice[i].setText("Disable");

				mesg="chat@01#"+ChatDeclare.to_app[i]+"/"+"Voice Chat ENABLED By "+client.user_name; 	
				UDPSpecialSend.send(ChatDeclare.to_addr[i],ChatDeclare.to_port[i],mesg);
				ChatDeclare.i_ta_mesg[i].append(client.user_name+" :  Voice Chat ENABLED By "+client.user_name+"\n");
					
				ChatDeclare.flag[i]=1;
				thread_rec=new Thread(new SoundRecord(ChatDeclare.to_addr[i],ChatDeclare.voice_to_port[i],ChatDeclare.to_app[i],i,true));
				thread_rec.start();
				
			}
			if(cap.equals("Disable"))
			{	
				ChatDeclare.flag_back[i]=1;
				
				ChatDeclare.bu_voice[i].setText("Initiate");
				ChatDeclare.bu_mute[i].setText("Mute");

				mesg="chat@01#"+ChatDeclare.to_app[i]+"/"+"Voice Chat DISABLED By "+client.user_name; 	
				UDPSpecialSend.send(ChatDeclare.to_addr[i],ChatDeclare.to_port[i],mesg);
				ChatDeclare.i_ta_mesg[i].append(client.user_name+" :  Voice Chat DISABLED By "+client.user_name+"\n");
				
				UDPSpecialSend.send(ChatDeclare.to_addr[i],ChatDeclare.to_port[i],"voice@901#"+ChatDeclare.to_app[i]);       /*     mute for other end     */
				
				thread_rec.yield();
				ChatDeclare.flag[i]=0;

				ChatDeclare.sound_ds[i].close();
				ChatDeclare.from_thread[i].yield();	

				UDPSend.send("voice_close@01_101#"+ChatDeclare.voice_to_port[i]);
				
			}


			if(cap.equals("Initiate"))
			{
				UDPSend.send("voice@01_101#"+i);
			}
		}

		if(but.equals(ChatDeclare.bu_mute[i]))
		{
		   cap=ChatDeclare.bu_mute[i].getText();	

			if(cap.equals("Mute"))
			{
			     ChatDeclare.flag_back[i]=1;
			     ChatDeclare.bu_mute[i].setText("Accept");
			}
			if(cap.equals("Accept"))
			{
				ChatDeclare.bu_mute[i].setText("Mute");
				ChatDeclare.flag_back[i]=0;
				new RecSoundReceive(ChatDeclare.voice_from_port[i],i,false);  
			}

		}


				/*   ---------   Chat      ----------*/



				/*       Conference              */

		if(but.equals(ChatDeclare.b_conference))
		{
			//UDPSend.send("conf@01_101#");
			ConferenceCreate.mainCreate();  
		}

		
	
	}
}

class ChatAction_1 implements ActionListener
{
      int i;
      ExTextField text_field;  
      String mesg,color_text;
	
	ChatAction_1(int t)
	{
		i=t;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		text_field=(ExTextField)ae.getSource();

		if(text_field.equals(ChatDeclare.i_t_mesg[i]))
		{
			mesg="chat@01#"+ChatDeclare.to_app[i]+"/"+ChatDeclare.i_t_mesg[i].getText();
			UDPSpecialSend.send(ChatDeclare.to_addr[i],ChatDeclare.to_port[i],mesg);           /* chat send_form send */
			ChatDeclare.i_ta_mesg[i].append(client.user_name+" : "+ChatDeclare.i_t_mesg[i].getText()+"\n");

			color_text=ChatDeclare.i_ta_mesg[i].getText();
			ChatDeclare.i_ta_mesg[i].setSelectionEnd(color_text.length());

			ChatDeclare.i_t_mesg[i].setText("");

			ChatDeclare.state[i]=0;
			ChatDeclare.state_flag[i]=1;
		}

		if(text_field.equals(ChatRecDeclare.i_t_mesg[i]))
		{
			mesg="chat@02#"+ChatRecDeclare.to_app[i]+"/"+ChatRecDeclare.i_t_mesg[i].getText();
			UDPSpecialSend.send(ChatRecDeclare.to_addr[i],ChatRecDeclare.to_port[i],mesg);           /* chat receive_form send */
			ChatRecDeclare.i_ta_mesg[i].append(client.user_name+" : "+ChatRecDeclare.i_t_mesg[i].getText()+"\n");

			color_text=ChatRecDeclare.i_ta_mesg[i].getText();
			ChatRecDeclare.i_ta_mesg[i].setSelectionEnd(color_text.length());

			ChatRecDeclare.i_t_mesg[i].setText("");

			ChatRecDeclare.state[i]=0;
			ChatRecDeclare.state_flag[i]=1;
		}
		
	}
		
     
}

class RecChatAction implements ActionListener
{
  int i;
  ExButton but;
  String mesg,color_text;

	RecChatAction()
	{}
	RecChatAction(int t)
	{
		i=t;
	}	
	public void actionPerformed(ActionEvent ae)
	{
		but=(ExButton)ae.getSource();
		
		if(but.equals(ChatRecDeclare.i_send[i]))
		{
			
			mesg="chat@02#"+ChatRecDeclare.to_app[i]+"/"+ChatRecDeclare.i_t_mesg[i].getText();
			UDPSpecialSend.send(ChatRecDeclare.to_addr[i],ChatRecDeclare.to_port[i],mesg);           /* chat receive_form send */
			ChatRecDeclare.i_ta_mesg[i].append(client.user_name+" : "+ChatRecDeclare.i_t_mesg[i].getText()+"\n");

			color_text=ChatRecDeclare.i_ta_mesg[i].getText();
			ChatRecDeclare.i_ta_mesg[i].setSelectionEnd(color_text.length());

			ChatRecDeclare.i_t_mesg[i].setText("");
			
			ChatRecDeclare.state[i]=0;
			ChatRecDeclare.state_flag[i]=1;
		}
	}
}


/*		Monitor		*/

class MultiCastReceive extends Thread
{
   MulticastSocket  ms;
   DatagramPacket dp;
   InetAddress addr;
   String str,service_id,core;
   String user_id,user_name;	
   int f1;	
   int i=0;
   int port;
   byte b[];      
   
	MultiCastReceive(String address,String portt)
	{
		port=Integer.parseInt(portt);
		try
		{
		addr=InetAddress.getByName(address);
		}
		catch(Exception ex)
		{  System.out.println("Multi Cast Address : "+ex);  }
		
		start();		
	}

	public void run()
	{
		
		
		try
		{
			ms=new MulticastSocket(port);
			ms.joinGroup(addr);

			while(true)
			{
				b=new byte[1024];
				dp=new DatagramPacket(b,b.length);	
				
				ms.receive(dp);
				str=new String(b);str=str.trim();
			
				
				service_id=str.substring(0,str.indexOf("#")+1);
				core=str.substring(str.indexOf("#")+1);

				if(service_id.equals("mc@901#"))
					ms.close();

				if(service_id.equals("mc@201#"))		
				{
					
					f1=core.indexOf("/");
					user_id=core.substring(0,f1);
/*  Group User  */				user_name=core.substring(f1+1);
					Declare.tm_group_user.setValueAt(user_id,i,0);
					Declare.tm_group_user.setValueAt(user_name,i,1);
					i++;
					
				}

				if(service_id.equals("mc@202#"))		
				{
					Declare.groupuser.setText("Group User  :  " +i);
					new TableClear(Declare.tm_group_user,i);
					i=0;
				}

				if(service_id.equals("mc@101#"))		
				{
					
					f1=core.indexOf("/");
					user_id=core.substring(0,f1);
					user_name=core.substring(f1+1);
					Declare.tm_current_user.setValueAt(user_id,i,0);
/* current User */				Declare.tm_current_user.setValueAt(user_name,i,1);
					i++;
					
				}

				if(service_id.equals("mc@102#"))		
				{
					Declare.currentuser.setText("Current User  :  "+i);
					new TableClear(Declare.tm_current_user,i);
					i=0;
				}

				if(service_id.equals("server@901#"))
				{
					Bussy.serverShutdown();
				}
				if(service_id.equals("server@902#"))
				{
					Bussy.serverRestart();
				}

				

				
			}
		}
		catch(Exception ex)
		{  System.out.println("Multi Cast Receive  : "+ ex);  }
	}	
}

	/*	Conference Send	*/
	
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


	/*	Conference Receive      */


class ConferevceReceive  extends InternalFrameAdapter implements Runnable,ActionListener
{
   
 
   ExInternalFrame i_frm;
   Container i_con;  

   ExLabel la_type,la_received,user;

   ExButton send;  
	  
   ExTextField mesg;
   JTextArea rec; 
   JScrollPane scroll,scroll_list;	     

   JList l_list;	
   DefaultListModel lm_list;

  
  MulticastSocket ms;
  InetAddress addr;
  DatagramPacket dp;	
  String str;
  byte b[]	;  

  String cap,pport;
  int port;

 String core,service_id;
 Object users;
 int no,f1,f2;
  
 Thread t;
  
  String color_text; 
  
    
	ConferevceReceive(String p)
	{
		pport=p;
		port=Integer.parseInt(p);
		

		 t=new Thread(this);
		t.start();
	}
	public void run()
	{	    
 	    this.frmCreate();
 	}
	public  void  frmCreate()
	{	new Beep();
		
		i_frm=new ExInternalFrame(30,50,420,220,"conf");
		i_frm.setClosable(true);  i_frm.addInternalFrameListener(this);

		i_con=i_frm.getContentPane();
		i_con.setLayout(null);
		i_frm.setTitle(client.user_name);
		

		Declare.conf_desk.add(i_frm);
		
		la_type=new ExLabel(5,5,100,20,"Type Here  :");
		mesg=new ExTextField(5,25,200,20);             mesg.addActionListener(this);
		send=new ExButton(205,25,70,20,"Send");    send.addActionListener(this);
		la_received=new  ExLabel(5,45,100,20,"Received  :");
		rec=new JTextArea();
			rec.setEditable(false);
			rec.setBackground(new Color(95,100,105));
			rec.setForeground(Color.WHITE);
		
		scroll=new JScrollPane(rec);
		scroll.setBounds(5,65,270,115);

		user=new ExLabel(320,5,70,20,"Member");
		lm_list=new DefaultListModel ();
		l_list=new JList(lm_list);
			l_list.setBackground(Color.BLACK);
			l_list.setForeground(Color.WHITE);
		scroll_list=new JScrollPane(l_list);
		scroll_list.setBounds(290,30,115,150);

		i_con.add(la_type);	
		i_con.add(mesg);
		i_con.add(send);
		i_con.add(la_received);
		i_con.add(scroll);
		i_con.add(user);
		i_con.add(scroll_list);
		
		i_frm.setVisible(true);


		

		try
		{
		addr=InetAddress.getByName("225.2.2.2");
		ms=new MulticastSocket(port);
		ms.joinGroup(addr);
		}

		catch(Exception ex)
		{  System.out.println("ConferevceReceive JoinGroup  : " + ex );  }
		

  		       try
		        {
			while(true)
			 {
				
				b=new byte[1024];
				dp=new DatagramPacket(b,b.length);	
				
				ms.receive(dp);
				str=new String (b);
				str=str.trim();
				
				
				service_id=str.substring(0,str.indexOf("#")+1);
				core=str.substring(str.indexOf("#")+1);
					
					if(service_id.equals("conf@03#"))
						{
							 rec.append(core+"\n");
							color_text=rec.getText();
							rec.setSelectionStart(color_text.lastIndexOf(":"));
							rec.setSelectionEnd(color_text.length());
							
						}

					if(service_id.equals("conf@02#"))
						{
						
						no=Integer.parseInt(core.substring(0,core.indexOf("/")));
						core=core.substring(core.indexOf("/")+1);
						
							while(no>1)
							{
							f1=core.indexOf("/");
							users=core.substring(0,f1);
							
							core=core.substring(f1+1);
							no--;

							lm_list.addElement(users);
							}

							users=core.substring(0);
							
							lm_list.addElement(users);
						
							
						
						}

					if(service_id.equals("conf_close@01#"))
						{
							rec.append(core+"\n");
							ms.close();
							break;
						}
				
						
			          }

		              }
			catch(Exception ex)
			{  System.out.println("ConferevceReceive  Receive  : "+ ex);  }	
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		
		ConferenceSend.send(pport,"conf@03#"+client.user_name+" : "+mesg.getText().trim());
		mesg.setText("");
	}

	public  void internalFrameClosing(InternalFrameEvent e) 
 	{
		ConferenceSend.send(pport,"close@02#");

		try
		{
			ms.close();
			t.yield();
			
		}
		catch(Exception ex)
		{
			System.out.println("Multicast Socket Close :  " +ex);
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

class ChangePasswordAction implements ActionListener
{
   String cap;

	public void actionPerformed(ActionEvent ae)
	{
		cap=ae.getActionCommand();
		
		if(cap.equals("Update"))
		           {
				UDPSend.send("pass@01_101#");
				Declare.b_update.setEnabled(false);
				Declare.b_cancel.setEnabled(false);

				Declare.chang_pass_frm.setTitle("wait...");
		             }	
		if(cap.equals("Change Password"))
		         {	
			Declare.text_old_pass.setText("");
			Declare.text_new_pass.setText("");			
			Declare.chang_pass_frm.setVisible(true);
			
			Declare.change.setEnabled(false);
		         }	

		if(cap.equals("Cancel"))
		        {	
			Declare.chang_pass_frm.setVisible(false);
			Declare.change.setEnabled(true);
		        }	

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
				UDPSend.send("close@01#");

				new ani_window();
				System.exit(1);
			}

		if(frm.equals(ChatDeclare.chat_main_frm))
			Declare.chat.setEnabled(true);

		if(frm.equals(ChatRecCreate.chat_main_frm))
			ChatRecCreate.frm_flag=0;
		     
			
	}
}

class UDPFileSend extends Thread
{
    DatagramSocket ds;
    DatagramPacket dp; 
    InetAddress addr;		
    byte b[],b_1[],b_2[];
    int port,i,to_port;
    String str,size_str;
    long size,cal=0;
    int div,val,flag=0;
   

	UDPFileSend(String aaddr)
	{
		
		try
		{

		port=5000;
		addr=InetAddress.getByName(aaddr);
		ds=new DatagramSocket();
		
		b=new byte[10240];

		start();
		
		
		}
		catch(Exception ex)
		{
		System.out.println("UDP_File_SEND : "+ex);
		}
	}

	public void run()
	{

	  size=FileAction.select_file.length();
	  size_str=Long.toString(size);
	  size_str=size_str+"/"+client.user_name;
	  div=(int)size/100;
	  
	  Declare.progress=new JProgressBar(0,100);
	  Declare.file_display.setText("File :  "+FileAction.select_file.getName());
	  Declare.progress.setValue(0);
	  Declare.progress.setStringPainted(true);
	  Declare.progress.setBounds(20,35,200,20);
	  Declare.file_frm_ani_con.add(Declare.progress);

	  Declare.file_frm_ani.setVisible(true);
	  
	

	        try
	        {

		Thread.sleep(3000);
		
		b_2=size_str.getBytes();
		dp=new DatagramPacket(b_2,b_2.length,addr,port);
		ds.send(dp);
		

		Thread.sleep(10);

		while((i=FileAction.fips.read(b))!=-1)
		{

		  						
		      	dp=new DatagramPacket(b,b.length,addr,port);
			ds.send(dp);

			cal=cal+b.length;

				try
				{
				val=(int)cal/div;
				Declare.progress.setValue(val);
				}
				catch(Exception e)
				{  Declare.progress.setValue(100); }
		        	
			
			Thread.sleep(10);
			
		}
		Thread.sleep(2500);
		b_1="close@".getBytes();

		dp=new DatagramPacket(b_1,b_1.length,addr,port);			
		ds.send(dp);
		
		

		UDPSend.send("file@close#");
		ds.close();
		
		

		new Beep();

		
				
	        }	
	       catch(Exception ex) { System.out.println("File data Send_1 " + ex ); }
	       finally
	       {
			
				Declare.browse.setEnabled(true);

				Declare.file_frm_ani.setVisible(false);
				Declare.file_frm_ani.remove(Declare.progress);
				JOptionPane.showMessageDialog(Declare.file_frm, "File Transfered Sucessfully", "File", JOptionPane.INFORMATION_MESSAGE); 
				Declare.text_file.setText("");
	        }	

	
	}
}

class UDPFileReceice extends Thread
{
     DatagramSocket ds;
    static int flag=0;	
    DatagramPacket dp; 
    InetAddress addr;
    byte b[];
   String str;
   int flag_s=0;
  long size;
  int cal,val,div;

	UDPFileReceice()
	{
		try
		{
		b=new byte[10240];
		ds=new DatagramSocket(5000);			
		start();

		}
		catch(Exception ex)
		{
		System.out.println("UDP_File_Receive : "+ex);
		}
		
	}
	public void run()
	{   
   	    
	 Declare.rec_progress=new JProgressBar(0,100);
	Declare.rec_file_display.setText("File :  "+FileAction.op_file.getName());
	  Declare.rec_progress.setValue(0);
	  Declare.rec_progress.setStringPainted(true);
	  Declare.rec_progress.setBounds(20,35,200,20);
	  Declare.rec_file_frm_ani_con.add(Declare.rec_progress);

	  Declare.rec_file_frm_ani.setVisible(true);
	
                   try
                   { 	
		while(true)
		{
		         
			b=new byte[10240];
			dp=new DatagramPacket(b,b.length);
			ds.receive(dp);

				str=new String(b);str=str.trim();
				if(str.equals("close@"))
				{  break;   }

			if(flag_s==0)
			{  flag_s=1; 
			    size=Long.parseLong(str.substring(0,str.indexOf("/")));
			    div=(int)size/100;
			  
			    Declare.rec_from.setText("From  :  "+str.substring(str.indexOf("/")+1));	
			}			
			
			else
			{
			FileAction.fops.write(b);
			cal=cal+b.length;
				try
				{  
				  val=(int)cal/div;
				  Declare.rec_progress.setValue(val);
				}
				catch(ArithmeticException e)
				{  Declare.rec_progress.setValue(100); }

			}
		      
		}

	       ds.close();
                     FileAction.fops.close();
		

	     }
	     catch(IOException ex)
	     {
	              System.out.println("UDP_File_Receive_1 : "+ex);
	     }
	     finally
	     {
				
		

		Declare.rec_file_frm_ani.setVisible(false);
		Declare.rec_file_frm_ani.remove(Declare.rec_progress);
		Declare.rec_from.setText("From  :  ");
		
	     }	
	
	
		  
	}
}

class SoundRecAction implements ActionListener
{
     ExButton but;	
     String cap,mesg;
     int i;

    Thread thread_rec;

	SoundRecAction(int t)
	{
		i=t;
	}
	public void actionPerformed(ActionEvent ae)
	{
		but=(ExButton)ae.getSource();
		cap=ae.getActionCommand();

		if(but.equals(ChatRecDeclare.bu_mute[i]))
		{
		          if(cap.equals("Mute")) 		
		                {
			     ChatRecDeclare.flag[i]=1;
			     ChatRecDeclare.bu_mute[i].setText("Accept");		
				
			  }
		          if(cap.equals("Accept"))
			{
				ChatRecDeclare.bu_mute[i].setText("Mute");

				ChatRecDeclare.flag[i]=0;
				new RecSoundReceive(ChatRecDeclare.voice_rec_port[i],i,true); 
			}	
		}

	             if(but.equals(ChatRecDeclare.bu_voice[i]))
		{
			cap=ChatRecDeclare.bu_voice[i].getText();

			if(cap.equals("Enable"))
			{
			JOptionPane.showMessageDialog(ChatRecDeclare.chat_main_frm, "You must Disable this option , After speech", "From Server", JOptionPane.WARNING_MESSAGE );

				ChatRecDeclare.bu_voice[i].setText("Disable");

				mesg="chat@02#"+ChatRecDeclare.to_app[i]+"/"+"Voice Chat ENABLED By "+client.user_name;
				UDPSpecialSend.send(ChatRecDeclare.to_addr[i],ChatRecDeclare.to_port[i],mesg);          
				ChatRecDeclare.i_ta_mesg[i].append(client.user_name+" : "+"Voice Chat ENABLED By "+client.user_name+"\n");

				ChatRecDeclare.flag_back[i]=1;
				thread_rec=new Thread(new SoundRecord(ChatRecDeclare.to_addr[i],ChatRecDeclare.voice_sen_port[i],ChatRecDeclare.to_app[i],i,false));
				ChatRecDeclare.sound_record[i]=thread_rec;
				thread_rec.start();
				
			}
			if(cap.equals("Disable"))
			{
				ChatRecDeclare.bu_voice[i].setText("Enable");

				mesg="chat@02#"+ChatRecDeclare.to_app[i]+"/"+"Voice Chat DISABLED By "+client.user_name;
				UDPSpecialSend.send(ChatRecDeclare.to_addr[i],ChatRecDeclare.to_port[i],mesg);          
				ChatRecDeclare.i_ta_mesg[i].append(client.user_name+" : "+"Voice Chat DISABLED By "+client.user_name+"\n");

				thread_rec.yield();
				ChatRecDeclare.flag_back[i]=0;
			}
		}


	}
}

		/*	Sound Record	*/

class SoundRecord implements Runnable
{
    TargetDataLine mike;
    AudioFormat format;
    DataLine.Info info;
    AudioInputStream sound;
    String addr,port,to_app;	    
    int from_app;

    File record; 
    FileOutputStream fops;

   boolean ini;

      
	SoundRecord(String aaddr,String pport,String tto_app,int i,boolean iini)
	{
		addr=aaddr;
		port=pport;
		to_app=tto_app;
		from_app=i;
		ini=iini;
		
	}

	public void run()
	{
		try
		{
		
		//record =File.createTempFile("sss",".wav",new File("c:/group_client/login/client/temp"));
		record =File.createTempFile("sss",".wav",new File("./temp"));
		format=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100.0f,16,2,4,44100.0f,false);
		info=new DataLine.Info(TargetDataLine.class,format);

	
		if(AudioSystem.isLineSupported(info))
			{}

		mike=(TargetDataLine)(AudioSystem.getLine(info));
		mike.open(format,mike.getBufferSize());
		sound =new AudioInputStream(mike);
				
				
				new SoundSend(addr,port,record,to_app,from_app, mike,ini);

		mike.start();
		AudioSystem.write(sound,AudioFileFormat.Type.WAVE,record);
		
		
		}
		catch(Exception ex)
		{   System.out.println(" Sound_record  :  "+ex);  }
	}
}

class SoundSend extends Thread
{
    String str,to_app;
    byte data[];
    int bc,port,from_app;
    File file;    
    boolean ini;

    InetAddress addr;	
    DatagramSocket ds;
    DatagramPacket dp;
    
   URL url;
   AudioInputStream source;
  TargetDataLine mike;
		

	SoundSend(String aaddr,String pport,File ssource,String tto_app,int i,TargetDataLine mmike,boolean iini)
	{
		try
		{	
		
		addr=InetAddress.getByName(aaddr);
		port=Integer.parseInt(pport);
		file=ssource;
                             ini=iini;
		url=ssource.toURL();
		to_app=tto_app;
		ds=new DatagramSocket();
		from_app=i;
		mike=mmike;
		
		start();
		

		}
		catch(Exception ex)
		{   System.out.println(" SoundSend  :  " + ex );   }
		
	}
	public void run()
	{
		try
		{
			data=new byte[30800];
			Thread.sleep(500);	
			source=AudioSystem.getAudioInputStream(url);
		}
		catch(Exception ex)
		{  System.out.println("Create Read  :  "+ ex  );  }

		while(true) 
		{

		                                  

			try
			{
                                                   if(ini)
			        {	
				 if(ChatDeclare.flag[from_app]==0)
		     		{   
				            mike.stop();
 				           ds.close();	
				           Thread.sleep(1000);	
					source.close();	
				            file.delete();
					
				            break;	
				 } 					
                                                    }
			        else
			         {
				if(ChatRecDeclare.flag_back[from_app]==0)
		     		{
					 mike.stop();
 				           ds.close();	
				           Thread.sleep(1000);	
					source.close();	
				            file.delete();
					
				            break;	   
				}
			         }		 	


			bc=source.read(data,0,data.length);
			str=new String(data);

			str=bc+"/"+to_app+"/"+str;
			
				if(str.length()>30488)
					str=str.substring(0,30488);
			
			data=str.getBytes();

			if(!(bc==-1))
			{
				dp=new DatagramPacket(data,data.length,addr,port);
				ds.send(dp);
				
			}
			data=new byte[30800];
			}
			catch(Exception ex)
			{   System.out.println("  SoundSend _ 1 :  " + ex);   }
					
		}

	}
}

	/* sound Reciving section	*/


class RecSoundReceive extends Thread
{
    String str_rec_port;
    int rec_port,app_id;
    boolean rec_ini;

    
	RecSoundReceive(String rrec_port,int iid,boolean rrec_ini)
	{
		str_rec_port=rrec_port;
		rec_ini=rrec_ini;
		app_id=iid;
		start();
	}
	public  void run()
	{

		if(rec_ini)
		{ ChatRecDeclare.end_thread[app_id]=Thread.currentThread(); }
		else
		{  ChatDeclare.from_thread[app_id]=Thread.currentThread(); }	
	



	rec_port=Integer.parseInt(str_rec_port);	

		try
		{		
		
		SourceDataLine sourceline=null;		
		DatagramSocket ds=new DatagramSocket(rec_port);

			if(rec_ini)
			{  ChatRecDeclare.sound_ds[app_id]=ds; }
			else
			{ ChatDeclare.sound_ds[app_id]=ds;  }
		
		AudioFormat format=new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,44100.0f,16,2,4,44100.0f,false );		
		DataLine.Info sourceinfo=new DataLine.Info(SourceDataLine.class,format);
		sourceline=(SourceDataLine)AudioSystem.getLine(sourceinfo);		

		byte b[]=new byte[88000];

		sourceline.open(format);
		sourceline.start();
		int i=0,j,id;
		String str,str1;
			while(true) 
			{
			        if(rec_ini)
			        {	
				if(ChatRecDeclare.flag[app_id]==1)
					{
						try
						{
						ds.close();
						 
						break;
						}
						catch(Exception ex)
						{ System.out.println(" RecSoundReceive_Close   : "+ex);  } 	
					}
                                                     }
			        else
			          {
					if(ChatDeclare.flag_back[app_id]==1)
					{
						try
						{
						ds.close();
						
						break;
						}
						catch(Exception ex)
						{ System.out.println(" RecSoundReceive_Close   : "+ex);  } 	
					}
			            }
				   

				DatagramPacket dp=new DatagramPacket(b,b.length);
				ds.receive(dp);
				str=new String(b);
				j=Integer.parseInt(str.substring(0,str.indexOf("/")));
				str=str.substring(str.indexOf("/")+1);
				str1=str.substring(0,str.indexOf("/"));
				id=Integer.parseInt(str1);
				str=str.substring(str.indexOf("/")+1);				
				
				b=str.getBytes();
				sourceline.write(b,0,j);
				
				
				
			} 

		
	      }
	catch(Exception ex){System.out.println("RecSoundReceive_1  :  " +  ex );  }
		
	}
}

class UserCheck
{
   static int i,j;
   static String str_id;
static Object obj_id;
static boolean bool;
	public static void userCheck(TableModel tm,String user_id)
	{
	i=0;
              bool=false;	

		do
		{	
			try
			{
		
			obj_id=tm.getValueAt(i,0);
			str_id=obj_id.toString();
			

				if(str_id.equals(user_id))
				      {	bool=true;         break;     }
				else
				      {	bool=false;                       }
					
			
			}
			catch(Exception ex)
			{  str_id=null; break;}
				
			
			i++;
		}while(i<=1000);
		
	}
}

class FrameState extends Thread
{
     ExInternalFrame frm;
     String str;
     int i;
     boolean bool;

	FrameState(ExInternalFrame t_frm,int id,boolean bbool)
	{
		frm=t_frm;
		bool=bbool;
		i=id;
		start();
	}
	public void run()
	{
	 str=frm.getTitle();

		while(true)				
		{

			frm.setTitle("");

			try{ Thread.sleep(500); }
			catch(Exception ex) { }

			frm.setTitle(str);	

			try{ Thread.sleep(500); }
			catch(Exception ex) { }	

			if(bool)
			{
				if(ChatRecDeclare.state[i]==0)
					break;
			}
			else
			{
				if(ChatDeclare.state[i]==0)
					break;
			}			
	
		}

	frm.setTitle(str);
	}
}

class FrameStateChange extends  InternalFrameAdapter
{
   int i;
  boolean bool;
    	
	FrameStateChange(int id,boolean bbool)
	{
		i=id;
		bool=bbool;
	}

	public void internalFrameActivated(InternalFrameEvent e) 
	{
		if(bool)
		{
			ChatRecDeclare.state[i]=0;
			ChatRecDeclare.state_flag[i]=1;
			
		}
		else
		{
			ChatDeclare.state[i]=0;
			ChatDeclare.state_flag[i]=1;
		}
	}
}

class Beep extends Thread
{
        Beep()
        {		
	start();
        }
        public void run()
        {
	Toolkit tool=Toolkit.getDefaultToolkit();
	tool.beep();
        }		

}

class ani_window
{
	ani_window()
	{
		try
		{
			Runtime run=Runtime.getRuntime();
			Process pro=run.exec("java info");
		}
		catch(Exception ex)
		{
			
		}
		
	}
}

