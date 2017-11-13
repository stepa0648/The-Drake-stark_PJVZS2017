package stark.thedrake.media.plaintext;

import java.io.BufferedReader;
import java.io.IOException;
import stark.thedrake.game.EmptyTile;
import stark.thedrake.game.PlayingSide;
import stark.thedrake.game.TheDrakeSetup;
import stark.thedrake.game.Tile;
import stark.thedrake.game.TilePosition;
import stark.thedrake.game.Troop;
import stark.thedrake.game.TroopFace;
import stark.thedrake.game.TroopInfo;
import stark.thedrake.game.TroopTile;

public class TileFromPlainText {

    private final TheDrakeSetup setup;
    private final BufferedReader reader;

    public TileFromPlainText(TheDrakeSetup setup, BufferedReader reader) {
        this.setup = setup;
        this.reader = reader;
    }

    public Tile readTile(TilePosition position) throws IOException {
        String line = reader.readLine();

        if ("empty".equals(line)) {
            return new EmptyTile(position);
        }

        String[] fields = line.split(" ");
        TroopInfo info = setup.infoByName(fields[0]);
        PlayingSide side = PlayingSide.valueOf(fields[1]);
        TroopFace face = TroopFace.valueOf(fields[2]);
        Troop troop = new Troop(info, side, face);
        return new TroopTile(position, troop);
    }
}
