package Util; // what exactly does this do?

public class Stats {
  //
  int hp, atk, rcv;
  int mhp, matk, mrcv; // max
  public Stats(int hp, int atk, int rcv, int mhp, int matk, int mrcv) {
    //
    this.hp = hp;
    this.atk = atk;
    this.rcv = rcv;
    this.mhp = mhp;
    this.matk = matk;
    this.mrcv = mrcv;
  }

  public String toString() {
    //
    return "HP: " + Integer.toString(this.hp) + "\n ATK: " + Integer.toString(this.atk) + "\n RCV: " + Integer.toString(this.rcv);
  }
  public int[] getMaxStats() {
    //
    int[] statlist = {this.mhp, this.matk, this.mrcv};
    return statlist;
  }
  public int[] getStats() {
    // current stats
    int[] statlist = {this.hp, this.atk, this.rcv};
    return statlist;
  }
  public void receiveDmg(int dmg) {
    //
    this.hp -= dmg;
  }
}
