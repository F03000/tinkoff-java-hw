package task4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task4 {
    private static final int QUEUE_CAPACITY = 100;
    private static final int EXECUTORS_POOL_SIZE = 10;

    private int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random(0);
        return random.nextInt(max - min) + min;
    }

    public void manageWarehouse() {
        ExecutorService executors = Executors.newFixedThreadPool(EXECUTORS_POOL_SIZE);
        ArrayBlockingQueue<Product> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY, true);

        for (int i = 0; i < EXECUTORS_POOL_SIZE; i++) {
            executors.submit(() -> {
                while (true) {
                    try {
                        Product currentProduct = taskQueue.take();
                        System.out.println("Thread " + Thread.currentThread().getId() + " processed product: " + currentProduct.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Thread supplierThread = new Thread(() -> {
            int productId = 0;
            while (true) {
                taskQueue.add(new Product("Product" + productId++));
                try {
                    Thread.sleep(getRandomNumberUsingNextInt(200, 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        supplierThread.start();
    }
}
