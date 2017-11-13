/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;
import stark.thedrake.game.BasicTroopStacks;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.game.TroopInfo;
import stark.thedrake.media.PrintMedia;
import stark.thedrake.media.TroopStacksMedia;

/**
 *
 * @author severste
 */
public class TroopStacksPlainTextMedia extends PrintMedia implements TroopStacksMedia<Void> {

    public TroopStacksPlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putBasicTroopStacks(BasicTroopStacks stacks) {
        PrintWriter w = writer();
        w.print("BLUE stack:");
        for (TroopInfo troop : stacks.troops(PlayingSide.BLUE)) {
            w.print(" " + troop.name());
        }
        w.println();
        w.print("ORANGE stack:");
        for (TroopInfo troop : stacks.troops(PlayingSide.ORANGE)) {
            w.print(" " + troop.name());
        }
        w.println();
        return null;
    }

}
