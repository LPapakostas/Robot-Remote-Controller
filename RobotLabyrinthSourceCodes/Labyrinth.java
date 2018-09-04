import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Vector;

public class Labyrinth extends java.awt.Canvas implements Animation, java.io.Serializable
{
  private Vector robots;
  private Color[] robotColor;
  private String[] colorNames;
  private int numOfBlocks;
  private int blockSize;
  private int wallSize;
  private LabyrinthBlock[][] blocks;
  private boolean drawGrid;
  private java.awt.Image back;
  private Animator anim;
  private int colorCount;
  
  public Labyrinth(int numOfBlocks, int blockSize)
  {
    this(numOfBlocks, blockSize, true);
  }
  
  public Labyrinth(int numOfBlocks, int blockSize, boolean drawGrid)
  {
    this.drawGrid = drawGrid;
    initialize(numOfBlocks, blockSize, 2);
    robots = new Vector();
    anim = new Animator(this, 100L);
    back = null;
    robotColor = new Color[8];
    colorNames = new String[8];
    robotColor[0] = Color.blue;colorNames[0] = new String("BLUE");
    robotColor[1] = Color.green;colorNames[1] = new String("GREEN");
    robotColor[2] = Color.yellow;colorNames[2] = new String("YELLOW");
    robotColor[3] = Color.cyan;colorNames[3] = new String("CYAN");
    robotColor[4] = Color.magenta;colorNames[4] = new String("MAGENTA");
    robotColor[5] = Color.orange;colorNames[5] = new String("ORANGE");
    robotColor[6] = Color.pink;colorNames[6] = new String("PINK");
    robotColor[7] = Color.gray;colorNames[7] = new String("GRAY");
    colorCount = 0;
  }
  
  public void save(String fname) throws java.io.IOException {
    java.io.FileOutputStream ostream = new java.io.FileOutputStream(fname);
    java.io.ObjectOutputStream p = new java.io.ObjectOutputStream(ostream);
    p.writeObject(new Integer(numOfBlocks));
    for (int i = 0; i < numOfBlocks; i++) {
      for (int j = 0; j < numOfBlocks; j++) {
        p.writeObject(blocks[i][j]);
      }
    }
    p.close();
  }
  
  public void load(String fname) throws java.io.IOException, ClassNotFoundException, LabyrinthInUseException { if (robots.size() > 0) throw new LabyrinthInUseException();
    java.io.FileInputStream istream = new java.io.FileInputStream(fname);
    java.io.ObjectInputStream p = new java.io.ObjectInputStream(istream);
    numOfBlocks = ((Integer)p.readObject()).intValue();
    blocks = new LabyrinthBlock[numOfBlocks][numOfBlocks];
    for (int i = 0; i < numOfBlocks; i++) {
      for (int j = 0; j < numOfBlocks; j++) {
        blocks[i][j] = ((LabyrinthBlock)p.readObject());
      }
    }
    p.close();
    
    reshape(getX(), getY(), numOfBlocks * blockSize, numOfBlocks * blockSize);
    back = null;
    repaint();
  }
  
  public int getNumOfBlocks() { return numOfBlocks; }
  public int getBlockSize() { return blockSize; }
  public LabyrinthBlock[][] getBlocks() { return blocks; }
  
  public void addRobot(Robot rob) {
    rob.setRobotColor(robotColor[(colorCount % 8)]);
    colorCount += 1;
    robots.add(rob);
    RobotLabyrinth.printMsg("New robot entered: " + rob.getRobotName() + " [" + colorNames[((colorCount - 1) % 8)] + "]");
  }
  
  public void removeRobot(Robot rob) { robots.remove(rob);
    RobotLabyrinth.printMsg("Robot " + rob.getRobotName() + " left");
  }
  
  public void initialize(int numOfBlocks, int blockSize, int wallSize) {
    this.numOfBlocks = numOfBlocks;
    this.blockSize = blockSize;
    this.wallSize = wallSize;
    blocks = new LabyrinthBlock[numOfBlocks][numOfBlocks];
    for (int x = 0; x < numOfBlocks; x++) {
      for (int y = 0; y < numOfBlocks; y++) {
        blocks[x][y] = new LabyrinthBlock(true, true, true, true);
      }
    }
    blocks[(numOfBlocks - 1)][(numOfBlocks - 1)].setExit();
    buildLabyrinth();
    
    setSize(numOfBlocks * blockSize, numOfBlocks * blockSize);
    setBackground(Color.white);
  }
  
