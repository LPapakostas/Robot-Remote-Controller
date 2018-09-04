package RRC;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
































class ConnectButtonHandler
  implements ActionListener
{
  Button b;
  
  ConnectButtonHandler(Button b)
  {
    this.b = b;
  }
  
  public void actionPerformed(ActionEvent e) {
    try {
      
    }
    catch (IOException c) {
      System.out.println("I/O Stream not found");
    }
  }
}
