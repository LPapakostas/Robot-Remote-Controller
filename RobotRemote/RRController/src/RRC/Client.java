package RRC;

import java.io.*;
import java.net.*;

public class Client{ 
	public static InetAddress host;
	public static String ip;
	//public static Start st;
	public static Integer number;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws UnknownHostException, IOException , ConnectException  {
		
		ip = (host.getLocalHost()).getHostAddress();
		new ControllerGUI(new Connector() , new Disconnector());
		//new Start() ; 
	}
}


