package stark.thedrake.game;

import stark.thedrake.media.CapturedTroopsMedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CapturedTroops
{
    private final List<TroopInfo> blueSide ;
    private final List<TroopInfo> orangeSide ;
//--------------------------------------------------------------------------------------------------
  //constructor creates class with emptyLists
    public CapturedTroops()
    {
        this.blueSide =  Collections.emptyList();
        this.orangeSide = Collections.emptyList();
    }
//--------------------------------------------------------------------------------------------------
  //constructor with given captured troops for each side
    public CapturedTroops( List<TroopInfo> blueSide, List<TroopInfo> orangeSide)
    {
        this.blueSide = new ArrayList(blueSide);
        this.orangeSide = new ArrayList(orangeSide);
    }
//--------------------------------------------------------------------------------------------------
  //getter for captured troops
    public List<TroopInfo> troops( PlayingSide side )
    {
        if ( side == PlayingSide.BLUE )
            return Collections.unmodifiableList(this.blueSide);

        return Collections.unmodifiableList(this.orangeSide);
    }
//--------------------------------------------------------------------------------------------------
  //adds newly captured troop at a beggining of a list for given playing side
    public CapturedTroops withTroop( PlayingSide side, TroopInfo info )
    {
        List<TroopInfo> newBlueTroops = new ArrayList<>(this.blueSide) ;
        List<TroopInfo> newOrangeTroops = new ArrayList<>(this.orangeSide);

        if(side == PlayingSide.BLUE)
            newBlueTroops.add(0, info);

        else
            newOrangeTroops.add(0, info);

        return new CapturedTroops(newBlueTroops ,newOrangeTroops);
    }
//--------------------------------------------------------------------------------------------------
    public <T> T putToMedia(CapturedTroopsMedia<T> media)
    {
        return media.putCapturedTroops(this);
    }
//--------------------------------------------------------------------------------------------------
}
