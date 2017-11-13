package stark.thedrake.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author severste
 */
public class StrikeAction implements TroopAction {

    private final Offset2D direction;

    public StrikeAction(int x, int y) {
        this.direction = new Offset2D(x, y);
    }

    @Override
    public List<BoardChange> changesFrom(TilePosition origin, PlayingSide side, Board board) {
        List<BoardChange> result = new ArrayList<>();

        TilePosition target = origin.stepByPlayingSide(direction, side);

        // if can step on the target tile 
        if (board.canCaptureOnly(origin, target)) {
            result.add(new CaptureOnly(board, origin, target));
        }

        return result;
    }

}
