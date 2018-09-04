public class Sensor
{
  public boolean wall;
  public int distance;
  
  public Sensor() {
    this(false, 0);
  }
  
  public Sensor(boolean wall, int dist) {
    this.wall = wall;
    distance = dist;
  }
  
  public void set(boolean wall, int dist) {
    this.wall = wall;
    distance = dist;
  }
}
