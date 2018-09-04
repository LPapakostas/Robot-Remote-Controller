package RRC;

public enum RobotControl {
  INSERT('\021', "INSERT"),  MOVE('\002', "MOVE"), 
  LEFT('\004', "LEFT"),  RIGHT('\005', "RIGHT"), 
  STOP('\001', "STOP"),  SETMODE('\f', "MODE"), 
  HOME('\020', "GO HOME"),  DESTROY('\013', "ROBOT DESTROY"), 
  STEP('\003', "STEP"),  STATE('\t', "STATE");
  
  char code;
  String name;
  
  private RobotControl(char code, String name) {
    this.name = name;
    this.code = code;
  }
  
  public char getCode() {
    return code;
  }
}
