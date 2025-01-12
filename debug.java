import Character.*;
import Util.*;
import Render.*;



public class debug {
    public static void main(String[] args) {
        Team team1 = new Team("Skibidi");
        team1.add(new Banfield("aAAA"));
        System.out.println(team1.hasA(1));
    }
}
