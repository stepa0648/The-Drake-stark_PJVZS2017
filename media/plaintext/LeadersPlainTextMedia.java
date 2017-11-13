/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;
import stark.thedrake.game.BothLeadersPlaced;
import stark.thedrake.game.NoLeadersPlaced;
import stark.thedrake.game.OneLeaderPlaced;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.media.LeadersMedia;
import stark.thedrake.media.PrintMedia;

/**
 *
 * @author severste
 */
public class LeadersPlainTextMedia extends PrintMedia implements LeadersMedia<Void> {

    public LeadersPlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putNoLeadersPlaced(NoLeadersPlaced leaders) {
        PrintWriter w = writer();
        w.println("NL");
        return null;
    }

    @Override
    public Void putOneLeaderPlaced(OneLeaderPlaced leaders) {
        PrintWriter w = writer();
        if (leaders.isPlaced(PlayingSide.BLUE) == true) {
            w.println("OL " + leaders.position(PlayingSide.BLUE).toString());
        } else {
            w.println("OL X " + leaders.position(PlayingSide.ORANGE).toString());
        }
        return null;
    }

    @Override
    public Void putBothLeadersPlaced(BothLeadersPlaced leaders) {
        PrintWriter w = writer();
        w.println("BL " + leaders.position(PlayingSide.BLUE).toString() + " " + leaders.position(PlayingSide.ORANGE).toString());
        return null;
    }

}
