import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;













public class RemoteRobotControler
  extends Thread
{
  private boolean stop;
  private boolean auto;
  private AutoRobotControler autoControl;
  private Socket sock;
  private Robot robot;
  private RobotControlerProtocol protocol;
  private Labyrinth labyrinth;
  
  public RemoteRobotControler(ThreadGroup group, Socket sock, Labyrinth lab)
  {
    super(group, sock.getInetAddress().getHostAddress());
    auto = false;
    robot = null;
    this.sock = sock;
    labyrinth = lab;
    protocol = new RobotControlerProtocol(sock);
    
    start();
  }
  
  public void terminate() {
    if (stop) {
      if (auto) autoControl.setStop(true);
    } else {
      if (auto) autoControl.setStop(true);
      closeConnection();
    }
  }
  
  public void closeConnection() {
    stop = true;
    try {
      System.out.println("Connection with " + sock.getInetAddress().getHostAddress() + " closed");
      if (sock != null) sock.close();
      if (robot != null) {
        if (auto) {
          autoControl.join();
        }
        robot.destroyRobot();
      } else { System.out.println("robot=null");
      }
    } catch (IOException e) {
      System.out.println("Error while closing client socket. (" + robot.getName() + ")");
      System.out.println(e.getMessage());
    } catch (InterruptedException e) {
      System.out.println("Error while joining auto thread. (" + robot.getName() + ")");
      System.out.println(e.getMessage());
    }
  }
  
  public void run()
  {
    stop = false;
    
    Parameter reqParam = new Parameter();
    Parameter rspParam = new Parameter();
    Boolean sendRespFlag = Boolean.valueOf(true);
    
    while (!stop) {
      sendRespFlag = Boolean.valueOf(true);
      byte req = protocol.getRequest(reqParam);
      switch (req) {
      case 12: 
        byte b = ((Byte)param[0]).byteValue();
        if ((b == 1) && (!auto)) {
          autoControl = new AutoRobotControler(robot);
          auto = true;
        } else if ((b == 0) && (auto)) {
          autoControl.setStop(true);
          auto = false;
        }
        type = 1;
        param = null;
        break;
      case 1: 
        if ((robot != null) && (!auto)) {
          robot.halt();
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      case 2: 
        if ((robot != null) && (!auto)) {
          robot.move();
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      
      case 3: 
        if ((robot != null) && (!auto))
        {
          robot.step(8);
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      
      case 4: 
        if ((robot != null) && (!auto)) {
          robot.turnLeft();
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      case 5: 
        if ((robot != null) && (!auto)) {
          robot.turnRight();
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      case 16: 
        if ((robot != null) && (!auto)) {
          robot.home();
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      case 8: 
        if (robot != null) {
          Sensor f = new Sensor();
          Sensor l = new Sensor();
          Sensor r = new Sensor();
          robot.getSensors(f, l, r);
          type = 2;
          param = new Object[3];
          param[0] = f;
          param[1] = l;
          param[2] = r;
        } else {
          type = -1;
          param = null;
        }
        break;
      case 9: 
        if (robot != null) {
          type = 3;
          param = new Object[1];
          param[0] = new Byte(robot.getRobotState());
        } else {
          type = -1;
          param = null;
        }
        break;
      case 15: 
        if (robot != null) {
          type = 4;
          param = new Object[1];
          param[0] = new Boolean(robot.foundExit());
        } else {
          type = -1;
          param = null;
        }
        break;
      case 7: 
        type = 1;
        param = null;
        stop = true;
        break;
      case -2: 
        type = -1;
        param = null;
        stop = true;
        break;
      
      case 17: 
        String name = new String((String)param[0] + "@" + sock.getInetAddress().getHostAddress());
        robot = new Robot(name, 0, 0, labyrinth, RobotLabyrinth.robotSize, RobotLabyrinth.labyrinthBlockSize / RobotLabyrinth.stepsInBlock);
        type = 1;
        param = null;
        



        break;
      case 11: 
        if (robot != null)
        {
          if (!auto) {
            robot.destroyRobot();
            robot = null;
          }
          type = 1;
          param = null;
        } else {
          type = -1;
          param = null;
        }
        break;
      case 14: 
        if (robot == null) {
          String fname = new String((String)param[0]);
          try {
            labyrinth.load(fname);
            RobotLabyrinth.updateGUI();
            type = 1;
            param = null;
          } catch (Exception e) {
            type = -1;
            param = null;
          }
        }
        else {
          type = -1;
          param = null;
        }
        break;
      case -1: case 0: case 6: case 10: case 13: default: 
        sendRespFlag = Boolean.valueOf(false);
      }
      
      if ((req != -1) && 
        (protocol.sendResponse(rspParam) != 0)) stop = true;
    }
    closeConnection();
  }
}
