package RRC;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;

class Start extends java.awt.Frame
{
  private static final long serialVersionUID = 1L;
  Label robot;
  public static TextField number;
  public static Button ok;
  
  public Start()
  {
    super("START");
    setVisible(true);
    toFront();
    setBackground(Color.gray);
    setSize(300, 150);
    setLayout(null);
    

    robot = new Label("Give Number of Robots");
    robot.setBounds(20, 30, 150, 30);
    robot.setFont(new Font("TimesRoman", 0, 12));
    add(robot);
    
    number = new TextField();
    number.setBounds(20, 70, 100, 30);
    number.setEditable(true);
    number.setVisible(true);
    add(number);
    
    ok = new Button("OK");
    ok.setFont(new Font("TimesRoman", 0, 14));
    ok.setForeground(Color.blue);
    ok.setBounds(200, 70, 50, 30);
    add(ok);
    ok.addActionListener(new OKButtonHandler(ok));
  }
  
  public class OKButtonHandler implements java.awt.event.ActionListener
  {
    Button b;
    
    OKButtonHandler(Button b) {
      this.b = b;
    }
    
    public void actionPerformed(ActionEvent e) {
      try {
        if (Start.number.getText() == "") throw new NullPointerException();
        int num = Integer.parseInt(Start.number.getText());
        for (int i = 0; i < num; i++) {
          new ControllerGUI(new Connector(), new Disconnector());
        }
      }
      catch (NullPointerException num) {
        System.err.println("Number of Robots not given");
      }
    }
  }
}
