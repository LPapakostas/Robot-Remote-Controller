package RRC;

public enum States {
	STOPED((int)1 , "STOPED") ,
	MOVING((int)2 , "MOVING") ,
	STEPING((int)3, "STEPING") ;
	
	public int code ;
	public String name;
	
	States(int code , String name){
		this.code = code ;
		this.name = name;
	}
}