import java.io.* ;
import java.net.* ;


public class Server {

	public static void main( String[] args ){
		ServerSocket serv =null;
		int port = 5010 ;
		Player p1 ;
		Player p2 ;


		try {
			serv = new ServerSocket(port) ;

			while( true ){

				System.out.println("En attente du 1er joueur ...");

				Socket player1Socket = serv.accept() ;
				System.out.println("Joueur 1 connecté ");

				ObjectOutputStream oos1 = new ObjectOutputStream( player1Socket.getOutputStream()) ;
				ObjectInputStream ois1  = new ObjectInputStream( player1Socket.getInputStream() ) ;
				p1 = new Player( 1 , player1Socket , ois1 , oos1 ) ;


				System.out.println("En attente du 2nd joueur ...");

				Socket player2Socket = serv.accept() ;
				System.out.println("Joueur 2 connecté ");
				ObjectOutputStream oos2 = new ObjectOutputStream( player2Socket.getOutputStream()) ;
				ObjectInputStream ois2  = new ObjectInputStream( player2Socket.getInputStream() ) ;
				p2 = new Player( 2 , player2Socket , ois2 , oos2 ) ;


				Game game = new Game(5 ,p1 , p2 );
				new Thread(game).start() ;

			}


		}catch( IOException  e ){


		}
	}


}
