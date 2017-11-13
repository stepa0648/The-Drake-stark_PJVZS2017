/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.media.plaintext;

import java.io.OutputStream;
import java.io.PrintWriter;
import stark.thedrake.game.CapturedTroops;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.game.TroopInfo;
import stark.thedrake.media.CapturedTroopsMedia;
import stark.thedrake.media.PrintMedia;

/**
 *
 * @author severste
 */
public class CapturedTroopsPlainTextMedia extends PrintMedia implements CapturedTroopsMedia<Void> {

    public CapturedTroopsPlainTextMedia(OutputStream stream) {
        super(stream);
    }

    @Override
    public Void putCapturedTroops(CapturedTroops captured) {
        PrintWriter w = writer();

        w.println("Captured BLUE: " + captured.troops(PlayingSide.BLUE).size());
        for (TroopInfo troop : captured.troops(PlayingSide.BLUE)) {
            w.println(troop.name());
        }
        w.printf("Captured ORANGE: %d", captured.troops(PlayingSide.ORANGE).size());
        for (TroopInfo troop : captured.troops(PlayingSide.ORANGE)) {
            w.printf("\n");
            w.printf("%s", troop.name());
        }

        return null;
    }

}
