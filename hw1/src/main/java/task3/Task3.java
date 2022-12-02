package task3;

public class Task3 {
    private volatile Integer turn = 1;
    private final Object mutexObject = new Object();

    public void streamTask() {
        turn = 1;
        for (int i = 1; i < 4; i++) {
            final int threadTurn = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 6; j++) {
                    try {
                        synchronized (mutexObject) {
                            while (turn != threadTurn) {
                                mutexObject.wait();
                            }
                            System.out.print(threadTurn);
                            turn = (turn % 3) + 1;
                            mutexObject.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
}

