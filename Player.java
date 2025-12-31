import java.io.* ;
import java.net.* ;

public class Player {
	private int id ;
	private Socket socket ;
	private ObjectInputStream ois ;
	private ObjectOutputStream oos ;


	public Player( int id , Socket socket , ObjectInputStream ois , ObjectOutputStream oos ){
		this.id = id ;
		this.socket = socket ;
		this.ois = ois ;
		this.oos = oos ;
	}

	public int getId(){
		return id ;

	}
	public Socket getSocket(){
		return socket ;

	}

	public ObjectInputStream getOis(){
		return ois ;

	}

	public ObjectOutputStream getOos(){
		return oos ;

	}

}
