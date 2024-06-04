package solid.dip.after;

public class Axe implements Weapon{
    @Override
    public void attack() {
        System.out.println("도끼로 공격합니다!");
    }
}
