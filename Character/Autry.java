package Character;

import Util.Util;
import Util.Sprite;
import java.util.SplittableRandom;
import Util.UniformRandom;

public class Autry extends Adventurer{
    int dumbbells, maxDumbbells;
    int numSpecial = 0;
    private SplittableRandom rand;
    private Sprite sp;
    public int id = 7; //I don't kintValintValnow why, but if this is static it breaks everything.
    public Autry(String name, int hp, int maxhp, int atk, int rcv, int special, int maxspecial){
        this.setName(name);
        this.setHP(hp);
        this.setmaxHP(maxhp);
        this.setSpecial(special);
        this.setRCV(rcv);
        this.setATK(atk);
        this.maxDumbbells = maxspecial;
        this.rand = UniformRandom.rand.split();
    }
    public Autry(String name){
      // Base stats:
      /*
      HP: 10.0 (total: 1000)
      ATK: 8.5 (total: 170)
      RCV: 0.0 (total: 0)
      */
      this(name, 1000, 1000, 170, 0, 10, 10);
    }
    @Override
    public String toString(){
        return "Autry-" + this.getName();
    }
    @Override
    public Adventurer clone() {
        return new Autry(this.getName(), this.getHP(), this.getmaxHP(), this.getATK(), this.getRCV(), this.getSpecial(), this.getSpecialMax());
    }

    @Override
    public String getSpecialName() {
        return "Dumbbells";
    }

    @Override
    public int getSpecial() {
        return dumbbells;
    }

    @Override
    public int getSpecialMax() {
        return maxDumbbells;
    }

    @Override
    public void setSpecial(int n) {
        dumbbells = n;
        if(dumbbells > maxDumbbells){
            dumbbells = maxDumbbells;
        }
    }

    @Override
    public Sprite getSprite() {
        return sp;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sp = sprite;
    }

    @Override
    public String support() {
        if(this.dumbbells < 1){
            return this.getName() + " tried to make a cellular wall, but did not have enough phospholipids.";
        }
        this.dumbbells--;
        this.modifyArmor(0.2);
        return this.getName() + " reinforces with a cellular wall, gaining +20% damage resistance.";
    }

    @Override
    public String specialAttack(Adventurer other) {
        if(this.dumbbells < 2){
            return this.getName() + " attempted to lift 300lb dumbbells, but did not have enough dumbbells to lift. ";
        }
        this.dumbbells-=2;
        this.numSpecial += 1;
        if (this.numSpecial % 5 != 0) {
          return this.getName() + " lifts " + Integer.toString(this.numSpecial * 100) + "lb dumbbells, making him deal 2x dmg for one turn (2.5x against unprepared enemies).";
        } else if (this.numSpecial % 5 == 0) {
          //
          return "It's Friday! " + this.getName() + " gets a new PR and deals 4x dmg for one turn (5x against unprepared enemies)!";
        }
        else {
          return "Huh?";
        }
    }

    @Override
    public String support(Adventurer other) {
        if(this.dumbbells < 1){
            return this.getName() + " tried to help " + other.getName() + " achieve a PR, but " + Util.noPRreasons[rand.nextInt(3)];
        }
        this.dumbbells--;
        int a = other.getATK();
        double d = a; // this should work but im not 100% sure
        other.modifyBonusatk((int) Math.floor(d * 0.15));
        return this.getName() + " helps " + other.getName() + " achieve a new PR, boosting " + other.getName() + "'s ATK by 15% for one turn.";
    }

    @Override
    public String attack(Adventurer other) {
        dumbbells++;
        int randint = (int) Math.floor(Math.random() * 5);
        other.modifyPreparedness(-1 * randint);
        other.applyDamage(this.getATK());
        if (randint > 3) {
          return this.getName() + " marks 4 people unprepared. Crying emoji!";
        }
        return this.getName() + " marks " + Integer.toString(randint) + " people unprepared.";
    }

    @Override
    public int getId(){
        return 7;
    }
}
