package utils.mock;

/**
 * Created by vitaly on 24.11.15.
 */
public abstract class AbstractWorker implements Runnable {

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                doWork();
            }
        } catch (InterruptedException e) {
            interrupting();
        }finally {
            finalizing();
        }

    }

    protected void finalizing() {
        System.out.println(getClass().getSimpleName() + " ended.");
    }

    protected void interrupting() {
        System.out.println(getClass().getSimpleName() + " interrupted.");
    }

    public abstract void doWork() throws InterruptedException;

}
