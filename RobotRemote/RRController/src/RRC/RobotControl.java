package RRC;

public enum RobotControl {
	INSERT((char)17,"INSERT") , MOVE((char)2,"MOVE") ,
	LEFT((char)4,"LEFT") , RIGHT((char)5,"RIGHT") ,
	STOP((char)1,"STOP") , SETMODE((char)12,"MODE") ,
	HOME((char)16,"GO HOME") , DESTROY((char)11,"ROBOT DESTROY") ,
	STEP((char)3,"STEP") , STATE((char)9,"STATE") ;
	
	char code;
	String name;

	RobotControl(char code,String name){
		this.name= name;
		this.code = code;
	}
	
	public char getCode() {
		return code;
	}
	
}

