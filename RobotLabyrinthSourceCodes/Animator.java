import java.util.Timer;





public class Animator
{
  private Timer timer;
  long period;
  Animation anim;
  boolean running;
  
  Animator(Animation anim, long period)
  {
    this.period = period;
    this.anim = anim;
    
    running = false;
  }
  
  public synchronized void start() {
    if (!running) {
      timer = new Timer();
      timer.schedule(new AnimTask(anim), 0L, period);
      running = true;
    }
  }
  
  public synchronized void stop() {
    if (running) {
      timer.cancel();
      running = false;
    }
  }
}
