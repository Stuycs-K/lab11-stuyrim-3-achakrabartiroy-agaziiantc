package Character;

public class Team {
    public Adventurer[] team;
    public String name;
    public int len ;
    public Team(String name, Adventurer[] adv) {
        this.name = name;
        this.team = adv.clone(); //idk if this is the best way of doing this, but it works well enough to the point where I can just come back to it later. Past me presumably made this .clone() for a reason, but I don't know how much I am willing to trust myself.
        len = 3;
    }
    public Team(String name) {
        this.name = name;
        this.team = new Adventurer[3];
        len = 0;
    }
    public void add(Adventurer adv){
        this.team[len++] = adv;
    }
    public boolean hasA(int adv){
        for(int i = 0; i < len; i++){
            if(team[i].getId() == (adv)){
                return true;
            }
        }
        return false;
    }

    public boolean hasA(Adventurer adv){
        for(int i = 0; i < len; i++){
            if(team[i].equals(adv)){
                return true;
            }
        }
        return false;
    }

}
