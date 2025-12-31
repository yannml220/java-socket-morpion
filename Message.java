import java.io.Serializable;

public class Message implements Serializable{

	private int id ;
	private MoveResult status ;
	private int x ;
	private int y ;
	private int messageType ;

	public Message(){} 
	public Message(int id ,MoveResult status, int x , int y,int messageType){
		this.id = id ;
		this.status = status ;
		this.x = x ;
		this.y = y ;
		this.messageType = messageType ;
	}


	public int getId(){
		return id ;

	}

	public int getX(){
		return x ;
	}

	public int getY(){
		return y ;

	}

	public MoveResult getStatus(){
		return status ;

	}

	public int getMessageType(){
		return messageType ;

	}

	@Override 
	public String toString(){
		return "[ "+ id + " , x = " + x + " ,y = "+ y +", status : "+ status+"  ]" ;

	}
}



