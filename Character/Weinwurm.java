package Character;

import Util.Sprite;
import Util.UniformRandom;

import java.util.SplittableRandom;

public class Weinwurm extends Adventurer{

    int HIP, maxHIP; //HIP = Health insurance policies
    private SplittableRandom rand;
    private Sprite sp;
    public Weinwurm(String name, int hp, int maxhp, int special, int maxspecial){
        this.setName(name);
        this.setHP(hp);
        this.setmaxHP(maxhp);
        this.setSpecial(special);
        this.maxHIP = maxspecial;
        this.rand = UniformRandom.rand.split();
    }
    public Weinwurm(String name){
        this(name, 10, 10, 10, 10);
    }

    @Override
    public Adventurer clone() {
        return new Weinwurm(this.getName(), this.getHP(), this.getmaxHP(), this.getSpecial(), this.getSpecialMax());
    }

    @Override
    public String getSpecialName() {
        return "Health insurance policies";
    }

    @Override
    public int getSpecial() {
        return HIP;
    }

    @Override
    public int getSpecialMax() {
        return maxHIP;
    }

    @Override
    public void setSpecial(int n) {
        this.HIP = n;
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
        if(this.getSpecial() < 1){
            return this.getName() + " tried to find a good health insurance policy, but didn't have any. ";
        }
        this.HIP--;
        this.setRegen(new int[]{1, 5});
        return this.getName() + " found a good health insurance policy, granting +1 regen for 5 turns.";
    }

    @Override
    public String specialAttack(Adventurer other) { //note: not actually an attack.
        if(this.getSpecial() < 3){
            return this.getName() + " tried to use a special, but didn't have enough " + this.getSpecialName();
        }
        this.HIP -= 3;
        if(other.getPreparedness() == 0){
            other.setPreparedness(0);
        }else if(other.getPreparedness() < 0){
            other.modifyPreparedness(1);
        }else if(other.getPreparedness() > 0){
            other.modifyPreparedness(-1);
        }
        other.modifyArmor(0.2);
        return this.getName() + " gives a 100 to " + other.getName() + " making their preparedness closer to 0, and slightly increasing their defense.";
    }

    @Override
    public String support(Adventurer other) {
        if(this.getSpecial() < 1){
            return this.getName() + " tried to find " + other.getName() + " a good health insurance policy, but didn't have any. ";
        }
        other.setRegen(new int[]{1, 5});
        return this.getName() + " found a good health insurance policy for " + other.getName() + ", granting +1 regen for 5 turns.";
    }

    @Override
    public String attack(Adventurer other) {
        int dmg = (1+rand.nextInt(1));
        other.applyDamage(dmg);
        other.modifyPreparedness(-1);
        return this.getName() + " throws pencils at " + other.getName() + ", making them less prepared and dealing " + dmg + " damage.";
    }

    @Override
    public int getId(){
        return 3;
    }


}
