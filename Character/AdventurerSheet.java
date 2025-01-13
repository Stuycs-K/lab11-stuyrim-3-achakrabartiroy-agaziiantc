package Character;

import Util.UniformRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class AdventurerSheet {
    //This class will make it easier to make randomized stuff.

    private static SplittableRandom rand = UniformRandom.rand.split();
    public static final String[] names = new String[]{ //names stolen off some website idk
            "Fae", "Jella", "Isona", "Quyana", "Eliss", "Mavena", "Alda", "Astryn", "Hephas", "Krisia", "Myrril", "Rián", "Faboren", "Robick", "Desiric"
    };
    public static final String[] teamNames = new String[]{
            "Harmonious Defeat", "Heroic Crusaders", "Shivering Death", "Whispers of Ending", "Association of the Ambitious", "Blood of the Jaguar", "Forsakenbrawlers", "Wreckingbrows", "Chaosstand"
    };
    public static final Adventurer[] adventurers = new Adventurer[]{
        new Banfield("banban"), new Sterr("skibidierr"), new Weinwurm("yeah idk")
    };

    public static Adventurer randomAdventurer() {
        Adventurer out = adventurers[rand.nextInt(adventurers.length)].clone();
        out.setName(names[rand.nextInt(names.length)]);
        return out;
    }

    public static Team randomTeam() {
        List<Integer> arr = new ArrayList<Integer>();
        Team out = new Team(teamNames[rand.nextInt(teamNames.length)]);
        for (int i = 0; i < adventurers.length; i++) {
            arr.add(i);
        }
        for(int i=0; i<3; i++){
            Adventurer a = adventurers[arr.remove(rand.nextInt(arr.size()))].clone();
            a.setName(names[rand.nextInt(names.length)]);
            out.add(a);
        }




        return out;
    }
}
