package Character;

import Util.Sprite;
import Util.UniformRandom;

import java.util.SplittableRandom;

public class Boss extends Adventurer {
    //not allowed to do mr k so we're replacing him with a different goat of cs
    private int divineIntellect;
    private int maxDivineIntellect;
    private Sprite sprite;
    private SplittableRandom rand;

    public Boss(String name, int hp, int maxhp, int atk, int rcv, int special, int maxspecial){
        this.setName(name);
        this.setHP(hp);
        this.setmaxHP(maxhp);
        this.setSpecial(special);
        this.setRCV(rcv);
        this.setATK(atk);
        this.maxDivineIntellect = maxspecial;
        this.rand = UniformRandom.rand.split();
    }
    public Boss(String name){
        // Base stats:
      /* (just multiplied the banfield stats by 2)
      HP: 22.0 (total: 1100)
      ATK: 13 (total: 130)
      RCV: 6.0 (total: 15)
      */
        this(name, 2200, 2200, 260, 30, 20, 20);
    }
    @Override
    public String toString(){
        return "Terry-" + this.getName();
    }

    @Override
    public Adventurer clone() {
        return new Boss(this.getName(), this.getHP(), this.getmaxHP(), this.getATK(), this.getRCV(), this.getSpecial(), this.getSpecialMax());
    }



    @Override
    public String getSpecialName() {
        return "Divine Intellect";
    }

    @Override
    public int getSpecial() {
        return divineIntellect;
    }

    @Override
    public int getSpecialMax() {
        return maxDivineIntellect;
    }

    @Override
    public void setSpecial(int n) {
        this.divineIntellect = n;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    @Override
    public String support() {
        return support(this);
    }

    @Override
    public int getId() {
        return 5;
    }


    @Override
    public String specialAttack(Adventurer other) {
        //Idk what to put for the string, but the functionality is there
        Adventurer[] arr = other.team.team; //Strongest basic coding convention vs weakest spaghetti code
        StringBuilder out = new StringBuilder();
        out.append(this.getName() + " starts smiting people ");
        for(int i=0; i<3; i++) {
            int dmg = ((int) (this.getSpecial() * getBonusatk()));
            arr[i].applyDamage(dmg);
            out.append(arr[i].getName() + " takes " + dmg + "damage, ");
            if (this.getSpecial() > 0) {
                this.divineIntellect--;
            }
            return out.toString().substring(0, out.length() - 2);
        }

        return "";
    }

    @Override
    public String support(Adventurer other) {
        other.restoreSpecial(3);
        other.modifyPreparedness(1);
        other.modifyArmor(0.1);
        other.modifyBonusatk(1);
        return this.getName() + " calls down the might of god to assist " + other.getName() + " strongly strengthening them";
    }

    @Override
    public String attack(Adventurer other) {
        int dmg = ((int)(other.getSpecialMax()+1*getBonusatk())-other.getSpecial())*10; //probably a good idea to tweak these numbers a bit
        other.applyDamage(dmg);
        return this.getName() + " asks " + other.getName() + " how many compilers they had written, dealing " + dmg + " damage";
    }
}
