import java.io.PrintStream;

public class RobotControler extends Thread {
  private Robot robot;
  private boolean stop;
  
  public RobotControler(Robot robot) {
    this.robot = robot;
  }
  
  public synchronized void setStop(boolean b) { stop = b; }
  public synchronized boolean getStop() { return stop; }
  
  private void logicA() {
    byte state = -1;
    Sensor f = new Sensor();
    Sensor l = new Sensor();
    Sensor r = new Sensor();
    stop = false;
    while ((!robot.foundExit()) && (!stop)) {
      robot.getSensors(f, l, r);
      if (!wall) {
        robot.turnRight();
        robot.step(RobotLabyrinth.stepsInBlock);
      } else if (!wall) {
        robot.step(RobotLabyrinth.stepsInBlock);
      } else {
        robot.turnLeft();
      }
      do {
        try {
          sleep(50L); } catch (Exception e) { System.out.println("Thread Sleep Error: " + e.toString()); }
        state = robot.getRobotState();
      } while (state != 1);
    }
    robot.destroyRobot();
  }
  
  public void run()
  {
    logicA();
  }
}
