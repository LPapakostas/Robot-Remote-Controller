package RRC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyButtonHandler implements ActionListener {
	MyButton b;
	
	MyButtonHandler(MyButton b ){
		this.b = b;
	}

	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == ControllerGUI.insert ) {
			Connector.out.println(RobotControl.INSERT.code);
			Connector.out.flush();
			
			ControllerGUI.auto.setEnabled(true);
			ControllerGUI.manual.setEnabled(true);
			ControllerGUI.insert.setEnabled(false);
			ControllerGUI.destroy.setEnabled(true);
			ControllerGUI.goHome.setEnabled(true);
		}
		else if (e.getSource() == ControllerGUI.auto){
			Connector.out.write(RobotControl.SETMODE.code);
			Connector.out.flush();
			Connector.out.write((char)1); 
			Connector.out.flush();
			
			ControllerGUI.manual.setEnabled(true);
			ControllerGUI.move.setEnabled(false);
			ControllerGUI.left.setEnabled(false);
			ControllerGUI.right.setEnabled(false);
			ControllerGUI.stop.setEnabled(false);
			ControllerGUI.auto.setEnabled(false);
			ControllerGUI.goHome.setEnabled(false);
			ControllerGUI.destroy.setEnabled(false);
			ControllerGUI.state.setEnabled(false);
			ControllerGUI.step.setEnabled(false);
		}
		else if (e.getSource() == ControllerGUI.manual) {
			Connector.out.write(RobotControl.SETMODE.code);
			Connector.out.flush();
			Connector.out.write((char)0); 
			Connector.out.flush();
			
			ControllerGUI.move.setEnabled(true);
			ControllerGUI.left.setEnabled(true);
			ControllerGUI.right.setEnabled(true);
			ControllerGUI.stop.setEnabled(true);
			ControllerGUI.auto.setEnabled(true);
			ControllerGUI.step.setEnabled(true);
			ControllerGUI.state.setEnabled(true);
			ControllerGUI.destroy.setEnabled(true);
			ControllerGUI.goHome.setEnabled(true);
			ControllerGUI.manual.setEnabled(false);
		}
		else if(e.getSource() == ControllerGUI.destroy) {
			Connector.out.println(RobotControl.DESTROY.code);
			Connector.out.flush();
			
			ControllerGUI.insert.setEnabled(true);
			ControllerGUI.move.setEnabled(false);
			ControllerGUI.left.setEnabled(false);
			ControllerGUI.right.setEnabled(false);
			ControllerGUI.stop.setEnabled(false);
			ControllerGUI.auto.setEnabled(false);
			ControllerGUI.manual.setEnabled(false);
			ControllerGUI.goHome.setEnabled(false);
			ControllerGUI.destroy.setEnabled(false);
			ControllerGUI.step.setEnabled(false);
			ControllerGUI.state.setEnabled(false);
			ControllerGUI.statedisplay.setText("");
		}
		else if (e.getSource() == ControllerGUI.state){
			Connector.out.println(RobotControl.STATE.code);
			Connector.out.flush();
			try {
				byte[] b = {0,0,0};
				Connector.in.read(b);
				if(b[2] == States.STOPED.code) ControllerGUI.statedisplay.setText(States.STOPED.name);
				else if(b[2] == States.MOVING.code) ControllerGUI.statedisplay.setText(States.MOVING.name);
				else if (b[2] == States.STEPING.code) ControllerGUI.statedisplay.setText(States.STEPING.name);
				else ControllerGUI.statedisplay.setText("");
			}
			catch (IOException state) {
				System.err.println("State cannnot Estimated");
			}
			catch(NullPointerException state_now) {
				System.err.println("Empty input");
			}
		}
		else {
			MyButton b = (MyButton)(e.getSource());
			Connector.out.println((b.rb).getCode());
			Connector.out.flush();
		}
	}

}
