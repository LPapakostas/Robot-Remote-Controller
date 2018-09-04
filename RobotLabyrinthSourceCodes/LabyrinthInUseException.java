

public class LabyrinthInUseException
  extends Exception
{
  public LabyrinthInUseException()
  {
    this("Labyrinth in use.");
  }
  




  public LabyrinthInUseException(String msg)
  {
    super(msg);
  }
}
