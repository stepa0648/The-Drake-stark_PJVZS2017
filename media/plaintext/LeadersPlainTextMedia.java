package stark.thedrake.media.plaintext;

import stark.thedrake.game.BothLeadersPlaced;
import stark.thedrake.game.NoLeadersPlaced;
import stark.thedrake.game.OneLeaderPlaced;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.media.LeadersMedia;
import stark.thedrake.media.PrintMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class LeadersPlainTextMedia extends PrintMedia implements LeadersMedia <Void>
{
    public LeadersPlainTextMedia(OutputStream stream)
    {
        super(stream);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putNoLeadersPlaced(NoLeadersPlaced leaders)
    {
        PrintWriter w = writer();
        w.printf("NL\n");
        return null ;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putOneLeaderPlaced(OneLeaderPlaced leaders)
    {
        PrintWriter w = writer();
        if ( leaders.isPlaced(PlayingSide.BLUE) )
            w.printf("OL %s\n", leaders.position(PlayingSide.BLUE));
        else
            w.printf("OL X %s\n" , leaders.position(PlayingSide.ORANGE));

        return null;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putBothLeadersPlaced(BothLeadersPlaced leaders)
    {
        PrintWriter w = writer();
        w.printf("BL %s %s\n", leaders.position(PlayingSide.BLUE),
                                  leaders.position(PlayingSide.ORANGE));
        return null;
    }
//--------------------------------------------------------------------------------------------------
}