  public void paintLabyrinth(Graphics g) {
    for (int x = 0; x < numOfBlocks; x++) {
      for (int y = 0; y < numOfBlocks; y++) {
        if (blocks[x][y].solid) {
          g.setColor(Color.black);
          g.fillRect(x * blockSize, y * blockSize, blockSize, blockSize);
        } else {
          if (drawGrid) {
            g.setColor(Color.lightGray);
            g.drawRect(x * blockSize, y * blockSize, blockSize, blockSize);
          }
          g.setColor(Color.black);
          if (blocks[x][y].wallUp) g.fillRect(x * blockSize, y * blockSize, blockSize, wallSize);
          if (blocks[x][y].wallDown) g.fillRect(x * blockSize, (y + 1) * blockSize - wallSize + 1, blockSize, wallSize);
          if (blocks[x][y].wallLeft) g.fillRect(x * blockSize, y * blockSize, wallSize, blockSize);
          if (blocks[x][y].wallRight) g.fillRect((x + 1) * blockSize - wallSize + 1, y * blockSize, wallSize, blockSize);
        }
        if (blocks[x][y].exit) {
          g.setColor(Color.darkGray);
          g.fillRect(x * blockSize + 3, y * blockSize + 3, blockSize - 6, blockSize - 6);
        }
      }
    }
  }
  
  public void paint(Graphics g) {
    if (back == null)
    {
      back = createImage(getWidth(), getHeight());
      paintLabyrinth(back.getGraphics());
    }
    java.awt.Image tmpimg = createImage(getWidth(), getHeight());
    Graphics tmpgr = tmpimg.getGraphics();
    tmpgr.drawImage(back, 0, 0, getWidth(), getHeight(), this);
    
    for (int i = 0; i < robots.size(); i++) {
      Robot r = (Robot)robots.get(i);
      Rectangle rect = r.getBorder();
      tmpgr.setColor(r.getRobotColor());
      

      tmpgr.fillRect(x + wallSize, y + wallSize, width - 2 * wallSize + 1, height - 2 * wallSize + 1);
      switch (r.getDirection()) {
      case 0: 
        tmpgr.setColor(Color.red);
        tmpgr.fillRect(x + wallSize + 1 + 1, y + wallSize + 1 + 1, width - 2 * wallSize - 2 - 1, 3);
        break;
      case 1: 
        tmpgr.setColor(Color.red);
        tmpgr.fillRect(x + wallSize + 1 + 1, y + wallSize + 1 + 1, 3, width - 2 * wallSize - 2 - 1);
        break;
      case 2: 
        tmpgr.setColor(Color.red);
        tmpgr.fillRect(x + wallSize + 1 + 1, y + height - wallSize - 4, width - 2 * wallSize - 2 - 1, 3);
        break;
      case 3: 
        tmpgr.setColor(Color.red);
        tmpgr.fillRect(x + width - wallSize - 4, y + wallSize + 1 + 1, 3, width - 2 * wallSize - 2 - 1);
      }
      
    }
    g.drawImage(tmpimg, 0, 0, getWidth(), getHeight(), this);
  }
  







  public void update(Graphics g)
  {
    paint(g);
  }
  
  public void buildLabyrinth()
  {
    java.util.Stack stack = new java.util.Stack();
    Random rnd = new Random(System.currentTimeMillis());
    int totalCells = numOfBlocks * numOfBlocks;
    int x = rnd.nextInt(numOfBlocks);
    int y = rnd.nextInt(numOfBlocks);
    int visitedCells = 1;
    while (visitedCells < totalCells) {
      Vector neighbors = new Vector(4);
      if ((y > 0) && (blocks[x][(y - 1)].solid)) neighbors.add(new Position(x, y - 1));
      if ((y < numOfBlocks - 1) && (blocks[x][(y + 1)].solid)) neighbors.add(new Position(x, y + 1));
      if ((x > 0) && (blocks[(x - 1)][y].solid)) neighbors.add(new Position(x - 1, y));
      if ((x < numOfBlocks - 1) && (blocks[(x + 1)][y].solid)) { neighbors.add(new Position(x + 1, y));
      }
      int size;
      if ((size = neighbors.size()) > 0) {
        Position pos = (Position)neighbors.get(rnd.nextInt(size));
        if (x == x) {
          if (y == y - 1) {
            blocks[x][y].breakWall(0);
            blocks[x][(y - 1)].breakWall(2);
          } else {
            blocks[x][y].breakWall(2);
            blocks[x][(y + 1)].breakWall(0);
          }
        }
        else if (x == x - 1) {
          blocks[x][y].breakWall(1);
          blocks[(x - 1)][y].breakWall(3);
        } else {
          blocks[x][y].breakWall(3);
          blocks[(x + 1)][y].breakWall(1);
        }
        
        stack.push(new Position(x, y));
        x = x;
        y = y;
        visitedCells++;
      } else {
        Position pos = (Position)stack.pop();
        x = x;
        y = y;
      }
    }
  }
  
  public void animate()
  {
    repaint();
  }
  
  public void start() {
    if (anim != null) anim.start();
  }
  
  public void stop() {
    if (anim != null) anim.stop();
  }
}
