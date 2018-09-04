import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RobotLabyrinth extends javax.swing.JFrame
{
  private LabyrinthServer server;
  private static java.awt.TextArea messageArea = null;
  private static String file2Load = null;
  private static String file2Save = null;
  private static RobotLabyrinth instanceRobotLabyrinth = null;
  private KeyboardRobotControler manualControl;
  private RobotControler autoControl;
  private Labyrinth labyrinth;
  public static int labyrinthSize;
  public static int labyrinthBlockSize;
  public static int stepsInBlock;
  public static int robotSize;
  public static boolean showGrid;
  private javax.swing.ButtonGroup butGroup;
  private JPanel botPanel;
  private javax.swing.JSeparator jSeparator2;
  
  public RobotLabyrinth(Labyrinth lab) {
    labyrinth = lab;
    initComponents();
    
    labyrinth.start();
    messageArea = msgArea;
    instanceRobotLabyrinth = this;
  }
  
  public static synchronized void printMsg(String msg) {
    if (messageArea != null) messageArea.append(msg + "\n");
  }
  
  public static void updateGUI() {
    instanceRobotLabyrinth.updateThisGUI();
  }
  
  private void updateThisGUI() {
    if (labyrinth.getHeight() > sidePanel.getHeight()) {
      if (labyrinth.getWidth() + sidePanel.getWidth() > botPanel.getWidth()) {
        setBounds(getX(), getY(), labyrinth.getWidth() + sidePanel.getWidth() + 6, labyrinth.getHeight() + botPanel.getHeight() + 26);
      } else {
        setBounds(getX(), getY(), botPanel.getWidth() + 6, labyrinth.getHeight() + botPanel.getHeight() + 26);
      }
      botPanel.setBounds(botPanel.getX(), labyrinth.getHeight(), botPanel.getWidth(), 100);
    } else {
      if (labyrinth.getWidth() + sidePanel.getWidth() > botPanel.getWidth()) {
        setBounds(getX(), getY(), labyrinth.getWidth() + sidePanel.getWidth() + 6, sidePanel.getHeight() + botPanel.getHeight() + 26);
      } else {
        setBounds(getX(), getY(), botPanel.getWidth() + 6, sidePanel.getHeight() + botPanel.getHeight() + 26);
      }
      botPanel.setBounds(botPanel.getX(), sidePanel.getHeight(), botPanel.getWidth(), 100);
    }
    repaint();
  }
  
  private javax.swing.JSeparator jSeparator1;
  private java.awt.TextArea msgArea;
  private JPanel sidePanel;
  private JButton butStop;
  private JButton butStart;
  private JRadioButton radioRemote;
  
  private void initComponents() {
    butGroup = new javax.swing.ButtonGroup();
    



    sidePanel = new JPanel();
    jLabel2 = new JLabel();
    radioAuto = new JRadioButton();
    radioManual = new JRadioButton();
    radioRemote = new JRadioButton();
    jLabel1 = new JLabel();
    textPort = new JTextField();
    jSeparator1 = new javax.swing.JSeparator();
    butStart = new JButton();
    butStop = new JButton();
    jSeparator2 = new javax.swing.JSeparator();
    butLoad = new JButton();
    botPanel = new JPanel();
    msgArea = new java.awt.TextArea();
    
    setTitle("RobotLabyrinthKTV3 Nov2016");
    setFont(new java.awt.Font("Verdana", 0, 10));
    setResizable(false);
    addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        RobotLabyrinth.this.formKeyPressed(evt);
      }
      
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        RobotLabyrinth.this.exitForm(evt);
      }
      
    });
    sidePanel.setLayout(new java.awt.GridBagLayout());
    

    getContentPane().add(labyrinth, "Center");
    
    sidePanel.setBorder(new javax.swing.border.BevelBorder(0));
    sidePanel.setMinimumSize(new Dimension(120, 200));
    sidePanel.setPreferredSize(new Dimension(120, 300));
    jLabel2.setText("Mode:");
    jLabel2.setMaximumSize(new Dimension(110, 16));
    jLabel2.setMinimumSize(new Dimension(110, 16));
    jLabel2.setPreferredSize(new Dimension(110, 16));
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(jLabel2, gridBagConstraints);
    
    radioAuto.setSelected(true);
    radioAuto.setText("Local auto");
    butGroup.add(radioAuto);
    radioAuto.setMaximumSize(new Dimension(120, 24));
    radioAuto.setMinimumSize(new Dimension(110, 24));
    radioAuto.setPreferredSize(new Dimension(110, 24));
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(radioAuto, gridBagConstraints);
    
    radioManual.setText("Local manual");
    butGroup.add(radioManual);
    radioManual.setMaximumSize(new Dimension(120, 24));
    radioManual.setMinimumSize(new Dimension(110, 24));
    radioManual.setPreferredSize(new Dimension(110, 24));
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(radioManual, gridBagConstraints);
    
    radioRemote.setText("Remote");
    butGroup.add(radioRemote);
    radioRemote.setMaximumSize(new Dimension(120, 24));
    radioRemote.setMinimumSize(new Dimension(110, 24));
    radioRemote.setPreferredSize(new Dimension(110, 24));
    radioRemote.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        RobotLabyrinth.this.radioRemoteStateChanged(evt);
      }
      
    });
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(radioRemote, gridBagConstraints);
    
    jLabel1.setText("Port Number:");
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(jLabel1, gridBagConstraints);
    
    textPort.setColumns(7);
    textPort.setEditable(false);
    textPort.setText("12345");
    textPort.setPreferredSize(new Dimension(77, 26));
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(textPort, gridBagConstraints);
    
    jSeparator1.setPreferredSize(new Dimension(110, 5));
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    insets = new java.awt.Insets(5, 0, 0, 0);
    sidePanel.add(jSeparator1, gridBagConstraints);
    
    butStart.setText("Start");
    butStart.setMaximumSize(new Dimension(100, 26));
    butStart.setMinimumSize(new Dimension(100, 26));
    butStart.setPreferredSize(new Dimension(100, 26));
    butStart.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        RobotLabyrinth.this.butStartActionPerformed(evt);
      }
      
    });
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(butStart, gridBagConstraints);
    
    butStop.setText("Stop");
    butStop.setMaximumSize(new Dimension(100, 26));
    butStop.setMinimumSize(new Dimension(100, 26));
    butStop.setPreferredSize(new Dimension(100, 26));
    butStop.setEnabled(false);
    butStop.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        RobotLabyrinth.this.butStopActionPerformed(evt);
      }
      
    });
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(butStop, gridBagConstraints);
    
    jSeparator2.setPreferredSize(new Dimension(110, 5));
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    insets = new java.awt.Insets(5, 0, 0, 0);
    sidePanel.add(jSeparator2, gridBagConstraints);
    
    butLoad.setText("Load labyrinth");
    butLoad.setMargin(new java.awt.Insets(2, 2, 2, 2));
    butLoad.setMaximumSize(new Dimension(100, 26));
    butLoad.setMinimumSize(new Dimension(100, 26));
    butLoad.setPreferredSize(new Dimension(100, 26));
    butLoad.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        RobotLabyrinth.this.butLoadActionPerformed(evt);
      }
      
    });
    gridBagConstraints = new GridBagConstraints();
    gridwidth = 0;
    sidePanel.add(butLoad, gridBagConstraints);
    
    getContentPane().add(sidePanel, "West");
    
    botPanel.setLayout(new java.awt.BorderLayout());
    
    botPanel.setMinimumSize(new Dimension(300, 80));
    botPanel.setPreferredSize(new Dimension(300, 80));
    botPanel.add(msgArea, "Center");
    
    getContentPane().add(botPanel, "South");
    
    pack();
  }
  
  private void butLoadActionPerformed(ActionEvent evt)
  {
    javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
    String userdir = System.getProperty("user.dir");
    chooser.setCurrentDirectory(new java.io.File(userdir));
    chooser.setFileFilter(new LABFileFilter());
    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == 0) {
      String fname = chooser.getSelectedFile().getAbsolutePath();
      try {
        labyrinth.load(fname);
      } catch (java.io.IOException e) {
        System.err.println("Error opening file: " + e.toString());
      } catch (ClassNotFoundException e) {
        System.err.println("Error opening file: " + e.toString());
      } catch (LabyrinthInUseException e) {
        System.err.println("Error opening file: " + e.toString());
      }
    }
    
    updateThisGUI();
  }
  
  private void radioRemoteStateChanged(javax.swing.event.ChangeEvent evt)
  {
    if (radioRemote.isSelected()) {
      textPort.setEditable(true);
    } else {
      textPort.setEditable(false);
    }
  }
  

  private void butStopActionPerformed(ActionEvent evt)
  {
    if (radioRemote.isSelected()) {
      server.stopServer();
    } else if (radioAuto.isSelected()) {
      autoControl.setStop(true);
    } else if (radioManual.isSelected()) {
      manualControl.myexit();
    }
    
    butStart.setEnabled(true);
    butLoad.setEnabled(true);
    butStop.setEnabled(false);
    if (radioRemote.isSelected()) { textPort.setEditable(true);
    }
    radioAuto.setEnabled(true);
    radioManual.setEnabled(true);
    radioRemote.setEnabled(true);
  }
  
  private void formKeyPressed(java.awt.event.KeyEvent evt)
  {
    System.out.println("CODE=" + evt.getKeyCode());
  }
  
  private void butStartActionPerformed(ActionEvent evt)
  {
    radioAuto.setEnabled(false);
    radioManual.setEnabled(false);
    radioRemote.setEnabled(false);
    
    if (radioRemote.isSelected()) {
      try {
        int port = Integer.valueOf(textPort.getText()).intValue();
        server = new LabyrinthServer(port, labyrinth);
        textPort.setEditable(false);
      } catch (NumberFormatException e) { System.out.println(e.toString());
      } } else if (radioAuto.isSelected()) {
      Robot auto = new Robot("AUTO", 0, 0, labyrinth, robotSize, labyrinthBlockSize / stepsInBlock);
      autoControl = new RobotControler(auto);
      autoControl.start();
    } else if (radioManual.isSelected()) {
      Robot test = new Robot("TEST", 0, 0, labyrinth, robotSize, labyrinthBlockSize / stepsInBlock);
      manualControl = new KeyboardRobotControler(test);
      manualControl.setLocation(getX() + getWidth(), getY());
      manualControl.show();
    }
    
    butStart.setEnabled(false);
    butLoad.setEnabled(false);
    butStop.setEnabled(true);
  }
  


  private void exitForm(java.awt.event.WindowEvent evt) { System.exit(0); }
  
  private JRadioButton radioManual;
  private JTextField textPort;
  private JRadioButton radioAuto;
  
  public static void main(String[] args) {
    labyrinthSize = 10;
    labyrinthBlockSize = 40;
    robotSize = 20;
    stepsInBlock = 8;
    showGrid = true;
    





    int i = 0;
    while (i < args.length) {
      String arg = args[i].toUpperCase();
      try {
        if (arg.startsWith("LABSIZE=")) {
          labyrinthSize = Integer.valueOf(arg.substring(8)).intValue();
        } else if (arg.startsWith("BLOCKSIZE=")) {
          labyrinthBlockSize = Integer.valueOf(arg.substring(10)).intValue();
        } else if (arg.startsWith("ROBOTSIZE=")) {
          robotSize = Integer.valueOf(arg.substring(10)).intValue();
        } else if (arg.startsWith("STEPS=")) {
          stepsInBlock = Integer.valueOf(arg.substring(6)).intValue();
        } else if (arg.startsWith("LOAD=")) {
          file2Load = new String(arg.substring(5));
        } else if (arg.startsWith("SAVE=")) {
          file2Save = new String(arg.substring(5));
        } else if (arg.equalsIgnoreCase("NOGRID")) {
          showGrid = false;
        } else {
          System.out.println("Parameter error!");
          System.exit(1);
        }
      } catch (NumberFormatException e) {
        System.out.println("Parameter error!");
        System.exit(1);
      }
      i++;
    }
    
    if ((labyrinthSize < 2) || (labyrinthBlockSize <= robotSize) || (stepsInBlock < 1) || (robotSize < 4)) {
      System.out.println("Invalid parameter value!");
      System.exit(1);
    }
    
    System.out.println("\nParameters set:");
    System.out.println("Labyrinth Size = " + labyrinthSize);
    System.out.println("Block Size = " + labyrinthBlockSize);
    System.out.println("Robot Size = " + robotSize);
    System.out.println("Steps Per Block = " + stepsInBlock);
    
    Labyrinth lab = new Labyrinth(labyrinthSize, labyrinthBlockSize, showGrid);
    try {
      if (file2Save != null) lab.save(file2Save);
      if (file2Load != null) lab.load(file2Load);
    } catch (java.io.IOException e) {
      System.out.println(e.toString());
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    } catch (LabyrinthInUseException e) {
      System.out.println(e.toString());
    }
    new RobotLabyrinth(lab).show();
  }
  
  private JLabel jLabel2;
  private JButton butLoad;
  private JLabel jLabel1;
}
