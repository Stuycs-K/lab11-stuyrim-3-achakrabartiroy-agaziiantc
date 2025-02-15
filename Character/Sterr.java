package Character;

import Util.Sprite;
import Util.UniformRandom;

import java.util.SplittableRandom;

public class Sterr extends Adventurer {
    int skillissues, maxSkillissues;
    private SplittableRandom rand;
    private Sprite sp;
    public Sterr(String name, int hp, int maxhp, int atk, int rcv, int special, int maxspecial){
        this.setName(name);
        this.setHP(hp);
        this.setmaxHP(maxhp);
        this.setSpecial(special);
        this.setATK(atk);
        this.setRCV(rcv);
        this.maxSkillissues = maxspecial;
        this.rand = UniformRandom.rand.split();
    }
    public Sterr(String name){
        // Base stats:
        /*
        HP: 9.0 (total: 900)
        ATK: 9.0 (total: 180)
        RCV: 2.0 (total: 10)
        */
        this(name, 900, 900, 180, 10, 10, 10);
    }

    @Override
    public String toString(){
        return "Sterr-" + this.getName();
    }
    @Override
    public Adventurer clone() {
        return new Sterr(this.getName(), this.getHP(), this.getmaxHP(), this.getATK(), this.getRCV(), this.getSpecial(), this.getSpecialMax());
    }
    @Override
    public String getSpecialName() {
        return "Skill issues";
    }

    @Override
    public int getSpecial() {
        return skillissues;
    }

    @Override
    public int getSpecialMax() {
        return maxSkillissues;
    }

    @Override
    public void setSpecial(int n) {
        skillissues = n;
        if(skillissues > maxSkillissues){
            skillissues = maxSkillissues;
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
    public String specialAttack(Adventurer other) { //gamblecore aesthetic
        if(this.getSpecial() < 5){
            return this.getName() + " attempts to give a test, but no one is skill issued enough.";
        }
        Team target = other.team; //50% chance of this line crashing and burning in like 3 days when I start actually implementing the game.
        //STATUS UPDATE: the line crashed and burned 3 days after I wrote it
        StringBuilder out = new StringBuilder();
        out.append(this.getName() + " Assigns a test to the enemy team. ");
        for(int i = 0; i < target.team.length; i++) {
            int dice = rand.nextInt(100);
            Adventurer gambler = target.team[i]; //This isn't the most memory efficient way of doing this but does it really matter
            if(gambler.getPreparedness() < 0){
                if(dice < 80){
                    out.append(gambler.getName() + " fails the test and takes " + ((int) ((double) this.getSpecial() * 2 * this.getBonusatk())) + " damage and is stunned for " + (this.getSpecial() / 5) + " turns.");
                    gambler.applyDamage((int) ((double) this.getSpecial() * 2 * this.getBonusatk()));
                    gambler.modifyStun(this.getSpecial() / 5);
                    gambler.modifyPreparedness(1);
                }else{
                    out.append(gambler.getName() + " passed the test and became more prepared");
                    gambler.modifyPreparedness(1);
                }
            }
            else if(gambler.getPreparedness() == 0){
                if(dice <= 50){
                    out.append(gambler.getName() + " fails the test and takes " + ((int) ((double) this.getSpecial() * 2 * this.getBonusatk())) + " damage and is stunned for " + (this.getSpecial() / 5) + " turns.");
                    gambler.applyDamage((int) ((double) this.getSpecial() * 2 * this.getBonusatk()));
                    gambler.modifyStun(this.getSpecial() / 5);
                    gambler.modifyPreparedness(1);
                }else{
                    out.append(gambler.getName() + " passed the test and became more prepared");
                    gambler.modifyPreparedness(1);
                }
            }
            else if(gambler.getPreparedness() > 0){
                if(dice <= 20){
                    out.append(gambler.getName() + " fails the test and takes " + ((int) ((double) this.getSpecial() * 2 * this.getBonusatk())) + " damage and is stunned for " + (this.getSpecial() / 5) + " turns.");
                    gambler.applyDamage((int) ((double) this.getSpecial() * 2 * this.getBonusatk()));
                    gambler.modifyStun(this.getSpecial() / 5);
                    gambler.modifyPreparedness(1);
                }else{
                    out.append(gambler.getName() + " passed the test and became more prepared");
                    gambler.modifyPreparedness(1);
                }
            }
        }
        return "" + out;
    }

    @Override
    public String support(Adventurer other) {
        other.modifyPreparedness(1);
        return this.getName() + " tutors " + other.getName() + ", giving them +10% attack on the next turn and +1 prepardness.";
    }

    @Override
    public String support() {
        this.modifyPreparedness(1);
        return this.getName() + " tutors himself, giving +10% attack on the next turn and +1 prepardness";
    }

    @Override
    public String attack(Adventurer other) {

        other.applyDamage((int) ((double) this.getSpecial() / 2 * this.getBonusatk()));
        other.modifyPreparedness(-1);
        int skillissuesgain = rand.nextInt(3);
        this.skillissues += skillissuesgain;
        return this.getName() + " calls " + other.getName() + " a skill issue. " + other.getName() + " takes " + (this.getSpecial() / 2) + " damage and is now less prepared. " + this.getName() + " gains " + skillissuesgain + " skill issues ";

    }

    @Override
    public int getId(){
        return 2;
    }
}
