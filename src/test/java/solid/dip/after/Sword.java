package solid.dip.after;

public class Sword implements Weapon{
    @Override
    public void attack() {
        System.out.println("칼로 공격합니다!");
    }
}
