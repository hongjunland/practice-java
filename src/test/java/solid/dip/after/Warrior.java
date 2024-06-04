package solid.dip.after;

public class Warrior {
    private Weapon weapon;

    public Warrior(Weapon weapon) {
        this.weapon = weapon;
    }
    public void attack(){
        weapon.attack();
    }
}
