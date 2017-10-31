/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake;

/**
 *
 * @author severste
 */
public class StepAndCapture extends BoardChange {

    public StepAndCapture(Board initialBoard, TilePosition origin, TilePosition target) {
        super(initialBoard, origin, target);
    }

    @Override
    public Board resultBoard() {
        return initialBoard.stepAndCapture(origin, target);
    }

}
