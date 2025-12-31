import java.io.Serializable;

public class Move implements Serializable {
	private int playerId ;
	private int x ;
	private int y ;

	public Move( int playerId , int x, int y ){
		this.playerId= playerId;
		this.x = x ;
		this.y = y ;

	}

	public int getPlayerId(){
		return playerId ;

	}

	public int getX(){
		return x ;

	}

	public int getY(){
		return y ;

	}

	@Override 
	public String toString(){
		return "[ "+ playerId + " , x = " + x + " ,y = "+ y + " ]" ;

	}
}
