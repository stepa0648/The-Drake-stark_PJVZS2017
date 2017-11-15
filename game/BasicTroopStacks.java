package stark.thedrake.game;

import stark.thedrake.media.TroopStacksMedia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasicTroopStacks implements TroopStacks
{
    private final List<TroopInfo> blueTroops;
    private final List<TroopInfo> orangeTroops;

    public BasicTroopStacks(List<TroopInfo> blueTroops, List<TroopInfo> orangeTroops)
    {
        this.blueTroops = new ArrayList<>(blueTroops);
        this.orangeTroops = new ArrayList<>(orangeTroops);
    }
//--------------------------------------------------------------------------------------------------
    public BasicTroopStacks(TroopInfo... troops)
    {
        blueTroops = Arrays.asList(troops);
        orangeTroops = Arrays.asList(troops);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public List<TroopInfo> troops(PlayingSide side)
    {
        return side == PlayingSide.BLUE ?
                Collections.unmodifiableList(blueTroops) :
                Collections.unmodifiableList(orangeTroops);
    }
//--------------------------------------------------------------------------------------------------
  //returns TroopStack for given side without first element
  //first element goes on board -- ready to play
    @Override
    public BasicTroopStacks pop(PlayingSide side)
    {
        if(side == PlayingSide.BLUE)
        {
            return new BasicTroopStacks(blueTroops.subList(1, blueTroops.size()), orangeTroops);
        }

        return new BasicTroopStacks(blueTroops, orangeTroops.subList(1, orangeTroops.size()));
    }
//--------------------------------------------------------------------------------------------------
  //returns the element to be popped
    public Troop peek(PlayingSide side)
    {
        TroopInfo info = side == PlayingSide.BLUE ? blueTroops.get(0) : orangeTroops.get(0);
        return new Troop(info, side);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public <T> T putToMedia(TroopStacksMedia<T> media)
    {
        return media.putBasicTroopStacks(this);
    }
//--------------------------------------------------------------------------------------------------
}