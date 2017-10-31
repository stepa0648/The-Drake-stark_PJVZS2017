package stark.thedrake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author severste
 */
public class CapturedTroops {

    private final List<TroopInfo> troopsOrange;
    private final List<TroopInfo> troopsBlue;

    public CapturedTroops() {
        this.troopsOrange = new ArrayList<>();
        this.troopsBlue = new ArrayList<>();
    }
    public CapturedTroops(List<TroopInfo> troopsBlue,List<TroopInfo> troopsOrange) {
        this.troopsOrange = new ArrayList(troopsOrange);
        this.troopsBlue = new ArrayList(troopsBlue);
    }

    public CapturedTroops(CapturedTroops troops, PlayingSide side, TroopInfo info) {
        this.troopsBlue = new ArrayList(troops.troopsBlue);
        this.troopsOrange = new ArrayList(troops.troopsOrange);

        if (side == PlayingSide.BLUE) {
            this.troopsBlue.add(info);
        } else {
            this.troopsOrange.add(info);
        }

    }

    public List<TroopInfo> troops(PlayingSide side) {
        if (side == PlayingSide.BLUE) {
            return Collections.unmodifiableList(troopsBlue);
        } else {
            return Collections.unmodifiableList(troopsOrange);
        }
    }

    public CapturedTroops withTroop(PlayingSide side, TroopInfo info) {
        List<TroopInfo> newBlue = new ArrayList(this.troopsBlue);
        List<TroopInfo> newOrange = new ArrayList(this.troopsOrange);
        if(side == PlayingSide.BLUE){
            newBlue.add(0, info);
        }else{
            newOrange.add(0, info);
        }
        return new CapturedTroops(newBlue, newOrange);
    }

}
