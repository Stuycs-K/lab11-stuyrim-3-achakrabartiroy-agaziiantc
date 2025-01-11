package Character;

public class Team {
    public Adventurer[] team;
    public String name;

    public Team(String name, Adventurer[] adv) {
        this.name = name;
        this.team = adv.clone();
    }
    public Team(String name) {
        this.name = name;
        this.team = new Adventurer[3];
    }
}
