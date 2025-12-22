public class ThreadTest {

    // Shared lock/data object. We use the same Box instance both as a monitor
    // and as a simple data carrier between threads (Producer waits; Consumer notifies).
    private final Box box = new Box();

    public ThreadTest() {
        System.out.println("ThreadTest constructor called.");

        // Create a producer and a consumer thread.
        Thread producer = new Thread(new Producer(), "Producer");
        Thread consumer = new Thread(new Consumer(), "Consumer");

        // Start consumer first to show ordering
        consumer.start();
        producer.start();
    }

    private class Producer implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": started and will wait for contents.");

            synchronized (box) {
                while (box.getContents() == null || box.getContents().isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ": calling wait() and releasing lock on box.");
                        box.wait();
                        System.out.println(Thread.currentThread().getName() + ": awakened and re-acquired lock on box.");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println(Thread.currentThread().getName() + ": interrupted while waiting.");
                        return;
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": detected contents = '" + box.getContents() + "'.");
            }

            System.out.println(Thread.currentThread().getName() + ": finished.");
        }
    }

    private class Consumer implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": started and will sleep then set contents.");

            try {
                Thread.sleep(1000); // simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (box) {
                System.out.println(Thread.currentThread().getName() + ": setting contents and calling notify().");
                box.setContents("Hello from Consumer");
                box.notify();
                System.out.println(Thread.currentThread().getName() + ": notify() called, still holds lock until leaving synchronized block.");

                try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }

            System.out.println(Thread.currentThread().getName() + ": finished.");
        }
    }

    // Small driver to run the demo from the command line
    public static void main(String[] args) {
        new ThreadTest();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Demo complete.");
    }
}