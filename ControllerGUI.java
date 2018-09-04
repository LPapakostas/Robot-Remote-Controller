package RRC;

import java.awt.TextField;

class ControllerGUI extends java.awt.Frame {
  private static final long serialVersionUID = 1L;
  static java.awt.Button connect;
  static java.awt.Button disconnect;
  public static MyButton insert;
  public static MyButton destroy;
  public static MyButton state;
  public static MyButton goHome;
  public static MyButton move;
  public static MyButton right;
  public static MyButton left;
  public static MyButton stop;
  
  public ControllerGUI(Connector con, Disconnector dis) { super("Remote Robot Controller");
    
    setVisible(true);
    toFront();
    setBackground(java.awt.Color.gray);
    setSize(300, 550);
    setLayout(null);
    addWindowListener(new CloseWindowAndExit());
    
    this.con = con;
    this.dis = dis;
    
    MyButton.f = this;
    insert = new MyButton(this, RobotControl.INSERT, "INSERT", 20, 40, 165, 30);
    move = new MyButton(this, RobotControl.MOVE, "MOVE", 20, 80, 80, 30);
    left = new MyButton(this, RobotControl.LEFT, "LEFT", 20, 120, 80, 30);
    right = new MyButton(this, RobotControl.RIGHT, "RIGHT", 105, 120, 80, 30);
    stop = new MyButton(this, RobotControl.STOP, "STOP", 105, 80, 80, 30);
    auto = new MyButton(this, RobotControl.SETMODE, "AUTO-MODE", 20, 270, 95, 30);
    manual = new MyButton(this, RobotControl.SETMODE, "MANUAL-MODE", 135, 270, 110, 30);
    goHome = new MyButton(this, RobotControl.HOME, "GO HOME", 20, 230, 75, 30);
    destroy = new MyButton(this, RobotControl.DESTROY, "ROBOT DESTROY", 20, 195, 135, 30);
    step = new MyButton(this, RobotControl.STEP, "STEP", 20, 160, 80, 30);
    state = new MyButton(this, RobotControl.STATE, "STATE", 105, 160, 80, 30);
    
    connect = new java.awt.Button("CONNECT");
    connect.setBounds(130, 370, 80, 30);
    connect.setForeground(java.awt.Color.blue);
    connect.setFont(new java.awt.Font("TimesRoman", 0, 14));
    connect.setEnabled(true);
    add(connect);
    connect.addActionListener(new ConnectButtonHandler(connect));
    
    disconnect = new java.awt.Button("DISCONNECT");
    disconnect.setBounds(130, 410, 100, 30);
    disconnect.setForeground(java.awt.Color.blue);
    disconnect.setFont(new java.awt.Font("TimesRoman", 0, 14));
    disconnect.setEnabled(false);
    add(disconnect);
    disconnect.addActionListener(new DisconnectButtonHandler(disconnect));
    
    ip = new java.awt.Label("IP address");
    ip.setBounds(20, 305, 100, 30);
    ip.setFont(new java.awt.Font("TimesRoman", 0, 12));
    add(ip);
    
    ipdisplay = new TextField();
    ipdisplay.setText(Client.ip);
    ipdisplay.setBounds(20, 335, 100, 30);
    ipdisplay.setEditable(false);
    ipdisplay.setVisible(true);
    add(ipdisplay);
    
    port = new java.awt.Label("Port");
    port.setBounds(20, 370, 100, 30);
    port.setFont(new java.awt.Font("TimesRoman", 0, 12));
    add(port);
    
    portdisplay = new TextField();
    portdisplay.setBounds(20, 400, 60, 30);
    portdisplay.setEditable(true);
    portdisplay.setVisible(true);
    add(portdisplay);
    
    getstate = new java.awt.Label("Robot Current State");
    getstate.setBounds(20, 440, 160, 30);
    getstate.setFont(new java.awt.Font("TimesRoman", 0, 12));
    add(getstate);
    
    statedisplay = new TextField();
    statedisplay.setBounds(20, 470, 160, 30);
    statedisplay.setEditable(false);
    statedisplay.setVisible(true);
    add(statedisplay);
  }
  
  class CloseWindowAndExit extends java.awt.event.WindowAdapter { CloseWindowAndExit() {}
    
    public void windowClosing(java.awt.event.WindowEvent closeWindowAndExit) { System.exit(0); }
  }
  
  public static MyButton step;
  static MyButton auto;
  static MyButton manual;
  public static TextField ipdisplay;
  public static TextField portdisplay;
  public static TextField statedisplay;
  java.awt.Label ip;
  java.awt.Label port;
  java.awt.Label getstate;
  Connector con;
  Disconnector dis;
}
