package solid.dip.before;

public class Warrior {
    private Sword sword;
    public Warrior(Sword sword) {
        this.sword = sword;
    }
    public void attack(){
        sword.swing();
    }
}
