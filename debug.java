import Character.*;
import Util.*;
import Render.*;

import java.util.Arrays;


public class debug {
    public static void main(String[] args) {
        Team team1 = new Team("Skibidi");
        team1.add(new Banfield("aAAA"));
        System.out.println(team1.hasA(1));
        for(int i=0; i<50; i++){
            Team team = AdventurerSheet.randomTeam();
            System.out.println(Arrays.toString(team.team) + " " + team.hasA(5));
        }
    }
}
