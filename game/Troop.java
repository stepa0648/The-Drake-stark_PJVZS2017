package stark.thedrake.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Troop
{
    private TroopInfo info ;
    private PlayingSide side ;
    private TroopFace face ;

    //creates a troop facing upwards
    public Troop( TroopInfo info, PlayingSide side )
    {
        this.info = info ;
        this.side = side ;
        this.face = TroopFace.FRONT;
    }
//--------------------------------------------------------------------------------------------------
    public Troop( TroopInfo info, PlayingSide side, TroopFace face )
    {
        this(info, side) ;
        this.face = face ;
    }
//--------------------------------------------------------------------------------------------------
    public TroopInfo info()
    {
        return info;
    }
//--------------------------------------------------------------------------------------------------
    public PlayingSide side() 
    {
        return side;
    }
//--------------------------------------------------------------------------------------------------
    public TroopFace face()
    {
        return face;
    }
//--------------------------------------------------------------------------------------------------
    public Offset2D pivot()
    {
        return info.pivot(face) ;
    }
//--------------------------------------------------------------------------------------------------
    public Troop flipped()
    {
        if ( face == TroopFace.FRONT )
              return new Troop ( info, side, TroopFace.BACK );

        else return new Troop ( info, side, TroopFace.FRONT );
    }
//--------------------------------------------------------------------------------------------------
  //returns all possible Board changes that troop can do
    public List<BoardChange> changesFrom(TilePosition pos, Board board )
    {
        List changes = new ArrayList<BoardChange>();

        if ( ! board.contains( pos ) )
            return  Collections.emptyList() ;

        for ( TroopAction action : info().actions( face() ) )
            changes.addAll(action.changesFrom( pos, side() , board ));

        return changes ;
    }
//--------------------------------------------------------------------------------------------------

}
