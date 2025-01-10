package Character;

public class Team {
    public Adventurer[] team;
    public String name;

    public Team(String name, Adventurer[] adv) {
        this.name = name;
        team = adv.clone();
    }
}
