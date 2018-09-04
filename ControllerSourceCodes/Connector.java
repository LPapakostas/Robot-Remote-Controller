package RRC;

import java.awt.TextField;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;

public class Connector
{
  public static java.io.PrintWriter out;
  public static DataInputStream in;
  public static Socket socket;
  
  public Connector() {}
  
  public static void connect() throws java.net.UnknownHostException, java.io.IOException, ConnectException
  {
    try
    {
      if (ControllerGUI.portdisplay.getText() == "") throw new NumberFormatException();
      int port = Integer.parseInt(ControllerGUI.portdisplay.getText());
      socket = new Socket(Client.ip, port);
      if (socket == null) throw new ConnectException();
      out = new java.io.PrintWriter(socket.getOutputStream(), true);
      in = new DataInputStream(socket.getInputStream());
      
      ControllerGUI.insert.setEnabled(true);
      ControllerGUI.connect.setEnabled(false);
      ControllerGUI.disconnect.setEnabled(true);
    }
    catch (NumberFormatException e) {
      System.err.println("Give Specific Port");
    }
    catch (ConnectException c) {
      System.err.println("Server not Started");
    }
  }
}
