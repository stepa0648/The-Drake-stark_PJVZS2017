/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author severste
 */
public class StrikeAction implements TroopAction {

    private final Offset2D direction;

    public StrikeAction(int x, int y)
    {
        this.direction = new Offset2D( x, y );
    }


    @Override
    public List<BoardChange> changesFrom(TilePosition origin, PlayingSide side, Board board) {
       
        TilePosition target = origin.stepByPlayingSide(direction, side);

        // if can step on the target tile 
        if (board.canCaptureOnly(origin, target)) {
            return Collections.singletonList(new CaptureOnly(board, origin, target));
        }

        return Collections.emptyList();
    }

}
