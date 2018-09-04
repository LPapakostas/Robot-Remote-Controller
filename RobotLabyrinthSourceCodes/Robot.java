import java.awt.Color;
import java.awt.Rectangle;
import java.io.PrintStream;
import java.util.Calendar;















public class Robot
  extends Thread
{
  private Calendar timeEnter;
  private Calendar timeExit;
  private boolean stop;
  private boolean exited;
  private Color color;
  private String name;
  private Rectangle border;
  private int size;
  private int homex;
  private int homey;
  private int posx;
  private int posy;
  private int hits;
  private Labyrinth labyrinth;
  private int step;
  private byte direction;
  private byte state;
  private int stepsLeft;
  public static final byte STOPED = 1;
  public static final byte MOVING = 2;
  public static final byte STEPING = 3;
  private LabyrinthBlock centerBlock;
  private LabyrinthBlock upBlock;
  private LabyrinthBlock downBlock;
  private LabyrinthBlock leftBlock;
  private LabyrinthBlock rightBlock;
  private int labx;
  private int laby;
  private int upSensor;
  private int downSensor;
  private int leftSensor;
  private int rightSensor;
  public static final byte DIR_UP = 0;
  public static final byte DIR_LEFT = 1;
  public static final byte DIR_DOWN = 2;
  public static final byte DIR_RIGHT = 3;
  
  public Robot(String name, int x, int y, Labyrinth lab, int size, int step)
  {
    homex = x;
    homey = y;
    posx = (x * lab.getBlockSize() + lab.getBlockSize() / 2);
    posy = (y * lab.getBlockSize() + lab.getBlockSize() / 2);
    

    this.size = size;
    this.step = step;
    this.name = new String(name);
    border = new Rectangle(x - size / 2, y - size / 2, size, size);
    hits = 0;
    labyrinth = lab;
    direction = 3;
    state = 1;
    
    labyrinth.addRobot(this);
    exited = false;
    


    updateBorder();
    updateBlocks();
    updateSensors();
    
    start();
  }
  
  public void destroyRobot() {
    labyrinth.removeRobot(this);
    stop = true;
  }
  

  public String getRobotName() { return new String(name); }
  public void setRobotColor(Color color) { this.color = new Color(color.getRGB()); }
  public Color getRobotColor() { return new Color(color.getRGB()); }
  

  public synchronized boolean foundExit() { return centerBlock.exit; }
  public synchronized byte getDirection() { return direction; }
  

  public synchronized byte getRobotState() { return state; }
  public synchronized Rectangle getBorder() { return new Rectangle(border); }
  
  public synchronized int getHits() { return hits; }
  
  public synchronized void home() {
    posx = (homex * labyrinth.getBlockSize() + labyrinth.getBlockSize() / 2);
    posy = (homey * labyrinth.getBlockSize() + labyrinth.getBlockSize() / 2);
    state = 1;
    direction = 3;
    updateBorder();
    updateBlocks();
    updateSensors();
  }
  
  public synchronized void move() {
    state = 2;
  }
  
  public synchronized void halt() { state = 1; }
  
  public synchronized void step(int steps) {
    if (steps < 0) steps = -steps;
    stepsLeft = steps;
    state = 3;
  }
  
  public synchronized void turnLeft() { if ((this.direction = (byte)(direction + 1)) > 3) direction = 0;
  }
  
  public synchronized void turnRight() { if ((this.direction = (byte)(direction - 1)) < 0) direction = 3;
  }
  
  public synchronized void getSensors(Sensor front, Sensor left, Sensor right)
  {
    int bsize = labyrinth.getBlockSize();
    switch (direction) {case 0:  boolean wall;
      boolean wall;
      if (upSensor < bsize) wall = true; else wall = false;
      front.set(wall, upSensor / step);
      if (leftSensor < bsize) wall = true; else wall = false;
      left.set(wall, leftSensor / step);
      if (rightSensor < bsize) wall = true; else wall = false;
      right.set(wall, rightSensor / step);
      break; case 1:  boolean wall;
      boolean wall;
      if (upSensor < bsize) wall = true; else wall = false;
      right.set(wall, upSensor / step);
      if (leftSensor < bsize) wall = true; else wall = false;
      front.set(wall, leftSensor / step);
      if (downSensor < bsize) wall = true; else wall = false;
      left.set(wall, downSensor / step);
      break; case 2:  boolean wall;
      boolean wall;
      if (downSensor < bsize) wall = true; else wall = false;
      front.set(wall, downSensor / step);
      if (leftSensor < bsize) wall = true; else wall = false;
      right.set(wall, leftSensor / step);
      if (rightSensor < bsize) wall = true; else wall = false;
      left.set(wall, rightSensor / step);
      break; case 3:  boolean wall;
      boolean wall;
      if (upSensor < bsize) wall = true; else wall = false;
      left.set(wall, upSensor / step);
      if (downSensor < bsize) wall = true; else wall = false;
      right.set(wall, downSensor / step);
      if (rightSensor < bsize) wall = true; else wall = false;
      front.set(wall, rightSensor / step);
    }
    
  }
  






  private void moveUp(int dy)
  {
    if (upSensor > 0) {
      if (upSensor < dy) {
        dy = upSensor;
        hits += 1;
      }
      posy -= dy;
      updateBorder();
      updateBlocks();
      updateSensors();
    } else { hits += 1;
    } }
  
  private void moveDown(int dy) { if (downSensor > 0) {
      if (downSensor < dy) {
        dy = downSensor;
        hits += 1;
      }
      posy += dy;
      updateBorder();
      updateBlocks();
      updateSensors();
    } else { hits += 1;
    } }
  
  private void moveLeft(int dx) { if (leftSensor > 0) {
      if (leftSensor < dx) {
        dx = leftSensor;
        hits += 1;
      }
      posx -= dx;
      updateBorder();
      updateBlocks();
      updateSensors();
    } else { hits += 1;
    } }
  
  private void moveRight(int dx) { if (rightSensor > 0) {
      if (rightSensor < dx) {
        dx = rightSensor;
        hits += 1;
      }
      posx += dx;
      updateBorder();
      updateBlocks();
      updateSensors();
    } else { hits += 1;
    } }
  
  private void moveForward(int d) { switch (direction) {
    case 0: 
      moveUp(d);
      break;
    case 1: 
      moveLeft(d);
      break;
    case 2: 
      moveDown(d);
      break;
    case 3: 
      moveRight(d);
    }
    
  }
  
  private void updateBlocks()
  {
    int bsize = labyrinth.getBlockSize();
    int lsize = labyrinth.getNumOfBlocks();
    LabyrinthBlock[][] blocks = labyrinth.getBlocks();
    int x = posx / bsize;
    int y = posy / bsize;
    centerBlock = blocks[x][y];
    
    if ((y > 0) && (posy - size / 2 < y * bsize)) upBlock = blocks[x][(y - 1)]; else {
      upBlock = null;
    }
    if ((y < lsize - 1) && (posy + size / 2 > (y + 1) * bsize)) downBlock = blocks[x][(y + 1)]; else {
      downBlock = null;
    }
    if ((x > 0) && (posx - size / 2 < x * bsize)) leftBlock = blocks[(x - 1)][y]; else {
      leftBlock = null;
    }
    if ((x < lsize - 1) && (posx + size / 2 > (x + 1) * bsize)) rightBlock = blocks[(x + 1)][y]; else {
      rightBlock = null;
    }
    labx = x;
    laby = y;
    
    if ((centerBlock.exit) && (!exited)) {
      timeExit = Calendar.getInstance();
      


      long msec = timeExit.getTimeInMillis() - timeEnter.getTimeInMillis();
      long min = msec / 60000L;
      long sec = msec % 60000L / 1000L;
      

      RobotLabyrinth.printMsg("Robot " + name + " found the EXIT in " + min + "min " + sec + "sec. Having " + getHits() + " hits on the wall.");
      exited = true;
    }
  }
  
  private void updateSensors() {
    int bsize = labyrinth.getBlockSize();
    
    if (centerBlock.wallUp) {
      upSensor = (posy - size / 2 - laby * bsize);
    } else if (leftBlock != null) {
      if (leftBlock.wallUp) upSensor = (posy - size / 2 - laby * bsize);
    } else if (rightBlock != null) {
      if (rightBlock.wallUp) upSensor = (posy - size / 2 - laby * bsize);
    } else { upSensor = bsize;
    }
    if (centerBlock.wallDown) {
      downSensor = ((laby + 1) * bsize - (posy + size / 2));
    } else if (leftBlock != null) {
      if (leftBlock.wallDown) downSensor = ((laby + 1) * bsize - (posy + size / 2));
    } else if (rightBlock != null) {
      if (rightBlock.wallDown) downSensor = ((laby + 1) * bsize - (posy + size / 2));
    } else { downSensor = bsize;
    }
    if (centerBlock.wallLeft) {
      leftSensor = (posx - size / 2 - labx * bsize);
    } else if (upBlock != null) {
      if (upBlock.wallLeft) leftSensor = (posx - size / 2 - labx * bsize);
    } else if (downBlock != null) {
      if (downBlock.wallLeft) leftSensor = (posx - size / 2 - labx * bsize);
    } else { leftSensor = bsize;
    }
    if (centerBlock.wallRight) {
      rightSensor = ((labx + 1) * bsize - (posx + size / 2));
    } else if (upBlock != null) {
      if (upBlock.wallRight) rightSensor = ((labx + 1) * bsize - (posx + size / 2));
    } else if (downBlock != null) {
      if (downBlock.wallRight) rightSensor = ((labx + 1) * bsize - (posx + size / 2));
    } else { rightSensor = bsize;
    }
    
    if ((leftBlock != null) || (rightBlock != null)) {
      downSensor = ((laby + 1) * bsize - (posy + size / 2));
      upSensor = (posy - size / 2 - laby * bsize);
    }
    if ((upBlock != null) || (downBlock != null)) {
      rightSensor = ((labx + 1) * bsize - (posx + size / 2));
      leftSensor = (posx - size / 2 - labx * bsize);
    }
  }
  
  private void updateBorder() {
    border.x = (posx - size / 2);
    border.y = (posy - size / 2);
    border.width = size;
    border.height = size;
  }
  
  private synchronized void next()
  {
    switch (state) {
    case 2: 
      moveForward(step);
      break;
    case 3: 
      if (stepsLeft > 0) {
        moveForward(step);
        stepsLeft -= 1;
      } else { state = 1; }
      break;
    }
    
  }
  
  public void run()
  {
    timeEnter = Calendar.getInstance();
    stop = false;
    while (!stop) {
      try { sleep(100L); } catch (Exception e) { System.out.println("Thread Sleep Error: " + e.toString()); }
      next();
    }
  }
}
