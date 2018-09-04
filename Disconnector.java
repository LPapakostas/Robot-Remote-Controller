package RRC;

import java.awt.Button;
import java.awt.TextField;

public class Disconnector
{
  public Disconnector() {}
  
  public static void disconnect() throws java.io.IOException
  {
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
