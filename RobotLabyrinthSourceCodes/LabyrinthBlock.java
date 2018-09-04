import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LabyrinthBlock implements java.io.Serializable
{
  public boolean solid;
  public boolean wallUp;
  public boolean wallDown;
  public boolean wallLeft;
  public boolean wallRight;
  public boolean exit;
  public static final int UP = 0;
  public static final int LEFT = 1;
  public static final int DOWN = 2;
  public static final int RIGHT = 3;
  
  public LabyrinthBlock()
  {
    this(false, false, false, false);
  }
  
  public LabyrinthBlock(boolean up, boolean down, boolean left, boolean right) {
    exit = false;
    wallUp = up;
    wallDown = down;
    wallLeft = left;
    wallRight = right;
    if ((wallUp) && (wallDown) && (wallLeft) && (wallRight)) solid = true; else
      solid = false;
  }
  
  private void writeObject(ObjectOutputStream out) throws java.io.IOException {
    out.writeBoolean(wallUp);
    out.writeBoolean(wallDown);
    out.writeBoolean(wallLeft);
    out.writeBoolean(wallRight);
    out.writeBoolean(solid);
    out.writeBoolean(exit);
  }
  
  private void readObject(ObjectInputStream in) throws java.io.IOException, ClassNotFoundException { wallUp = in.readBoolean();
    wallDown = in.readBoolean();
    wallLeft = in.readBoolean();
    wallRight = in.readBoolean();
    solid = in.readBoolean();
    exit = in.readBoolean();
  }
  
  public void setExit() { exit = true; }
  public boolean getExit() { return exit; }
  
  public void breakWall(int wall) {
    solid = false;
    if (wall == 0) {
      wallUp = false;
    } else if (wall == 2) {
      wallDown = false;
    } else if (wall == 1) {
      wallLeft = false;
    } else {
      wallRight = false;
    }
  }
}
