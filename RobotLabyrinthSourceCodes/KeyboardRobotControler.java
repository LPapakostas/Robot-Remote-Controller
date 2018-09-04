import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class KeyboardRobotControler extends javax.swing.JFrame
{
  private Robot robot;
  private JButton jButton5;
  private JButton jButton4;
  private JButton jButton3;
  private JButton jButton2;
  private JButton jButton1;
  
  public KeyboardRobotControler(Robot robot)
  {
    this.robot = robot;
    initComponents();
  }
  
  public void setRobot(Robot robot) {
    this.robot = robot;
  }
  




  private void initComponents()
  {
    jButton1 = new JButton();
    jButton2 = new JButton();
    jButton3 = new JButton();
    jButton4 = new JButton();
    jButton5 = new JButton();
    
    setTitle("Control panel");
    addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        KeyboardRobotControler.this.formKeyPressed(evt);
      }
      
    });
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        KeyboardRobotControler.this.exitForm(evt);
      }
      
    });
    jButton1.setText("Go Ahead");
    jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        KeyboardRobotControler.this.jButton1ActionPerformed(evt);
      }
      
    });
    getContentPane().add(jButton1, "North");
    
    jButton2.setText("Turn Left");
    jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
    jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        KeyboardRobotControler.this.jButton2ActionPerformed(evt);
      }
      
    });
    getContentPane().add(jButton2, "West");
    
    jButton3.setText("Turn Right");
    jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
    jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        KeyboardRobotControler.this.jButton3ActionPerformed(evt);
      }
      
    });
    getContentPane().add(jButton3, "East");
    
    jButton4.setText("Stop");
    jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
    jButton4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        KeyboardRobotControler.this.jButton4ActionPerformed(evt);
      }
      
    });
    getContentPane().add(jButton4, "Center");
    
    jButton5.setText("Home");
    jButton5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        KeyboardRobotControler.this.jButton5ActionPerformed(evt);
      }
      
    });
    getContentPane().add(jButton5, "South");
    
    pack();
  }
  
  private void jButton5ActionPerformed(ActionEvent evt)
  {
    robot.home();
  }
  
  private void formKeyPressed(java.awt.event.KeyEvent evt)
  {
    char c = evt.getKeyChar();
    System.out.println("code=" + c);
  }
  



  private void jButton3ActionPerformed(ActionEvent evt)
  {
    if (robot != null) robot.turnRight(); else {
      System.out.println("Robot=null");
    }
  }
  
  private void jButton2ActionPerformed(ActionEvent evt)
  {
    if (robot != null) robot.turnLeft(); else {
      System.out.println("Robot=null");
    }
  }
  
  private void jButton4ActionPerformed(ActionEvent evt)
  {
    if (robot != null) robot.halt(); else {
      System.out.println("Robot=null");
    }
  }
  
  private void jButton1ActionPerformed(ActionEvent evt)
  {
    if (robot != null) robot.move(); else {
      System.out.println("Robot=null");
    }
  }
  
  private void exitForm(java.awt.event.WindowEvent evt)
  {
    robot.destroyRobot();
    dispose();
  }
  
  public void myexit()
  {
    robot.destroyRobot();
    dispose();
  }
}
