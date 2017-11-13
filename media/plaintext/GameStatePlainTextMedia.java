/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;
import stark.thedrake.game.BasicTroopStacks;
import stark.thedrake.game.BothLeadersPlaced;
import stark.thedrake.game.MiddleGameState;
import stark.thedrake.game.NoLeadersPlaced;
import stark.thedrake.game.OneLeaderPlaced;
import stark.thedrake.game.PlacingGuardsGameState;
import stark.thedrake.game.PlacingLeadersGameState;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.game.VictoryGameState;
import stark.thedrake.media.GameStateMedia;
import stark.thedrake.media.PrintMedia;

/**
 *
 * @author severste
 */
public class GameStatePlainTextMedia extends PrintMedia implements GameStateMedia<Void> {

    private final TroopStacksPlainTextMedia troopStacksMedia;
    private final LeadersPlainTextMedia leaderMedia;
    private final BoardPlainTextMedia boardMedia;

    public GameStatePlainTextMedia(OutputStream stream) {
        super(stream);
        this.troopStacksMedia = new TroopStacksPlainTextMedia(stream);
        this.leaderMedia = new LeadersPlainTextMedia(stream);
        this.boardMedia = new BoardPlainTextMedia(stream);
    }

    @Override
    public Void putPlacingLeadersGameState(PlacingLeadersGameState state) {
        PrintWriter w = writer();
        w.println("LEADERS");
        w.println("0");
        if (state.sideOnTurn() == PlayingSide.BLUE) {
            w.println("BLUE");
        } else {
            w.println("ORANGE");
        }
        troopStacksMedia.putBasicTroopStacks((BasicTroopStacks) state.troopStacks());

        if (state.leaders().isPlaced(PlayingSide.BLUE) == false) {
            leaderMedia.putNoLeadersPlaced((NoLeadersPlaced) state.leaders());
        } else {
            leaderMedia.putOneLeaderPlaced((OneLeaderPlaced) state.leaders());
        }
        boardMedia.putBoard(state.board());
        return null;
    }

    @Override
    public Void putPlacingGuardsGameState(PlacingGuardsGameState state) {
        PrintWriter w = writer();
        w.println("GUARDS");
        w.println(state.guardsCount());
        if (state.sideOnTurn() == PlayingSide.BLUE) {
            w.println("BLUE");
        } else {
            w.println("ORANGE");
        }
        troopStacksMedia.putBasicTroopStacks((BasicTroopStacks) state.troopStacks());

        leaderMedia.putBothLeadersPlaced(state.leaders());

        boardMedia.putBoard(state.board());
        return null;
    }

    @Override
    public Void putMiddleGameState(MiddleGameState state) {
        PrintWriter w = writer();
        //stav hry
        w.println("MIDDLE");
        //pocet strazi
        w.println("4");

        if (state.sideOnTurn() == PlayingSide.BLUE) {
            w.println("BLUE");
        } else {
            w.println("ORANGE");
        }
        troopStacksMedia.putBasicTroopStacks((BasicTroopStacks) state.troopStacks());
        leaderMedia.putBothLeadersPlaced((BothLeadersPlaced) state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }

    @Override
    public Void putFinishedGameState(VictoryGameState state) {
        PrintWriter w = writer();
        //stav hry
        w.println("VICTORY");
        //pocet strazi
        w.println("4");

        if (state.sideOnTurn() == PlayingSide.BLUE) {
            w.println("BLUE");
        } else {
            w.println("ORANGE");
        }
        troopStacksMedia.putBasicTroopStacks((BasicTroopStacks) state.troopStacks());
        leaderMedia.putOneLeaderPlaced((OneLeaderPlaced) state.leaders());
        boardMedia.putBoard(state.board());
        return null;
    }

}
