package stark.thedrake.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author severste
 */
public class Troop {

    private final TroopInfo info;
    private final PlayingSide side;
    private final TroopFace face;

    // Vytvoří jednotku lícem nahoru
    public Troop(TroopInfo info, PlayingSide side) {
        this.info = info;
        this.side = side;
        this.face = TroopFace.FRONT;
    }

    public Troop(TroopInfo info, PlayingSide side, TroopFace face) {
        this.info = info;
        this.side = side;
        this.face = face;
    }

    public TroopInfo info() {
        return info;
    }

    public PlayingSide side() {
        return side;
    }

    public TroopFace face() {
        return face;
    }

    public Offset2D pivot() {
        return info.pivot(face);
    }

    public Troop flipped() {
        if (face == TroopFace.BACK) {
            return new Troop(info, side, TroopFace.FRONT);
        } else {
            return new Troop(info, side, TroopFace.BACK);
        }
    }

    // Všechny změny desky, které může jednotka provést na desce board, pokud stojí na pozici pos.
    public List<BoardChange> changesFrom(TilePosition pos, Board board) {

        List<BoardChange> boardChanges = new ArrayList<>();

        if (board.contains(pos)) {

            for (TroopAction action : info().actions(face())) {
                boardChanges.addAll(action.changesFrom(pos, side(), board));
            }
        }

        return boardChanges;
    }

}
