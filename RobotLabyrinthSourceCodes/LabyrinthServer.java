import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;































































































































































































































































































































































































































class LabyrinthServer
  extends Thread
{
  private boolean stop;
  private Labyrinth labyrinth;
  private int serverPort = 0;
  private ServerSocket server;
  private ThreadGroup serverThreads;
  
  public LabyrinthServer(int port, Labyrinth lab)
  {
    labyrinth = lab;
    serverPort = port;
    serverThreads = new ThreadGroup("Controlers");
    bindPort();
    start();
  }
  
  public void bindPort() {
    try {
      server = new ServerSocket(serverPort);
    } catch (IOException e) {
      System.out.println("Error binding port " + serverPort);
      System.out.println(e.getMessage());
    }
  }
  
  public void freePort()
  {
    try {
      server.close();
    } catch (IOException e) {
      System.out.println("Error while closing server socket. (Port " + serverPort);
      System.out.println(e.getMessage());
    }
  }
  
  public void stopServer()
  {
    int i = 0;
    int n = 0;
    int k = 0;
    n = serverThreads.activeCount();
    Thread[] thr = new Thread[n];
    k = serverThreads.enumerate(thr);
    for (i = 0; i < k; i++) {
      try {
        RemoteRobotControler rrc = (RemoteRobotControler)thr[i];
        rrc.terminate();
      } catch (ArrayStoreException localArrayStoreException) {}catch (ClassCastException localClassCastException) {}
    }
    freePort();
    stop = true;
  }
  
  public void run()
  {
    try {
      server.getInetAddress();RobotLabyrinth.printMsg("Server started! [" + InetAddress.getLocalHost().getHostAddress() + "]");
    } catch (Exception localException) {}
    stop = false;
    while (!stop) {
      try {
        Socket client = server.accept();
        RemoteRobotControler thread = new RemoteRobotControler(serverThreads, client, labyrinth);
        System.out.println("New connection from " + client.getInetAddress().getHostAddress());
        RobotLabyrinth.printMsg("New connection from " + client.getInetAddress().getHostAddress());
      } catch (IOException e) {
        System.out.println("Error accepting connection!");
        System.out.println(e.getMessage());
        stop = true;
      }
    }
  }
}
