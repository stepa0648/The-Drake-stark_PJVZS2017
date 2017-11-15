package stark.thedrake.media.plaintext;

import stark.thedrake.game.*;
import stark.thedrake.media.GameStateMedia;
import stark.thedrake.media.PrintMedia;

import java.io.OutputStream;
import java.io.PrintWriter;

public class GameStatePlainTextMedia extends PrintMedia implements GameStateMedia <Void>
{
    LeadersPlainTextMedia leadersMedia ;
    BoardPlainTextMedia boardMedia ;
    TroopStacksPlainTextMedia troopStacksMedia ;

    public GameStatePlainTextMedia(OutputStream stream)
    {
        super(stream);
        this.troopStacksMedia = new TroopStacksPlainTextMedia(stream);
        this.leadersMedia = new LeadersPlainTextMedia(stream);
        this.boardMedia = new BoardPlainTextMedia(stream);
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putPlacingLeadersGameState(PlacingLeadersGameState state)
    {
        PrintWriter w = writer();
        w.printf("LEADERS\n0\n%s\n",state.sideOnTurn());
        troopStacksMedia.putBasicTroopStacks( (BasicTroopStacks) state.troopStacks());

        if ( state.leaders().isPlaced(PlayingSide.BLUE) ||
             state.leaders().isPlaced(PlayingSide.ORANGE) )
            leadersMedia.putOneLeaderPlaced( (OneLeaderPlaced) state.leaders());
        else
            leadersMedia.putNoLeadersPlaced( (NoLeadersPlaced) state.leaders() );

        boardMedia.putBoard(state.board());
        return null;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putPlacingGuardsGameState(PlacingGuardsGameState state)
    {
        PrintWriter w = writer();
        w.printf("GUARDS\n%d\n%s\n", state.guardsCount(),  state.sideOnTurn());
        troopStacksMedia.putBasicTroopStacks( (BasicTroopStacks) state.troopStacks());
        leadersMedia.putBothLeadersPlaced( state.leaders());
        boardMedia.putBoard(state.board());
        return null ;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putMiddleGameState(MiddleGameState state)
    {
        PrintWriter w = writer();
        w.printf("MIDDLE\n4\n%s\n", state.sideOnTurn() );
        troopStacksMedia.putBasicTroopStacks( (BasicTroopStacks) state.troopStacks());
        leadersMedia.putBothLeadersPlaced( state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }
//--------------------------------------------------------------------------------------------------
    @Override
    public Void putFinishedGameState(VictoryGameState state)
    {
        PrintWriter w = writer();
        w.printf("VICTORY\n4\n%s\n", state.sideOnTurn());
        troopStacksMedia.putBasicTroopStacks( (BasicTroopStacks) state.troopStacks());
        leadersMedia.putOneLeaderPlaced( (OneLeaderPlaced) state.leaders());
        boardMedia.putBoard(state.board());

        return null;
    }
//--------------------------------------------------------------------------------------------------
}
