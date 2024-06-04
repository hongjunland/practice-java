package solid.lsp.before;

public class Sparrow extends Bird{
    @Override
    public void fly() {
        throw new UnsupportedOperationException("타조는 하늘을 날 수 없습니다.");
    }
}
