package mqdelay.send.strategy;

public class vipStrategy implements PriorityStrategy{
    @Override
    public void MySetPriority() {
        Thread.currentThread().setPriority(1);
    }
}
