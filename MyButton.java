package RRC;

import java.awt.Button;

public class MyButton extends Button {
  private static final long serialVersionUID = 1L;
  public static ControllerGUI f;
  public RobotControl rb;
  
  public MyButton(ControllerGUI f, RobotControl rb, String name, int x, int y, int h, int w) {
    super(name);
    this.rb = rb;
    setFont(new java.awt.Font("TimesRoman", 0, 14));
    setForeground(java.awt.Color.blue);
    setEnabled(false);
    setBounds(x, y, h, w);
    f.add(this);
    addActionListener(new MyButtonHandler(this));
  }
}
