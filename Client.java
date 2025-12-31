import java.io.* ;
import java.net.* ;
import java.util.Scanner;


public class Client {

	public static void printGrid(int id , int[][] grid ){

		for( int r = 0 ; r < 5 ; r++ ){
			for( int c = 0 ; c < 5 ; c++ ){
				if( grid[r][c] == 0 ){
					System.out.print(". " ) ;
				}else if( grid[r][c] == 1){
					System.out.print("O " ) ;
				}else {
					System.out.print("X " ) ;
				}

			}
			System.out.println() ;


		}

	}

	public static int[] playMove(){
		int x =-1 ;
		int y =-1 ;
		Scanner scanner = new Scanner(System.in) ;

		System.out.println("Entrer la position x :" ) ;
		x = scanner.nextInt() ;

		System.out.println("Entrer la position y :" ) ;
		y = scanner.nextInt() ;

		scanner.close() ;

		return new int[]{x,y} ;
	}

	public static void main( String[] args ){
		Socket socket ;
		ObjectOutputStream oos ;
		ObjectInputStream ois ;
		Scanner scanner = new Scanner(System.in) ;
		int port = 5010 ;
		String host = "localhost" ;
		int playerId = 0 ;
		int[][] grid = new int[5][5] ;
		Message  msg = new Message();
		Move move ;
		MoveResult result = MoveResult.PLAY ;


		try {
			System.out.println("Connexion en cours") ;
			socket = new Socket( host , port ) ;
			System.out.println("Connecté au serveur") ;
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());


			msg = (Message)  ois.readObject() ;

			playerId = msg.getId() ;

			System.out.println("Your id is "+ playerId) ;

			// l , l , w , l ... w , l ... w , l
			while(result == MoveResult.PLAY){

				printGrid(playerId ,grid);

				msg = (Message)  ois.readObject() ;

				if( msg.getStatus() == MoveResult.PLAY && msg.getId() == playerId && msg.getMessageType() == 1){
					int x =-1 ;
					int y =-1 ;

					System.out.println("Entrer la position x :" ) ;
					x = scanner.nextInt() ;

					System.out.println("Entrer la position y :" ) ;
					y = scanner.nextInt() ;



					move = new Move(playerId, x, y) ;
					oos.writeObject(move) ;
				}else if( msg.getStatus() == MoveResult.PLAY && msg.getId() == playerId && msg.getMessageType() == 2){
					System.out.println("you've played [x : "+ msg.getX() + ", y : "+ msg.getY()+" ]" ) ;
					grid[msg.getX()][msg.getY()] = msg.getId() ;

				}else if( msg.getStatus() == MoveResult.PLAY && msg.getId() != playerId && msg.getMessageType() == 2){
					//int oponent = playerId == 1 ? 2 : 1 ;
					System.out.println("the oponent played [x : "+ msg.getX() + ", y : "+ msg.getY()+" ]" ) ;
					grid[msg.getX()][msg.getY()] = msg.getId() ;
				}else if( msg.getStatus() == MoveResult.INVALID && msg.getId() == playerId ){
					result = MoveResult.INVALID ;
					System.out.println("You lose !" ) ;
				}else if( msg.getStatus() == MoveResult.WON && msg.getId() != playerId ){
					grid[msg.getX()][msg.getY()] = msg.getId() ;
					result = MoveResult.WON ;
					System.out.println("You lose !" ) ;
				}
				else if( msg.getStatus() == MoveResult.WON && msg.getId() == playerId ){
					grid[msg.getX()][msg.getY()] = msg.getId() ;
					result = MoveResult.WON ;
					System.out.println("You win !" ) ;
				}else if( msg.getStatus() == MoveResult.INVALID && msg.getId() != playerId ){
					result = MoveResult.INVALID ;
					System.out.println("You win !" ) ;
				}else{
					grid[msg.getX()][msg.getY()] = msg.getId() ;
					result = MoveResult.DRAW ;
					System.out.println("Draw !" ) ;

				}

			}

			printGrid(playerId ,grid);
			socket.close() ;
			oos.close() ;
			ois.close() ;
			scanner.close() ;


		}catch( IOException e){

		}catch( ClassNotFoundException e){

		}

	}
}
