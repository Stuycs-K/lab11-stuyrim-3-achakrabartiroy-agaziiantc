package Character;

public abstract class Adventurer{
  private String name;
  private int HP,maxHP;
  private int stun;
  private int prepared; // <0 = unprepared, >0=prepared, 0=no status.
  public Team team; //Gonna have to pray that this doesn't cause an infinite loop of doom
  private double armor = 1;
  private double bonusatk = 0;
  //Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Character.Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  /*
  standard methods
  */

  public void applyDamage(int amount){
    this.HP -= (int) (amount / armor);
    if (armor < 1){
      armor *= 1.1;
    }else if(armor > 1){
      armor /= 1.1;
    }
  }

  //You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
  }

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }
  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  //Set Methods
  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  public int getStun(){
    return stun;
  }
  public void setStun(int n){
    stun = n;
  }
  public void modifyStun(int n){
    stun += n;
  }
  public int getPreparedness(){
    return prepared;
  }
  public void setPreparedness(int n){
    prepared = n;
  }
  public void modifyPreparedness(int n){
    prepared += n;
  }
  public double getArmor(){
    return this.armor;
  }
  public void setArmor(int n){
    this.armor = n;
  }
  public void modifyArmor(double n){
    this.armor += n;
  }
  public double getBonusatk(){
    return this.bonusatk;
  }
  public void setBonusatk(int n){
    this.bonusatk = n;
  }
  public void modifyBonusatk(int n){
    this.bonusatk += n;
  }


}
