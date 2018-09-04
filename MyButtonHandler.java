package RRC;

import java.io.PrintWriter;

public class MyButtonHandler implements java.awt.event.ActionListener
{
  MyButton b;
  
  MyButtonHandler(MyButton b)
  {
    this.b = b;
  }
  
  public void actionPerformed(java.awt.event.ActionEvent e)
  {
    if (e.getSource() == ControllerGUI.insert) {
      Connector.out.println(INSERTcode);
      Connector.out.flush();
      
      ControllerGUI.auto.setEnabled(true);
      ControllerGUI.manual.setEnabled(true);
      ControllerGUI.insert.setEnabled(false);
      ControllerGUI.destroy.setEnabled(true);
      ControllerGUI.goHome.setEnabled(true);
    }
    else if (e.getSource() == ControllerGUI.auto) {
      Connector.out.write(SETMODEcode);
      Connector.out.flush();
      Connector.out.write(1);
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
      Connector.out.write(SETMODEcode);
      Connector.out.flush();
      Connector.out.write(0);
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
    else if (e.getSource() == ControllerGUI.destroy) {
      Connector.out.println(DESTROYcode);
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
    else if (e.getSource() == ControllerGUI.state) {
      Connector.out.println(STATEcode);
      Connector.out.flush();
      try {
        byte[] b = new byte[3];
        Connector.in.read(b);
        if (b[2] == STOPEDcode) { ControllerGUI.statedisplay.setText(STOPEDname);
        } else if (b[2] == MOVINGcode) { ControllerGUI.statedisplay.setText(MOVINGname);
        } else if (b[2] == STEPINGcode) ControllerGUI.statedisplay.setText(STEPINGname); else {
          ControllerGUI.statedisplay.setText("");
        }
      } catch (java.io.IOException state) {
        System.err.println("State cannnot Estimated");
      }
      catch (NullPointerException state_now) {
        System.err.println("Empty input");
      }
    }
    else {
      MyButton b = (MyButton)e.getSource();
      Connector.out.println(rb.getCode());
      Connector.out.flush();
    }
  }
}
