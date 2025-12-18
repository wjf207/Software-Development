public class YieldExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread A");
        t2.setName("Thread B");

        t1.start();
        t2.start();
    }
}