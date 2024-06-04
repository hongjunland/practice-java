package solid.isp.before;

public class HumanWorker implements Worker{
    @Override
    public void work() {
        System.out.println("Human working");
    }

    @Override
    public void eat() {
        System.out.println("Human eating");
    }
}
