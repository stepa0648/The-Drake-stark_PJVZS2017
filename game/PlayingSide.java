/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stark.thedrake.game;

/**
 *
 * @author severste
 */
public enum PlayingSide {
    BLUE, ORANGE {
        @Override
        public PlayingSide flipped() {
            return BLUE;
        }

        @Override
        public PlayingSide opposite() {
            return BLUE;
        }
    };

    public PlayingSide flipped() {
        return ORANGE;
    }

    public PlayingSide opposite() {
        return ORANGE;
    }
}
