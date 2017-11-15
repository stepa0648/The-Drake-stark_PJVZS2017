package stark.thedrake.game;

import java.util.List;

public class TroopInfo
{
    private final String name ;
    private Offset2D frontPivot ;
    private Offset2D backPivot ;
    private List<TroopAction> frontActions ;
    private List<TroopAction> backActions ;

    public TroopInfo(String name, Offset2D frontPivot, Offset2D backPivot)
    {
        this.name = name ;
        this.frontPivot = frontPivot ;
        this.backPivot = backPivot ;
    }
//--------------------------------------------------------------------------------------------------
    public TroopInfo ( String name , List<TroopAction> frontActions,
                                     List<TroopAction> backActions )
    {
        this( name );
        this.frontActions = frontActions ;
        this.backActions = backActions ;
    }
//--------------------------------------------------------------------------------------------------
    public TroopInfo ( String name , Offset2D frontPivot, Offset2D backPivot,
                       List<TroopAction> frontActions , List<TroopAction> backActions )
    {
        this(name, frontPivot, backPivot);
        this.frontActions = frontActions ;
        this.backActions = backActions ;
    }
//--------------------------------------------------------------------------------------------------
    public TroopInfo(String name, Offset2D pivot)
    {
        this(name, pivot, pivot);        
    }
//--------------------------------------------------------------------------------------------------
    public TroopInfo( String name )
    {   
        this ( name , new Offset2D (1,1) , new Offset2D(1,1));
    }    
//--------------------------------------------------------------------------------------------------
    public String name()
    {
        return name ;
    }
//--------------------------------------------------------------------------------------------------
    public Offset2D pivot(TroopFace face) 
    {
        if ( face == TroopFace.FRONT) return frontPivot ;
        return backPivot ;
    }
//--------------------------------------------------------------------------------------------------
    public List<TroopAction> actions(TroopFace face)
    {
        if ( face == TroopFace.BACK )
            return backActions ;
        return frontActions ;
    }
//--------------------------------------------------------------------------------------------------
}
