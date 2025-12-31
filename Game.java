import java.io.IOException;

public class Game implements Runnable {

	private MoveResult state = MoveResult.PLAY  ;
	private int currentPlayer ;
	private Player player1 ;
	private Player player2 ;
	private Morpion morpion ;
	
	public Game(int dimension ,   Player player1 , Player player2){
		this.state = MoveResult.PLAY ;
		this.currentPlayer = player1.getId() ;
		this.player1 = player1 ;
		this.player2 = player2 ;
		this.morpion = new Morpion(player1.getId() , player2.getId() , dimension ) ;

	}


	public void run(){

		try{

			System.out.println("Game starting ...") ;
			Move move  ;

			// le client est dans un premier en position d' attente 
			

			player1.getOos().writeObject( new Message(player1.getId(),MoveResult.INIT ,-1 ,-1,1)) ;

			player2.getOos().writeObject( new Message(player2.getId(),MoveResult.INIT ,-1 ,-1,1)) ;

			player1.getOos().writeObject( new Message(player1.getId(),MoveResult.PLAY ,-1 ,-1,1)) ;


			while( state == MoveResult.PLAY ){

				if( currentPlayer == player1.getId() ){
					move = (Move ) player1.getOis().readObject() ;
					state = morpion.validateMove(move.getPlayerId(),move.getX() , move.getY() ) ;
					System.out.println(new Message(player1.getId(),state ,move.getX() ,move.getY(),1).toString() );
					player1.getOos().writeObject(new Message(player1.getId(),state ,move.getX() ,move.getY(),2)) ;
					player2.getOos().writeObject(new Message(player1.getId(),state ,move.getX() ,move.getY(),2)) ;
					player2.getOos().writeObject(new Message(player2.getId(),state ,move.getX() ,move.getY(),1)) ;

				}else {
					move = (Move ) player2.getOis().readObject() ;
					state = morpion.validateMove(move.getPlayerId(),move.getX() , move.getY() ) ;
					System.out.println(new Message(player2.getId(),state ,move.getX() ,move.getY(),1).toString() );
					player2.getOos().writeObject(new Message(player2.getId(),state ,move.getX() ,move.getY(),2)) ;
					player1.getOos().writeObject(new Message(player2.getId(),state ,move.getX() ,move.getY(),2)) ;
					player1.getOos().writeObject(new Message(player1.getId(),state ,move.getX() ,move.getY(),1)) ;
				}
				currentPlayer = currentPlayer == player1.getId() ? player2.getId() : player1.getId() ;

			}

		
			player1.getSocket().close();
			player1.getOis().close();
			player1.getOos().close();
			player2.getSocket().close();
			player2.getOis().close();
			player2.getOos().close();

		}catch( IOException e ){

		}catch( ClassNotFoundException e){

		}

	}
}
