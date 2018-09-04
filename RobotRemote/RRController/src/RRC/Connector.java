package RRC;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector  {
	public static PrintWriter out;
	public static DataInputStream in;
	public static Socket socket;
	
	public static void connect() throws UnknownHostException, IOException , ConnectException   {
		
		try {
			if(ControllerGUI.portdisplay.getText() == "") throw new NumberFormatException();
			int port = Integer.parseInt(ControllerGUI.portdisplay.getText());
			socket = new Socket(Client.ip,port);
			if (socket == null) throw new ConnectException();
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new DataInputStream( socket.getInputStream());
			
			ControllerGUI.insert.setEnabled(true);
			ControllerGUI.connect.setEnabled(false);
			ControllerGUI.disconnect.setEnabled(true);
		}
		catch(NumberFormatException e) {
			System.err.println("Give Specific Port");
		}
		catch(ConnectException c) {
			System.err.println("Server not Started");
		}
	}

	
}

class ConnectButtonHandler implements ActionListener {
	Button b;
	
	ConnectButtonHandler(Button b){
		this.b = b;
	}
	
	public void actionPerformed(ActionEvent e) {
				try {
					Connector.connect();
				} 
				catch (IOException c) {
					System.out.println("I/O Stream not found");
				}
		}


}
	