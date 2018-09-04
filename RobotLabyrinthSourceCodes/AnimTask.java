import java.util.TimerTask;

































class AnimTask
  extends TimerTask
{
  Animation anim;
  
  AnimTask(Animation anim)
  {
    this.anim = anim;
  }
  
  public void run() {
    anim.animate();
  }
}
