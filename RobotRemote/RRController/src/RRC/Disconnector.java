package RRC;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Disconnector {
	
	public static void disconnect() throws IOException {
		
		Connector.in.close();
		Connector.out.close();
		Connector.socket.close();
		ControllerGUI.portdisplay.setText("");
		ControllerGUI.statedisplay.setText("");
		ControllerGUI.insert.setEnabled(false);
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
		ControllerGUI.disconnect.setEnabled(false);
		ControllerGUI.connect.setEnabled(true);
		
	}

}

class DisconnectButtonHandler implements ActionListener {
	Button b;
	
	DisconnectButtonHandler(Button b){
		this.b = b ;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			Disconnector.disconnect();
		} 
		catch (IOException d) {
			System.err.println("I/O Stream cannot be closed");
		}
	}



}