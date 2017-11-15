package stark.thedrake.game;

import stark.thedrake.media.BoardMedia;

import java.util.Iterator;

public class Board implements Iterable<Tile>
{
    private int dimension ;
    private Tile[][] tiles ;
    private CapturedTroops capturedTroops ;

  //constructor with given dimension and array of tiles
    public Board ( int dimension , Tile... tiles)
    {
        this.dimension = dimension ;
        this.tiles = new Tile [dimension][dimension] ;
        this.capturedTroops = new CapturedTroops() ;
     //fill the board with empty tiles first
        for ( int i = 0 ; i < dimension ; i++) 
        {
            for ( int j = 0 ; j < dimension ; j++)
            {
                TilePosition pos = new TilePosition(i, j);
                EmptyTile empty = new EmptyTile (pos);
                this.tiles[i][j] = empty;
            } 
        }
     //copy all of the previous tiles to a new board
        for ( Tile tile : tiles)
        {
            this.tiles[tile.position().i][tile.position().j] = tile ;
        }
    }
//--------------------------------------------------------------------------------------------------
    public Board(int dimension, CapturedTroops captured, Tile... tiles)
    {
        this ( dimension, tiles );
        this.capturedTroops = captured ;
    }
//--------------------------------------------------------------------------------------------------
    public Board(Board board , CapturedTroops captured)
    {
        this.dimension = board.dimension;
        this.tiles = board.tiles ;
        this.capturedTroops = captured;
    }
//--------------------------------------------------------------------------------------------------
    public int dimension()
    {
        return dimension ;
    }
//--------------------------------------------------------------------------------------------------
  //returns a Tile at given position
  // if the postion is out of range throws IllegalArgumentException()
    public Tile tileAt( TilePosition position )
    {
         if ( ! contains(position) )
            throw new IllegalArgumentException() ;
        
         return tiles[position.i][position.j] ;
    }
//--------------------------------------------------------------------------------------------------
  //does the board contains Tile with given postion?
    public boolean contains ( TilePosition... positions )
    {
       for ( TilePosition pos : positions) 
       {
           if ( pos.i >= dimension || pos.j >= dimension || pos.i < 0 || pos.j < 0 )                 
            return false ;
       }

        return true ;
    }
//--------------------------------------------------------------------------------------------------
  //creates a new Board with given tiles
    public Board withTiles(Tile... tiles)
    {
       Tile [] newTiles = new Tile[dimension()*dimension() + tiles.length] ;
       int counter = 0;
       for ( int i = 0 ; i < dimension() ; i++)
       {    
         for ( int j = 0 ; j < dimension() ; j++)
         {
           newTiles[counter] = this.tiles[i][j] ;
           counter++;
         }
       }  
       
        for ( Tile tile : tiles)
        {
            newTiles[counter] = tile ;
            counter++;
        }   
    
        return new Board(dimension , capturedTroops, newTiles);
    }
//--------------------------------------------------------------------------------------------------
  //creates new Board with given tiles and captured troops
    public Board withCaptureAndTiles(TroopInfo info, PlayingSide side, Tile... tiles)
    {
        Board newBoard = this.withTiles(tiles);
        return new Board(newBoard, capturedTroops.withTroop(side, info));
    }
//--------------------------------------------------------------------------------------------------
    public CapturedTroops captured()
    {
        return capturedTroops;
    }
//--------------------------------------------------------------------------------------------------
  //can i take a troop from given position?
  // is position valid and is there a troop?
    public boolean canTakeFrom(TilePosition origin)
    {
        if ( ! contains( origin ) )
            return false ;

        return tileAt(origin).hasTroop() ;
    }
//--------------------------------------------------------------------------------------------------
  //can i place troop to a given position?
  //is position valid and is the position empty?
    public boolean canPlaceTo(Troop troop, TilePosition target)
    {
        if ( ! contains( target ) )
            return false ;
        return tileAt(target).acceptsTroop( troop ) ;
    }
//--------------------------------------------------------------------------------------------------
  //can given troop capture a troop at given a position
  //is position valid, is there some troop and is the troop from the opposite side?
    public boolean canCaptureOn(Troop troop, TilePosition target)
    {
        if ( ! canTakeFrom(target) )
            return false ;
        if ( troop.side() == tileAt(target).troop().side()  )
            return false ;

        return true ;
    }
//--------------------------------------------------------------------------------------------------
  //can i take a troop from origin and place it to target?
    public boolean canStepOnly(TilePosition origin, TilePosition target)
    {
        if ( ! canTakeFrom( origin )  || ! canPlaceTo ( tileAt(origin).troop() , target ) )
            return false ;

        return true ;
    }
//--------------------------------------------------------------------------------------------------
  //can troop at origin capture troop at target, without moving?
    public boolean canCaptureOnly(TilePosition origin, TilePosition target)
    {
        if ( ! canTakeFrom( origin ) || ! canCaptureOn( tileAt(origin).troop() , target ) )
            return false ;
        return true ;
    }
//--------------------------------------------------------------------------------------------------
  //can troop at origin capture troopt at target?
    public boolean canStepAndCapture(TilePosition origin, TilePosition target)
    {
        if ( ! canTakeFrom( origin ) || ! canCaptureOn( tileAt(origin).troop() , target ) )
            return false ;
        return true ;
    }
//--------------------------------------------------------------------------------------------------
  //returns new Board, origin becomes empty, target becomes origin --  moving the troop
  //after every step troop is required to flipe -- flipped()
    public Board stepOnly(TilePosition origin, TilePosition target)
    {
        Troop troop =  tileAt(origin).troop() ;
        return withTiles( new EmptyTile( origin ), new TroopTile( target , troop.flipped() ) );
    }
//--------------------------------------------------------------------------------------------------
  //returns new Board, origin becomes empty, target becomes origin -- moving, capturing, flipping
  //adding a capture troop - target
    public Board stepAndCapture(TilePosition origin, TilePosition target)
    {
        Troop attacker = tileAt(origin).troop();
        Troop targetTroop = tileAt(target).troop();

        return withCaptureAndTiles(
                targetTroop.info(),
                targetTroop.side(),
                new EmptyTile(origin),
                new TroopTile(target, attacker.flipped()));
    }
//--------------------------------------------------------------------------------------------------
  //returns new Board, origin stays the same, target becomes empty -- only capturing, flipping
    public Board captureOnly(TilePosition origin, TilePosition target)
    {
        Troop attacker = tileAt(origin).troop();
        Troop targetTroop = tileAt(target).troop();

        return withCaptureAndTiles
        (
            targetTroop.info(),
            targetTroop.side(),
            new TroopTile(origin, attacker.flipped() ),
            new EmptyTile( target )
        );
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Iterator<Tile> iterator()
    {
        return new Iterator<Tile>()
        {
            // position a,b,c,....
            int posW = 0 ;
            // position 1,2,3,....
            int posH = 0 ;

            @Override
            public boolean hasNext()
            {
                return posW < dimension() && posH < dimension() ;
            }

            @Override
            public Tile next()
            {
                Tile nextTile = tileAt( new TilePosition( posW, posH )) ;
                if ( posW == dimension()-1 )
                {
                    posW = 0 ;
                    posH++ ;
                }

                else posW++ ;

                return nextTile ;
            }
        };
    }
//--------------------------------------------------------------------------------------------------
     public <T> T putToMedia(BoardMedia<T> media)
     {
         return media.putBoard(this);
     }
//--------------------------------------------------------------------------------------------------
}
