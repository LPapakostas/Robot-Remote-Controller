package RRC;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
























class DisconnectButtonHandler
  implements ActionListener
{
  Button b;
  
  DisconnectButtonHandler(Button b)
  {
    this.b = b;
  }
  
  public void actionPerformed(ActionEvent e) {
    try {
      
    }
    catch (IOException d) {
      System.err.println("I/O Stream cannot be closed");
    }
  }
}
