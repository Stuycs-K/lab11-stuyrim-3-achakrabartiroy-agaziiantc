package Character;

import Util.Util;

import java.util.SplittableRandom;
import Util.UniformRandom;

public class Banfield extends Adventurer{
    int phospholipids, maxPhospholipids;
    private SplittableRandom rand;
    public int id = 1; //I don't kintValintValnow why, but if this is static it breaks everything.
    public Banfield(String name, int hp, int maxhp, int special, int maxspecial){
        this.setName(name);
        this.setHP(hp);
        this.setmaxHP(maxhp);
        this.setSpecial(special);
        this.maxPhospholipids = maxspecial;
        this.rand = UniformRandom.rand.split();
    }
    public Banfield(String name){
        this(name, 10, 10, 10, 10);
    }
    @Override
    public String getSpecialName() {
        return "Phospholipids";
    }

    @Override
    public int getSpecial() {
        return phospholipids;
    }

    @Override
    public int getSpecialMax() {
        return maxPhospholipids;
    }

    @Override
    public void setSpecial(int n) {
        phospholipids = n;
        if(phospholipids > maxPhospholipids){
            phospholipids = maxPhospholipids;
        }
    }

    @Override
    public String support() {
        if(this.phospholipids < 1){
            return this.getName() + " tried to make a cellular wall, but did not have enough phospholipids.";
        }
        this.phospholipids--;
        this.modifyArmor(0.2);
        return this.getName() + " reinforces with a cellular wall, gaining +20% damage resistance.";
    }

    @Override
    public String specialAttack(Adventurer other) {
        if(this.phospholipids < 2){
            return this.getName() + " attempted to make " + other.getName() + " give a presentation, but did not have enough phospholipids";
        }
        this.phospholipids-=2;
        return this.getName() + " makes " + other.getName() + " give a presentation on " + Util.topics[rand.nextInt(4)] + ", stunning them for a turn";
    }

    @Override
    public String support(Adventurer other) {
        if(this.phospholipids < 1){
            return this.getName() + " tried to make a cellular wall, but did not have enough phospholipids.";
        }
        this.phospholipids--;
        other.modifyArmor(0.2);
        return this.getName() + " reinforces " + other.getName() + " with a cellular wall, gaining +20% damage resistance.";
    }

    @Override
    public String attack(Adventurer other) {
        phospholipids++;
        other.modifyPreparedness(-1);
        other.applyDamage(2);
        return this.getName() + " gives " + other.getName() + " a fake quiz, making them less prepared and dealing 2 damage.";
    }

    @Override
    public int getId(){
        return 1;
    }
}
