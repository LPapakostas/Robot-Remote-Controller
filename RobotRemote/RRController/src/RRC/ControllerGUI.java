package RRC;

import java.awt.*;
import java.awt.event.*;

class ControllerGUI extends Frame  {
	private static final long serialVersionUID = 1L;
	static Button connect , disconnect ;
	public static MyButton insert , destroy , state , goHome ;
	public static MyButton move , right, left , stop , step ;
	static MyButton auto , manual ;
	public static TextField ipdisplay , portdisplay , statedisplay ;
	Label ip , port , getstate;
	Connector con;
	Disconnector dis;
	
	public ControllerGUI(Connector con , Disconnector dis) {
		super("Remote Robot Controller");
		
		this.setVisible(true);
		this.toFront();
		this.setBackground(Color.gray);
		this.setSize(300,550);
		this.setLayout(null);
		this.addWindowListener(new CloseWindowAndExit());
		
		this.con = con;
		this.dis = dis;
	
		MyButton.f = this;
		insert = new MyButton(this,RobotControl.INSERT,"INSERT", 20 , 40 , 165 , 30);
	    move = new MyButton(this,RobotControl.MOVE,"MOVE", 20 , 80 , 80 , 30);
	    left = new MyButton(this,RobotControl.LEFT,"LEFT", 20 , 120 , 80 , 30);
	    right = new MyButton(this,RobotControl.RIGHT,"RIGHT", 105 , 120 , 80 , 30);
	    stop = new MyButton(this,RobotControl.STOP,"STOP", 105 , 80 , 80 , 30);
	    auto = new MyButton(this,RobotControl.SETMODE,"AUTO-MODE", 20 , 270 , 95 , 30);
	    manual = new MyButton(this,RobotControl.SETMODE,"MANUAL-MODE", 135 , 270 , 110 , 30);
	    goHome = new MyButton(this,RobotControl.HOME,"GO HOME", 20 , 230, 75, 30);
	    destroy = new MyButton(this,RobotControl.DESTROY,"ROBOT DESTROY", 20 , 195 , 135 , 30);
	    step = new MyButton(this,RobotControl.STEP,"STEP", 20 , 160 , 80 , 30);
	    state = new MyButton(this,RobotControl.STATE,"STATE" , 105 , 160 , 80 ,30);
	    
	    connect = new Button ( "CONNECT");
	    connect.setBounds( 130, 370, 80, 30);
	    connect.setForeground(Color.blue);
	    connect.setFont(new Font("TimesRoman",Font.PLAIN,14));
	    connect.setEnabled(true);
	    this.add(connect);
	    connect.addActionListener(new ConnectButtonHandler(connect));
	    
	    disconnect = new Button ( "DISCONNECT");
	    disconnect.setBounds( 130, 410, 100, 30);
	    disconnect.setForeground(Color.blue);
	    disconnect.setFont(new Font("TimesRoman",Font.PLAIN,14));
	    disconnect.setEnabled(false);
	    this.add(disconnect);
	    disconnect.addActionListener(new DisconnectButtonHandler(disconnect));
	    
	    ip = new Label("IP address");
		ip.setBounds(20,305,100,30);
		ip.setFont(new Font("TimesRoman",Font.PLAIN,12));
		this.add(ip);
	    
	    ipdisplay = new TextField();
	    ipdisplay.setText(Client.ip);
	    ipdisplay.setBounds(20,335,100,30);
	    ipdisplay.setEditable(false);
	    ipdisplay.setVisible(true);
	    this.add(ipdisplay);
	    
	    port = new Label("Port");
		port.setBounds(20,370,100,30);
		port.setFont(new Font("TimesRoman",Font.PLAIN,12));
		this.add(port);
	    
		portdisplay = new TextField();
		portdisplay.setBounds(20,400,60,30);
		portdisplay.setEditable(true);
		portdisplay.setVisible(true);
		this.add(portdisplay);
		
		getstate = new Label("Robot Current State");
		getstate.setBounds(20,440,160,30);
		getstate.setFont(new Font("TimesRoman",Font.PLAIN,12));
		this.add(getstate);
		
		statedisplay = new TextField();
		statedisplay.setBounds(20,470,160,30);
		statedisplay.setEditable(false);
		statedisplay.setVisible(true);
		this.add(statedisplay);
	}
	
	class CloseWindowAndExit extends WindowAdapter{
		public void windowClosing(WindowEvent closeWindowAndExit){
			System.exit(0);
		}
	}

}

