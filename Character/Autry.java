package Character;

import Util.Util;
import Util.Sprite;
import java.util.SplittableRandom;
import Util.UniformRandom;

public class Autry extends Adventurer{
    int dumbbells, maxDumbbells;
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
            return this.getName() + " attempted to make " + other.getName() + " give a presentation, but did not have enough phospholipids";
        }
        this.dumbbells-=2;
        return this.getName() + " makes " + other.getName() + " give a presentation on " + Util.topics[rand.nextInt(4)] + ", stunning them for a turn";
    }

    @Override
    public String support(Adventurer other) {
        if(this.dumbbells < 1){
            return this.getName() + " tried to make a cellular wall, but did not have enough phospholipids.";
        }
        this.dumbbells--;
        other.modifyArmor(0.2);
        return this.getName() + " reinforces " + other.getName() + " with a cellular wall, gaining +20% damage resistance.";
    }

    @Override
    public String attack(Adventurer other) {
        dumbbells++;
        other.modifyPreparedness(-1);
        other.applyDamage(2);
        return this.getName() + " gives " + other.getName() + " a fake quiz, making them less prepared and dealing 2 damage.";
    }

    @Override
    public int getId(){
        return 7;
    }
}
