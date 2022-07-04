package mqdelay.send.strategy;

public class normalStrategy implements PriorityStrategy{
    @Override
    public void MySetPriority() throws InterruptedException {
        Thread.sleep(10000);
        Thread.currentThread().setPriority(10);
    }
}
