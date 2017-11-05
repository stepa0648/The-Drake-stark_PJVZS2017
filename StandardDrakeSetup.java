package stark.thedrake;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardDrakeSetup implements TheDrakeSetup {

    Map<String, TroopInfo> troops;

    @Override
    public List<TroopInfo> troops() {
        return Arrays.asList(DRAKE, CLUBMAN, MONK, SPEARMAN, SWORDSMAN, ARCHER);
    }

    public final TroopInfo DRAKE = new TroopInfo(
            "Drake",
            Arrays.asList(
                    new SlideAction(1, 0),
                    new SlideAction(-1, 0)),
            Arrays.asList(
                    new SlideAction(0, 1),
                    new SlideAction(0, -1)));

    public final TroopInfo CLUBMAN = new TroopInfo(
            "Clubman",
            Arrays.asList(
                    new ShiftAction(1, 0),
                    new ShiftAction(0, 1),
                    new ShiftAction(-1, 0),
                    new ShiftAction(0, -1)),
            Arrays.asList(
                    new ShiftAction(1, 1),
                    new ShiftAction(-1, 1),
                    new ShiftAction(1, -1),
                    new ShiftAction(-1, -1)));

    public final TroopInfo MONK = new TroopInfo(
            "Monk",
            Arrays.asList(
                    new SlideAction(1, 1),
                    new SlideAction(-1, 1),
                    new SlideAction(1, -1),
                    new SlideAction(-1, -1)),
            Arrays.asList(
                    new ShiftAction(1, 0),
                    new ShiftAction(0, 1),
                    new ShiftAction(-1, 0),
                    new ShiftAction(0, -1)));

    public final TroopInfo SPEARMAN = new TroopInfo(
            "Spearman",
            Arrays.asList(
                    new ShiftAction(0, 1),
                    new StrikeAction(1, 2),
                    new StrikeAction(-1, 2)),
            Arrays.asList(
                    new ShiftAction(1, 1),
                    new ShiftAction(-1, 1),
                    new ShiftAction(0, -1)));

    public final TroopInfo SWORDSMAN = new TroopInfo(
            "Swordsman",
            Arrays.asList(
                    new StrikeAction(1, 0),
                    new StrikeAction(0, 1),
                    new StrikeAction(-1, 0),
                    new StrikeAction(0, -1)),
            Arrays.asList(
                    new ShiftAction(1, 0),
                    new ShiftAction(0, 1),
                    new ShiftAction(-1, 0),
                    new ShiftAction(0, -1)));

    public final TroopInfo ARCHER = new TroopInfo(
            "Archer",
            new Offset2D(1, 1),
            new Offset2D(1, 0),
            Arrays.asList(
                    new ShiftAction(1, 0),
                    new ShiftAction(-1, 0),
                    new ShiftAction(0, -1)),
            Arrays.asList(
                    new ShiftAction(0, 1),
                    new StrikeAction(-1, 1),
                    new StrikeAction(1, 1),
                    new StrikeAction(0, 2)));

    @Override
    public TroopInfo infoByName(String name) {
        if (troops == null) {
            troops = new HashMap<>();
        }
        troops.put("Drake", DRAKE);
        troops.put("Clubman", CLUBMAN);
        troops.put("Monk", MONK);
        troops.put("Spearman", SPEARMAN);
        troops.put("Archer", ARCHER);
        troops.put("Swordsman", SWORDSMAN);

        if (troops.containsKey(name)) {
            return troops.get(name);
        }

        throw new IllegalArgumentException("Neznámá jednotka");
    }
}
