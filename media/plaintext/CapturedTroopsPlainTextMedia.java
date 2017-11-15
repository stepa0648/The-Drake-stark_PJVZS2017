package stark.thedrake.media.plaintext;

import stark.thedrake.game.CapturedTroops;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.game.TroopInfo;
import stark.thedrake.media.CapturedTroopsMedia;
import stark.thedrake.media.PrintMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class CapturedTroopsPlainTextMedia extends PrintMedia implements CapturedTroopsMedia<Void>
{
    public CapturedTroopsPlainTextMedia(OutputStream stream)
    {
        super(stream);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putCapturedTroops(CapturedTroops captured)
    {
        PrintWriter w = writer() ;
        int counter = 1 ;

        w.printf("Captured BLUE: %d\n", captured.troops(PlayingSide.BLUE).size());
        for ( TroopInfo info : captured.troops(PlayingSide.BLUE) )
            w.printf( "%s\n", info.name() );

        if ( captured.troops(PlayingSide.ORANGE).size() == 0 )
            w.printf("Captured ORANGE: %d", captured.troops(PlayingSide.ORANGE).size());
        else
            w.printf("Captured ORANGE: %d\n", captured.troops(PlayingSide.ORANGE).size());

        for ( TroopInfo info : captured.troops(PlayingSide.ORANGE) )
        {
            if (counter != captured.troops(PlayingSide.ORANGE).size())
                w.printf("%s\n", info.name());
            else
                w.printf("%s", info.name());
            counter++;
        }

        return null ;
    }
//--------------------------------------------------------------------------------------------------
}
