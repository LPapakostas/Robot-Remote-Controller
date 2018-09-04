package RRC;

import java.net.InetAddress;

public class Client
{
  public static InetAddress host;
  public static String ip;
  public static Integer number;
  
  public Client() {}
  
  public static void main(String[] args) throws java.net.UnknownHostException, java.io.IOException, java.net.ConnectException
  {
    ip = InetAddress.getLocalHost().getHostAddress();
    new ControllerGUI(new Connector(), new Disconnector());
  }
}
