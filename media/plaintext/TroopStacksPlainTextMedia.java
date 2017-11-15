package stark.thedrake.media.plaintext;

import stark.thedrake.game.BasicTroopStacks;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.game.TroopInfo;
import stark.thedrake.media.PrintMedia;
import stark.thedrake.media.TroopStacksMedia;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class TroopStacksPlainTextMedia extends PrintMedia implements TroopStacksMedia <Void>
{
    public TroopStacksPlainTextMedia(OutputStream stream)
    {
        super(stream);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putBasicTroopStacks(BasicTroopStacks stacks)
    {
        int counter = 1 ;
        PrintWriter w = writer();
        w.printf("BLUE stack: ");

        for (TroopInfo info : stacks.troops(PlayingSide.BLUE) )
        {
            if ( counter != stacks.troops(PlayingSide.BLUE).size() )
                w.printf("%s " , info.name());
            else
                w.printf(info.name() + "\n");
            counter++;
        }

        counter = 1 ;
        w.printf("ORANGE stack: ");
        for (TroopInfo info : stacks.troops(PlayingSide.ORANGE) )
        {
            if ( counter != stacks.troops(PlayingSide.ORANGE).size() )
                w.printf("%s " , info.name());
            else
                w.printf(info.name() + "\n");
           counter++;
        }

        return null ;
    }
//--------------------------------------------------------------------------------------------------
}
