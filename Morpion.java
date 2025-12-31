





public class Morpion {
	private int id1 ;
	private int id2 ;
	private int dimension = 5 ;
	private int[][] grid   ;


	public Morpion( int id1 , int id2 , int dimension ){
		this.id1 = id1 ;
		this.id2 = id2 ;
		this.dimension = dimension ;
		grid = new int[dimension][dimension] ;
	}

	public MoveResult validateMove( int id , int x, int y ){
		if( isInvalidPosition( id , x , y )){
			return MoveResult.INVALID ;
		}

		grid[x][y] = id ;
		System.out.println("ici " +grid[x][y]) ;

		if( isWinner(id, x, y) ) {
			return MoveResult.WON ;

		}
		if( isDraw( id , x , y ) ){
			return MoveResult.DRAW ;
		}
		return MoveResult.PLAY;

	}

	private boolean isHorizontalWin( int id , int x , int y ){
		return ( dfs( id , grid , x , y , new int[]{0,-1} ) == dimension ) ||
			( dfs( id , grid , x , y , new int[]{0,1} ) == dimension ) ;

	}

	private boolean isVerticalWin( int id , int x , int y ){
		return ( dfs( id , grid , x , y , new int[]{-1,0} ) == dimension ) ||
			( dfs( id , grid , x , y , new int[]{1,0} ) == dimension ) ;

	}

	private boolean isDiagonalWin( int id , int x , int y){
		return ( dfs( id , grid , x , y , new int[]{-1,-1} ) == dimension ) ||
			( dfs( id , grid , x , y , new int[]{-1,1} ) == dimension ) || 
			( dfs( id , grid , x , y , new int[]{1,-1} ) == dimension ) || 
			( dfs( id , grid , x , y , new int[]{1,1} ) == dimension ) ;
	}


	private int  dfs( int id , int[][] grid , int x , int y , int[] direction ){
		if( isInvalidPosition( id , x , y ) ){
			return 0 ;
		}
		return 1 + dfs(  id , grid , x+direction[0] , y+ direction[1] , direction ) ;
	}


	private boolean isWinner( int id , int x , int y ){
		return isHorizontalWin(id, x, y) || isVerticalWin(id, x, y) || isDiagonalWin(id, x, y) ;
	}

	private boolean isDraw( int id , int x , int y ){
		boolean foundZero = false ;

		for( int r=0 ; r < dimension ; r++ ){
			for( int c=0 ; c < dimension ; c++ ){
				if( grid[x][y] == 0 ){
					foundZero = true ;
				}
			}
		}
		return foundZero == true ;

	}

	private boolean isInvalidPosition(int id , int x , int y ){
		if( (x < 0 || x >= dimension ) || (y < 0 || y >= dimension  ) ){
			return true ;

		}
		if(  grid[x][y] != 0  ){
			return true ;
		}
		
		return false ;
	}

}
