package RRC;

public enum States {
  STOPED(1, "STOPED"), 
  MOVING(2, "MOVING"), 
  STEPING(3, "STEPING");
  
  public int code;
  public String name;
  
  private States(int code, String name) {
    this.code = code;
    this.name = name;
  }
}
