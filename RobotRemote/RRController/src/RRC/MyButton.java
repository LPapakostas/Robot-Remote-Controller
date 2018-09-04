package RRC;

import java.awt.*;

public class MyButton extends Button {
	private static final long serialVersionUID = 1L;
	public static ControllerGUI f;
	public RobotControl rb;

	public MyButton(ControllerGUI f,RobotControl rb, String name , int x , int y , int h , int w) {
		super(name);
		this.rb=rb;
		this.setFont(new Font("TimesRoman",Font.PLAIN,14));
		this.setForeground(Color.blue);
		this.setEnabled(false);
		this.setBounds(x,y,h,w);
		f.add(this);
		this.addActionListener(new MyButtonHandler(this));
	}

}
