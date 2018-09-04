import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;































































































































































































































































class RobotControlerProtocol
{
  private DataOutputStream out = null;
  private DataInputStream in = null;
  private BufferedReader bufread = null;
  
  public static final byte REQ_ERROR = -2;
  
  public static final byte REQ_UNKNOWN = -1;
  
  public static final byte REQ_STOP = 1;
  public static final byte REQ_MOVE = 2;
  public static final byte REQ_STEP = 3;
  public static final byte REQ_TURNLEFT = 4;
  public static final byte REQ_TURNRIGHT = 5;
  public static final byte REQ_RESET = 6;
  public static final byte REQ_QUIT = 7;
  public static final byte REQ_SENSE = 8;
  public static final byte REQ_STATE = 9;
  public static final byte REQ_ROBOTDESTROY = 11;
  public static final byte REQ_SETMODE = 12;
  public static final byte REQ_LOADLABYRINTH = 14;
  public static final byte REQ_EXITFOUND = 15;
  public static final byte REQ_GOHOME = 16;
  public static final byte REQ_ROBOTINIT = 17;
  public static final byte RSP_ERROR = -1;
  public static final byte RSP_OK = 1;
  public static final byte RSP_SENSE = 2;
  public static final byte RSP_STATE = 3;
  public static final byte RSP_EXITFOUND = 4;
  
  RobotControlerProtocol(Socket sock)
  {
    try
    {
      out = new DataOutputStream(sock.getOutputStream());
      in = new DataInputStream(sock.getInputStream());
      bufread = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    } catch (IOException e) {
      System.out.println("RobotControlProtocol Constructor: " + e.toString());
    }
  }
  
  public byte getRequest(Parameter param) {
    byte type = -1;
    try {
      type = in.readByte();
      switch (type) {
      case 17: 
        String name = bufread.readLine();
        param = new Object[1];
        param[0] = new String(name);
        break;
      case 11: 
        param = null;
        break;
      case 1: 
        param = null;
        break;
      case 2: 
        param = null;
        break;
      


      case 3: 
        param = null;
        break;
      case 4: 
        param = null;
        break;
      case 5: 
        param = null;
        break;
      case 16: 
        param = null;
        break;
      case 6: 
        param = null;
        break;
      case 7: 
        param = null;
        break;
      case 8: 
        param = null;
        break;
      case 9: 
        param = null;
        break;
      case 15: 
        param = null;
        break;
      case 12: 
        byte b = in.readByte();
        param = new Object[1];
        param[0] = new Byte(b);
        break;
      case 14: 
        String fname = bufread.readLine();
        param = new Object[1];
        param[0] = new String(fname);
        break;
      case 10: case 13: default: 
        type = -1;
        param = null;
      }
      
      type = type;
    }
    catch (IOException e) {
      System.out.println("RobotControlProtocol getRequest: " + e.toString());
      type = -2;
    }
    
    return type;
  }
  
  public int sendResponse(Parameter param) {
    try {
      switch (type) {
      case 1: 
        out.writeByte(1);
        break;
      case -1: 
        out.writeByte(-1);
        break;
      case 2: 
        if ((param != null) && 
          (param.length == 3)) {
          out.writeByte(2);
          for (int i = 0; i < 3; i++) {
            Sensor s = (Sensor)param[i];
            out.writeBoolean(wall);
            out.writeInt(distance);
          }
        }
        break;
      case 3:  if ((goto 269) && 
        
          (param != null) && 
          (param.length == 1)) {
          out.writeByte(3);
          out.writeByte(((Byte)param[0]).byteValue());
        }
        
        break;
      case 4: 
        if ((param != null) && 
          (param.length == 1)) {
          out.writeByte(4);
          out.writeBoolean(((Boolean)param[0]).booleanValue());
        }
        
        break;
      case 0: default: 
        out.writeByte(-1);
      }
    }
    catch (IOException e) {
      System.out.println("RobotControlProtocol sendResponse: " + e.toString());
      return -1;
    }
    return 0;
  }
}
