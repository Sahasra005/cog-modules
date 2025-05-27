public class VirtualThreadsExample {
    public static void main(String[] args) throws InterruptedException {
        int count = 100_000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread!");
            });
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken (ms): " + (end - start));
    }
}
